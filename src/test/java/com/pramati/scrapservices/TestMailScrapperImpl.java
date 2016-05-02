package com.pramati.scrapservices;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.junit.Assert;
import org.junit.Test;

public class TestMailScrapperImpl {

	Map<String,String> expectedMap = new HashMap<String, String>();
	/*
	 * success case 
	 */
	
	
	@Test(expected= FileNotFoundException.class)
	public void testGetMailsNullCase() throws FileNotFoundException{
		MailScrapperImpl mailScrapperImpl = new MailScrapperImpl("");
		mailScrapperImpl.getMails();
	}
}
