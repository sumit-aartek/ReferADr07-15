package com.referadr.practice.repository;

import com.referadr.login.model.Login;
import com.referadr.practice.model.CHInfo;

public interface LoginDAO {
	
	public void insertLoginCredentials(Login login);
	
	public Login validateCredentials(Login login); 
	
	public CHInfo fetchChInfo(Login login);
	
	public Login fetchLoginInfo(Login login);

}
