package com.referadr.practice.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadBoardCertifications;
import com.referadr.practice.model.RadCodes;
import com.referadr.practice.model.RadLanguage;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.RadProviderSpeciality;
import com.referadr.practice.model.RadSpecialityVisitReasons;
import com.referadr.practice.model.RadSuffix;
import com.referadr.practice.repository.DoctorRepository;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.DateUtil;
@SuppressWarnings("unchecked")
@Repository
public class DoctorRepositoryImpl implements DoctorRepository{
@Autowired
private HibernateTemplate hibernateTemplate;
JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
JdbcTemplate jdbcTemplateObject=CommonUtils.getJdbcTemplate();
public List<RadCodes> getAllProviderRole() {
	List<RadCodes> list=null;
	//List codeList=null;
	//String sql = "SELECT CODE_ID, CODE_VALUE FROM rad_codes where code_type=prov_role";
	/*list=hibernateTemplate.find("select CODE_ID,CODE_VALUE from RadCodes");
	System.out.println("===list"+list);*/
/*	SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery("select CODE_ID,CODE_VALUE from rad_codes where CODE_TYPE="+"'prov_role'");
      codeList=query.list();
	return codeList;*/
	
	
	String fetchPractSplSQL="SELECT CODE_ID,CODE_VALUE FROM rad_codes WHERE CODE_TYPE='prov_role'";
	
	list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<RadCodes>(){

			public RadCodes mapRow(ResultSet rs, int rowNumber) throws SQLException {
				
				RadCodes radCodes=new RadCodes();  
				radCodes.setCodeId(rs.getInt("CODE_ID"));
				radCodes.setCodeValue(rs.getString("CODE_VALUE"));
    return radCodes;
}
	 });
	return list;
}
public List saveAllProviderInfo(ProviderInfo providerInfo) {
	
	  String sql="INSERT INTO rad_provider_info (PROVIDER_PRAC_ID, Provider_Email, Provider_First_Name,Provider_Last_Name,PROVIDER_CD_PRVROLE_ID,CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] {providerInfo.getPracticeInfo().getPracticeId(),providerInfo.getProviderEmail(),providerInfo.getProviderFirstName(),providerInfo.getProviderLastName(),providerInfo.getRadCodes().getCodeId(),providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(sql,params,types);
//	hibernateTemplate.saveOrUpdate(providerInfo);

	/*SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery("SELECT * FROM rad_provider_info WHERE PROVIDER_ID = (SELECT MAX(PROVIDER_ID) FROM rad_provider_info)");
      return query.list();*/
		List<ProviderInfo> list=null;
		String fetchPractSplSQL="SELECT * FROM rad_provider_info WHERE PROVIDER_ID = (SELECT MAX(PROVIDER_ID) FROM rad_provider_info)";
		
		list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<ProviderInfo>(){

				public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
					
					ProviderInfo providerInfo=new ProviderInfo();  
					providerInfo.setProviderId(rs.getInt("PROVIDER_ID"));
					providerInfo.setProviderFirstName(rs.getString("PROVIDER_FIRST_NAME"));
					providerInfo.setProviderLastName(rs.getString("PROVIDER_LAST_NAME"));
					providerInfo.setProviderEmail(rs.getString("PROVIDER_EMAIL"));
					providerInfo.setProviderCdPrvroleId(rs.getInt("PROVIDER_CD_PRVROLE_ID"));
	    return providerInfo;
	}
		 });
		return list;
	
}

public List<RadSuffix> getAllRadSuffix() {
	
	List<RadSuffix> list=null;
	/*List radsuffixlist=null;
	SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery("select SUFFIX_ID,SUFFIX_VALUE from RAD_SUFFIX ");
      radsuffixlist=query.list();
	return radsuffixlist;*/
	
	
String fetchPractSplSQL="select SUFFIX_ID,SUFFIX_VALUE from RAD_SUFFIX";
	
	list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<RadSuffix>(){

			public RadSuffix mapRow(ResultSet rs, int rowNumber) throws SQLException {
				
				RadSuffix radSuffix=new RadSuffix();  
				radSuffix.setSuffixId(rs.getInt("SUFFIX_ID"));
				radSuffix.setSuffixValue(rs.getString("SUFFIX_VALUE"));
    return radSuffix;
}
	 });
	return list;
}

