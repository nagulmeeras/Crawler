package com.pramati.scrapservices;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pramati.Crawler.MainCrawler;
import com.pramati.utils.FileUtilities;

public class MailScrapper implements ScrapperFactory {
	public static int noOfMails = 0;
	//final static Logger logger = Logger.getLogger(MailScrapper.class);

	public  synchronized void getData(Document doc) throws Exception {
		if (doc != null) {
			try {
				Elements tables = doc.select("table[id=msgview]");
				for (Element table : tables) {
					String mail_id = null, fileName = null;
					if (table.hasAttr("id") && table.hasAttr("class") ) {
						Elements fromAttrs = table.select("tr[class]");
						for (Element fromAttr : fromAttrs) {
							String tr_classname = fromAttr.attr("class");
							if (tr_classname.equals("from") || tr_classname.equals("date")) {
								Elements fromMail = fromAttr.select("td[class=right]");
								for (Element mail : fromMail) {
									if (tr_classname.equals("from")) {
										mail_id = mail.text();
										MainCrawler.mailMap.put(mail_id, 1);
										noOfMails++;
									} else if (tr_classname.equals("date")) {
										fileName = mail.text().substring(5, 16);
									}
								}
							}
						}
						if (mail_id != null && fileName != null) {
							FileUtilities.writeToFile(fileName, mail_id, true);
						}
					}
				}
			} catch (Exception e) {
				//logger.debug("Parsing of document is failed");
				throw new Exception("Parsing of document is failed");
			}
		} else {
			//logger.debug("Document object in nullified");
		}
	}
}
