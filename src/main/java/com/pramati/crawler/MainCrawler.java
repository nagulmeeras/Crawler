package com.pramati.crawler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.pramati.daoService.DaoService;
import com.pramati.daoService.DaoServiceImpl;
import com.pramati.scrapservices.UrlScrapper;
import com.pramati.scrapservices.UrlScrappperImpl;
import com.pramati.utils.FileUtils;

public class MainCrawler {
	public static Map<String, Integer> urlMap = new ConcurrentHashMap<String, Integer>();
	public static Map<String, Integer> mailMap = new HashMap<String, Integer>();
	final static Logger logger = Logger.getLogger(MainCrawler.class);
	public static String type = null;

	public static void main(String args[]) {

		try {

			Properties properties = FileUtils.readProperties("src/main/resources/crawler.properties");
			String baseUrl = properties.getProperty("MainUrl");
			String yearToFetch = properties.getProperty("YearToFetch");

			logger.info(baseUrl);
			UrlScrapper urlScrapper = new UrlScrappperImpl(baseUrl, yearToFetch);
			Map<String, String> firstLevelUrls = urlScrapper.getFirstLevelUrls();
			System.out.println("size:" + firstLevelUrls.size());
			Map<String, String> secondLevelUrls = new HashMap<String, String>();
			PoolExecutor poolExecutor = new PoolExecutor(firstLevelUrls, secondLevelUrls, yearToFetch);
			poolExecutor.executePoolForUrls();
			Map<String, String> mailsMap = new HashMap<String, String>();
			PoolExecutor poolExecutor2 = new PoolExecutor(secondLevelUrls, mailsMap, yearToFetch);
			poolExecutor2.executePoolForMails();
			if (mailsMap.size() > 0) {
				DaoService daoService = new DaoServiceImpl(mailsMap, yearToFetch);
				daoService.save();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