public List<RadProviderSpeciality> getAllRadProviderSpeciality() {
		List<RadProviderSpeciality> list=null;
		/*List radproviderspecialitylist=null;
		SessionFactory sessionFactory = null;
		Session session = null;
		sessionFactory = hibernateTemplate.getSessionFactory();
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery("select PROV_SPL_ID,PROV_SPL_DESC from rad_provider_speciality ");
		radproviderspecialitylist=query.list();
		return radproviderspecialitylist;*/
		String fetchPractSplSQL="select PROV_SPL_ID,PROV_SPL_DESC from rad_provider_speciality ";
		list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<RadProviderSpeciality>(){
				public RadProviderSpeciality mapRow(ResultSet rs, int rowNumber) throws SQLException {
					RadProviderSpeciality radProviderSpeciality=new RadProviderSpeciality();  
					radProviderSpeciality.setProvSplId(rs.getInt("PROV_SPL_ID"));
					radProviderSpeciality.setProvSplDesc(rs.getString("PROV_SPL_DESC"));
	    return radProviderSpeciality;
	}
		 });
		return list;
}
public List<RadLanguage> getAllRadLanguage() {
	List<RadLanguage> list=null;
	/*List radLanguagelist=null;
	SessionFactory sessionFactory = null;
	Session session = null;
	sessionFactory = hibernateTemplate.getSessionFactory();
	session = sessionFactory.openSession();
	Query query = session.createSQLQuery("select LANGUAGE_ID,LANGUAGE_DESC from RAD_LANGUAGE");
	radLanguagelist=query.list();
	return radLanguagelist;*/
	String fetchPractSplSQL="select LANGUAGE_ID,LANGUAGE_DESC from RAD_LANGUAGE";
	list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<RadLanguage>(){
			public RadLanguage mapRow(ResultSet rs, int rowNumber) throws SQLException {
				RadLanguage radLanguage=new RadLanguage();  
				radLanguage.setLanguageId(rs.getInt("LANGUAGE_ID"));
				radLanguage.setLanguageDesc(rs.getString("LANGUAGE_DESC"));
    return radLanguage;
}
	 });
	return list;
}
public List<RadBoardCertifications> getAllRadBoardCertifications() {
	List<RadBoardCertifications> list=null;
	/*List radboardcertificationslist=null;
	SessionFactory sessionFactory = null;
	Session session = null;
	sessionFactory = hibernateTemplate.getSessionFactory();
	session = sessionFactory.openSession();
	Query query = session.createSQLQuery("select BOARD_CERT_ID,BOARD_CERT_DESC from RAD_BOARD_CERTIFICATIONS");
	radboardcertificationslist=query.list();
	return radboardcertificationslist;*/
String fetchPractSplSQL="select BOARD_CERT_ID,BOARD_CERT_DESC from RAD_BOARD_CERTIFICATIONS";
	list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<RadBoardCertifications>(){
			public RadBoardCertifications mapRow(ResultSet rs, int rowNumber) throws SQLException {
				RadBoardCertifications radBoardCertifications=new RadBoardCertifications();  
				radBoardCertifications.setBoardCertId(rs.getInt("BOARD_CERT_ID"));
				radBoardCertifications.setBoardCertDesc(rs.getString("BOARD_CERT_DESC"));
    return radBoardCertifications;
}
	 });
	return list;
}
public List<RadSpecialityVisitReasons> getVisitorReason(Integer radioId) {
	List<RadSpecialityVisitReasons> list=null;
	/*List visitorreasonlist=null;
	SessionFactory sessionFactory = null;
	Session session = null;
	sessionFactory = hibernateTemplate.getSessionFactory();
	session = sessionFactory.openSession();
	Query query = session.createSQLQuery("select SPL_VISIT_RSN_ID,VISIT_REASON from RAD_SPECIALITY_VISIT_REASONS WHERE PROV_SPL_ID ="+radioId);
	visitorreasonlist=query.list();
	return visitorreasonlist;*/
String fetchPractSplSQL="select SPL_VISIT_RSN_ID,VISIT_REASON from RAD_SPECIALITY_VISIT_REASONS WHERE PROV_SPL_ID ="+radioId;
	
	list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<RadSpecialityVisitReasons>(){

			public RadSpecialityVisitReasons mapRow(ResultSet rs, int rowNumber) throws SQLException {
				
				RadSpecialityVisitReasons specialityVisitReasons=new RadSpecialityVisitReasons();  
				specialityVisitReasons.setSplVisitRsnId(rs.getInt("SPL_VISIT_RSN_ID"));
				specialityVisitReasons.setVisitReason(rs.getString("VISIT_REASON"));
    return specialityVisitReasons;
}
	 });
	return list;
	}

public List findLastProvedr() {
	SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery("SELECT rpf.PROVIDER_ID, rpf.PROVIDER_FIRST_NAME,rpf.PROVIDER_LAST_NAME,PROVIDER_CD_PRVROLE_ID, rpf.PROVIDER_GENDER,rpf.PROVIDER_EMAIL FROM rad_provider_info rpf WHERE rpf.PROVIDER_ID = (SELECT MAX(PROVIDER_ID) FROM rad_provider_info)");
      return query.list();
}

