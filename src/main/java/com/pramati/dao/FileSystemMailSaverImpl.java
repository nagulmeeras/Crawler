package com.pramati.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.pramati.bean.MailDataBean;

public class FileSystemMailSaverImpl implements MailSaverDao{
	public MailDataBean mdb;
	public FileSystemMailSaverImpl(MailDataBean mdb){
		this.mdb = mdb;
	}
	public void save() throws IOException {
		File file = new File("MailBox_"+mdb.getYear());
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(mdb.getContent().getBytes());
		fos.close();
	}

	public void closeConnection() {
		
	}

}
