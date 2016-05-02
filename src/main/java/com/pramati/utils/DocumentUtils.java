package com.pramati.utils;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentUtils {
	final static Logger logger = Logger.getLogger(DocumentUtils.class);

	public static Document getDocumentObject(String url)throws FileNotFoundException {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.info("Sorry connection was lost");
			throw new FileNotFoundException("Given url not found");
		}
		return doc;
	}
}
