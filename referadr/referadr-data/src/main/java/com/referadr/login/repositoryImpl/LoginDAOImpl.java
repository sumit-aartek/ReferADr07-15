package com.referadr.login.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.referadr.login.model.Login;
import com.referadr.login.repository.LoginDAO;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.LoginTrackingVO;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.util.CommonUtils;

@Repository
public class LoginDAOImpl implements LoginDAO{

	
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new  JdbcTemplate(dataSource);
	}

	public Login validateCredentials(Login login) throws Exception{
		String loginSQL="select role_id from rad_login where username=? and password=?"; 
		Object[] params=new Object[]{login.getUserName(),login.getPassword()};
		int roleId=jdbcTemplateObject.queryForInt(loginSQL, params);
		login.setRoleId(roleId);
		return login;
	}

	public int insertLoginCredentials(Login login) {
		String insertLoginSql="insert into rad_login(username,password,role_id) values(?,?,?)";
		Object[] params=new Object[] {login.getUserName(),login.getPassword(),login.getRoleId()};
		int[] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		int loginRow=jdbcTemplateObject.update(insertLoginSql,params,types);
		return loginRow;
	}

	public RadLocation fetchLocation(int locId)
	{
		String fetchLocInfoSQL="select a.loc_id loc_id,a.LOC_ADDRESS1 LOC_ADDRESS1,a.LOC_ADDRESS2 LOC_ADDRESS2,a.LOC_CITY LOC_CITY,b.STATE_NAME STATE_NAME,a.LOC_ZIP LOC_ZIP,a.LOC_PHONE LOC_PHONE,a.LOC_FAX LOC_FAX,a.LOC_WEBSITE LOC_WEBSITE from rad_location a,rad_state b where a.loc_id=? and a.LOC_STATE_ID=b.STATE_ID";
		Object[] params=new Object[]{locId};
		RadLocation radLocation=new RadLocation();
		List<RadLocation> radLocations=new ArrayList<RadLocation>();
		radLocations= jdbcTemplateObject.query(fetchLocInfoSQL, params, new RowMapper<RadLocation>(){

			public RadLocation mapRow(ResultSet rs, int rowNumber) throws SQLException {
				RadLocation radLocation=new RadLocation();
				radLocation.setLocId(rs.getInt("loc_id"));
				radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
				radLocation.setLocAddress2(rs.getString("LOC_ADDRESS2"));
				radLocation.setLocCity(rs.getString("LOC_CITY"));
				radLocation.setLocState(rs.getString("STATE_NAME"));
				radLocation.setLocZip(rs.getString("LOC_ZIP"));
				radLocation.setLocPhone(rs.getString("LOC_PHONE"));
				radLocation.setLocFax(rs.getString("LOC_FAX"));
				radLocation.setLocWebsite(rs.getString("LOC_WEBSITE"));
				
				return radLocation;
			}
			
		});
		if(!radLocations.isEmpty()){
			radLocation=radLocations.get(0);
		}
		
return radLocation;
	}

	public CHInfo fetchChInfo(int chId) {
		String fetchChInfoSQL="select * from rad_ch_info where ch_id=?";
		Object[] params=new Object[]{chId};
		CHInfo chInfo=new CHInfo();
		List<CHInfo> chInfos=new ArrayList<CHInfo>();
		chInfos= jdbcTemplateObject.query(fetchChInfoSQL, params, new RowMapper<CHInfo>(){

			public CHInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				CHInfo chInfo=new CHInfo();
				chInfo.setId(rs.getInt("CH_ID"));
				chInfo.setName(rs.getString("CN_NAME"));
				chInfo.setDescription(rs.getString("CH_DEC"));
				chInfo.setChAncProvide(rs.getInt("CH_ANC_PROVIDE"));
				int locId=(Integer)rs.getInt("CH_LOC_ID");
				if(locId>0)
				{
					chInfo.setRadLocation(fetchLocation(rs.getInt("CH_LOC_ID")));
				}
				else
				{
					RadLocation radLocation=new RadLocation();
					radLocation.setLocId(999999);
					chInfo.setRadLocation(radLocation);
				}
				
				return chInfo;
			}
			
		});
		
		if(!chInfos.isEmpty()){
			chInfo=chInfos.get(0);
		}
		
