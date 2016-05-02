package com.pramati.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class FileUtils {
	final static Logger logger = Logger.getLogger(FileUtils.class);
	public static String dir = System.getProperty("user.dir");

	public static void writeToFile(String fileName, String content, boolean append) throws Exception {

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, append);
		fw.write(content + "\n");
		fw.flush();
		fw.close();
	}

	public static Properties readProperties(String fileName) throws IOException {
		Properties properties = null;
		File file = new File(fileName);
		if (!file.exists()) {
			file = new File(fileName.replace("src/main", "Crawler"));
		}

		FileInputStream fis = new FileInputStream(file);
		properties = new Properties();
		properties.load(fis);
		fis.close();
		return properties;
	}
	public static String generateMD5(String url) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(url.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
}
