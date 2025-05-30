/*
 *      Copyright (c) 2004-2014 Matthew Altman & Stuart Boston
 *
 *      This file is part of TheTVDB API.
 *
 *      TheTVDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      TheTVDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with TheTVDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.kricko.tvshowupdater.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Generic set of routines to process the DOM model data
 *
 * @author Stuart.Boston
 *
 * @version $Revision: 1.0 $
 */
@Slf4j
public class DOMHelper {

    private static final String YES = "yes";
    private static final String ENCODING = "UTF-8";
    private static final int RETRY_COUNT = 5;
    // Milliseconds to retry
    private static final int RETRY_TIME = 250;
    private static HttpClient httpClient = null;
    // Constants
    private static final String ERROR_WRITING = "Error writing the document to {}";

    // Hide the constructor
    protected DOMHelper() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    /**
     * Method setHttpClient.
     * @param newHttpClient CommonHttpClient
     */
    public static void setHttpClient(HttpClient newHttpClient) {
        httpClient = newHttpClient;
    }

    /**
     * Gets the string value of the tag element name passed
     *
     * @param element
     * @param tagName
    
     * @return String
     */
    public static String getValueFromElement(Element element, String tagName) {
        NodeList elementNodeList = element.getElementsByTagName(tagName);
        Element tagElement = (Element) elementNodeList.item(0);
        if (tagElement == null) {
            return "";
        }

        NodeList tagNodeList = tagElement.getChildNodes();
        if (tagNodeList.getLength() == 0) {
            return "";
        }
        return tagNodeList.item(0).getNodeValue();
    }

    /**
     * Get a DOM document from the supplied URL
     *
     * @param url
    
     * @return Document
     */
    public static synchronized Document getEventDocFromUrl(String url) {
        InputStream in = null;
        Document doc = null;

        try {
            String webPage = getValidWebpage(url);

            if (StringUtils.isNotBlank(webPage)) {
                in = new ByteArrayInputStream(webPage.getBytes(ENCODING));

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                doc = db.parse(in);
                in.close();
                doc.getDocumentElement().normalize();
            }
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Unable to encode URL: " + url, ex);
        } catch (ParserConfigurationException error) {
            throw new RuntimeException("Unable to parse TheTVDb response, please try again later.", error);
        } catch (SAXException error) {
            throw new RuntimeException("Unable to parse TheTVDb response, please try again later.", error);
        } catch (IOException error) {
            throw new RuntimeException("Unable to parse TheTVDb response, please try again later.", error);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // Input Stream was already closed or null
            	log.error("Failed to close InputStream", ex);
            }
        }

        return doc;
    }

    /**
     * Method getValidWebpage.
     * @param url String
     * @return String
     */
    private static String getValidWebpage(String url) {
        // Count the number of times we download the web page
        int retryCount = 0;
        String webPage;

        try {
            while (retryCount < RETRY_COUNT) {
                retryCount++;
                webPage = requestWebPage(url);
                if (StringUtils.isNotBlank(webPage)) {
                    // See if the ID is null
                    if (!webPage.contains("<id>") || webPage.contains("<id></id>")) {
                        // Wait an increasing amount of time the more retries that happen
                        waiting(retryCount * RETRY_TIME);
                        continue;
                    }
                    return webPage;
                }

                // Couldn't get a valid webPage so, quit.
                throw new RuntimeException("Failed to download data from " + url);
            }
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Unable to encode URL: " + url, ex);
        } catch (IOException ex) {
            throw new RuntimeException("Unable to download URL: " + url, ex);
        } catch (URISyntaxException ex) {
            throw new RuntimeException("Invalid URL: " + url, ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException("Request failed with interruption", ex);
        }

        return null;
    }

    /**
     * Wait for a few milliseconds
     *
     * @param milliseconds
     */
    private static void waiting(int milliseconds) {
        long t0, t1;
        t0 = System.currentTimeMillis();
        do {
            t1 = System.currentTimeMillis();
        } while ((t1 - t0) < milliseconds);
    }

    /**
     * Method requestWebPage.
     * @param url String
     * @return String
     * @throws IOException
     */
    private static String requestWebPage(String url) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(new URI(url))
                                         .GET()
                                         .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                         .body();
    }
}
