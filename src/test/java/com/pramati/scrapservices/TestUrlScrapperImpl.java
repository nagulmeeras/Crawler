package com.pramati.scrapservices;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestUrlScrapperImpl {
	
	@Test(timeout = 1000)
	public void testGetFirstLevelUrlsTimeoutCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/", "2012");
		urlScrappperImpl.getFirstLevelUrls();
	}
	
	@Test
	public void testGetFirstLevelUrlsFailureCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/", "");
		Map<String , String> actualMap = urlScrappperImpl.getFirstLevelUrls();
		Assert.assertTrue(actualMap.size() < 1);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testGetFirstLevelUrlsExceptionCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-us", "");
		urlScrappperImpl.getFirstLevelUrls();
	}
	
	@Test
	public void testGetFirstLevelUrlsSuccessCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/", "2014");
		Map<String , String> actualMap = urlScrappperImpl.getFirstLevelUrls();
		Assert.assertTrue(actualMap.size() > 0);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testSecondLevelUrlsExceptionCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("","");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
		Assert.assertTrue(actualMap.size()< 1);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testSecondLevelUrlsFailureCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mb","2014");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
		Assert.assertTrue(actualMap.size()< 1);
	}
	
	@Test(timeout = 2000)
	public void testSecondLevelUrlsTimeoutCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox/date","2014");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
	}
	
	@Test
	public void testSecondLevelUrlsSuccessCase() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox/date","2014");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
		Assert.assertTrue(actualMap.size()> 0);
	}
}
