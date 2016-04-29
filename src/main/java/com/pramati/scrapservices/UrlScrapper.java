package com.pramati.scrapservices;

import java.util.Map;

public interface UrlScrapper {
	public Map<String, String> getFirstLevelUrls()throws Exception;
	public Map<String, String> getSecondLevelUrls()throws Exception;
}
