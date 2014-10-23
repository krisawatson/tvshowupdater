package com.kricko.tvshowupdater;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.json.simple.parser.ParseException;

import com.kricko.tvshowupdater.objects.Details;
import com.kricko.tvshowupdater.objects.Item;
import com.kricko.tvshowupdater.objects.Rss;
import com.kricko.tvshowupdater.objects.Shows;
import com.kricko.tvshowupdater.parser.TvShowParser;
import com.kricko.tvshowupdater.utils.TvShowUtils;

public class App 
{
    public static void main( String[] args ) throws JAXBException, IOException, ParseException
    {
    	JAXBContext jc = JAXBContext.newInstance(Rss.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        
        TvShowParser parser = new TvShowParser();
        Shows shows = parser.parseShows();
        
        for(Details detail:shows.getShows()){
        	System.out.println(detail.getName());
        	URL rssFeed = new URL(detail.getRssfeed());
            InputStream is = rssFeed.openStream();
            Rss rss = (Rss) unmarshaller.unmarshal(is);

            List<Item> items = rss.getChannel().getItem();
            items = TvShowUtils.removeDuplicateEpisodes(items, detail.getRegex());
            for(Item item:items){
            	try {
					TvShowUtils.downloadNewItems(item, detail);
				} catch (Throwable e) {
					System.err.println(e.getLocalizedMessage());
				}
            	System.out.println(detail.getPath());
            	System.out.println(item.getTitle());
            }       
            is.close();
        }
        
    }
}
