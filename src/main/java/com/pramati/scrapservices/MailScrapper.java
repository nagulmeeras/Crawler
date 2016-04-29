package com.pramati.scrapservices;

import java.util.Map;

public interface MailScrapper {
	public Map<String , String> getMails() throws Exception;
}
