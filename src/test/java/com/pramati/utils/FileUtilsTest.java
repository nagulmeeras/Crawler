package com.pramati.utils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.pramati.utils.FileUtils;

import junit.framework.Assert;

public class FileUtilsTest {

	@Test(expected = IOException.class)
	public void testWriteToFileToCheckExcpetionCase() throws Exception{
		FileUtils.writeToFile("", "", false);	
	}
	
	@Test
	public void testWriteToFileSuccessCase() throws Exception{
		FileUtils.writeToFile("test.txt", "", false);	
	}
	
	@Test(timeout = 1000)
	public void testWriteToFileTimeoutCase() throws Exception{
		FileUtils.writeToFile("test.txt", "this is test content", false);	
	}
	
	@Test(expected = IOException.class)
	public void testReadPropertiesExceptionCase() throws IOException{
		FileUtils.readProperties("");
	}
	
	@Test
	public void testReadPropertiesSuccessCase() throws IOException{
		FileUtils.readProperties("src/main/resources/log4j.properties");
	}
	
	@Test(timeout = 1000)
	public void testReadPropertiesTimeoutCase() throws IOException{
		FileUtils.readProperties("src/main/resources/crawler.properties");
	}
	
	@Test
	public void testGenerateMd5FailureCase() throws NoSuchAlgorithmException{
		String actual = FileUtils.generateMD5("");
		Assert.assertNotNull(actual);
	}
	
	@Test(timeout = 500)
	public void testGenerateMd5TimeoutCase() throws NoSuchAlgorithmException{
		FileUtils.generateMD5("");
	}
}
