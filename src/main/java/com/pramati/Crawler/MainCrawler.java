package com.pramati.Crawler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import com.pramati.loaders.PoolExecutor;
import com.pramati.scrapservices.UrlScrappper;
import com.pramati.utils.DocumentUtil;
import com.pramati.utils.FileUtilities;

public class MainCrawler {
	public static Map<String, Integer> urlMap = new ConcurrentHashMap<String, Integer>();
	public static Map<String, Integer> mailMap = new HashMap<String, Integer>();
	final static Logger logger = Logger.getLogger(MainCrawler.class);
	public static String type = null;

	public static void main(String args[]) {

		try {
			
			Properties properties = FileUtilities.readFromFile("src/main/resources/crawler.properties");
			String baseUrl = properties.getProperty("MainUrl");
			type = properties.getProperty("condition");

			Document doc = DocumentUtil.getDocumentObject(baseUrl);
			UrlScrappper urlScrappper = new UrlScrappper();
			urlScrappper.getUrls(doc, type);
			logger.info("Started executor service");
			PoolExecutor poolExecutor = new PoolExecutor();
			poolExecutor.assignThreadsToGetMails();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
