package com.pramati.loaders;

import org.jsoup.nodes.Document;

import com.pramati.scrapservices.UrlScrappper;
import com.pramati.utils.DocumentUtil;

public class UrlLoader implements Runnable{
	public String url;
	public Document document;
	public UrlLoader(String url) throws Exception{
		this.url = url;
		this.document = DocumentUtil.getDocumentObject(url);
	}
	public void run() {
		UrlScrappper urlScrappper = new UrlScrappper();
		try {
			urlScrappper.getUrls(document , "list");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
