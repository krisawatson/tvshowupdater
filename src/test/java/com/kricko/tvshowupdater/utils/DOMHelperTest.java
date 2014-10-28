package com.kricko.tvshowupdater.utils;

import javax.imageio.metadata.IIOMetadataNode;
import org.junit.*;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.yamj.api.common.http.CommonHttpClient;
import org.yamj.api.common.http.DefaultPoolingHttpClient;

/**
 * The class <code>DOMHelperTest</code> contains tests for the class <code>{@link DOMHelper}</code>.
 *
 * @generatedBy CodePro at 28/10/14 09:04
 * @author kris
 * @version $Revision: 1.0 $
 */
public class DOMHelperTest {
	/**
	 * Run the DOMHelper() constructor test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test(expected = java.lang.UnsupportedOperationException.class)
	public void testDOMHelper_1()
		throws Exception {

		DOMHelper result = new DOMHelper();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void appendChild(Document,Element,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testAppendChild_1()
		throws Exception {
		Document doc = null;
		Element parentElement = new IIOMetadataNode();
		String elementName = "";
		String elementValue = "";

		DOMHelper.appendChild(doc, parentElement, elementName, elementValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.kricko.tvshowupdater.utils.DOMHelper.appendChild(DOMHelper.java:241)
	}

	/**
	 * Run the void appendChild(Document,Element,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testAppendChild_2()
		throws Exception {
		Document doc = null;
		Element parentElement = new IIOMetadataNode();
		String elementName = "";
		String elementValue = "";

		DOMHelper.appendChild(doc, parentElement, elementName, elementValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.kricko.tvshowupdater.utils.DOMHelper.appendChild(DOMHelper.java:241)
	}

	/**
	 * Run the void appendChild(Document,Element,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testAppendChild_3()
		throws Exception {
		Document doc = null;
		Element parentElement = new IIOMetadataNode();
		String elementName = "";
		String elementValue = "";

		DOMHelper.appendChild(doc, parentElement, elementName, elementValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.kricko.tvshowupdater.utils.DOMHelper.appendChild(DOMHelper.java:241)
	}

	/**
	 * Run the void appendChild(Document,Element,String,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testAppendChild_4()
		throws Exception {
		Document doc = null;
		Element parentElement = new IIOMetadataNode();
		String elementName = "";
		String elementValue = "";

		DOMHelper.appendChild(doc, parentElement, elementName, elementValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at com.kricko.tvshowupdater.utils.DOMHelper.appendChild(DOMHelper.java:241)
	}

	/**
	 * Run the String convertDocToString(Document) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testConvertDocToString_1()
		throws Exception {
		Document doc = null;

		String result = DOMHelper.convertDocToString(doc);

		// add additional test code here
		assertEquals("\n", result);
	}

	/**
	 * Run the String convertDocToString(Document) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testConvertDocToString_2()
		throws Exception {
		Document doc = null;

		String result = DOMHelper.convertDocToString(doc);

		// add additional test code here
		assertEquals("\n", result);
	}

	/**
	 * Run the String convertDocToString(Document) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testConvertDocToString_3()
		throws Exception {
		Document doc = null;

		String result = DOMHelper.convertDocToString(doc);

		// add additional test code here
		assertEquals("\n", result);
	}

	/**
	 * Run the String convertDocToString(Document) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testConvertDocToString_4()
		throws Exception {
		Document doc = null;

		String result = DOMHelper.convertDocToString(doc);

		// add additional test code here
		assertEquals("\n", result);
	}

	/**
	 * Run the String convertDocToString(Document) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testConvertDocToString_5()
		throws Exception {
		Document doc = null;

		String result = DOMHelper.convertDocToString(doc);

		// add additional test code here
		assertEquals("\n", result);
	}

	/**
	 * Run the String convertDocToString(Document) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testConvertDocToString_6()
		throws Exception {
		Document doc = null;

		String result = DOMHelper.convertDocToString(doc);

		// add additional test code here
		assertEquals("\n", result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_1()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_2()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_3()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_4()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_5()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_6()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the Document getEventDocFromUrl(String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetEventDocFromUrl_7()
		throws Exception {
		String url = "";

		Document result = DOMHelper.getEventDocFromUrl(url);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.IllegalStateException: Target host must not be null, or set in parameters.
		//       at org.apache.http.impl.client.DefaultRequestDirector.determineRoute(DefaultRequestDirector.java:787)
		//       at org.apache.http.impl.client.DefaultRequestDirector.execute(DefaultRequestDirector.java:414)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:906)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:805)
		//       at org.apache.http.impl.client.AbstractHttpClient.execute(AbstractHttpClient.java:784)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:99)
		//       at org.yamj.api.common.http.DefaultPoolingHttpClient.requestContent(DefaultPoolingHttpClient.java:78)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.requestWebPage(DOMHelper.java:261)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValidWebpage(DOMHelper.java:158)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getEventDocFromUrl(DOMHelper.java:114)
		assertNotNull(result);
	}

	/**
	 * Run the String getValueFromElement(Element,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetValueFromElement_1()
		throws Exception {
		Element element = new IIOMetadataNode();
		String tagName = "";

		String result = DOMHelper.getValueFromElement(element, tagName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:881)
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:876)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValueFromElement(DOMHelper.java:86)
		assertNotNull(result);
	}

	/**
	 * Run the String getValueFromElement(Element,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetValueFromElement_2()
		throws Exception {
		Element element = new IIOMetadataNode();
		String tagName = "";

		String result = DOMHelper.getValueFromElement(element, tagName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:881)
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:876)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValueFromElement(DOMHelper.java:86)
		assertNotNull(result);
	}

	/**
	 * Run the String getValueFromElement(Element,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetValueFromElement_3()
		throws Exception {
		Element element = new IIOMetadataNode();
		String tagName = "";

		String result = DOMHelper.getValueFromElement(element, tagName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:881)
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:876)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValueFromElement(DOMHelper.java:86)
		assertNotNull(result);
	}

	/**
	 * Run the String getValueFromElement(Element,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetValueFromElement_4()
		throws Exception {
		Element element = new IIOMetadataNode();
		String tagName = "";

		String result = DOMHelper.getValueFromElement(element, tagName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:881)
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:876)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValueFromElement(DOMHelper.java:86)
		assertNotNull(result);
	}

	/**
	 * Run the String getValueFromElement(Element,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetValueFromElement_5()
		throws Exception {
		Element element = new IIOMetadataNode();
		String tagName = "";

		String result = DOMHelper.getValueFromElement(element, tagName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:881)
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:876)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValueFromElement(DOMHelper.java:86)
		assertNotNull(result);
	}

	/**
	 * Run the String getValueFromElement(Element,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testGetValueFromElement_6()
		throws Exception {
		Element element = new IIOMetadataNode();
		String tagName = "";

		String result = DOMHelper.getValueFromElement(element, tagName);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:881)
		//       at javax.imageio.metadata.IIOMetadataNode.getElementsByTagName(IIOMetadataNode.java:876)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.getValueFromElement(DOMHelper.java:86)
		assertNotNull(result);
	}

	/**
	 * Run the void setHttpClient(CommonHttpClient) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testSetHttpClient_1()
		throws Exception {
		CommonHttpClient newHttpClient = new DefaultPoolingHttpClient();

		DOMHelper.setHttpClient(newHttpClient);

		// add additional test code here
	}

	/**
	 * Run the boolean writeDocumentToFile(Document,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testWriteDocumentToFile_1()
		throws Exception {
		Document doc = null;
		String localFile = "";

		boolean result = DOMHelper.writeDocumentToFile(doc, localFile);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:110)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.getOutputHandler(TransformerImpl.java:502)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.transform(TransformerImpl.java:344)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.writeDocumentToFile(DOMHelper.java:221)
		assertTrue(result);
	}

	/**
	 * Run the boolean writeDocumentToFile(Document,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testWriteDocumentToFile_2()
		throws Exception {
		Document doc = null;
		String localFile = "";

		boolean result = DOMHelper.writeDocumentToFile(doc, localFile);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:110)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.getOutputHandler(TransformerImpl.java:502)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.transform(TransformerImpl.java:344)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.writeDocumentToFile(DOMHelper.java:221)
		assertTrue(result);
	}

	/**
	 * Run the boolean writeDocumentToFile(Document,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testWriteDocumentToFile_3()
		throws Exception {
		Document doc = null;
		String localFile = "";

		boolean result = DOMHelper.writeDocumentToFile(doc, localFile);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:110)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.getOutputHandler(TransformerImpl.java:502)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.transform(TransformerImpl.java:344)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.writeDocumentToFile(DOMHelper.java:221)
		assertTrue(result);
	}

	/**
	 * Run the boolean writeDocumentToFile(Document,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testWriteDocumentToFile_4()
		throws Exception {
		Document doc = null;
		String localFile = "";

		boolean result = DOMHelper.writeDocumentToFile(doc, localFile);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:110)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.getOutputHandler(TransformerImpl.java:502)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.transform(TransformerImpl.java:344)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.writeDocumentToFile(DOMHelper.java:221)
		assertTrue(result);
	}

	/**
	 * Run the boolean writeDocumentToFile(Document,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testWriteDocumentToFile_5()
		throws Exception {
		Document doc = null;
		String localFile = "";

		boolean result = DOMHelper.writeDocumentToFile(doc, localFile);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:110)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.getOutputHandler(TransformerImpl.java:502)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.transform(TransformerImpl.java:344)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.writeDocumentToFile(DOMHelper.java:221)
		assertTrue(result);
	}

	/**
	 * Run the boolean writeDocumentToFile(Document,String) method test.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception */
	@Test
	public void testWriteDocumentToFile_6()
		throws Exception {
		Document doc = null;
		String localFile = "";

		boolean result = DOMHelper.writeDocumentToFile(doc, localFile);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.SecurityException: Cannot write to files while generating test cases
		//       at com.instantiations.assist.eclipse.junit.CodeProJUnitSecurityManager.checkWrite(CodeProJUnitSecurityManager.java:76)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:209)
		//       at java.io.FileOutputStream.<init>(FileOutputStream.java:110)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.getOutputHandler(TransformerImpl.java:502)
		//       at com.sun.org.apache.xalan.internal.xsltc.trax.TransformerImpl.transform(TransformerImpl.java:344)
		//       at com.kricko.tvshowupdater.utils.DOMHelper.writeDocumentToFile(DOMHelper.java:221)
		assertTrue(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception
	 *         if the initialization fails for some reason */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 * @throws Exception
	 *         if the clean-up fails for some reason */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 28/10/14 09:04
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(DOMHelperTest.class);
	}
}