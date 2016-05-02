package com.pramati.scrapservices;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestUrlScrapperImpl {
	
	@Test(timeout = 1000)
	public void testGetFirstLevelUrls() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/", "2012");
		urlScrappperImpl.getFirstLevelUrls();
	}
	
	@Test
	public void testGetFirstLevelUrls1() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/", "");
		Map<String , String> actualMap = urlScrappperImpl.getFirstLevelUrls();
		Assert.assertTrue(actualMap.size() < 1);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testGetFirstLevelUrls2() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-us", "");
		urlScrappperImpl.getFirstLevelUrls();
	}
	
	@Test
	public void testGetFirstLevelUrls3() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/", "2014");
		Map<String , String> actualMap = urlScrappperImpl.getFirstLevelUrls();
		Assert.assertTrue(actualMap.size() > 0);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testSecondLevelUrls() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("","");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
		Assert.assertTrue(actualMap.size()< 1);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testSecondLevelUrls1() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mb","2014");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
		Assert.assertTrue(actualMap.size()< 1);
	}
	
	@Test(timeout = 2000)
	public void testSecondLevelUrls2() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox/date","2014");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
	}
	
	@Test
	public void testSecondLevelUrls3() throws FileNotFoundException{
		UrlScrappperImpl urlScrappperImpl = new UrlScrappperImpl("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox/date","2014");
		Map<String , String> actualMap = urlScrappperImpl.getSecondLevelUrls();
		Assert.assertTrue(actualMap.size()> 0);
	}
}
