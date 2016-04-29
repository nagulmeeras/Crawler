package com.pramati.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtils {
	public static int noOfDocs = 0;
	final static Logger logger = Logger.getLogger(DocumentUtils.class);

	public synchronized static Document getDocumentObject(String url) throws Exception {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
			noOfDocs++;
		} catch (Exception e) {
			FileUtils.writeToFile("WrongUrls.txt", url, true);
			logger.debug(e.getMessage());
			logger.info("Sorry connection was lost");
			System.exit(1);
		}
		return doc;
	}
}
