package com.pramati.scrapservices;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pramati.utils.DocumentUtils;

public class MailScrapperImpl implements MailScrapper {
	public String url;

	public MailScrapperImpl(String url) {
		this.url = url;
	}

	public Map<String, String> getMails() throws Exception  {
		
		Map<String, String> mails = new HashMap<String, String>();
		Document document = DocumentUtils.getDocumentObject(url);
		if (document != null) {
			Elements tableRows = document.select("tr[class]");
			String mail_id = null;
			for (Element tableRow : tableRows) {
				if (tableRow.attr("class").equals("from")) {
					Elements tableCells = tableRow.children();
					for (Element tableCell : tableCells) {
						if (tableCell.attr("class").equals("right")) {
							mail_id = tableCell.text();
							mails.put(url, mail_id);
						}
					}
				}
				// } else if (tableRow.attr("class").equals("date")) {
				// Elements tableCells = tableRow.children();
				// for (Element tableCell : tableCells) {
				// if (tableCell.attr("class").equals("right")) {
				// fileName = tableCell.text().substring(5, 16);
				// }
				// }
				// }
			}
		}
		return mails;
	}
}
