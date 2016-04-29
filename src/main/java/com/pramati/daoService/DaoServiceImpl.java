package com.pramati.daoService;

import java.util.Map;

import com.pramati.bean.MailDataBean;
import com.pramati.dao.FileSystemMailSaverImpl;
import com.pramati.dao.MailSaverDao;

public class DaoServiceImpl implements DaoService{
	public Map<String,String> mails;
	public String year;
	
	public DaoServiceImpl(Map<String , String> mails, String year){
		this.mails = mails;
		this.year = year;
	}
	public void save() throws Exception {
		String content = null;
		for (String url : mails.keySet()) {
			content += mails.get(url);
			content += "\n";
		}
		MailDataBean mdb = new MailDataBean();
		mdb.setContent(content);
		mdb.setYear(year);
		MailSaverDao mailSaverDao = new FileSystemMailSaverImpl(mdb);
		mailSaverDao.save();
		mailSaverDao.closeConnection();
	}
	
}
