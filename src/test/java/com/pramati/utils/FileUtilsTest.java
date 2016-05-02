package com.pramati.utils;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.pramati.utils.FileUtils;

public class FileUtilsTest {

	@Test(expected = IOException.class)
	public void testWriteToFile() throws Exception{
		FileUtils.writeToFile("", "", false);	
	}
	
	@Test
	public void testWriteToFile1() throws Exception{
		FileUtils.writeToFile("test.txt", "", false);	
	}
	
	@Test(timeout = 1000)
	public void testWriteToFile2() throws Exception{
		FileUtils.writeToFile("test.txt", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", false);	
	}
	
	@Test(expected = IOException.class)
	public void testReadProperties() throws IOException{
		FileUtils.readProperties("");
	}
	
	@Test
	public void testReadProperties1() throws IOException{
		FileUtils.readProperties("src/main/resources/log4j.properties");
	}
}