public List<DoctorTimingVO> DayOffDetails(int provId){
	List<DoctorTimingVO>doctorTimingVOs=new ArrayList<DoctorTimingVO>();
	
	
	String fetchDayOffDetails="SELECT DAY_OFF_ID,PROVIDER_ID,DAY_ID,IS_OFF FROM rad_provider_day_off_map WHERE PROVIDER_ID="+provId;
	
	doctorTimingVOs= jdbcTemplate.query(fetchDayOffDetails, new RowMapper<DoctorTimingVO>(){

				public DoctorTimingVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
					
					DoctorTimingVO doctorTimingVO=new DoctorTimingVO();  
					doctorTimingVO.setDayID(rs.getInt("DAY_ID"));
					doctorTimingVO.setCheckBox(rs.getBoolean("IS_OFF"));
			
	    return doctorTimingVO;
	}
		 });
	
	return doctorTimingVOs;
}


public List<DoctorTimingVO> getProviderTiming(int provId){
	List<DoctorTimingVO>doctorTimingVOs=new ArrayList<DoctorTimingVO>();
	
String fetchPractSplSQL="SELECT TIMING_ID,PROVIDER_ID,DAY_ID,START_TIME,END_TIME,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE FROM rad_provider_availability WHERE PROVIDER_ID="+provId;
	
doctorTimingVOs= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<DoctorTimingVO>(){

			public DoctorTimingVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
				
				DoctorTimingVO doctorTimingVO=new DoctorTimingVO();  
				doctorTimingVO.setDayID(rs.getInt("DAY_ID"));
				doctorTimingVO.setStartTime(rs.getString("START_TIME"));
				doctorTimingVO.setEndTime(rs.getString("END_TIME"));
    return doctorTimingVO;
}
	 });
	
	
	return doctorTimingVOs;
}

