package com.pramati.scrapservices;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pramati.utils.DocumentUtils;

public class UrlScrappperImpl implements UrlScrapper {
	public String url;
	public String yearToFetch;

	public UrlScrappperImpl(String url, String yearToFetch) {
		this.url = url;
		this.yearToFetch = yearToFetch;
	}

	public Map<String, String> getFirstLevelUrls() throws FileNotFoundException{
		
		Map<String, String> firstLevelUrls = new HashMap<String, String>();
		Document document = DocumentUtils.getDocumentObject(url);
		Elements links = document.select("a[href]");
		for (Element link : links) {
			if (!yearToFetch.equals("") && link.attr("href").contains(yearToFetch) && link.attr("href").contains("date")) {
				Document documentOfMonth = DocumentUtils.getDocumentObject(link.absUrl("href"));
				Elements tableHeaders = documentOfMonth.select("th[class=pages]");
				for (Element tableHeader : tableHeaders) {
					if (tableHeader.hasAttr("class") && tableHeader.attr("class").equals("pages")) {
						Elements tableHeaderChilds = tableHeader.children();
						for (Element tableHeaderChild : tableHeaderChilds) {
							if (!tableHeaderChild.text().contains("Next")) {
								String pageWiseUrl = link.absUrl("href") + "?"
										+ (Integer.parseInt(tableHeaderChild.text()) - 1);
								firstLevelUrls.put(pageWiseUrl, "");
							}
						}
						/*
						 * Here we will adding 0th indexed page
						 * 
						 */
						if (!firstLevelUrls.containsKey(link.absUrl("href")))
							firstLevelUrls.put(link.absUrl("href"), "");
					}
				}
			}
		}
		return firstLevelUrls;
	}

	public Map<String, String> getSecondLevelUrls() throws FileNotFoundException {
		
		Map<String, String> secondLevelUrls = new HashMap<String, String>();
		Document document = DocumentUtils.getDocumentObject(url);
		Elements tables = document.select("table[id=msglist]");
		for (Element table : tables) {
			if (table.hasAttr("id") && table.attr("id").equals("msglist")) {
				Elements tableChilds = table.children();
				for (Element tableChild : tableChilds) {
					if (tableChild.nodeName().equals("tbody")) {
						Elements tableRows = tableChild.children();
						for (Element tableRow : tableRows) {
							Elements tableCells = tableRow.children();
							for (Element tableCell : tableCells) {
								if (tableCell.hasAttr("class") && tableCell.attr("class").equals("subject")) {
									Elements cellChild = tableCell.children();
									for (Element childElement : cellChild) {
										if (childElement.hasAttr("href")) {
											String url = childElement.absUrl("href");
											secondLevelUrls.put(url, "");
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return secondLevelUrls;
	}
}
