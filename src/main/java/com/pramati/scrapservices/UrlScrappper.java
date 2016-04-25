package com.pramati.scrapservices;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pramati.Crawler.MainCrawler;
import com.pramati.utils.DocumentUtil;
import com.pramati.utils.FileUtilities;

public class UrlScrappper implements ScrapperFactory {

	public void getData(Document doc ) throws Exception {
		getUrls(doc , "list");
	}
	public void getUrls(Document document, String type) throws Exception {
		if (document != null) {
			if (type.equals(MainCrawler.type)) {
				Elements links = document.select("a[href]");
				for (Element link : links) {
					if (link.attr("href").contains(type) && link.attr("href").contains("thread")) {
						Document documentOfMonth = DocumentUtil.getDocumentObject(link.absUrl("href"));
						Elements tableHeaders = documentOfMonth.select("th[class=pages]");
						for (Element tableHeader : tableHeaders) {
							if (tableHeader.hasAttr("class") && tableHeader.attr("class").equals("pages")) {
								Elements tableHeaderChilds = tableHeader.children();
								for (Element tableHeaderChild : tableHeaderChilds) {
									if (!tableHeaderChild.text().contains("Next")) {
										String pageWiseUrl = link.absUrl("href") + "?"
												+ (Integer.parseInt(tableHeaderChild.text()) - 1);
										/*
										 * Here we are adding 1th and above
										 * indexed page urls
										 */
										if (!MainCrawler.urlMap.containsKey(pageWiseUrl))
											MainCrawler.urlMap.put(pageWiseUrl, 1);
									}
								}
								/*
								 * Here we will adding 0th indexed page
								 * 
								 */
								if (!MainCrawler.urlMap.containsKey(link.absUrl("href")))
									MainCrawler.urlMap.put(link.absUrl("href"), 1);
							}
						}
					}
				}
			} else if (type.equals("list")) {

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
													FileUtilities.writeToFile("scrpedUrls.txt", url, true);
													/*
													 * Here we are adding the
													 * page wise urls for map
													 */
													if (!MainCrawler.urlMap.containsKey(url)) {
														MainCrawler.urlMap.put(url, 1);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