public int SaveDoctorTiming(DoctorTimingVO doctorTimingVO){
	
	
	 
	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Date date = new Date();
	 String currentDate=dateFormat.format(date); 
	
	 String deleteProviderDayOffDetails="DELETE FROM rad_provider_day_off_map WHERE PROVIDER_ID="+doctorTimingVO.getProviderID();
     int yyy=jdbcTemplateObject.update(deleteProviderDayOffDetails); 
	 
     for(int weekday=1;weekday<=7;weekday++)
	 {
    Boolean check= doctorTimingVO.getDoctorTimingVO().get(weekday).isCheckBox();
	 
    String sql="INSERT INTO rad_provider_day_off_map(PROVIDER_ID,DAY_ID,IS_OFF,CREATION_DATE,UPDATED_DATE) VALUES(?,?,?,?,?)";
	Object[] params = new Object[] {doctorTimingVO.getProviderID(),weekday,check,currentDate,currentDate};
	int[] types = new int[] { Types.INTEGER,Types.INTEGER,Types.BOOLEAN,Types.TIMESTAMP,Types.TIMESTAMP};
	int row=jdbcTemplateObject.update(sql,params,types);
    
	 }
     
     
	 
	 String deleteProviderTiming="DELETE FROM rad_provider_availability WHERE PROVIDER_ID="+doctorTimingVO.getProviderID();
     int xxx=jdbcTemplateObject.update(deleteProviderTiming); 
     
     
     
		 for(int weekday=1;weekday<=7;weekday++)
		 {
			 
			 
	 if(!doctorTimingVO.getDoctorTimingVO().get(weekday).isCheckBox()){ 
	 String StartTime[]=doctorTimingVO.getDoctorTimingVO().get(weekday).getStartTime().split(",");
	 String EndTime[]=doctorTimingVO.getDoctorTimingVO().get(weekday).getEndTime().split(",");
	 List<DoctorTimingVO> monlist=new ArrayList<DoctorTimingVO>();
	 for(int i=0;i<StartTime.length;i++){
		 DoctorTimingVO dt=new DoctorTimingVO();
	      dt.setStartTime(StartTime[i]);
	      dt.setEndTime(EndTime[i]);
	      monlist.add(dt);
	 				}
	 for(DoctorTimingVO dto: monlist){
		  String sql="INSERT INTO rad_provider_availability(PROVIDER_ID,DAY_ID,START_TIME,END_TIME,CREATION_DATE,UPDATED_DATE) VALUES(?,?,?,?,?,?)";
			Object[] params = new Object[] {doctorTimingVO.getProviderID(),weekday,dto.getStartTime(),dto.getEndTime(),currentDate,currentDate};
			int[] types = new int[] { Types.INTEGER,Types.INTEGER, Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
			int row=jdbcTemplateObject.update(sql,params,types);
	 				}
	 				}
	 
		 }
	 
	 
	 
	return 1;
}



public boolean saveFinalProvider(ProviderInfo providerInfo) {
	if(providerInfo.getRadProviderSuffixes()!=null && providerInfo.getRadProvSpleatiy()!=null && providerInfo.getRadVisitReasons()!=null && providerInfo.getRadProvEduction()!=null){
	String boardCertifications[] = providerInfo.getBoardCertifications().split(","); 
	String radProviderSuffixes[]=providerInfo.getRadProviderSuffixes().split(",");
	String radProvSpleaties[] =providerInfo.getRadProvSpleatiy().split(",");
	String radProEducation[]=providerInfo.getRadProvEduction().split(",");
	String radprovLanguage[]=providerInfo.getRadprovLanguage().split(",");
	String radProvHospital []=providerInfo.getRadProvHospital().split(",");
	String radVisitReasons[]=providerInfo.getRadVisitReasons().split(",");
	//String radprovMember[]=providerInfo.getRadprovMember().split(",");
//	String radprovPublication[]=providerInfo.getRadprovPublication().split(",");
	SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
    /*  Query query1 = session.createSQLQuery("DELETE FROM rad_prov_board_cert_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query1.executeUpdate();*/
      
      String sqlBoard="DELETE FROM rad_prov_board_cert_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowBoard=jdbcTemplateObject.update(sqlBoard);
      
      for(String board:boardCertifications){
    	 
    /*  Query query = session.createSQLQuery("INSERT INTO rad_prov_board_cert_mapping(PROVIDER_ID,BOARD_CERT_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("++"')");
      query.executeUpdate();*/
      
      String sql="INSERT INTO rad_prov_board_cert_mapping(PROVIDER_ID,BOARD_CERT_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] {providerInfo.getProviderId(),board,providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(sql,params,types);
      }
     /* Query query2 = session.createSQLQuery("DELETE FROM rad_provider_suffix_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query2.executeUpdate();*/
      String sqlsuffix="DELETE FROM rad_provider_suffix_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowsuffix=jdbcTemplateObject.update(sqlsuffix);
      
      for(String sufixes:radProviderSuffixes){
    	 /* Query query = session.createSQLQuery("INSERT INTO rad_provider_suffix_mapping(PROVIDER_ID,SUFFIX_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("+providerInfo.getProviderId()+","+sufixes+",'"+providerInfo.getCreatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getCreationDate())+"','"+providerInfo.getUpdatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getUpdatedDate())+"')");
    	  query.executeUpdate();*/
    	  
    	  String sql="INSERT INTO rad_provider_suffix_mapping(PROVIDER_ID,SUFFIX_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE)VALUES (?, ?, ?, ?, ?, ?)";
  		Object[] params = new Object[] {providerInfo.getProviderId(),sufixes,providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
  		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
  		int row=jdbcTemplateObject.update(sql,params,types);
    	  
      }
     /* Query query3 = session.createSQLQuery("DELETE FROM rad_prov_spl_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query3.executeUpdate();*/
      String sqlspl="DELETE FROM rad_prov_spl_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowspl=jdbcTemplateObject.update(sqlspl);
      
      for(String speciality:radProvSpleaties){
    	/*  Query query = session.createSQLQuery("INSERT INTO rad_prov_spl_mapping(PROVIDER_ID,PROV_SPL_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("+providerInfo.getProviderId()+","+speciality+",'"+providerInfo.getCreatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getCreationDate())+"','"+providerInfo.getUpdatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getUpdatedDate())+"')");  
    	  query.executeUpdate();*/
    	  
    	  String sql="INSERT INTO rad_prov_spl_mapping(PROVIDER_ID,PROV_SPL_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE)VALUES (?, ?, ?, ?, ?, ?)";
    		Object[] params = new Object[] {providerInfo.getProviderId(),speciality,providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
    		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
    		int row=jdbcTemplateObject.update(sql,params,types);
    	  
      }
     /* Query query4 = session.createSQLQuery("DELETE FROM rad_provider_school WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query4.executeUpdate();*/
      
      String sqll="DELETE FROM rad_provider_school WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowww=jdbcTemplateObject.update(sqll);
      
    //  for(String education:radProEducation)
   //   {
    	  /*Query query = session.createSQLQuery("INSERT INTO rad_provider_school(PROVIDER_ID,PROV_SCHOOL,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("+providerInfo.getProviderId()+",'"+providerInfo.getRadProvEduction()+"','"+providerInfo.getCreatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getCreationDate())+"','"+providerInfo.getUpdatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getUpdatedDate())+"')");  
    	  query.executeUpdate(); */
    	  
    	  String sql="INSERT INTO rad_provider_school(PROVIDER_ID,PROV_SCHOOL,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
  		Object[] params = new Object[] {providerInfo.getProviderId(),providerInfo.getRadProvEduction(),providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
  		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
  		int row=jdbcTemplateObject.update(sql,params,types);
    	  
     // }
     /* Query query5 = session.createSQLQuery("DELETE FROM rad_provider_hospital WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query5.executeUpdate();*/
      String sqlhospital="DELETE FROM rad_provider_hospital WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowhospital=jdbcTemplateObject.update(sqlhospital);
      
    //  for(String hospital:radProvHospital){
    	 /* Query query11 = session.createSQLQuery("INSERT INTO rad_provider_hospital(PROVIDER_ID,PROVIDER_HOSP_NAME,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("+providerInfo.getProviderId()+",'"+providerInfo.getRadProvHospital()+"','"+providerInfo.getCreatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getCreationDate())+"','"+providerInfo.getUpdatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getUpdatedDate())+"')");  
    	  query11.executeUpdate();  */ 
    	  
    	  String sql1="INSERT INTO rad_provider_hospital(PROVIDER_ID,PROVIDER_HOSP_NAME,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
    		Object[] params1 = new Object[] {providerInfo.getProviderId(),providerInfo.getRadProvHospital(),providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
    		int[] types1 = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
    		int row1=jdbcTemplateObject.update(sql1,params1,types1);
    	  
  //    }
      /*Query query6 = session.createSQLQuery("DELETE FROM rad_prov_language_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query6.executeUpdate();*/
      String sqllanguage="DELETE FROM rad_prov_language_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowlanguage=jdbcTemplateObject.update(sqllanguage);
      
      for(String language:radprovLanguage){
    	/*  Query query22 = session.createSQLQuery("INSERT INTO rad_prov_language_mapping(PROVIDER_ID,LANGUAGE_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("+providerInfo.getProviderId()+","+language+",'"+providerInfo.getCreatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getCreationDate())+"','"+providerInfo.getUpdatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getUpdatedDate())+"')");
    	  query22.executeUpdate(); */ 
    	  
    	  String sql11="INSERT INTO rad_prov_language_mapping(PROVIDER_ID,LANGUAGE_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
    		Object[] params11 = new Object[] {providerInfo.getProviderId(),language,providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
    		int[] types11 = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
    		int row11=jdbcTemplateObject.update(sql11,params11,types11);
      	  
    	  
      }
     /* Query query7 = session.createSQLQuery("DELETE FROM rad_prov_visit_reason_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query7.executeUpdate();*/
      String sqlvisit="DELETE FROM rad_prov_visit_reason_mapping WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowvisit=jdbcTemplateObject.update(sqlvisit);
      
      for(String visitReason:radVisitReasons){
    	 /* Query query12 = session.createSQLQuery("INSERT INTO rad_prov_visit_reason_mapping(PROVIDER_ID,SPL_VISIT_RSN_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES("+providerInfo.getProviderId()+","+visitReason+",'"+providerInfo.getCreatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getCreationDate())+"','"+providerInfo.getUpdatedBy()+"','"+DateUtil.getYYYYMMDD(providerInfo.getUpdatedDate())+"')");
    	  query12.executeUpdate(); */ 
    	  
    	  String sql11="INSERT INTO rad_prov_visit_reason_mapping(PROVIDER_ID,SPL_VISIT_RSN_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES (?, ?, ?, ?, ?, ?)";
  		Object[] params11 = new Object[] {providerInfo.getProviderId(),visitReason,providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
  		int[] types11 = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
  		int row11=jdbcTemplateObject.update(sql11,params11,types11);
    	  
      }
     /* Query query8 = session.createSQLQuery("DELETE FROM rad_provider_membership WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query8.executeUpdate();*/
      String sqlmembership="DELETE FROM rad_provider_membership WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowmembership=jdbcTemplateObject.update(sqlmembership);
      
      String sql11="insert into rad_provider_membership ( PROVIDER_ID,PROV_MEMBERSHIP,CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE) values (?, ?, ?, ?, ?, ?)";
		Object[] params11 = new Object[] {providerInfo.getProviderId(),providerInfo.getRadProviderMemberships().get(0).getProvMembership(),providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
		int[] types11 = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row11=jdbcTemplateObject.update(sql11,params11,types11);
      
      /*Query query9 = session.createSQLQuery("DELETE FROM rad_provider_publication WHERE PROVIDER_ID="+providerInfo.getProviderId());
      query9.executeUpdate();*/
      String sqlpublication="DELETE FROM rad_provider_publication WHERE PROVIDER_ID="+providerInfo.getProviderId();
      int rowpublication=jdbcTemplateObject.update(sqlpublication);
      String sqlp="insert into rad_provider_publication ( PROVIDER_ID, PROV_PUBLICATION,CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE) values (?, ?, ?, ?, ?, ?)";
		Object[] paramsp = new Object[] {providerInfo.getProviderId(),providerInfo.getRadProviderPublications().get(0).getProvPublication(),providerInfo.getCreatedBy(),providerInfo.getCreationDate(),providerInfo.getUpdatedBy(),providerInfo.getUpdatedDate()};
		int[] typesp = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int rowp=jdbcTemplateObject.update(sqlp,paramsp,typesp);
       
		//it will not convert to jdbc template because of mapping lets try
		/*Query query = session.createSQLQuery("SELECT PROVIDER_CD_PRVROLE_ID FROM rad_provider_info WHERE PROVIDER_ID="+providerInfo.getProviderId());
	     List providerRoll = query.list();  
		Integer providerRolleId=(Integer) providerRoll.get(0);*/
		List<ProviderInfo> providerRoll=null;
		//=====================================================
		String fetchChAdminSQL="SELECT PROVIDER_CD_PRVROLE_ID FROM rad_provider_info WHERE PROVIDER_ID="+providerInfo.getProviderId();
		providerRoll= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<ProviderInfo>(){

			public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				ProviderInfo providerRoll=new ProviderInfo();
				providerRoll.setProviderCdPrvroleId(rs.getInt("PROVIDER_CD_PRVROLE_ID"));				
				return providerRoll;
			}
			
		});
		
		
		String sqlQury="update rad_provider_info set  UPDATED_DATE=?, PROVIDER_PRAC_ID=?, PROVIDER_PATIENT_CAT=?, Provider_Email=?, Provider_First_Name=?, PROVIDER_GENDER=?, Provider_Last_Name=?, Provider_Notes=?, Provider_NPI_Num=?, PROVIDER_CD_PRVROLE_ID=? where Provider_ID=?";
		Object[] paramsUp = new Object[] { providerInfo.getUpdatedDate(),providerInfo.getPracticeInfo().getPracticeId(),providerInfo.getPracticePatientCat(),providerInfo.getProviderEmail(),providerInfo.getProviderFirstName(),providerInfo.getProviderGender(),providerInfo.getProviderLastName(),providerInfo.getProviderNotes(),providerInfo.getProviderNpiNum(),providerRoll.get(0).getProviderCdPrvroleId(),providerInfo.getProviderId()};
		int[] typesUp = new int[] { Types.TIMESTAMP, Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};

		int rowUp=jdbcTemplateObject.update(sqlQury,paramsUp,typesUp);
		
      session.close();
	//hibernateTemplate.saveOrUpdate(providerInfo);
	}
	return true;
}
//21 fab
public List<ProviderInfo> getvalueProviderInfo(int provId) {
	List<ProviderInfo> providerList=null;
	SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
     /* String showEditDrInfo="SELECT rpf.PROVIDER_FIRST_NAME,rpf.PROVIDER_LAST_NAME, rpf.PROVIDER_GENDER,rpf.PROVIDER_EMAIL, " +
      		"rpf.PROVIDER_NPI_NUM,rpf.PROVIDER_NOTES, rpvrm.SPL_VISIT_RSN_ID, rpsm.PROV_SPL_ID, rph.PROVIDER_HOSP_NAME, " +
      		"rpm.PROV_MEMBERSHIP, rpp.PROV_PUBLICATION,rps.PROV_SCHOOL, rpbcm.BOARD_CERT_ID,rpsuffm.SUFFIX_ID, " +
      		"rlm.LANGUAGE_ID FROM rad_provider_info rpf " +
      		"LEFT JOIN  rad_prov_visit_reason_mapping rpvrm ON rpf.PROVIDER_ID = rpvrm.PROVIDER_ID " +
      		"LEFT JOIN  rad_prov_spl_mapping rpsm ON rpf.PROVIDER_ID = rpsm.PROVIDER_ID " +
      		"LEFT JOIN  rad_provider_hospital rph ON rpf.PROVIDER_ID = rph.PROVIDER_ID " +
      		"LEFT JOIN  rad_provider_membership rpm ON rpf.PROVIDER_ID = rpm.PROVIDER_ID " +
      		"LEFT JOIN  rad_provider_publication rpp ON rpf.PROVIDER_ID = rpp.PROVIDER_ID " +
      		"LEFT JOIN  rad_provider_school rps ON rpf.PROVIDER_ID = rps.PROVIDER_ID " +
      		"LEFT JOIN  rad_prov_board_cert_mapping rpbcm ON rpf.PROVIDER_ID = rpbcm.PROVIDER_ID " +
      		"LEFT JOIN  rad_provider_suffix_mapping rpsuffm ON rpf.PROVIDER_ID = rpsuffm.PROVIDER_ID " +
      		"LEFT JOIN  rad_prov_language_mapping rlm ON rpf.PROVIDER_ID = rlm.PROVIDER_ID";
      		"WHERE rpf.PROVIDER_ID=";*/
      Query query = session.createSQLQuery("SELECT rpf.PROVIDER_ID, rpf.PROVIDER_FIRST_NAME,rpf.PROVIDER_LAST_NAME,PROVIDER_CD_PRVROLE_ID, rpf.PROVIDER_GENDER,rpf.PROVIDER_EMAIL,rpf.PROVIDER_NPI_NUM,rpf.PROVIDER_NOTES, rpf.PROVIDER_PATIENT_CAT,rpvrm.SPL_VISIT_RSN_ID, rpsm.PROV_SPL_ID, rph.PROVIDER_HOSP_NAME,rpm.PROV_MEMBERSHIP, rpp.PROV_PUBLICATION,rps.PROV_SCHOOL, rpbcm.BOARD_CERT_ID,rpsuffm.SUFFIX_ID,rlm.LANGUAGE_ID FROM rad_provider_info rpf LEFT JOIN  rad_prov_visit_reason_mapping rpvrm ON rpf.PROVIDER_ID = rpvrm.PROVIDER_ID LEFT JOIN  rad_prov_spl_mapping rpsm ON rpf.PROVIDER_ID = rpsm.PROVIDER_ID LEFT JOIN  rad_provider_hospital rph ON rpf.PROVIDER_ID = rph.PROVIDER_ID LEFT JOIN  rad_provider_membership rpm ON rpf.PROVIDER_ID = rpm.PROVIDER_ID LEFT JOIN  rad_provider_publication rpp ON rpf.PROVIDER_ID = rpp.PROVIDER_ID LEFT JOIN  rad_provider_school rps ON rpf.PROVIDER_ID = rps.PROVIDER_ID LEFT JOIN  rad_prov_board_cert_mapping rpbcm ON rpf.PROVIDER_ID = rpbcm.PROVIDER_ID LEFT JOIN  rad_provider_suffix_mapping rpsuffm ON rpf.PROVIDER_ID = rpsuffm.PROVIDER_ID LEFT JOIN  rad_prov_language_mapping rlm ON rpf.PROVIDER_ID = rlm.PROVIDER_ID WHERE rpf.PROVIDER_ID="+provId);
      providerList=query.list();
    return providerList;
}

public List<Integer> getvalueProviderInfoSuffixId(int provId) {
	List<Integer> providerList=new ArrayList<Integer>();;
	List<ProviderInfo> provlist=new ArrayList<ProviderInfo>();
	//=====================================================
	String fetchChAdminSQL="SELECT SUFFIX_ID FROM rad_provider_suffix_mapping WHERE PROVIDER_ID ="+provId;
	provlist= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<ProviderInfo>(){

		public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
			ProviderInfo providerSuffix=new ProviderInfo();
			providerSuffix.setProviderCdPrvroleId(rs.getInt("SUFFIX_ID"));				
			return providerSuffix;
		}
	});
	for(int i=0;i<provlist.size();i++){
		providerList.add(provlist.get(i).getProviderCdPrvroleId());
	}
	
    return providerList;
}
public List<Integer> getvalueProviderVisitRsnId(int provId) {
	List<Integer> visitIdlist=new ArrayList<Integer>();
	List<ProviderInfo> visitlist=new ArrayList<ProviderInfo>();
	//=====================================================
	String fetchChAdminSQL="SELECT 	SPL_VISIT_RSN_ID FROM rad_prov_visit_reason_mapping WHERE PROVIDER_ID="+provId;
	visitlist= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<ProviderInfo>(){

		public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
			ProviderInfo providervisit=new ProviderInfo();
			providervisit.setProviderCdPrvroleId(rs.getInt("SPL_VISIT_RSN_ID"));				
			return providervisit;
		}
	});
	for(int i=0;i<visitlist.size();i++){
		visitIdlist.add(visitlist.get(i).getProviderCdPrvroleId());
	}
return visitIdlist;	
}


public List<Integer> getvalueProviderLangSpokenId(int provId) {
	List<Integer> SpokenLangIdlist=new ArrayList<Integer>();
	List<ProviderInfo> SpokenLanglist=new ArrayList<ProviderInfo>();
	//=====================================================
	String fetchChAdminSQL="SELECT LANGUAGE_ID FROM rad_prov_language_mapping WHERE PROVIDER_ID="+provId;
	SpokenLanglist= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<ProviderInfo>(){

		public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
			ProviderInfo providerlang=new ProviderInfo();
			providerlang.setProviderCdPrvroleId(rs.getInt("LANGUAGE_ID"));				
			return providerlang;
		}
	});
	for(int i=0;i<SpokenLanglist.size();i++){
		SpokenLangIdlist.add(SpokenLanglist.get(i).getProviderCdPrvroleId());
	}
return SpokenLangIdlist;	
}


