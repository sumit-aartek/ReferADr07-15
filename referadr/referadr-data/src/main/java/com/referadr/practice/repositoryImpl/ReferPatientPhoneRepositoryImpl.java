package com.referadr.practice.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PatientInsuranceInfo;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.repository.ReferPatientPhoneRepository;
import com.referadr.practice.util.CommonUtils;

@Repository
public class ReferPatientPhoneRepositoryImpl implements ReferPatientPhoneRepository {
	JdbcTemplate jdbcTemplateObject = CommonUtils.getJdbcTemplate();
	public int saveReferAPatientDraft(PatientReferralInfo patientReferralInfo){
		
		//SELECT PRAC_SPL_ID FROM rad_practice_speciality WHERE PRAC_SPL_DESC='Spine Surgery'
		
		if(patientReferralInfo.getProviderInfo().getProviderId()==null || patientReferralInfo.getPatientInfo().getPatientId()==null || patientReferralInfo.getProviderInfo().getProviderId()==0 || patientReferralInfo.getPatientInfo().getPatientId()==0)
		{
			return 0;	
		}		
		else{
		
		String fetchSplId = "SELECT PRAC_SPL_ID FROM rad_practice_speciality WHERE PRAC_SPL_DESC='"
				+ patientReferralInfo.getPracticeSpecialty().getPraticeSplDesc()+"'";
		
		
		List<Integer>list=new ArrayList<Integer>();
		list = jdbcTemplateObject.query(fetchSplId,
				new RowMapper<Integer>() {
					public Integer mapRow(ResultSet rs,int rowNumber) throws SQLException {
					int	splId=rs.getInt("PRAC_SPL_ID");
						return splId;
					}

				});
		
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();
	String currentDate=""+dateFormat.format(date);
	
	if(list.size()!=0){
	String saveDraft = "INSERT INTO rad_referral_provider_draft (PROVIDER_ID,PATIENT_ID,SPL_ID,DRAFT_NOTES,CREATION_DATE,UPDATED_DATE)	VALUES(	?,?,?,?,?,?)";
	Object[] params = new Object[] {patientReferralInfo.getProviderInfo().getProviderId(),patientReferralInfo.getPatientInfo().getPatientId(),list.get(0),patientReferralInfo.getReferral_Provider_ActionList().get(0).getProviderNotes(),currentDate,currentDate};
	int[] types = new int[] { Types.INTEGER, Types.INTEGER ,Types.INTEGER , Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP };
	int rowCurrStatus1 = jdbcTemplateObject.update(saveDraft, params,types);
	}
		return 1;
		
		}	
	}	
	
	
	public PatientReferralInfo fetchDraftInfo(Integer draftId){
		
	String fetchSplId = "SELECT " +	 
	"rrpd.PROVIDER_ID, "+
	"rrpd.PATIENT_ID, "+
	"rrpd.SPL_ID, "+
	"rrpd.DRAFT_NOTES, "+
	"rpi.PATIENT_FIRST_NAME, "+
	"rpi.PATIENT_LAST_NAME, "+
	"rpi.PATIENT_EMAIL, "+
	"rpi.PATIENT_GENDER, "+
	"rpi.PATIENT_DOB, "+
	"rpi.PHONE_NO, "+
	"ins.INSURANCE_COMPANY, "+
	"rpins.PAT_INSURANCE_ID, "+
	"rpins.PAT_INSURANCE_GROUP, "+
	"rpins.PAT_INS_ID  "+
	"FROM "+
	"rad_referral_provider_draft rrpd "+
	"LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rrpd.PATIENT_ID "+
	"LEFT JOIN rad_patient_insurance_info rpins  ON rpins.PATIENT_ID=rrpd.PATIENT_ID "+
	"LEFT JOIN rad_insurance_info ins  ON ins.INSURANCE_ID=rpins.PAT_INS_ID "+
	"WHERE DRAFT_ID="+draftId+" AND IN_DRAFT='YES'";
		
	PatientReferralInfo patientReferralInfo=new PatientReferralInfo();
		List<PatientReferralInfo>list=new ArrayList<PatientReferralInfo>();
		list = jdbcTemplateObject.query(fetchSplId,
				new RowMapper<PatientReferralInfo>() {
					public PatientReferralInfo mapRow(ResultSet rs,int rowNumber) throws SQLException {
						PatientReferralInfo info=new PatientReferralInfo();
						PracticeSpecialty specialty=new PracticeSpecialty();
						//info=rs.getInt("PRAC_SPL_ID");
						PatientInfo patientInfo=new PatientInfo();
						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientEmail(rs.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString("PATIENT_GENDER").charAt(0));
						
						
						 String dateSample = rs.getString("PATIENT_DOB");

					      String oldFormat = "yyyy-MM-dd HH:mm:ss";
					      String newFormat = "MM/dd/yyyy";

					      SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
					      SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

					      try {
							patientInfo.setPatientDob(sdf2.format(sdf1.parse(dateSample)));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						patientInfo.setPatientPhoneNo(rs.getString("PHONE_NO"));
						info.setScheduleNotes(rs.getString("DRAFT_NOTES"));
						info.setPatientInfo(patientInfo);
						info.setProId(rs.getInt("PROVIDER_ID"));
						specialty.setPraticeSplID(rs.getInt("SPL_ID"));
						info.setPracticeSpecialty(specialty);
						PatientInsuranceInfo insuranceInfo=new PatientInsuranceInfo();
						insuranceInfo.setPatientInsuranceGroup(rs.getString("PAT_INSURANCE_GROUP"));
						insuranceInfo.setPatientInsuranceId(rs.getString("PAT_INSURANCE_ID"));
						insuranceInfo.setPatientInsuranceNotes(rs.getString("INSURANCE_COMPANY"));
						
						List<PatientInsuranceInfo> patientInsuranceInfoList=new ArrayList<PatientInsuranceInfo>();
						patientInsuranceInfoList.add(insuranceInfo);
						patientInfo.setPatientInsuranceInfoList(patientInsuranceInfoList);
						
						return info;
					}

				});
		
		
		if(list.size()>0){
		patientReferralInfo=list.get(0);
		}
		return patientReferralInfo;
			
	}
	
	
}
