package com.pramati.crawler;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestPoolExecutor {
	@Test
	public void testexecutePoolForUrlsNullCase() throws Exception{
		Map<String , String> sharedMap = null;
		PoolExecutor poolExecutor = new PoolExecutor(null, sharedMap, null);
		poolExecutor.executePoolForUrls();
		Assert.assertNull(sharedMap);
	}
	
	@Test
	public void testexecutePoolForUrlsEmptyCase1() throws Exception{
		Map<String , String> tasks = new HashMap<String , String>();
		Map<String , String> sharedMap = null;
		PoolExecutor poolExecutor = new PoolExecutor(tasks, sharedMap, null);
		poolExecutor.executePoolForUrls();
		Assert.assertNull(sharedMap);
	}
	
	@Test
	public void testexecutePoolForUrlsEmptyCase2() throws Exception{
		Map<String , String> tasks = new HashMap<String , String>();
		Map<String , String> sharedMap = new HashMap<String, String>();
		tasks.put("http://mail-archives.apache.org/mod_mbox/maven-users/201512.mbox/date", "");
		PoolExecutor poolExecutor = new PoolExecutor(tasks,sharedMap , "");
		poolExecutor.executePoolForUrls();
		Assert.assertNotNull(sharedMap);
	}
	
	@Test
	public void testexecutePoolForMailsNullCase() throws Exception{
		Map<String , String> sharedMap = null;
		PoolExecutor poolExecutor = new PoolExecutor(null, sharedMap, null);
		poolExecutor.executePoolForMails();
		Assert.assertNull(sharedMap);
	}
	
	@Test
	public void testexecutePoolForMailsEmptyCase1() throws Exception{
		Map<String , String> tasks = new HashMap<String , String>();
		Map<String , String> sharedMap = null;
		PoolExecutor poolExecutor = new PoolExecutor(tasks, sharedMap, null);
		poolExecutor.executePoolForMails();
		Assert.assertNull(sharedMap);
	}
	
}
