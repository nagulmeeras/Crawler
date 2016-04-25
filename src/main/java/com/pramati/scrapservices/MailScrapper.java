package com.pramati.scrapservices;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pramati.utils.FileUtilities;

public class MailScrapper implements ScrapperFactory {
	public static int noOfMails = 0;
	final static Logger logger = Logger.getLogger(MailScrapper.class);

	public void getData(Document doc) throws Exception {
		if (doc != null) {
			try {
				// Elements tables = doc.select("table[id=msgview]");
				// for (Element table : tables) {
				// String mail_id = null, fileName = null;
				// if (table.hasAttr("id") && table.hasAttr("class")) {
				// Elements fromAttrs = table.select("tr[class]");
				// for (Element fromAttr : fromAttrs) {
				// String tr_classname = fromAttr.attr("class");
				// if (tr_classname.equals("from") ||
				// tr_classname.equals("date")) {
				// Elements fromMail = fromAttr.select("td[class=right]");
				// for (Element mail : fromMail) {
				// if (tr_classname.equals("from")) {
				// mail_id = mail.text();
				// MainCrawler.mailMap.put(mail_id, 1);
				// noOfMails++;
				// } else if (tr_classname.equals("date")) {
				// fileName = mail.text().substring(5, 16);
				// }
				// }
				// }
				// }
				// if (mail_id != null && fileName != null) {
				//
				// FileUtilities.writeToFile(fileName + ".txt", mail_id, true);
				// }
				// }
				// }
				Elements tableRows = doc.select("tr[class]");
				String mail_id = null, fileName = null;
				for (Element tableRow : tableRows) {
					if (tableRow.attr("class").equals("from")) {
						Elements tableCells = tableRow.children();
						for (Element tableCell : tableCells) {
							if (tableCell.attr("class").equals("right")) {
								mail_id = tableCell.text();
							}
						}
					} else if (tableRow.attr("class").equals("date")) {
						Elements tableCells = tableRow.children();
						for (Element tableCell : tableCells) {
							if (tableCell.attr("class").equals("right")) {
								fileName = tableCell.text().substring(5, 16);
							}
						}
					}
				}
				if (mail_id != null && fileName != null) {
					noOfMails++;
					FileUtilities.writeToFile(fileName + ".txt", mail_id, true);
					logger.info("Downloaded : "+noOfMails);;
				}
			} catch (Exception e) {
				logger.debug("Parsing of document is failed");
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
		} else {
			logger.debug("Document object in nullified");
		}
	}
}
