package com.referadr.login.service;

import java.util.List;
import java.util.Map;

import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.login.model.Login;

public interface LoginService {
	
	public int insertLoginCredentials(Login login);
	
	public Login validateCredentials(Login login) throws Exception;
	
	public CHInfo fetchCHInfo(int chId);
	
	public ProviderInfo fetchProviderPracticeId(String userName);

	public String supportMail(String fromEmailId,
			String subjecName, String descriptions);

	public PracticeInfo getPracticeName(int providePracticeId);

	public int getloginID(String userName) throws Exception;

	public void updatePassword(int id, String password);

	public int saveLoginTrack(int roleId, Login login, String ipAddress);


	
}