return chInfo;
	}

	public ProviderInfo fetchProviderPracticeId(String userName) {
		/*String fetchProviderPracticeIdSQL="select provider_prac_id from rad_provider_info where provider_email=?";
		Object[] params=new Object[]{userName};
		return jdbcTemplateObject.queryForInt(fetchProviderPracticeIdSQL, params);*/
		 ProviderInfo providerInfo=new ProviderInfo();
		 List<ProviderInfo> providerInfos=new ArrayList<ProviderInfo>();
	        JdbcTemplate jdbcTemplateRe = CommonUtils.getJdbcTemplate();
			//=====================================================
			String fetchProviderSQL="select PROVIDER_ID,provider_prac_id,PROVIDER_FIRST_NAME from rad_provider_info where provider_email='"+userName+"'";
		
			try{
			providerInfos= jdbcTemplateObject.query(fetchProviderSQL, new RowMapper<ProviderInfo>(){

				public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
					ProviderInfo providerInfo=new ProviderInfo();
					
					PracticeInfo practiceInfo = new PracticeInfo();
					providerInfo.setPracticeInfo(practiceInfo);
					providerInfo.setProviderId(rs.getInt("Provider_ID"));	
					providerInfo.setProviderLastName(rs.getString("Provider_First_Name"));	
					practiceInfo.setPracticeId(rs.getInt("provider_prac_id"));	
					return providerInfo;
				}
				
			});
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			if(!providerInfos.isEmpty()){
				providerInfo=providerInfos.get(0);
			}
			
			
			return providerInfo;
		
	}

	public PracticeInfo getPracticeName(int providePracticeId) {
		PracticeInfo practiceInfo=new PracticeInfo();
		List<PracticeInfo> practiceInfos=new ArrayList<PracticeInfo>();
		JdbcTemplate jdbcTemplateRe = CommonUtils.getJdbcTemplate();
		String fetchProviderSQL="SELECT PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID="+providePracticeId;
		practiceInfos= jdbcTemplateObject.query(fetchProviderSQL, new RowMapper<PracticeInfo>(){

			public PracticeInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				PracticeInfo practiceInfo=new PracticeInfo();
				
				practiceInfo.setPracticeName(rs.getString("PRACTICE_NAME"));	
			
				
				return practiceInfo;
			}
			
		});
		if(!practiceInfos.isEmpty()){
			practiceInfo=practiceInfos.get(0);
		}
		return practiceInfo;
	}

	
	public int getloginId(String userName){
		int loginId=0;
		
		List<Login> loginlist=new ArrayList<Login>();
		JdbcTemplate jdbcTemplateRe = CommonUtils.getJdbcTemplate();
		String fetchIdSQL="SELECT id FROM rad_login WHERE username='"+userName+"'";
		loginlist= jdbcTemplateObject.query(fetchIdSQL, new RowMapper<Login>(){

			public Login mapRow(ResultSet rs, int rowNumber) throws SQLException {
				Login login=new Login();
				
				login.setId(rs.getInt("id"));	
			
				
				return login;
			}
			
		});
		
		if(loginlist.size()!=0)
		{
			loginId=loginlist.get(0).getId();
		}
		return loginId;
	}
	
	
	
	public void updatePassword(int id, String password){
		
		 String sqlQury="UPDATE rad_login SET PASSWORD = ? WHERE id = ? ";
			Object[] params1 = new Object[] {password,id};
			int[] types1 = new int[] { Types.VARCHAR,Types.INTEGER};
			int row1=jdbcTemplateObject.update(sqlQury,params1,types1);
		System.out.print(row1);
	}
	

	
	public Login getRoleId(Login login){
		String loginSQL="select role_id from rad_login where username=?"; 
		Object[] params=new Object[]{login.getUserName()};
		int roleId=0;
				try{
					roleId=jdbcTemplateObject.queryForInt(loginSQL, params);
				}
				catch(Exception e){
					
				}
		Login loginRoleid=new Login();
		loginRoleid.setRoleId(roleId);
		return loginRoleid;
	}
	
	
	public boolean saveLoginTrack(int roleId, Login login, int id,
			Login loginrole,String ipAddress){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate=""+dateFormat.format(date);
		String oldDate=""+dateFormat.format(date);
		
		
	
		// if both are not selected
	
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -30);
			Date result = cal.getTime();
			oldDate = "" + dateFormat.format(result);
		
		
		
		
		
		
		
		
		
		
		
		if(loginrole.getRoleId()==2){
			String loginSQL="SELECT PROV_LOGIN_TIME,  "+
			"PROV_LOGIN_RESULT,  "+
		"	PROV_LOGIN_ATTEMPT,  "+
			"PROVIDER_ID "+
		"	FROM  "+
		"	rad_prov_roles_login_track  "+
		"	WHERE PROV_LOGIN_TIME BETWEEN '"+oldDate+"' AND '"+currentDate+"' ORDER BY PROV_ROLES_LOGIN_TK_ID DESC LIMIT 1 ";
			LoginTrackingVO loginTrackingVO=new LoginTrackingVO();
			List<LoginTrackingVO> loginTrackingVOs=new ArrayList<LoginTrackingVO>();
			loginTrackingVOs= jdbcTemplateObject.query(loginSQL, new RowMapper<LoginTrackingVO>(){

				public LoginTrackingVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
					LoginTrackingVO loginTrackingVO=new LoginTrackingVO();
					
					loginTrackingVO.setLoginAttempt(rs.getInt("PROV_LOGIN_ATTEMPT")); 
					loginTrackingVO.setLoginResult(rs.getInt("PROV_LOGIN_RESULT"));
					loginTrackingVO.setLoginTime(rs.getString("PROV_LOGIN_TIME"));
					loginTrackingVO.setProviderId(rs.getInt("PROVIDER_ID"));
					
					return loginTrackingVO;
				}
				
			});
			
			int lastattemptresult=1;
			int noOfAttempt=0;
			if(loginTrackingVOs.size()!=0){
				loginTrackingVO=loginTrackingVOs.get(0);
				lastattemptresult=loginTrackingVO.getLoginResult();
				noOfAttempt=loginTrackingVO.getLoginAttempt();
				}
			
			
			
			
			if(lastattemptresult!=0 || noOfAttempt<=4){
				if(roleId==2)
				{
					if(lastattemptresult!=0){
						//insert new row
						String saveSchedule = "INSERT INTO rad_prov_roles_login_track "+
								"(PROV_LOGIN_TIME,  "+
								"PROV_LOGIN_RESULT,  "+
								"PROV_LOGIN_IP,  "+
								"PROV_LOGIN_ATTEMPT,  "+
								"PROVIDER_ID,  "+
								"CREATED_BY,  "+
								"CREATION_DATE,  "+
								"UPDATED_BY,  "+
								"UPDATED_DATE "+
								") "+
								"VALUES "+
								"(?,?,?,?,?,?,?,?,?)";
						Object[] paramsShedule = new Object[] {currentDate,1,ipAddress,1,id,"SYSTEM",currentDate,"SYSTEM",currentDate};
						int[] typesShedule = new int[] { Types.TIMESTAMP, Types.INTEGER ,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
						int rowCurrStatus5 = jdbcTemplateObject.update(saveSchedule, paramsShedule,typesShedule);
						
						
						
					}else{
						//update old row with 1
						
						String loginSQLl="SELECT 	MAX(PROV_ROLES_LOGIN_TK_ID)FROM rad_prov_roles_login_track WHERE PROV_LOGIN_RESULT=0 AND PROVIDER_ID=? "; 
						Object[] params=new Object[]{id};
						int indexid=0;
						try{
							indexid=jdbcTemplateObject.queryForInt(loginSQLl, params);
						}catch(Exception e){
							
						}
						
						 String sqlQury="UPDATE rad_prov_roles_login_track  "+
								 "	SET "+
								 "	PROV_LOGIN_TIME = ? ,  "+
								 "	PROV_LOGIN_RESULT = ? ,  "+
								 "	PROV_LOGIN_IP = ? ,  "+
								 "	PROV_LOGIN_ATTEMPT = ? ,  "+
								 "	UPDATED_BY = ? ,  "+
								 "	UPDATED_DATE = ? "+
								 "	WHERE "+
								 "	PROV_ROLES_LOGIN_TK_ID ="+indexid;
							Object[] params1 = new Object[] {currentDate,1,ipAddress,loginTrackingVO.getLoginAttempt()+1,"SYSTEM",currentDate};
							int[] types1 = new int[] { Types.TIMESTAMP,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP};
							int row1=jdbcTemplateObject.update(sqlQury,params1,types1);
					}
					
				}
				else
				{
					if(lastattemptresult==0){
						//update old row
						
						String loginSQLl="SELECT 	MAX(PROV_ROLES_LOGIN_TK_ID)FROM rad_prov_roles_login_track WHERE PROV_LOGIN_RESULT=0 AND PROVIDER_ID=? "; 
						Object[] params=new Object[]{id};
						int indexid=0;
						try{
						 indexid=jdbcTemplateObject.queryForInt(loginSQLl, params);
						}catch(Exception e){
							
						}
						
						
						 String sqlQury="UPDATE rad_prov_roles_login_track  "+
								 "	SET "+
								 "	PROV_LOGIN_TIME = ? ,  "+
								 "	PROV_LOGIN_RESULT = ? ,  "+
								 "	PROV_LOGIN_IP = ? ,  "+
								 "	PROV_LOGIN_ATTEMPT = ? ,  "+
								 "	UPDATED_BY = ? ,  "+
								 "	UPDATED_DATE = ? "+
								 "	WHERE "+
								 "	PROV_ROLES_LOGIN_TK_ID ="+indexid;
							Object[] params1 = new Object[] {currentDate,0,ipAddress,loginTrackingVO.getLoginAttempt()+1,"SYSTEM",currentDate};
							int[] types1 = new int[] { Types.TIMESTAMP,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP};
							int row1=jdbcTemplateObject.update(sqlQury,params1,types1);
					}else{
						//insert new row
						String saveSchedule = "INSERT INTO rad_prov_roles_login_track "+
								"(PROV_LOGIN_TIME,  "+
								"PROV_LOGIN_RESULT,  "+
								"PROV_LOGIN_IP,  "+
								"PROV_LOGIN_ATTEMPT,  "+
								"PROVIDER_ID,  "+
								"CREATED_BY,  "+
								"CREATION_DATE,  "+
								"UPDATED_BY,  "+
								"UPDATED_DATE "+
								") "+
								"VALUES "+
								"(?,?,?,?,?,?,?,?,?)";
						Object[] paramsShedule = new Object[] {currentDate,0,ipAddress,1,id,"SYSTEM",currentDate,"SYSTEM",currentDate};
						int[] typesShedule = new int[] { Types.TIMESTAMP, Types.INTEGER ,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
						int rowCurrStatus5 = jdbcTemplateObject.update(saveSchedule, paramsShedule,typesShedule);
						
						
					}	
				}
			}else{
				return false;
			}
			
		} 
		
		
		
		
		
		
		
		
		
		
		
		
		if(loginrole.getRoleId()==1){
			String loginSQL="SELECT CH_ADMIN_LOGIN_TIME,  "+
					"CH_ADMIN_LOGIN_RESULT,  "+
				"	CH_ADMIN_LOGIN_ATTEMPT,  "+
					"CH_ADMIN_ID "+
				"	FROM  "+
				"	rad_ch_admin_roles_login_track  "+
				"	WHERE CH_ADMIN_LOGIN_TIME BETWEEN '"+oldDate+"' AND '"+currentDate+"' ORDER BY CH_ADMIN_LOGIN_ROLE_TRACK_ID DESC LIMIT 1 ";
			LoginTrackingVO loginTrackingVO=new LoginTrackingVO();
			List<LoginTrackingVO> loginTrackingVOs=new ArrayList<LoginTrackingVO>();
			loginTrackingVOs= jdbcTemplateObject.query(loginSQL, new RowMapper<LoginTrackingVO>(){

				public LoginTrackingVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
					LoginTrackingVO loginTrackingVO=new LoginTrackingVO();
					
					loginTrackingVO.setLoginAttempt(rs.getInt("CH_ADMIN_LOGIN_ATTEMPT")); 
					loginTrackingVO.setLoginResult(rs.getInt("CH_ADMIN_LOGIN_RESULT"));
					loginTrackingVO.setLoginTime(rs.getString("CH_ADMIN_LOGIN_TIME"));
					loginTrackingVO.setProviderId(rs.getInt("CH_ADMIN_ID"));
					
					return loginTrackingVO;
				}
				
			});
			
			int lastattemptresult=1;
			int noOfAttempt=0;
			if(loginTrackingVOs.size()!=0){
				loginTrackingVO=loginTrackingVOs.get(0);
				lastattemptresult=loginTrackingVO.getLoginResult();
				noOfAttempt=loginTrackingVO.getLoginAttempt();
				}
			
			
			
			
			if(lastattemptresult!=0 || noOfAttempt<=4){
				if(roleId==1)
				{
					if(lastattemptresult!=0){
						//insert new row
						String saveSchedule = "INSERT INTO rad_ch_admin_roles_login_track "+
								"(CH_ADMIN_LOGIN_TIME,  "+
								"CH_ADMIN_LOGIN_RESULT,  "+
								"CH_ADMIN_LOGIN_IP,  "+
								"CH_ADMIN_LOGIN_ATTEMPT,  "+
								"CH_ADMIN_ID,  "+
								"CREATED_BY,  "+
								"CREATION_DATE,  "+
								"UPDATED_BY,  "+
								"UPDATED_DATE "+
								") "+
								"VALUES "+
								"(?,?,?,?,?,?,?,?,?)";
						Object[] paramsShedule = new Object[] {currentDate,1,ipAddress,1,id,"SYSTEM",currentDate,"SYSTEM",currentDate};
						int[] typesShedule = new int[] { Types.TIMESTAMP, Types.INTEGER ,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
						int rowCurrStatus5 = jdbcTemplateObject.update(saveSchedule, paramsShedule,typesShedule);
						
						
						
					}else{
						//update old row with 1
						
						String loginSQLl="SELECT MAX(CH_ADMIN_LOGIN_ROLE_TRACK_ID)FROM rad_ch_admin_roles_login_track WHERE CH_ADMIN_LOGIN_RESULT=0 AND CH_ADMIN_ID=? "; 
						Object[] params=new Object[]{id};
						int indexid=0;
						try{
						 indexid=jdbcTemplateObject.queryForInt(loginSQLl, params);
						}catch(Exception e){
							
						}
						
						String sqlQury="UPDATE rad_ch_admin_roles_login_track  "+
								 "	SET "+
								 "	CH_ADMIN_LOGIN_TIME = ? ,  "+
								 "	CH_ADMIN_LOGIN_RESULT = ? ,  "+
								 "	CH_ADMIN_LOGIN_IP = ? ,  "+
								 "	CH_ADMIN_LOGIN_ATTEMPT = ? ,  "+
								 "	UPDATED_BY = ? ,  "+
								 "	UPDATED_DATE = ? "+
								 "	WHERE "+
								 "	CH_ADMIN_LOGIN_ROLE_TRACK_ID ="+indexid;
							Object[] params1 = new Object[] {currentDate,1,ipAddress,loginTrackingVO.getLoginAttempt()+1,"SYSTEM",currentDate};
							int[] types1 = new int[] { Types.TIMESTAMP,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP};
							int row1=jdbcTemplateObject.update(sqlQury,params1,types1);
					}
					
				}
				else
				{
					if(lastattemptresult==0){
						//update old row
						
						String loginSQLl="SELECT 	MAX(CH_ADMIN_LOGIN_ROLE_TRACK_ID)FROM rad_ch_admin_roles_login_track WHERE CH_ADMIN_LOGIN_RESULT=0 AND CH_ADMIN_ID=? ";	 
						Object[] params=new Object[]{id};
						int indexid=0;
						try{
						indexid=jdbcTemplateObject.queryForInt(loginSQLl, params);
						}catch(Exception e){
							
						}
						
						
						 String sqlQury="UPDATE rad_ch_admin_roles_login_track  "+
								 "	SET "+
								 "	CH_ADMIN_LOGIN_TIME = ? ,  "+
								 "	CH_ADMIN_LOGIN_RESULT = ? ,  "+
								 "	CH_ADMIN_LOGIN_IP = ? ,  "+
								 "	CH_ADMIN_LOGIN_ATTEMPT = ? ,  "+
								 "	UPDATED_BY = ? ,  "+
								 "	UPDATED_DATE = ? "+
								 "	WHERE "+
								 "	CH_ADMIN_LOGIN_ROLE_TRACK_ID ="+indexid;
							Object[] params1 = new Object[] {currentDate,0,ipAddress,loginTrackingVO.getLoginAttempt()+1,"SYSTEM",currentDate};
							int[] types1 = new int[] { Types.TIMESTAMP,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP};
							int row1=jdbcTemplateObject.update(sqlQury,params1,types1);
					}else{
						//insert new row
						String saveSchedule = "INSERT INTO rad_ch_admin_roles_login_track "+
								"(CH_ADMIN_LOGIN_TIME,  "+
								"CH_ADMIN_LOGIN_RESULT,  "+
								"CH_ADMIN_LOGIN_IP,  "+
								"CH_ADMIN_LOGIN_ATTEMPT,  "+
								"CH_ADMIN_ID,  "+
								"CREATED_BY,  "+
								"CREATION_DATE,  "+
								"UPDATED_BY,  "+
								"UPDATED_DATE "+
								") "+
								"VALUES "+
								"(?,?,?,?,?,?,?,?,?)";
						Object[] paramsShedule = new Object[] {currentDate,0,ipAddress,1,id,"SYSTEM",currentDate,"SYSTEM",currentDate};
						int[] typesShedule = new int[] { Types.TIMESTAMP, Types.INTEGER ,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
						int rowCurrStatus5 = jdbcTemplateObject.update(saveSchedule, paramsShedule,typesShedule);
						
						
					}	
				}
			}else{
				return false;
			}}
		
		return true;
	}
	
	

	

}
