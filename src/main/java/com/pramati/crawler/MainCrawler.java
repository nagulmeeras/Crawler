package com.pramati.crawler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.pramati.daoService.DaoService;
import com.pramati.daoService.DaoServiceImpl;
import com.pramati.scrapservices.UrlScrapper;
import com.pramati.scrapservices.UrlScrappperImpl;
import com.pramati.utils.FileUtils;

public class MainCrawler {
	final static Logger logger = Logger.getLogger(MainCrawler.class);

	public static void main(String args[]) {

		try {

			Properties properties = FileUtils.readProperties("src/main/resources/crawler.properties");
			String baseUrl = properties.getProperty("MainUrl");
			String yearToFetch = properties.getProperty("YearToFetch");
			logger.info("Base url : "+baseUrl+"\n"+"Fetching year :"+yearToFetch);
			System.out.println("Base url : "+baseUrl+"\n"+"Fetching year :"+yearToFetch);
			
			UrlScrapper urlScrapper = new UrlScrappperImpl(baseUrl, yearToFetch);
			Map<String, String> firstLevelUrls = urlScrapper.getFirstLevelUrls();
			logger.info("No of urls fetched :"+firstLevelUrls.size());
			
			Map<String, String> secondLevelUrls = new HashMap<String, String>();
			PoolExecutor poolExecutor = new PoolExecutor(firstLevelUrls, secondLevelUrls, yearToFetch);
			poolExecutor.executePoolForUrls();
			logger.info("No of second level urls fetched:"+secondLevelUrls.size());
			
			Map<String, String> mailsMap = new HashMap<String, String>();
			PoolExecutor poolExecutor2 = new PoolExecutor(secondLevelUrls, mailsMap, yearToFetch);
			poolExecutor2.executePoolForMails();
			logger.info("No of mails were scrapped :"+mailsMap.size());
			
			if (mailsMap.size() > 0) {
				DaoService daoService = new DaoServiceImpl(mailsMap, yearToFetch);
				daoService.save();
				logger.info("Mails were successfully saved to file");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
