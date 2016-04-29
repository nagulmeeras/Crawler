package com.pramati.dao;

public interface MailSaverDao {
	public void save() throws Exception;
	public void closeConnection();
}
