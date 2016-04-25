package com.pramati.scrapservices;

import org.jsoup.nodes.Document;

public interface ScrapperFactory {
	public void getData(Document doc ) throws Exception;
}
