package com.pramati.daoService;

import java.util.Map;

import org.apache.log4j.Logger;

import com.pramati.bean.MailDataBean;
import com.pramati.dao.FileSystemMailSaverImpl;
import com.pramati.dao.MailSaverDao;

public class DaoServiceImpl implements DaoService{
	final static Logger logger = Logger.getLogger(DaoServiceImpl.class);
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
		logger.info("Saved mails");
		mailSaverDao.closeConnection();
	}
	
}
