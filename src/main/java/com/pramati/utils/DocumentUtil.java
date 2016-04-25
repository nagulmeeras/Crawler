package com.pramati.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtil {
	public static int noOfDocs = 0;
	final static Logger logger = Logger.getLogger(DocumentUtil.class);

	public synchronized static Document getDocumentObject(String url) throws Exception {
		Document doc = null;
		try {
			// logger.debug(url);
			doc = Jsoup.connect(url).get();
			noOfDocs++;
			// logger.debug("no of documents:" + noOfDocs);

		} catch (

		Exception e) {
			FileUtilities.writeToFile("WrongUrls.txt", url, true);
			logger.debug(e.getMessage());
//			String indexText = "";
//			for (String month : UrlScrappper.monthWiseSummaryStored.keySet()) {
//				indexText += month + "=" + UrlScrappper.monthWiseSummaryStored.get(month) + "\n";
//			}
//			FileUtilities.writeToFile("MonthWiseIndex.txt", indexText, false);

			System.out.println("Sorry connection was lost");
			System.exit(1);
		}
		return doc;
	}
}
