package com.pramati.scrapservices;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestMailScrapperImpl {

	Map<String,String> expectedMap = new HashMap<String, String>();
	/*
	 * success case 
	 */
	@Test(timeout = 1000)
	public void testGetMailsSuccess() throws Exception{
		MailScrapperImpl mailScrapperImpl = new MailScrapperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201512.mbox/ajax/%3CBLU172-W359710D4EBAEC8B1EC069EAE0F0%40phx.gbl%3E");
		Map<String , String> mails = mailScrapperImpl.getMails();
		System.out.println(mails.size());
		expectedMap.put("Martin Gainty <mgai...@hotmail.com>", "");
		Assert.assertSame(expectedMap, mails);
		
	}
	
	@Test(timeout = 1000)
	public void testGetMailsEmpty() throws Exception{
		MailScrapperImpl mailScrapperImpl = new MailScrapperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201512.mbox/ajax/");
		Map<String , String> mails = mailScrapperImpl.getMails();
		System.out.println(mails.size());
		Assert.assertSame(expectedMap, mails);	
	}
	
	@Test(timeout = 1000)
	public void testGetMailsNull() throws Exception{
		MailScrapperImpl mailScrapperImpl = new MailScrapperImpl("");
		Map<String , String> mails = mailScrapperImpl.getMails();
		System.out.println(mails.size());
		Assert.assertNull(mails);
	}
}
