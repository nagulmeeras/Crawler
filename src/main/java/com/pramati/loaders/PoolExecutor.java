package com.pramati.loaders;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.pramati.Crawler.MainCrawler;
import com.pramati.utils.FileUtilities;

public class PoolExecutor {
	//final static Logger logger = Logger.getLogger(PoolExecutor.class);
	public ExecutorService executorService2 = Executors.newFixedThreadPool(20);

	public void assignThreadsToGetMails() throws Exception {
		boolean finishedJob = false;
		int totalUrlsFinished = 0;
		while (!finishedJob) {
			for (String url : MainCrawler.urlMap.keySet()) {
				if (MainCrawler.urlMap.get(url) == 1) {
					executorService2.execute(new PageLoader(url));
					MainCrawler.urlMap.put(url, 0);
					FileUtilities.writeToFile("poolUrls", url, true);
				} else {
					totalUrlsFinished++;
				}
			}
			if (totalUrlsFinished >= MainCrawler.urlMap.size() - 1) {
				finishedJob = true;
			}

		}
		//logger.info("Total mails downloaded" + MailScrapper.noOfMails);
		executorService2.shutdown();
		while (!executorService2.isTerminated()) {
		}
		//logger.info("Executor service is shutted down");
	}

//	public void assignThreadsToGetAllUrls() throws Exception {
//		ExecutorService executorService2 = Executors.newFixedThreadPool(10);
//		for (String individualPageUrl : MainCrawler.urlMap.keySet()) {
//			if (MainCrawler.urlMap.get(individualPageUrl) == 1) {
//				executorService2.execute(new UrlLoader(individualPageUrl));
//				MainCrawler.urlMap.put(individualPageUrl, 0);
//			}
//		}
//		executorService2.shutdown();
//		while (!executorService2.isTerminated()) {
//		}
//	}
}
