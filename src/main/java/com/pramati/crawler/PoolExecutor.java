package com.pramati.crawler;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.pramati.scrapservices.MailRunnableScrapperImpl;
import com.pramati.scrapservices.MailScrapper;
import com.pramati.scrapservices.MailScrapperImpl;
import com.pramati.scrapservices.RunnableScrapper;
import com.pramati.scrapservices.UrlRunnableScrapperImpl;
import com.pramati.scrapservices.UrlScrapper;
import com.pramati.scrapservices.UrlScrappperImpl;
import com.pramati.utils.FileUtils;

public class PoolExecutor {
	public Map<String, String> mapOfTasks;
	public Map<String, String> sharedMap;
	public String yearToFetch;
	public ExecutorService executorService;
	public final static Logger logger = Logger.getLogger(PoolExecutor.class);

	public PoolExecutor(Map<String, String> mapOfTasks, Map<String, String> sharedMap, String yearToFetch) {
		this.mapOfTasks = mapOfTasks;
		this.sharedMap = sharedMap;
		this.yearToFetch = yearToFetch;
		executorService = Executors.newFixedThreadPool(30);
	}

	public void executePoolForUrls() throws Exception {
		if (mapOfTasks != null && sharedMap != null && yearToFetch != null) {
			for (String url : mapOfTasks.keySet()) {

				UrlScrapper urlScrapper2 = new UrlScrappperImpl(url, yearToFetch);
				RunnableScrapper runnableScrapper = new UrlRunnableScrapperImpl(urlScrapper2, sharedMap);
				executorService.execute(runnableScrapper);
			}
			executorService.shutdown();
			while (!executorService.isTerminated()) {
			}
			logger.info("Executor service is shutdowned");
		}
	}

	public void executePoolForMails() throws Exception {
		if (mapOfTasks != null && sharedMap != null) {
			Properties properties = FileUtils.readProperties("DownloadedMailUrls.txt");
			for (String url : mapOfTasks.keySet()) {
				String md5Url = FileUtils.generateMD5(url);
				if (properties != null && properties.contains(md5Url)) {
					continue;
				} else {
					MailScrapper mailScrapper = new MailScrapperImpl(url);
					RunnableScrapper runnableScrapper = new MailRunnableScrapperImpl(mailScrapper, sharedMap);
					executorService.submit(runnableScrapper);
					FileUtils.writeToFile("DownloadedMailUrls.txt", md5Url + "=" + md5Url, true);
				}
			}
			executorService.shutdown();
			while (!executorService.isTerminated()) {
			}
			logger.info("Executor service is shutdowned");
		}
	}

}
