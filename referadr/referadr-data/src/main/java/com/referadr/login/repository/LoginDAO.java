package com.referadr.login.repository;

import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.login.model.Login;

public interface LoginDAO {
	
	public int insertLoginCredentials(Login login);
	
	public Login validateCredentials(Login login) throws Exception; 
	
	public CHInfo fetchChInfo(int chId);
	
	public ProviderInfo fetchProviderPracticeId(String userName);

	public PracticeInfo getPracticeName(int providePracticeId);

	public int getloginId(String userName);

	public void updatePassword(int id, String password);

	public Login getRoleId(Login login);

	public boolean saveLoginTrack(int roleId, Login login, int id,
			Login loginrole,String ipAddress);


}
