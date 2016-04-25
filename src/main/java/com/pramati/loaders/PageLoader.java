package com.pramati.loaders;

import org.jsoup.nodes.Document;

import com.pramati.scrapservices.MailScrapper;
import com.pramati.scrapservices.ScrapperFactory;
import com.pramati.scrapservices.UrlScrappper;
import com.pramati.utils.DocumentUtil;

public class PageLoader implements Runnable {
	public Document doc;
	public String url;
	public PageLoader(String url ) throws Exception{
		this.doc = DocumentUtil.getDocumentObject(url);
		this.url = url;
	}

	public void run() {
		try {
			ScrapperFactory urlScrapperObject = new UrlScrappper();
			urlScrapperObject.getData(doc );
			ScrapperFactory mailScrapperObject = new MailScrapper();
			mailScrapperObject.getData(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
