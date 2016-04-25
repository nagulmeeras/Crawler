package com.pramati.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

public class FileUtilities {
	//final static Logger logger = Logger.getLogger(FileUtilities.class);
	public static String dir = System.getProperty("user.dir");
	
	public static void writeToFile(String fileName, String content, boolean append) throws Exception {
			
			File dirFile = new File(dir+"/logs/");
			
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			File file = new File(dir+"/logs/" + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, append);
			fw.write(content + "\n");
			fw.flush();
			fw.close();
	}

	public static Properties readFromFile(String fileName) throws Exception {
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fis);
			fis.close();
			return properties;
		} catch (Exception e) {
			//logger.debug("Unable to read content from file :" + fileName);
			throw new Exception("Unable to read the Properties File :" + fileName);
		}

	}
}
