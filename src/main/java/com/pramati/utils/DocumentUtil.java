package com.pramati.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtil {
	public static int noOfDocs = 0;
	//final static Logger logger = Logger.getLogger(DocumentUtil.class);

	public synchronized static Document getDocumentObject(String url) throws Exception {
		Document doc = null;
		try {
			// logger.debug(url);
			FileUtilities.writeToFile("urls.txt", url, true);
			doc = Jsoup.connect(url).get();
			noOfDocs++;
			// logger.debug("no of documents:" + noOfDocs);

		} catch (Exception e) {
			FileUtilities.writeToFile("WrongUrls.txt", url, true);
			//logger.debug(e.getMessage());
		}
		return doc;
	}
}
