package com.pramati.scrapservices;

import java.util.Map;

public class UrlRunnableScrapperImpl implements RunnableScrapper {
	public UrlScrapper urlScrapper;
	public Map<String , String> secondLevelUrls; 
	public UrlRunnableScrapperImpl(UrlScrapper urlScrapper , Map<String , String> secondLevelUrls){
		this.urlScrapper = urlScrapper;
		this.secondLevelUrls = secondLevelUrls;
		
	}
	public void run() {
		try {
			secondLevelUrls.putAll(urlScrapper.getSecondLevelUrls());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
