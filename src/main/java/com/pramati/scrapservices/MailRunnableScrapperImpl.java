package com.pramati.scrapservices;

import java.util.Map;

public class MailRunnableScrapperImpl implements RunnableScrapper {
	public MailScrapper mailScrapper;
	public Map<String , String> mailsMap;
	public MailRunnableScrapperImpl(MailScrapper mailScrapper , Map<String,String> mailsMap){
		this.mailScrapper = mailScrapper;
		this.mailsMap = mailsMap;
	}
	public void run() {
		try {
			mailsMap.putAll(mailScrapper.getMails());
			System.out.println(mailsMap.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
