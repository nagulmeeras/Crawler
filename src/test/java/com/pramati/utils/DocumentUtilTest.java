package com.pramati.utils;

import java.io.FileNotFoundException;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class DocumentUtilTest {

	@Test(expected = FileNotFoundException.class)
	public void getDocumentObject() throws FileNotFoundException{
		DocumentUtils.getDocumentObject("");
	}
	
	@Test(timeout= 1000)
	public void getDocumentObject1() throws FileNotFoundException{
		Document doc= DocumentUtils.getDocumentObject("http://mail-archives.apache.org/mod_mbox/maven-users");
	}
}
