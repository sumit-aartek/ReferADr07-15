package com.referadr.login.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.simpleworkflow.flow.core.TryCatch;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;
import com.referadr.login.model.Login;
import com.referadr.login.repository.LoginDAO;
import com.referadr.login.service.LoginService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDAO loginDAO;

	public int insertLoginCredentials(Login login) {
		System.out.println(login.getUserName());
		return loginDAO.insertLoginCredentials(login);
		
	}

	public Login validateCredentials(Login login) throws Exception{
		try{
		return loginDAO.validateCredentials(login);}
		catch(Exception e){return null;}
	}

	public CHInfo fetchCHInfo(int chId) {
		return loginDAO.fetchChInfo(chId);
	}

	public ProviderInfo fetchProviderPracticeId(String userName) {
		return loginDAO.fetchProviderPracticeId(userName);
	}

	public String supportMail(String fromEmailId,
			String subjecName, String descriptions) {
		subjecName="Ticket Subject : "+subjecName;
		/*String toEmailId="referadr@mkonnekt.com";*/
		String toEmailId="patidarsandeep991@gmail.com";
		  ReferadrUtils.sendMail(fromEmailId,toEmailId,subjecName, descriptions);
		return null;
	}

	public PracticeInfo getPracticeName(int providePracticeId) {
		
		return loginDAO.getPracticeName(providePracticeId);
	}
	
	public int getloginID(String userName) throws Exception{
		Login login=new Login();
		int id=loginDAO.getloginId(userName);
		String encrId=ReferadrUtils.encrypt(""+id);
		//System.out.println("encrId="+encrId);
//		String decrId=ReferadrUtils.decrypt(encrId);
//		System.out.println("decrId="+decrId);
		login.setId(id);
		String fromEmailId="";
		String toEmailId=userName;
		String subjecName="Password Recovery";
		//String toEmailId="referadr@mkonnekt.com";
		String descriptions="Please open the given URL to Create New Password: '"+IConstant.PASSWORD_RECOVERY_URL+"?loginId="+encrId+"'";
		System.out.println(descriptions);
		 try{
			 if(id!=0){
		ReferadrUtils.sendMail(fromEmailId, toEmailId, subjecName, descriptions);
			 }
		 }catch(Exception e){
			// System.out.print("error");
		 }
		return id;
		
	}
	
	
	public void updatePassword(int id, String password){
		
		password=ReferadrUtils.sha1(password);
		
		
		loginDAO.updatePassword(id,password);
		
	}
	
	
	
	public int saveLoginTrack(int roleId, Login login,String ipAddress){
		boolean loginStatus=false;
		int status=0;
		int id=loginDAO.getloginId(login.getUserName());
		Login loginrole=loginDAO.getRoleId(login);
	if(id!=0){
		loginStatus=loginDAO.saveLoginTrack(roleId,login,id,loginrole,ipAddress);
	
		if(!loginStatus){
			status=1;	
		}else if(loginStatus){
			status=2;	
		}
	}
		return status;
	}

	
}