public List<Integer> getvalueProviderCertificationId(int provId) {
	List<Integer> CertificationIdlist=new ArrayList<Integer>();
	List<ProviderInfo> Certificationlist=new ArrayList<ProviderInfo>();
	//=====================================================
	String fetchChAdminSQL="SELECT BOARD_CERT_ID FROM rad_prov_board_cert_mapping WHERE PROVIDER_ID="+provId;
	Certificationlist= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<ProviderInfo>(){

		public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
			ProviderInfo providerCertification=new ProviderInfo();
			providerCertification.setProviderCdPrvroleId(rs.getInt("BOARD_CERT_ID"));				
			return providerCertification;
		}
	});
	for(int i=0;i<Certificationlist.size();i++){
		CertificationIdlist.add(Certificationlist.get(i).getProviderCdPrvroleId());
	}
return CertificationIdlist;	
}

public List<ProviderInfo> getAllProviderInfo(int providerPracticeId) {
	List<ProviderInfo> providerList=null;
	/*SessionFactory sessionFactory = null;
    Session session = null;
      sessionFactory = hibernateTemplate.getSessionFactory();
      session = sessionFactory.openSession();
      Query query = session.createSQLQuery("SELECT p.PROVIDER_ID,p.PROVIDER_FIRST_NAME,p.PROVIDER_EMAIL ,r.CODE_DESC ,r.CODE_ID FROM rad_provider_info p   LEFT JOIN rad_codes r ON p.PROVIDER_CD_PRVROLE_ID=r.CODE_ID WHERE p.PROVIDER_PRAC_ID="+providerPracticeId);
      providerList=query.list();*/
	
	String fetchPractSplSQL="SELECT p.PROVIDER_ID,p.PROVIDER_FIRST_NAME,p.PROVIDER_EMAIL ,r.CODE_DESC ,r.CODE_ID FROM rad_provider_info p   LEFT JOIN rad_codes r ON p.PROVIDER_CD_PRVROLE_ID=r.CODE_ID WHERE p.PROVIDER_PRAC_ID="+providerPracticeId;
	providerList= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<ProviderInfo>(){

			public ProviderInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				ProviderInfo providerInfo=new ProviderInfo();
				RadCodes radCodes=new RadCodes();  
				providerInfo.setRadCodes(radCodes);
				
				providerInfo.setProviderId(rs.getInt("PROVIDER_ID"));
				providerInfo.setProviderFirstName(rs.getString("PROVIDER_FIRST_NAME"));
				providerInfo.setProviderEmail(rs.getString("PROVIDER_EMAIL"));
				//===RadCodes
				radCodes.setCodeDesc(rs.getString("CODE_DESC"));
				radCodes.setCodeId(rs.getInt("CODE_ID"));
			
				
    return providerInfo;
}
	 });
	return providerList;
}
/*public PracticeInfo getPracticeName() {
	
	PracticeInfo practiceName=null;
		//=====================================================
		String fetchPracticeName="SELECT 	PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID="+ProviderInfo.;
		practiceName= jdbcTemplateObject.query(fetchPracticeName, new RowMapper<PracticeInfo>(){

			public PracticeInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				PracticeInfo practiceName=new PracticeInfo();
				practiceName.setPracticeName(rs.getString("Practice_Name"));				
				return practiceName;
			}
			
		}).get(0);
	return practiceName;
}*/

public PracticeInfo getPracticeName(int practiceId) {
	PracticeInfo practiceName=new PracticeInfo();
	List<PracticeInfo> practiceInfos=new ArrayList<PracticeInfo>();
	//=====================================================
	String fetchPracticeName="SELECT 	PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID="+practiceId;
	practiceInfos= jdbcTemplateObject.query(fetchPracticeName, new RowMapper<PracticeInfo>(){

		public PracticeInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
			PracticeInfo practiceName=new PracticeInfo();
			practiceName.setPracticeName(rs.getString("Practice_Name"));				
			return practiceName;
		}
		
	});
	if(!practiceInfos.isEmpty()){
		practiceName=practiceInfos.get(0);
	}
return practiceName;
}
}
