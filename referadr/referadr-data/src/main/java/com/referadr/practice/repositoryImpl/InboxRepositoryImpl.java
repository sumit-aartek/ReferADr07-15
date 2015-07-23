package com.referadr.practice.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.PateintAndInsuranceInfoVO;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeLocations;
import com.referadr.practice.model.PrintVO;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.ReferralProviderActionVO;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.model.RefferalCurrentStatus;
import com.referadr.practice.model.ScheduleVO;
import com.referadr.practice.repository.InboxRepository;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.ReferadrUtils;

@SuppressWarnings("unchecked")
@Repository
public class InboxRepositoryImpl implements InboxRepository {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	JdbcTemplate jdbcTemplateObject = CommonUtils.getJdbcTemplate();
	JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();

	public List<PateintInfoVO> getInboxValue(int providePracticeId) {
		List<RefferalCurrentStatus> referralInfoList = null;
		List<PateintInfoVO> pateintInfoVOList = new ArrayList<PateintInfoVO>();
		List<PateintInfoVO> pateintInfoVOList1 = null;
		String sql1 = "SELECT REFERRAL_ID FROM rad_refferal_current_status WHERE PRACTICE_ID="
				+ providePracticeId;

		pateintInfoVOList1 = jdbcTemplate.query(sql1,
				new RowMapper<PateintInfoVO>() {
					public PateintInfoVO mapRow(ResultSet rs1, int rowNumber)
							throws SQLException {
						PateintInfoVO pateintInfoVO = new PateintInfoVO();
						pateintInfoVO.setRefId(rs1.getInt("REFERRAL_ID"));
						return pateintInfoVO;
					}
				});

		if (pateintInfoVOList1.size() != 0) {
			for (int i = 0; i < pateintInfoVOList1.size(); i++) {
				List<PateintInfoVO> pateintInfoVOList2 = null;
				List<PateintInfoVO> pateintInfoVOList3 = null;
				int refid = pateintInfoVOList1.get(i).getRefId();
				String sql2 = "SELECT rrcs.REF_CURR_STS_ID,rrcs.UPDATED_DATE, rprii.PATIENT_REFERRAL_ID,rrpa.REF_CH_ACTION_ID,rrpa.REF_PROVIDER_ID,rrpa.REF_CH_NOTES,rpi.CH_ADMIN_ROLE_FIRST_NAME ,rpri.CN_NAME ,rrpa.CREATION_DATE, rp.PATIENT_FIRST_NAME,rp.PATIENT_LAST_NAME, rp.PATIENT_ID "
						+ "FROM rad_referral_ch_action rrpa "
						+ "LEFT JOIN rad_patient_referral_info rprii ON  rprii.PATIENT_REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_patient_info rp ON rp.PATIENT_ID =rprii.PATIENT_ID "
						+ "LEFT JOIN rad_refferal_current_status rrcs ON  rrcs.REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_ch_admin rpi ON rpi.CH_ADMIN_ID=rrpa.REF_CH_ADMIN_ID "
						+ "LEFT JOIN rad_ch_info rpri ON rpri.CH_ID=rpi.CH_ADMIN_CH_ID "
						+ "WHERE rrpa.REFERRAL_ID = "
						+ refid
						+ "  ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";

				String sql3 = "SELECT rrcs.REF_CURR_STS_ID,rrcs.UPDATED_DATE,rprii.PATIENT_REFERRAL_ID, rrpa.REF_PROV_ACTION_ID,rrpa.REF_PROVIDER_ID, rrpa.REF_PROV_REF_REASON,rrpa.REF_PROV_DIAG_CODE,rrpa.REF_PROV_NOTES,rpi.PROVIDER_FIRST_NAME ,rpri.PRACTICE_NAME ,rrpa.CREATION_DATE, rp.PATIENT_FIRST_NAME,rp.PATIENT_LAST_NAME, rp.PATIENT_ID "
						+ "FROM rad_referral_provider_action rrpa "
						+ "LEFT JOIN rad_patient_referral_info rprii ON  rprii.PATIENT_REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_patient_info rp ON rp.PATIENT_ID =rprii.PATIENT_ID "
						+ "LEFT JOIN rad_refferal_current_status rrcs ON  rrcs.REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_provider_info rpi ON rpi.PROVIDER_ID=rrpa.REF_PROVIDER_ID "
						+ "LEFT JOIN rad_practice_info rpri ON rpri.PRACTICE_ID=rpi.PROVIDER_PRAC_ID "
						+ "WHERE rrpa.REFERRAL_ID="
						+ refid
						+ " ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";
				pateintInfoVOList2 = jdbcTemplate.query(sql2,
						new RowMapper<PateintInfoVO>() {
							public PateintInfoVO mapRow(ResultSet rs2,
									int rowNumber) throws SQLException {
								PateintInfoVO pateintInfoVO = new PateintInfoVO();
								String date=rs2.getString("CREATION_DATE").substring(0, 10);
								pateintInfoVO.setCreationDate(date);
								pateintInfoVO.setPateintFirstName(rs2
										.getString("PATIENT_FIRST_NAME"));
								pateintInfoVO.setPateintLastName(rs2.getString("PATIENT_LAST_NAME"));
								pateintInfoVO.setPatientId(rs2
										.getInt("PATIENT_ID"));
								pateintInfoVO.setPracticeName(rs2
										.getString("CN_NAME"));
								pateintInfoVO.setProNotes(rs2
										.getString("REF_CH_NOTES"));
								pateintInfoVO.setRefId(rs2
										.getInt("PATIENT_REFERRAL_ID"));
								pateintInfoVO.setCurrentStatusId(rs2
										.getInt("REF_CURR_STS_ID"));
								pateintInfoVO.setProvRefActionId(rs2
										.getInt("REF_CH_ACTION_ID"));
								pateintInfoVO.setProviderId(rs2
										.getInt("REF_PROVIDER_ID"));

								return pateintInfoVO;
							}
						});
				pateintInfoVOList3 = jdbcTemplate.query(sql3,
						new RowMapper<PateintInfoVO>() {
							public PateintInfoVO mapRow(ResultSet rs3,
									int rowNumber) throws SQLException {
								PateintInfoVO pateintInfoVO = new PateintInfoVO();
								
								String date=rs3.getString("CREATION_DATE").substring(0, 10);
								pateintInfoVO.setCreationDate(date);
								pateintInfoVO.setPateintFirstName(rs3
										.getString("PATIENT_FIRST_NAME"));
								pateintInfoVO.setPateintLastName(rs3.getString("PATIENT_LAST_NAME"));
								pateintInfoVO.setPatientId(rs3
										.getInt("PATIENT_ID"));
								pateintInfoVO.setPracticeName(rs3
										.getString("PRACTICE_NAME"));
								pateintInfoVO.setProNotes(rs3
										.getString("REF_PROV_NOTES"));
								pateintInfoVO.setRefId(rs3
										.getInt("PATIENT_REFERRAL_ID"));
								pateintInfoVO.setCurrentStatusId(rs3
										.getInt("REF_CURR_STS_ID"));
								pateintInfoVO.setProvRefActionId(rs3
										.getInt("REF_PROV_ACTION_ID"));
								pateintInfoVO.setProviderId(rs3
										.getInt("REF_PROVIDER_ID"));
								return pateintInfoVO;
							}
						});
				pateintInfoVOList2.addAll(pateintInfoVOList3);
				if (pateintInfoVOList2.size() != 0) {
					Collections.sort(pateintInfoVOList2);
					pateintInfoVOList.add(pateintInfoVOList2.get(0));
				}
			}
		}
		if (pateintInfoVOList.size() != 0) {
			Collections.sort(pateintInfoVOList);
		}
		return pateintInfoVOList;
	}
	
	
	public List<PateintInfoVO> getSendboxValue(int providePracticeId){
		List<PateintInfoVO> pateintInfoVOs= new ArrayList<PateintInfoVO>();
		
		List<PateintInfoVO> pateintInfoVOs1= new ArrayList<PateintInfoVO>();
		String sql2="SELECT rrrt.REF_REC_TRACK_ID,"+ 
				" rrrt.REFERRAL_ID, "+ 
				" rrrt.REF_CD_REFACTION_ID,"+ 
				" rrrt.CREATION_DATE,"+ 
				" rpi.PRACTICE_NAME,"+ 
				" rpat.PATIENT_FIRST_NAME,"+ 
				" rpat.PATIENT_LAST_NAME,"+ 
				" rrpa.REF_PROV_NOTES"+ 
				" FROM "+ 
				" rad_referral_rec_tracking rrrt"+ 
				" LEFT JOIN rad_referral_provider_action rrpa ON rrpa.REF_PROV_ACTION_ID=rrrt.REF_CD_REFACTION_ID"+ 
				" LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID=rrrt.REFERRAL_ID"+ 
				" LEFT JOIN rad_practice_info rpi ON rpi.PRACTICE_ID=rpri.PRACTICE_ID"+ 
				" LEFT JOIN rad_patient_info rpat ON rpat.PATIENT_ID=rpri.PATIENT_ID"+ 
				" WHERE rrrt.PRACTICE_ID="+providePracticeId;
		pateintInfoVOs = jdbcTemplate.query(sql2,
				new RowMapper<PateintInfoVO>() {
					public PateintInfoVO mapRow(ResultSet rs2,
							int rowNumber) throws SQLException {
						PateintInfoVO pateintInfoVO = new PateintInfoVO();
						String date=rs2.getString("CREATION_DATE").substring(0, 10);
						pateintInfoVO.setCreationDate(date);
						pateintInfoVO.setPateintFirstName(rs2.getString("PATIENT_FIRST_NAME"));
						pateintInfoVO.setPateintLastName(rs2.getString("PATIENT_LAST_NAME"));
						pateintInfoVO.setPracticeName(rs2.getString("PRACTICE_NAME"));
						pateintInfoVO.setProNotes(rs2.getString("REF_PROV_NOTES"));
						return pateintInfoVO;
					}
				});
		
		      String labSql="SELECT rlra.LAB_REF_NOTES, rlra.CREATION_DATE,rpi.PATIENT_FIRST_NAME,rpi.PATIENT_LAST_NAME,rli.LAB_DESC"
		    		  		+" FROM rad_lab_referral_action rlra" 
		    		  		+" LEFT JOIN rad_lab_patient_referral_info rlpri ON rlpri.LAB_REFERRAL_ID=rlra.LAB_REFERRAL_ID"
		    		  		+" LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID= rlpri.PATIENT_ID"
                            +" LEFT JOIN rad_lab_info rli ON rli.LAB_ID=rlpri.LAB_ID"
                            +" WHERE rlra.LAB_REF_PROVIDER_ID IN(SELECT PROVIDER_ID FROM rad_provider_info WHERE PROVIDER_PRAC_ID="+providePracticeId+") ORDER BY rlra.CREATION_DATE DESC ";
		
		pateintInfoVOs1 = jdbcTemplate.query(labSql,new RowMapper<PateintInfoVO>() {
					public PateintInfoVO mapRow(ResultSet rs2,
							int rowNumber) throws SQLException {
						PateintInfoVO pateintInfoVO = new PateintInfoVO();
						String date=rs2.getString("CREATION_DATE").substring(0, 10);
						pateintInfoVO.setCreationDate(date);
						pateintInfoVO.setPateintFirstName(rs2.getString("PATIENT_FIRST_NAME"));
						pateintInfoVO.setPateintLastName(rs2.getString("PATIENT_LAST_NAME"));
						pateintInfoVO.setPracticeName(rs2.getString("LAB_DESC"));
						pateintInfoVO.setProNotes(rs2.getString("LAB_REF_NOTES"));
						return pateintInfoVO;
					}
				});
		if(!pateintInfoVOs1.isEmpty()){
		pateintInfoVOs.addAll(pateintInfoVOs1);
		Collections.sort(pateintInfoVOs);
		}
		return pateintInfoVOs;
	}
	

	public PateintAndInsuranceInfoVO getPatientInformation(
			Integer currentStatusId) {
		PateintAndInsuranceInfoVO patientList = null;
		List<PateintAndInsuranceInfoVO> pateintInfoVOList1 = null;

		/*
		 * String sql1 =
		 * "SELECT rpri.PATIENT_REFERRAL_ID,rpin.PAT_INS_ID,rl.LOC_PHONE,rl.LOC_STATE_ID,rl.LOC_ADDRESS1,rl.LOC_ADDRESS2,rl.LOC_CITY,rl.LOC_FAX,rl.LOC_PHONE,rl.LOC_ZIP,rpi.PATIENT_FIRST_NAME,rpi.PATIENT_LAST_NAME,rpi.PATIENT_EMAIL,rpi.PATIENT_DOB,rpi.PATIENT_GENDER,rpi.PATIENT_ID,rpi.PATIENT_LOC_ID,rpi.PATIENT_SSN,rpin.PAT_INS_INFO_ID,rpin.PAT_INSURANCE_ID,rpin.PAT_INSURANCE_GROUP,PAT_PREAUTH_REQ,rpin.PAT_PREAUTH_ID,rpin.PAT_INSURANCE_PHONE,rpin.PAT_PREAUTH_START_DATE,rpin.PAT_INSURANCE_NOTES,rpin.PAT_PREAUTH_CONTACT_NAME,rpin.PAT_PREAUTH_END_DATE,rpin.PAT_PREAUTH_CONFIRMATIOn, rii.INSURANCE_COMPANY   "
		 * +
		 * "FROM rad_location rl LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_LOC_ID= rl.LOC_ID "
		 * +
		 * "LEFT JOIN rad_patient_insurance_info rpin ON rpin.PATIENT_ID=rpi.PATIENT_ID "
		 * +
		 * "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID = rpin.PAT_INS_ID "
		 * +
		 * "LEFT JOIN rad_patient_referral_info rpri ON  rpri.PATIENT_ID=rpi.PATIENT_ID "
		 * +
		 * " WHERE rl.LOC_ID IN(SELECT rpi.PATIENT_LOC_ID FROM rad_patient_info rpi WHERE rpi.PATIENT_ID  =(SELECT rpri.PATIENT_ID FROM rad_patient_referral_info rpri WHERE rpri.PATIENT_REFERRAL_ID ="
		 * + currentStatusId + "))";
		 */

		String sql1 = "SELECT rpri.PATIENT_REFERRAL_ID,rpin.PAT_INS_ID,rl.LOC_PHONE,rl.LOC_STATE_ID,rl.LOC_ADDRESS1, "
				+ "rl.LOC_ADDRESS2,rl.LOC_CITY,rl.LOC_FAX,rl.LOC_PHONE,rl.LOC_ZIP,rpi.PATIENT_FIRST_NAME, "
				+ "rpi.PATIENT_LAST_NAME,rpi.PATIENT_EMAIL,rpi.PATIENT_DOB,rpi.PATIENT_GENDER,rpi.PATIENT_ID, "
				+ "rpi.PATIENT_LOC_ID,rpi.PATIENT_SSN,rpi.PHONE_NO,rpin.PAT_INS_INFO_ID,rpin.PAT_INSURANCE_ID, "
				+ "rpin.PAT_INSURANCE_GROUP,rpin.PAT_PREAUTH_REQ,rpin.PAT_PREAUTH_ID,rpin.PAT_INSURANCE_PHONE, "
				+ "rpin.PAT_PREAUTH_START_DATE,rpin.PAT_INSURANCE_NOTES,rpin.PAT_PREAUTH_CONTACT_NAME, "
				+ "rpin.PAT_PREAUTH_END_DATE,rpin.PAT_PREAUTH_CONFIRMATIOn, rii.INSURANCE_COMPANY "
				+ "FROM rad_patient_referral_info rpri 	LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID=rpi.PATIENT_LOC_ID LEFT JOIN rad_patient_insurance_info rpin ON rpin.PATIENT_ID=rpri.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpin.PAT_INS_ID WHERE PATIENT_REFERRAL_ID ="
				+ currentStatusId
				+ " ORDER BY rpin.CREATION_DATE DESC LIMIT 1 ";

		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
List<PateintAndInsuranceInfoVO> pateintAndInsuranceInfoVOs=new ArrayList<PateintAndInsuranceInfoVO>();
		PateintAndInsuranceInfoVO pateintInfoVO1=new PateintAndInsuranceInfoVO();
		pateintAndInsuranceInfoVOs= jdbcTemplate.query(sql1,
				new RowMapper<PateintAndInsuranceInfoVO>() {

					public PateintAndInsuranceInfoVO mapRow(ResultSet rs,
							int rowNumber) throws SQLException {

						PateintAndInsuranceInfoVO pateintInfoVO1 = new PateintAndInsuranceInfoVO();

						pateintInfoVO1.setPateintFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						pateintInfoVO1.setPateintLastName(rs
								.getString("PATIENT_LAST_NAME"));
						pateintInfoVO1.setPateintEmail(rs
								.getString("PATIENT_EMAIL"));
						pateintInfoVO1.setInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						pateintInfoVO1.setLocAddress1(rs
								.getString("LOC_ADDRESS1"));
						pateintInfoVO1.setLocPhone(rs.getString("PHONE_NO"));
						pateintInfoVO1.setLocAddress2(rs
								.getString("LOC_ADDRESS2"));
						pateintInfoVO1.setLocCity(rs.getString("LOC_CITY"));
						pateintInfoVO1.setLocFax(rs.getString("LOC_FAX"));
						pateintInfoVO1.setLocId(rs.getInt("PATIENT_LOC_ID"));
						pateintInfoVO1.setLocZip(rs.getString("LOC_ZIP"));
						pateintInfoVO1.setPatientDob(rs
								.getString("PATIENT_DOB"));
						pateintInfoVO1.setPatientGender(rs
								.getString("PATIENT_GENDER"));
						pateintInfoVO1.setPatientId(rs.getInt("Patient_ID"));
						pateintInfoVO1.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						pateintInfoVO1.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						pateintInfoVO1.setPatientInsuranceNotes(rs
								.getString("PAT_INSURANCE_NOTES"));
						pateintInfoVO1.setPatientInsurancePhone(rs
								.getString("PAT_INSURANCE_PHONE"));
						pateintInfoVO1.setPatientPre1AuthReq(rs
								.getString("PAT_PREAUTH_REQ"));
						pateintInfoVO1.setPatientPreauthConfirmation(rs
								.getString("PAT_PREAUTH_CONFIRMATIOn"));
						pateintInfoVO1.setPatientPreAuthContactName(rs
								.getString("PAT_PREAUTH_CONTACT_NAME"));
						pateintInfoVO1.setPatientPreAuthEndDate(rs
								.getDate("PAT_PREAUTH_END_DATE"));
						pateintInfoVO1.setPatientPreAuthId(rs
								.getString("PAT_PREAUTH_ID"));
						pateintInfoVO1.setPatientPreAuthStartDate(rs
								.getDate("PAT_PREAUTH_START_DATE"));
						pateintInfoVO1.setPatientSSN(rs
								.getString("PATIENT_SSN"));
						pateintInfoVO1.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						pateintInfoVO1.setRefId(rs
								.getInt("PATIENT_REFERRAL_ID"));
						pateintInfoVO1.setStateId(rs.getInt("LOC_STATE_ID"));

						return pateintInfoVO1;
					}
				});
		
		if(pateintAndInsuranceInfoVOs.size()!=0)
		{
			pateintInfoVO1=pateintAndInsuranceInfoVOs.get(0);
		}
		
		
		return pateintInfoVO1;
	}

	public List<ReferralProviderActionVO> getReferPatientList(int refId) {

		List<ReferralProviderActionVO> referralProviderActionVOList = null;
		List<ReferralProviderActionVO> referralProviderActionVOList1 = null;
		List<PatientReferralInfo> infos = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String sql = "SELECT rrpa.REF_PROV_ACTION_ID,"
				+ "rrpa.REF_PROV_REF_REASON,"
				+ "rrpa.REF_PROV_DIAG_CODE,"
				+ "rrpa.REF_PROV_NOTES,"
				+ "rpi.PROVIDER_LAST_NAME ,"
				+ "rpri.PRACTICE_NAME ,"
				+ "rrpa.CREATION_DATE ,"
				+ "rrpa.SCHEDULE_ID,"
				+ "rrps.PROV_ID,"
				+ "rpiii.PROVIDER_LAST_NAME AS WITH_PROVIDER_LAST_NAME,"
				+ "rpriii.PRACTICE_NAME AS WITH_PRACTICE_NAME,"
				+ "rrps.SHEDULE_DATE,rrps.SHEDULE_TIME "
				+ "FROM rad_referral_provider_action rrpa "
				+ "LEFT JOIN rad_provider_info rpi ON rpi.PROVIDER_ID=rrpa.REF_PROVIDER_ID "
				+ "LEFT JOIN rad_practice_info rpri ON rpri.PRACTICE_ID=rpi.PROVIDER_PRAC_ID  "
				+ "LEFT JOIN rad_referral_provider_shedule rrps ON rrps.SHEDULE_ID=rrpa.SCHEDULE_ID "
				+ "LEFT JOIN rad_provider_info rpiii ON rpiii.PROVIDER_ID=rrps.PROV_ID "
				+ "LEFT JOIN rad_practice_info rpriii ON rpriii.PRACTICE_ID=rpiii.PROVIDER_PRAC_ID "
				+ "WHERE rrpa.REFERRAL_ID=" + refId
				+ " ORDER BY CREATION_DATE DESC";

		String sql1 = "SELECT rrpa.REF_CH_ACTION_ID,rrpa.REF_CH_NOTES,rpi.CH_ADMIN_ROLE_FIRST_NAME ,rpri.CN_NAME ,rrpa.CREATION_DATE	"
				+ "FROM rad_referral_ch_action rrpa "
				+ "LEFT JOIN rad_ch_admin rpi ON rpi.CH_ADMIN_ID=rrpa.REF_CH_ADMIN_ID "
				+ "LEFT JOIN rad_ch_info rpri ON rpri.CH_ID=rpi.CH_ADMIN_CH_ID"
				+ " WHERE rrpa.REFERRAL_ID="
				+ refId
				+ " ORDER BY CREATION_DATE DESC";

		referralProviderActionVOList = jdbcTemplate.query(sql,
				new RowMapper<ReferralProviderActionVO>() {

					public ReferralProviderActionVO mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						ReferralProviderActionVO referralProviderActionVO = new ReferralProviderActionVO();
						referralProviderActionVO.setCreationDate(rs
								.getString("CREATION_DATE"));
						referralProviderActionVO.setPracticeName(rs
								.getString("PRACTICE_NAME"));
						referralProviderActionVO.setProviderName("Dr."
								+ rs.getString("PROVIDER_LAST_NAME"));
						referralProviderActionVO.setRefDigCode(rs
								.getString("REF_PROV_DIAG_CODE"));
						referralProviderActionVO.setRefNotes(rs
								.getString("REF_PROV_NOTES"));
						referralProviderActionVO.setRefReason(rs
								.getString("REF_PROV_REF_REASON"));
						referralProviderActionVO.setRefProActionId(rs
								.getInt("REF_PROV_ACTION_ID"));
						referralProviderActionVO.setFlag(true);
						if (rs.getInt("SCHEDULE_ID") != 0) {
							referralProviderActionVO.setProviderName("Dr."
									+ rs.getString("WITH_PROVIDER_LAST_NAME"));
							referralProviderActionVO
									.setRefReason("Scheduled at: "
											+ rs.getString("SHEDULE_DATE")
											+ " "
											+ rs.getString("SHEDULE_TIME"));
							referralProviderActionVO.setPracticeName(rs
									.getString("WITH_PRACTICE_NAME"));

						}

						return referralProviderActionVO;
					}
				});

		referralProviderActionVOList1 = jdbcTemplate.query(sql1,
				new RowMapper<ReferralProviderActionVO>() {

					public ReferralProviderActionVO mapRow(ResultSet rs1,
							int rowNumber) throws SQLException {
						ReferralProviderActionVO referralProviderActionVO = new ReferralProviderActionVO();
						referralProviderActionVO.setCreationDate(rs1
								.getString("CREATION_DATE"));
						referralProviderActionVO.setPracticeName(rs1
								.getString("CN_NAME"));
						referralProviderActionVO.setProviderName(rs1
								.getString("CH_ADMIN_ROLE_FIRST_NAME"));
						// referralProviderActionVO.setRefDigCode(rs1.getString("REF_PROV_DIAG_CODE"));
						referralProviderActionVO.setRefNotes(rs1
								.getString("REF_CH_NOTES"));
						referralProviderActionVO.setFlag(false);
						referralProviderActionVO.setRefProActionId(rs1
								.getInt("REF_CH_ACTION_ID"));

						return referralProviderActionVO;
					}
				});

		referralProviderActionVOList.addAll(referralProviderActionVOList1);
		Collections.sort(referralProviderActionVOList);

		return referralProviderActionVOList;
	}

	public boolean updateRefferInformation(
			PatientReferralInfo patientReferralInfo, Integer providePracticeId,

			String fromEmail, int fromProviderId, Integer loggedInProvideId) {

		int provideIdLast = patientReferralInfo.getProviderInfo()
				.getProviderId();
		PatientReferralInfo maxInsId = new PatientReferralInfo();
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		// =====================================================
		String fetchProviderId = "SELECT PROVIDER_ID FROM rad_patient_referral_info WHERE PATIENT_REFERRAL_ID="
				+ patientReferralInfo.getRefId();
		patientReferralInfos = jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<PatientReferralInfo>() {

					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo maxLInsuraId = new PatientReferralInfo();
						maxLInsuraId.setProId(rs.getInt("PROVIDER_ID"));
						return maxLInsuraId;
					}

				});
		
		if(patientReferralInfos.size()!=0){
			
			maxInsId=patientReferralInfos.get(0);
		}
		
		

		Integer practiceId = null;
		if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
			practiceId = patientReferralInfo.getPracticeInfo().getPracticeId();
		}
		Integer providerId1 = null;
		if (patientReferralInfo.getProviderInfo().getProviderId() != 0) {
			providerId1 = patientReferralInfo.getProviderInfo().getProviderId();
		}
		String sqlQury = "UPDATE rad_patient_referral_info SET	CH_ID = ? , PRAC_SPL_ID = ? ,PRACTICE_ID = ? ,PROVIDER_ID = ? WHERE	 PATIENT_REFERRAL_ID = ? ";

		Object[] params1 = new Object[] {
				patientReferralInfo.getChInfo().getId(),
				patientReferralInfo.getPracticeSpecialty().getPraticeSplID(),
				practiceId, providerId1, patientReferralInfo.getRefId() };

		int[] types1 = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.INTEGER };

		int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);
		// =====rad_referral_provider_action
		Integer providerId = null;
		if (maxInsId.getProId() != 0) {
			providerId = maxInsId.getProId();
		} else {
			providerId = patientReferralInfo.getProId();
		}

		String sqlQury1 = "INSERT INTO rad_referral_provider_action(REF_PROV_REF_REASON, REF_PROV_DIAG_CODE,REF_PROV_NOTES, REF_PROVIDER_ID,REFERRAL_ID, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE)VALUES( ?,?,?,?,?,?,?,?,?)";
		Object[] params11 = new Object[] {
				patientReferralInfo.getReferral_Provider_ActionList().get(0)
						.getProviderRefReasons(),
				patientReferralInfo.getReferral_Provider_ActionList().get(0)
						.getProviderDiagCode(),
				patientReferralInfo.getReferral_Provider_ActionList().get(0)
						.getProviderNotes(), providerId,
				patientReferralInfo.getRefId(),
				patientReferralInfo.getCreatedBy(),
				patientReferralInfo.getCreationDate(),
				patientReferralInfo.getUpdatedBy(),
				patientReferralInfo.getUpdatedDate() };
		int[] types11 = new int[] { Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
				Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP, };
		int row11 = jdbcTemplateObject.update(sqlQury1, params11, types11);

		// =======update curreststatus tavle=====
		if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
			String sqlQury11 = "UPDATE rad_refferal_current_status SET PRACTICE_ID = ? ,  UPDATED_BY = ? , 	UPDATED_DATE = ? WHERE	REFERRAL_ID = ? ";

			Object[] params111 = new Object[] {
					patientReferralInfo.getPracticeInfo().getPracticeId(),
					patientReferralInfo.getUpdatedBy(),
					patientReferralInfo.getUpdatedDate(),
					patientReferralInfo.getRefId() };

			int[] types111 = new int[] { Types.INTEGER, Types.VARCHAR,
					Types.TIMESTAMP, Types.INTEGER };

			int row111 = jdbcTemplateObject.update(sqlQury11, params111,
					types111);
		} else {
			String sqlQury11 = "UPDATE rad_refferal_current_status SET CH_ID = ? ,PRACTICE_ID = ?,  UPDATED_BY = ? , 	UPDATED_DATE = ? WHERE	REFERRAL_ID = ? ";

			Object[] params111 = new Object[] {
					patientReferralInfo.getChInfo().getId(), null,
					patientReferralInfo.getUpdatedBy(),
					patientReferralInfo.getUpdatedDate(),
					patientReferralInfo.getRefId() };

			int[] types111 = new int[] { Types.INTEGER, Types.INTEGER,
					Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };

			int row111 = jdbcTemplateObject.update(sqlQury11, params111,
					types111);
		}

		List<Referral_Provider_Action> maxRefProvActId = null;
		String fetchMaxrefIdSQL = "SELECT REF_PROV_ACTION_ID FROM rad_referral_provider_action WHERE REF_PROV_ACTION_ID = (SELECT MAX(REF_PROV_ACTION_ID) FROM rad_referral_provider_action)";
		maxRefProvActId = jdbcTemplate.query(fetchMaxrefIdSQL,
				new RowMapper<Referral_Provider_Action>() {

					public Referral_Provider_Action mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						Referral_Provider_Action maxPatientRefId = new Referral_Provider_Action();
						maxPatientRefId.setRefProviderActionId(rs
								.getInt("REF_PROV_ACTION_ID"));
						return maxPatientRefId;
					}
				});

		String rpo11 = "INSERT INTO rad_referral_rec_tracking ( REFERRAL_ID, REF_PROVIDER_ID, REF_CD_REFACTION_ID, REF_PAT_REC_ACTN_TIMESTAMP,    CREATED_BY, CREATION_DATE,UPDATED_BY, UPDATED_DATE,CH_ID,PRACTICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] paramsrpo11 = new Object[] { patientReferralInfo.getRefId(),
				loggedInProvideId,
				maxRefProvActId.get(0).getRefProviderActionId(),
				patientReferralInfo.getUpdatedDate(),
				patientReferralInfo.getCreatedBy(),
				patientReferralInfo.getCreationDate(),
				patientReferralInfo.getUpdatedBy(),
				patientReferralInfo.getUpdatedDate(), null, providePracticeId };
		int[] typesrpo11 = new int[] { Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,
				Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER };
		int rowCurrStatus11 = jdbcTemplateObject.update(rpo11, paramsrpo11,
				typesrpo11);

		try {
			Integer iFromPractice = providePracticeId;
			if (iFromPractice == 0) {
				iFromPractice = null;
			}
			Integer iToPractice = patientReferralInfo.getPracticeInfo()
					.getPracticeId();
			if (iToPractice == 0) {
				iToPractice = null;
			}
			Integer iTospl = patientReferralInfo.getPracticeSpecialty()
					.getPraticeSplID();
			if (iTospl == 0) {
				iTospl = null;
			}
			Integer iFromprovider = patientReferralInfo.getProId();
			if (iFromprovider == 0) {
				iFromprovider = providerId;
			}
			Integer iToprovider = patientReferralInfo.getProviderInfo()
					.getProviderId();
			if (iToprovider == 0) {
				iToprovider = null;
			}
			Integer iToCH = patientReferralInfo.getChInfo().getId();
			if (iToCH == 0) {
				iToCH = null;
			}
			String iCreatedBy = patientReferralInfo.getCreatedBy();
			String strUpdatedBy = patientReferralInfo.getUpdatedBy();

			// //////////for report table

			String rpo = "INSERT INTO report (FROM_PROVIDER_ID,TO_PROVIDER_ID,TO_SPL_ID,FROM_PRACTICE_ID,TO_PRACTICE_ID,FROM_CH_ID,TO_CH_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE,REFFERAL_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] paramsrpo = new Object[] { iFromprovider, iToprovider,
					iTospl, iFromPractice, iToPractice, null, iToCH,
					iCreatedBy, patientReferralInfo.getCreationDate(),
					strUpdatedBy, patientReferralInfo.getUpdatedDate(),
					patientReferralInfo.getRefId() };
			int[] typesrpo = new int[] { Types.INTEGER, Types.INTEGER,
					Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
					Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,
					Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
			int rowCurrStatus = jdbcTemplateObject.update(rpo, paramsrpo,
					typesrpo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String lastName = null;
			List<ProviderInfo> emailIdlist = null;
			String fetchMaxrefIdSQL11 = "SELECT PROVIDER_EMAIL,PROVIDER_FIRST_NAME,PROVIDER_LAST_NAME FROM rad_provider_info WHERE PROVIDER_PRAC_ID= "
					+ patientReferralInfo.getPracticeInfo().getPracticeId()
					+ " AND (PROVIDER_CD_PRVROLE_ID=477 OR PROVIDER_CD_PRVROLE_ID=478)";
			emailIdlist = jdbcTemplateObject.query(fetchMaxrefIdSQL11,
					new RowMapper<ProviderInfo>() {

						public ProviderInfo mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							ProviderInfo emailIdlist = new ProviderInfo();
							emailIdlist.setProviderEmail(rs
									.getString("PROVIDER_EMAIL"));
							emailIdlist.setProviderFirstName(rs
									.getString("PROVIDER_FIRST_NAME"));
							emailIdlist.setProviderLastName(rs
									.getString("PROVIDER_LAST_NAME"));
							return emailIdlist;
						}
					});
			if (provideIdLast != 0) {
				List<ProviderInfo> providerList = null;
				String fetchMaxrefIdSQL1 = "SELECT PROVIDER_FIRST_NAME ,PROVIDER_LAST_NAME FROM rad_provider_info WHERE PROVIDER_ID="
						+ provideIdLast;
				providerList = jdbcTemplateObject.query(fetchMaxrefIdSQL1,
						new RowMapper<ProviderInfo>() {

							public ProviderInfo mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								ProviderInfo providerList = new ProviderInfo();
								providerList.setProviderFirstName(rs
										.getString("PROVIDER_FIRST_NAME"));
								providerList.setProviderLastName(rs
										.getString("PROVIDER_LAST_NAME"));
								return providerList;
							}
						});
				if (!providerList.isEmpty()) {
					lastName = providerList.get(0).getProviderLastName();
					//System.out.println("===" + lastName);
				}
			}
			List<PracticeInfo> practicelist = null;
			String fetchMaxrefIdSQL1 = "SELECT PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID="
					+ providePracticeId;
			practicelist = jdbcTemplateObject.query(fetchMaxrefIdSQL1,
					new RowMapper<PracticeInfo>() {

						public PracticeInfo mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							PracticeInfo practicelist = new PracticeInfo();
							practicelist.setPracticeName(rs
									.getString("PRACTICE_NAME"));
							return practicelist;
						}
					});

			String practiceName = null;
			String toMail = null;
			if (!practicelist.isEmpty())
				practiceName = (String) practicelist.get(0).getPracticeName();
			String subject = "Patient Referral";
			for (int i = 0; i < emailIdlist.size(); i++) {
				String body = null;
				String providerName = null;
				if (lastName == null) {
					providerName = emailIdlist.get(i).getProviderLastName();
				} else {
					providerName = lastName;
				}
				body = "Dear Dr. "
						+ providerName
						+ " \n  "
						+ practiceName
						+ ". has referred a patient to your practice. Please login here - www.tinyurl.com/referadr to check the patient details. \n Best \n ReferADr  ";
				toMail = emailIdlist.get(i).getProviderEmail();
			try{
				ReferadrUtils.sendMail(fromEmail, toMail, subject, body);
			}catch(Exception e){
				System.out.print("mail error");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
 		 if(patientReferralInfo.isSchedulFlag()){
 	  		
 		String schrduledate=patientReferralInfo.getScheduleDate();
 		String schrduletime=patientReferralInfo.getScheduleTime();
 		int refId= patientReferralInfo.getRefId();
 		Integer provId = patientReferralInfo.getProId();
		if (provId == 0) {
			provId = providerId;
		}
 		int withProvId= provideIdLast;
 		String notes=patientReferralInfo.getScheduleNotes();
 		fromEmail="";
 		
 		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate=""+dateFormat.format(date);
 		
		//to save in shedule table
		String saveSchedule = "INSERT INTO rad_referral_provider_shedule(REFFERAL_ID,PROV_ID,SHEDULE_DATE,SHEDULE_TIME,CREATION_DATE,UPDATED_DATE)VALUES(?,?,?,?,?,?)";
		Object[] paramsShedule = new Object[] { refId, withProvId,schrduledate,schrduletime,currentDate,currentDate };
		int[] typesShedule = new int[] { Types.INTEGER, Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP };
		int rowCurrStatus5 = jdbcTemplateObject.update(saveSchedule, paramsShedule,typesShedule);
		
		
		//to get schedule id
		String sqlgetscheduleid="SELECT	SHEDULE_ID FROM rad_referral_provider_shedule WHERE CREATION_DATE='"+currentDate+"' AND REFFERAL_ID="+refId+" AND PROV_ID="+withProvId+"";
		Integer scheduleId=null;
		try{
		scheduleId = jdbcTemplateObject.query(sqlgetscheduleid,
					new RowMapper<Integer>() {

						public Integer mapRow(ResultSet rs,
								int rowNumber) throws SQLException {
							Integer providerId = rs.getInt("SHEDULE_ID");
							return providerId;
						}

					}).get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.print("schedule id"+scheduleId);
		
		
		//to save in provider action table
		String saveactionProv = "INSERT INTO rad_referral_provider_action(REF_PROV_NOTES,REFERRAL_ID,REF_PROVIDER_ID,CREATION_DATE,UPDATED_DATE,SCHEDULE_ID)VALUES(?,?,?,?,?,?)";
		Object[] params = new Object[] { notes,refId, provId,currentDate,currentDate,scheduleId};
		int[] types = new int[] { Types.VARCHAR, Types.INTEGER ,Types.INTEGER , Types.TIMESTAMP,Types.TIMESTAMP,Types.INTEGER };
		int rowCurrStatus1 = jdbcTemplateObject.update(saveactionProv, params,types);
		
		String referingDrEmailId="SELECT rpii.PROVIDER_FIRST_NAME,rpii.PROVIDER_LAST_NAME,rpii.PROVIDER_EMAIL FROM rad_provider_info rpii WHERE (PROVIDER_CD_PRVROLE_ID=477 OR PROVIDER_CD_PRVROLE_ID=478) AND PROVIDER_PRAC_ID=(SELECT rpi.PROVIDER_PRAC_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_ID="+withProvId+")";
		List<ProviderInfo> referingDocInfo = jdbcTemplateObject.query(referingDrEmailId, new RowMapper<ProviderInfo>() {

						public ProviderInfo mapRow(ResultSet rs,int rowNumber) throws SQLException {
							ProviderInfo providerInfo = new ProviderInfo();
							providerInfo.setProviderEmail(rs.getString("PROVIDER_EMAIL"));
							providerInfo.setProviderLastName(rs.getString("PROVIDER_LAST_NAME"));
							return providerInfo;
						}

					});
		
		String patientNameInfo="SELECT PATIENT_FIRST_NAME,PATIENT_EMAIL,PATIENT_HIPAA_AGREEMENT FROM rad_patient_referral_info  rpri LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID = rpri.PATIENT_ID WHERE PATIENT_REFERRAL_ID="+refId+"";
		PatientInfo patientInfo2 =new PatientInfo();
		List<PatientInfo> patientInfos=new ArrayList<PatientInfo>();
		patientInfos = jdbcTemplateObject.query(patientNameInfo,
					new RowMapper<PatientInfo>() {

						public PatientInfo mapRow(ResultSet rs,
								int rowNumber) throws SQLException {
							PatientInfo info=new PatientInfo();
							info.setPatientFirstName(rs.getString("PATIENT_FIRST_NAME"));
							info.setPatientEmail(rs.getString("PATIENT_EMAIL"));
							info.setPatientHipaaAgreement(rs.getString("PATIENT_HIPAA_AGREEMENT"));
							return info;
						}
					});
		if(!patientInfos.isEmpty()){
			patientInfo2=patientInfos.get(0);
		}
		String patientName=patientInfo2.getPatientFirstName();
		
		String providerNameAndPracticename = "SELECT rpi.PROVIDER_LAST_NAME ,rprai.PRACTICE_NAME,rl.LOC_ADDRESS1,rl.LOC_ADDRESS2,rl.LOC_CITY,rs.STATE_CODE,rl.LOC_ZIP,rl.LOC_PHONE FROM rad_provider_info rpi LEFT JOIN rad_practice_info rprai ON rprai.PRACTICE_ID=rpi.PROVIDER_PRAC_ID LEFT JOIN rad_practice_locations rpl ON rpl.PRACTICE_ID=rprai.PRACTICE_ID LEFT JOIN rad_location rl ON rl.LOC_ID= rpl.LOC_ID LEFT JOIN rad_state rs ON rs.STATE_ID=rl.LOC_STATE_ID WHERE rpi.PROVIDER_ID="
				+ withProvId + "";
		ProviderInfo referedandPracticeInfo=new ProviderInfo();
		List<ProviderInfo> providerInfos= jdbcTemplateObject.query(
				providerNameAndPracticename, new RowMapper<ProviderInfo>() {

					public ProviderInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ProviderInfo referedandPracticeInfo = new ProviderInfo();
						RadLocation radLocation=new RadLocation();
						PracticeLocations practiceLocations=new PracticeLocations();
						practiceLocations.setRadLocation(radLocation);
						PracticeInfo practiceInfo = new PracticeInfo();
						practiceInfo.setPracticeLocations(practiceLocations);
						referedandPracticeInfo.setPracticeInfo(practiceInfo);
						referedandPracticeInfo.getPracticeInfo()
								.setPracticeName(rs.getString("PRACTICE_NAME"));
						
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocAddress1(rs.getString("LOC_ADDRESS1"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocAddress2(rs.getString("LOC_ADDRESS2"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocCity(rs.getString("LOC_CITY"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocState(rs.getString("STATE_CODE"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocZip(rs.getString("LOC_ZIP"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocPhone(rs.getString("LOC_PHONE"));
						
						referedandPracticeInfo.setProviderLastName(rs
								.getString("PROVIDER_LAST_NAME"));
						return referedandPracticeInfo;
					}

				});
		
		if(!providerInfos.isEmpty()){
			referedandPracticeInfo=providerInfos.get(0);
		//	referedandPracticeInfo.getPracticeInfo().getPracticeLocations();
		}
		
		for(ProviderInfo providerInfo : referingDocInfo){
			String toMail=providerInfo.getProviderEmail();
			
	//	String fromEmail="patidarsandeep991@gmail.com";
		String subject="Referral Update";
		 String body="Dear Dr. "+providerInfo.getProviderLastName()+"\n\n Your patient "+patientName+". has been scheduled to see "+referedandPracticeInfo.getProviderLastName()+" on "+schrduledate+" "+schrduletime+". We will keep you posted about the prognosis.\n \n Best \n "+referedandPracticeInfo.getPracticeInfo().getPracticeName()+" ";
		
		 try{
		 ReferadrUtils.sendMail( fromEmail,toMail, subject, body);
		 } catch (Exception e) {
		    	//e.printStackTrace();
			 System.out.println("mail error");
		    	} 
		}
		
		//patientInfo2.getPatientHipaaAgreement().equals("YES") && 
		 if(!patientInfo2.getPatientEmail().equals(null) && !patientInfo2.getPatientEmail().equals("")){
			 String subject1="Schedule Update";
			// String body1="Dear "+patientName+"\n\n You are scheduled with Dr. "+referedandPracticeInfo.getProviderLastName()+"  on "+schrduledate+" "+schrduletime+"\n\n "+referedandPracticeInfo.getPracticeInfo().getPracticeName();	 
			
			
			 
			 
			// String body1="Dear "+patientName+"\n\n You are scheduled with Dr. "+referedandPracticeInfo.getProviderLastName()+"  on "+dateTime+"\n\n "+referedandPracticeInfo.getPracticeInfo().getPracticeName();	 
			 String body1="Dear "+patientName+"\n\n You have been scheduled to see Dr. "+referedandPracticeInfo.getProviderLastName()+" on "+schrduledate+" at "+schrduletime+" at "+ referedandPracticeInfo.getPracticeInfo()
						.getPracticeName() +". The address for "+ referedandPracticeInfo.getPracticeInfo()
						.getPracticeName() +" is "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocAddress1()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocAddress2()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocCity()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocState()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocZip()+""+
						".\n\n Please call us ("+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocPhone()+") if you would like to re-schedule or if you have any more questions. \n\n Best \n "+ referedandPracticeInfo.getPracticeInfo()
							.getPracticeName() +" ";
			 
			 
			 
			 
			 try{
				 ReferadrUtils.sendMail( fromEmail,patientInfo2.getPatientEmail(),subject1, body1);
				 } catch (Exception e) {
				    	e.printStackTrace();
				    	} 
		 }
		
		
 		 }	
		
		
		
		
		
		
		
		
		
		
		
		
		return true;
	}

	public List<Object> updateAdditionalBotes(Integer providerId) {
		List<Object> list = hibernateTemplate
				.find("from Referral_Provider_Action rpa where rpa.refProviderActionId="
						+ providerId);
		return list;
	}

	public void updateMessage(
			Referral_Provider_Action referral_Provider_Action,
			ProviderInfo providerInfo, int practiceId, Integer loggedInProvideId) {
		PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		String fetchProviderId = "SELECT rpri.CH_ID ,rpri.PROVIDER_ID FROM rad_patient_referral_info rpri WHERE rpri.PATIENT_REFERRAL_ID="
				+ referral_Provider_Action.getPatientReferralInfo().getRefId();
		patientReferralInfos = jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<PatientReferralInfo>() {
					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
						CHInfo chInfo = new CHInfo();
						ProviderInfo info = new ProviderInfo();
						chInfo.setId(rs.getInt("CH_ID"));
						info.setProviderId(rs.getInt("PROVIDER_ID"));
						patientReferralInfo.setChInfo(chInfo);
						patientReferralInfo.setProviderInfo(info);
						return patientReferralInfo;
					}

				});
		
		if(!patientReferralInfos.isEmpty()){
			patientReferralInfo=patientReferralInfos.get(0);
			
		}
		
		Integer providerId = null;
		if (patientReferralInfo.getProviderInfo().getProviderId() != 0) {
			providerId = patientReferralInfo.getProviderInfo().getProviderId();
		} else {
			providerId = providerInfo.getProviderId();
		}
		String refProAct = "INSERT INTO rad_referral_provider_action (REF_PROV_NOTES,REF_PROVIDER_ID,REFERRAL_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) VALUES ( ?,?, ?, ?, ?,?,?)";
		Object[] paramsProAct = new Object[] {
				referral_Provider_Action.getProviderNotes(), providerId,
				referral_Provider_Action.getPatientReferralInfo().getRefId(),
				referral_Provider_Action.getCreatedBy(),
				referral_Provider_Action.getCreationDate(),
				referral_Provider_Action.getUpdatedBy(),
				referral_Provider_Action.getUpdatedDate() };
		int[] typesProvAc = new int[] { Types.VARCHAR, Types.INTEGER,
				Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
				Types.TIMESTAMP };
		int rowRefProAct = jdbcTemplateObject.update(refProAct, paramsProAct,
				typesProvAc);

		String sql = "UPDATE rad_refferal_current_status SET UPDATED_DATE =? WHERE REFERRAL_ID=?; ";

		Object[] param = new Object[] {
				referral_Provider_Action.getUpdatedDate(),
				referral_Provider_Action.getPatientReferralInfo().getRefId() };
		int[] type = new int[] { Types.TIMESTAMP, Types.INTEGER };
		jdbcTemplateObject.update(sql, param, type);

		List<Referral_Provider_Action> maxRefProvActId = null;
		String fetchMaxrefIdSQL = "SELECT REF_PROV_ACTION_ID FROM rad_referral_provider_action WHERE REF_PROV_ACTION_ID = (SELECT MAX(REF_PROV_ACTION_ID) FROM rad_referral_provider_action)";
		maxRefProvActId = jdbcTemplate.query(fetchMaxrefIdSQL,
				new RowMapper<Referral_Provider_Action>() {

					public Referral_Provider_Action mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						Referral_Provider_Action maxPatientRefId = new Referral_Provider_Action();
						maxPatientRefId.setRefProviderActionId(rs
								.getInt("REF_PROV_ACTION_ID"));
						return maxPatientRefId;
					}
				});

		String rpo11 = "INSERT INTO rad_referral_rec_tracking ( REFERRAL_ID, REF_PROVIDER_ID, REF_CD_REFACTION_ID, REF_PAT_REC_ACTN_TIMESTAMP,    CREATED_BY, CREATION_DATE,UPDATED_BY, UPDATED_DATE,CH_ID,PRACTICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] paramsrpo11 = new Object[] {
				referral_Provider_Action.getPatientReferralInfo().getRefId(),
				loggedInProvideId,
				maxRefProvActId.get(0).getRefProviderActionId(),
				patientReferralInfo.getUpdatedDate(),
				patientReferralInfo.getCreatedBy(),
				patientReferralInfo.getCreationDate(),
				patientReferralInfo.getUpdatedBy(),
				patientReferralInfo.getUpdatedDate(), null, practiceId };
		int[] typesrpo11 = new int[] { Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,
				Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER };
		int rowCurrStatus11 = jdbcTemplateObject.update(rpo11, paramsrpo11,
				typesrpo11);
		/*
		 * String rpo11=
		 * "INSERT INTO rad_referral_rec_tracking ( REFERRAL_ID, REF_PROVIDER_ID, REF_CD_REFACTION_ID, REF_PAT_REC_ACTN_TIMESTAMP,    CREATED_BY, CREATION_DATE,UPDATED_BY, UPDATED_DATE,CH_ID,PRACTICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?)"
		 * ; Object[] paramsrpo11 = new Object[]
		 * {patientReferralInfo.getRefId(),
		 * patientReferralInfo.getProId(),providerId
		 * ,patientReferralInfo.getUpdatedDate
		 * (),patientReferralInfo.getCreatedBy(),
		 * patientReferralInfo.getCreationDate(),
		 * patientReferralInfo.getUpdatedBy(),
		 * patientReferralInfo.getUpdatedDate(),null,practiceId}; int[]
		 * typesrpo11 = new int[] {
		 * Types.INTEGER,Types.INTEGER,Types.INTEGER,Types
		 * .TIMESTAMP,Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
		 * Types.TIMESTAMP,Types.INTEGER,Types.INTEGER}; int
		 * rowCurrStatus11=jdbcTemplateObject
		 * .update(rpo11,paramsrpo11,typesrpo11);
		 */

	}

	public PatientReferralInfo getProviderId(Integer patientReferralInfo) {
		PatientReferralInfo providerId = null;
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		// =====================================================
		String fetchProviderId = "SELECT PROVIDER_ID FROM rad_patient_referral_info WHERE PATIENT_REFERRAL_ID="
				+ patientReferralInfo;
		patientReferralInfos = jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<PatientReferralInfo>() {

					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo providerId = new PatientReferralInfo();
						providerId.setProId(rs.getInt("PROVIDER_ID"));
						return providerId;
					}

				});
		
		if(!patientReferralInfos.isEmpty())
		{
			providerId=patientReferralInfos.get(0);
			
		}
		return providerId;
	}

	public PatientReferralInfo getFromProviderId(Integer patientReferralId) {
		PatientReferralInfo providerId = new PatientReferralInfo();
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		// =====================================================
		String fetchProviderId = "SELECT rrpa.REF_PROVIDER_ID FROM rad_referral_provider_action rrpa	WHERE rrpa.REFERRAL_ID="
				+ patientReferralId
				+ " ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";

		patientReferralInfos = jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<PatientReferralInfo>() {

					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo providerId = new PatientReferralInfo();
						providerId.setProId(rs.getInt("REF_PROVIDER_ID"));
						return providerId;
					}

				});
		
		if(!patientReferralInfos.isEmpty()){
			providerId = patientReferralInfos.get(0);
		}
		
		return providerId;
	}

	public int saveShedule(String dateTime, Integer refId, Integer provId,
			Integer withProvId, String notes, String fromEmail) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate = "" + dateFormat.format(date);

		/*
		 * String fetchProviderId =
		 * "SELECT REF_PROVIDER_ID FROM rad_referral_provider_action WHERE REF_PROV_ACTION_ID="
		 * +provId+"  ORDER BY CREATION_DATE DESC LIMIT 1";
		 * 
		 * Integer providerId = jdbcTemplateObject.query(fetchProviderId, new
		 * RowMapper<Integer>() {
		 * 
		 * public Integer mapRow(ResultSet rs, int rowNumber) throws
		 * SQLException { Integer providerId = rs.getInt("REF_PROVIDER_ID");
		 * return providerId; }
		 * 
		 * }).get(0);
		 * 
		 * provId=providerId;
		 */
		String scheduleDate = dateTime.substring(0, 10);
		String scheduleTime = dateTime.substring(11, 16);

		// to save in shedule table
		String saveSchedule = "INSERT INTO rad_referral_provider_shedule(REFFERAL_ID,PROV_ID,SHEDULE_DATE,SHEDULE_TIME,CREATION_DATE,UPDATED_DATE)VALUES(?,?,?,?,?,?)";
		Object[] paramsShedule = new Object[] { refId, withProvId,
				scheduleDate, scheduleTime, currentDate, currentDate };
		int[] typesShedule = new int[] { Types.INTEGER, Types.INTEGER,
				Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
		int rowCurrStatus = jdbcTemplateObject.update(saveSchedule,
				paramsShedule, typesShedule);

		// to get schedule id
		String sqlgetscheduleid = "SELECT	SHEDULE_ID FROM rad_referral_provider_shedule WHERE CREATION_DATE='"
				+ currentDate
				+ "' AND REFFERAL_ID="
				+ refId
				+ " AND PROV_ID="
				+ withProvId + "";
		Integer scheduleId=null; 
		List<Integer> list= jdbcTemplateObject.query(sqlgetscheduleid,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						Integer providerId = rs.getInt("SHEDULE_ID");
						return providerId;
					}

				});
if(!list.isEmpty()){
	scheduleId=list.get(0);
	
}
		//System.out.print("schedule id" + scheduleId);

		// to save in provider action table
		String saveactionProv = "INSERT INTO rad_referral_provider_action(REF_PROV_NOTES,REFERRAL_ID,REF_PROVIDER_ID,CREATION_DATE,UPDATED_DATE,SCHEDULE_ID)VALUES(?,?,?,?,?,?)";
		Object[] params = new Object[] { notes, refId, provId, currentDate,
				currentDate, scheduleId };
		int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER,
				Types.TIMESTAMP, Types.TIMESTAMP, Types.INTEGER };
		int rowCurrStatus1 = jdbcTemplateObject.update(saveactionProv, params,
				types);

		String referingDrEmailId = "SELECT rpii.PROVIDER_FIRST_NAME,rpii.PROVIDER_LAST_NAME,rpii.PROVIDER_EMAIL FROM rad_provider_info rpii WHERE (PROVIDER_CD_PRVROLE_ID=477 OR PROVIDER_CD_PRVROLE_ID=478) AND PROVIDER_PRAC_ID=(SELECT rpi.PROVIDER_PRAC_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_ID="
				+ withProvId + ")";
		List<ProviderInfo> referingDocInfo = jdbcTemplateObject.query(
				referingDrEmailId, new RowMapper<ProviderInfo>() {

					public ProviderInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ProviderInfo providerInfo = new ProviderInfo();
						providerInfo.setProviderEmail(rs
								.getString("PROVIDER_EMAIL"));
						providerInfo.setProviderLastName(rs
								.getString("PROVIDER_LAST_NAME"));
						return providerInfo;
					}

				});

	/*	String patientNameInfo = "SELECT PATIENT_FIRST_NAME FROM rad_patient_referral_info  rpri LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID = rpri.PATIENT_ID WHERE PATIENT_REFERRAL_ID="
				+ refId + "";
		
		List<String> patients= jdbcTemplateObject.query(patientNameInfo,
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						String patientName = rs.getString("PATIENT_FIRST_NAME");
						return patientName;
					}

				});
		String patientName="";
		if(!patients.isEmpty()){
			patientName=patients.get(0);
		}*/
		
		
		
		
		
		String patientNameInfo="SELECT PATIENT_FIRST_NAME,PATIENT_EMAIL,PATIENT_HIPAA_AGREEMENT FROM rad_patient_referral_info  rpri LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID = rpri.PATIENT_ID WHERE PATIENT_REFERRAL_ID="+refId+"";
		PatientInfo patientInfo2=new PatientInfo();
		List<PatientInfo> patientInfos=new ArrayList<PatientInfo>();
		patientInfos = jdbcTemplateObject.query(patientNameInfo,
					new RowMapper<PatientInfo>() {

						public PatientInfo mapRow(ResultSet rs,
								int rowNumber) throws SQLException {
							PatientInfo info=new PatientInfo();
							info.setPatientFirstName(rs.getString("PATIENT_FIRST_NAME"));
							info.setPatientEmail(rs.getString("PATIENT_EMAIL"));
							info.setPatientHipaaAgreement(rs.getString("PATIENT_HIPAA_AGREEMENT"));
							return info;
						}

					});
		if(!patientInfos.isEmpty()){
			patientInfo2=patientInfos.get(0);
		}
		String patientName=patientInfo2.getPatientFirstName();
		
		
		
		
		
		
		
			
		/*
		 * String referedDocName=
		 * "SELECT PROVIDER_LAST_NAME  FROM rad_provider_info WHERE PROVIDER_ID="
		 * +withProvId+""; String referedDrName =
		 * jdbcTemplateObject.query(referedDocName, new RowMapper<String>() {
		 * 
		 * public String mapRow(ResultSet rs, int rowNumber) throws SQLException
		 * { String referedDrName = rs.getString("PROVIDER_LAST_NAME"); return
		 * referedDrName; }
		 * 
		 * }).get(0);
		 */
		String providerNameAndPracticename = "SELECT rpi.PROVIDER_LAST_NAME ,rprai.PRACTICE_NAME,rl.LOC_ADDRESS1,rl.LOC_ADDRESS2,rl.LOC_CITY,rs.STATE_CODE,rl.LOC_ZIP,rl.LOC_PHONE FROM rad_provider_info rpi LEFT JOIN rad_practice_info rprai ON rprai.PRACTICE_ID=rpi.PROVIDER_PRAC_ID LEFT JOIN rad_practice_locations rpl ON rpl.PRACTICE_ID=rprai.PRACTICE_ID LEFT JOIN rad_location rl ON rl.LOC_ID= rpl.LOC_ID LEFT JOIN rad_state rs ON rs.STATE_ID=rl.LOC_STATE_ID WHERE rpi.PROVIDER_ID="
				+ withProvId + "";
		ProviderInfo referedandPracticeInfo=new ProviderInfo();
		List<ProviderInfo> providerInfos= jdbcTemplateObject.query(
				providerNameAndPracticename, new RowMapper<ProviderInfo>() {

					public ProviderInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ProviderInfo referedandPracticeInfo = new ProviderInfo();
						RadLocation radLocation=new RadLocation();
						PracticeLocations practiceLocations=new PracticeLocations();
						practiceLocations.setRadLocation(radLocation);
						PracticeInfo practiceInfo = new PracticeInfo();
						practiceInfo.setPracticeLocations(practiceLocations);
						referedandPracticeInfo.setPracticeInfo(practiceInfo);
						referedandPracticeInfo.getPracticeInfo()
								.setPracticeName(rs.getString("PRACTICE_NAME"));
						
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocAddress1(rs.getString("LOC_ADDRESS1"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocAddress2(rs.getString("LOC_ADDRESS2"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocCity(rs.getString("LOC_CITY"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocState(rs.getString("STATE_CODE"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocZip(rs.getString("LOC_ZIP"));
						referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().setLocPhone(rs.getString("LOC_PHONE"));
						
						referedandPracticeInfo.setProviderLastName(rs
								.getString("PROVIDER_LAST_NAME"));
						return referedandPracticeInfo;
					}

				});
		
		if(!providerInfos.isEmpty()){
			referedandPracticeInfo=providerInfos.get(0);
		//	referedandPracticeInfo.getPracticeInfo().getPracticeLocations();
		}
		for (ProviderInfo providerInfo : referingDocInfo) {
			String toMail = providerInfo.getProviderEmail();

			// String fromEmail="patidarsandeep991@gmail.com";
			String subject = "Referral Update";

			String body = "Dear Dr. "
					+ providerInfo.getProviderLastName()
					+ "\n\n Your patient "
					+ patientName
					+ ". has been scheduled to see "
					+ referedandPracticeInfo.getProviderLastName()
					+ " on "
					+ dateTime
					+ ". We will keep you posted about the prognosis.\n \n Best \n "
					+ referedandPracticeInfo.getPracticeInfo()
							.getPracticeName() + " ";
			try{
			ReferadrUtils.sendMail(fromEmail, toMail, subject, body);
			}catch(Exception e){
				System.out.print("mail send error");
			}
			}
		
		
		
		//patientInfo2.getPatientHipaaAgreement().equals("YES") && 
		 if(!patientInfo2.getPatientEmail().equals(null) && !patientInfo2.getPatientEmail().equals("")){
			 String subject1="Schedule Update";
			 
			 String oldFormat = "yyyy-MM-dd HH:mm";
				String newFormat = "MMM d yyyy  hh:mm a";

				SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
				SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
			 
				dateTime=sdf2.format(sdf1.parse(dateTime));
			 
			 
			// String body1="Dear "+patientName+"\n\n You are scheduled with Dr. "+referedandPracticeInfo.getProviderLastName()+"  on "+dateTime+"\n\n "+referedandPracticeInfo.getPracticeInfo().getPracticeName();	 
			 String body1="Dear "+patientName+"\n\n You have been scheduled to see Dr. "+referedandPracticeInfo.getProviderLastName()+" on "+dateTime+" at "+ referedandPracticeInfo.getPracticeInfo()
						.getPracticeName() +". The address for "+ referedandPracticeInfo.getPracticeInfo()
						.getPracticeName() +" is "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocAddress1()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocAddress2()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocCity()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocState()+", "+
						""+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocZip()+""+
						".\n\n Please call us ("+referedandPracticeInfo.getPracticeInfo().getPracticeLocations().getRadLocation().getLocPhone()+") if you would like to re-schedule or if you have any more questions. \n\n Best \n "+ referedandPracticeInfo.getPracticeInfo()
							.getPracticeName() +" ";
			 
			 try{
				 ReferadrUtils.sendMail( fromEmail,patientInfo2.getPatientEmail(),subject1, body1);
				 } catch (Exception e) {
				    	e.printStackTrace();
				    	} 
		 }
		
		
		
		
		
		return rowCurrStatus;
	}

	public int findWithProvider(Integer refId) {
		// select providerID a patient have to schedule with
		String fetchProviderId = "SELECT 	PROVIDER_ID FROM rad_patient_referral_info WHERE PATIENT_REFERRAL_ID="
				+ refId + " ORDER BY CREATION_DATE DESC LIMIT 1";
List<Integer> list=new ArrayList<Integer>();
		
		list= jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<Integer>() {

					public Integer mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						Integer providerId = rs.getInt("PROVIDER_ID");
						return providerId;
					}

				});
		Integer providerId = null;
		if(!list.isEmpty()){
			
			providerId=list.get(0);
		}
		int provId = providerId;

		return provId;
	}

	public List<ScheduleVO> getscheduleList(Integer patientRefInfo) {
		List<ScheduleVO> schedulelist = new ArrayList<ScheduleVO>();

		String fetchScheduleinfo = "SELECT	rrps.SHEDULE_ID, rrps.SHEDULE_DATETIME,rrpa.REF_PROV_NOTES FROM rad_referral_provider_shedule rrps LEFT JOIN rad_referral_provider_action rrpa ON rrpa.SCHEDULE_ID=rrps.SHEDULE_ID WHERE rrps.REFFERAL_ID="
				+ patientRefInfo + " ORDER BY rrps.CREATION_DATE DESC";

		schedulelist = jdbcTemplateObject.query(fetchScheduleinfo,
				new RowMapper<ScheduleVO>() {

					public ScheduleVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ScheduleVO schedule = new ScheduleVO();
						schedule.setScheduleId(rs.getInt("SHEDULE_ID"));
						schedule.setScheduleDate(rs
								.getString("SHEDULE_DATETIME"));
						schedule.setScheduleNotes(rs
								.getString("REF_PROV_NOTES"));
						return schedule;
					}
				});

		return schedulelist;

	}

	public PrintVO getPrintInfo(Integer patientReferralId) {
		List<PrintVO> printVOlist = new ArrayList<PrintVO>();
		PrintVO printVO = new PrintVO();
		List<PrintVO> printVO2 = new ArrayList<PrintVO>();
		List<PrintVO> printVO1 = new ArrayList<PrintVO>();
		// =====================================================
		String fetchPrintFromProviderAction = "SELECT 	rpri.PATIENT_REFERRAL_ID,rpri.CH_ID,"
				+ "	rpri.PATIENT_ID,"
				+ "	rpri.PRAC_SPL_ID,"
				+ "	rpri.PRACTICE_ID,"
				+ "	rpri.PROVIDER_ID,"
				+ "	rrpa.REF_PROVIDER_ID,"
				+ "	rrpa.CREATION_DATE,"
				+ "	refrproi.PROVIDER_FIRST_NAME AS REFPROVIDER_FIRST_NAME,"
				+ "	refrproi.PROVIDER_LAST_NAME AS REFPROVIDER_LAST_NAME,"
				+ "	refrprai.PRACTICE_NAME AS REFPRACTICE_NAME,"
				+ "	rrpa.REF_PROV_DIAG_CODE,"
				+ "	rrpa.REF_PROV_NOTES,"
				+ "	rrpa.REF_PROV_REF_REASON,"
				+ "	rpi.PATIENT_FIRST_NAME,"
				+ "	rpi.PATIENT_LAST_NAME,"
				+ "	rpi.PATIENT_EMAIL,"
				+ "	rpi.PATIENT_DOB,"
				+ "	rpi.PATIENT_GENDER,"
				+ "	rps.PRAC_SPL_DESC,"
				+ "	rprai.PRACTICE_NAME,"
				+ "	rproi.PROVIDER_FIRST_NAME,"
				+ "	rproi.PROVIDER_LAST_NAME,"
				+ "	rpii.PAT_INSURANCE_ID,"
				+ "	rpii.PAT_INSURANCE_GROUP,"
				+ "	rii.INSURANCE_COMPANY,"
				+ "	rl.LOC_ADDRESS1,"
				+ "	rl.LOC_CITY"
				+ "	FROM rad_referral_provider_action rrpa"
				+ "	LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID=rrpa.REFERRAL_ID"
				+ "	LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID	"
				+ "	LEFT JOIN rad_practice_speciality rps ON rps.PRAC_SPL_ID=rpri.PRAC_SPL_ID"
				+ "	LEFT JOIN rad_practice_info rprai ON rprai.PRACTICE_ID=rpri.PRACTICE_ID	"
				+ "	LEFT JOIN rad_provider_info rproi ON rproi.PROVIDER_ID=rpri.PROVIDER_ID	"
				+ "	LEFT JOIN rad_provider_info refrproi ON refrproi.PROVIDER_ID=rrpa.REF_PROVIDER_ID	"
				+ "	LEFT JOIN rad_practice_info refrprai ON refrprai.PRACTICE_ID=refrproi.PROVIDER_PRAC_ID"
				+ "	LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID"
				+ "	LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID"
				+ "	LEFT JOIN rad_practice_locations rpl ON rpl.PRACTICE_ID=refrprai.PRACTICE_ID "
				+ "	LEFT JOIN rad_location rl ON rl.LOC_ID=rpl.LOC_ID"
				+ "	WHERE rpri.PATIENT_REFERRAL_ID="
				+ patientReferralId
				+ " "
				+ " 	ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";

		String fetchPrintFromCHAction = "SELECT 	rpri.PATIENT_REFERRAL_ID,rpri.CH_ID,"
				+ "	rpri.PATIENT_ID,"
				+ "	rpri.PRAC_SPL_ID,"
				+ "	rpri.PRACTICE_ID,"
				+ "	rpri.PROVIDER_ID,"
				+ "	rrpa.REF_PROVIDER_ID,"
				+ "	rrpa.CREATION_DATE,"
				+ "	refrproi.PROVIDER_FIRST_NAME AS REFPROVIDER_FIRST_NAME,"
				+ "	refrproi.PROVIDER_LAST_NAME AS REFPROVIDER_LAST_NAME,"
				+ "	refrprai.PRACTICE_NAME AS REFPRACTICE_NAME,"
				+ "	rrpa.REF_CH_NOTES,"
				+ "	rpi.PATIENT_FIRST_NAME,"
				+ "	rpi.PATIENT_LAST_NAME,"
				+ "	rpi.PATIENT_EMAIL,"
				+ "	rpi.PATIENT_DOB,"
				+ "	rpi.PATIENT_GENDER,"
				+ "	rps.PRAC_SPL_DESC,"
				+ "	rprai.PRACTICE_NAME,"
				+ "	rproi.PROVIDER_FIRST_NAME,"
				+ "	rproi.PROVIDER_LAST_NAME,"
				+ "	rpii.PAT_INSURANCE_ID,"
				+ "	rpii.PAT_INSURANCE_GROUP,"
				+ "	rii.INSURANCE_COMPANY,"
				+ "	rl.LOC_ADDRESS1,"
				+ "	rl.LOC_CITY"
				+ "	FROM rad_referral_ch_action rrpa"
				+ "	LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID=rrpa.REFERRAL_ID"
				+ "	LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID	"
				+ "	LEFT JOIN rad_practice_speciality rps ON rps.PRAC_SPL_ID=rpri.PRAC_SPL_ID"
				+ "	LEFT JOIN rad_practice_info rprai ON rprai.PRACTICE_ID=rpri.PRACTICE_ID	"
				+ "	LEFT JOIN rad_provider_info rproi ON rproi.PROVIDER_ID=rpri.PROVIDER_ID	"
				+ "	LEFT JOIN rad_provider_info refrproi ON refrproi.PROVIDER_ID=rrpa.REF_PROVIDER_ID	"
				+ "	LEFT JOIN rad_practice_info refrprai ON refrprai.PRACTICE_ID=refrproi.PROVIDER_PRAC_ID"
				+ "	LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID"
				+ "	LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID"
				+ "	LEFT JOIN rad_practice_locations rpl ON rpl.PRACTICE_ID=refrprai.PRACTICE_ID "
				+ "	LEFT JOIN rad_location rl ON rl.LOC_ID=rpl.LOC_ID"
				+ "	WHERE rpri.PATIENT_REFERRAL_ID="
				+ patientReferralId
				+ " "
				+ " 	ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";

		printVO2 = jdbcTemplateObject.query(fetchPrintFromCHAction,
				new RowMapper<PrintVO>() {

					public PrintVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PrintVO printVO = new PrintVO();

						printVO.setRefproviderFirstName(rs
								.getString("REFPROVIDER_FIRST_NAME"));
						printVO.setRefProviderLastName(rs
								.getString("REFPROVIDER_LAST_NAME"));
						printVO.setRefPracticeName(rs
								.getString("REFPRACTICE_NAME"));
						printVO.setRefProvDiagCode(null);
						printVO.setRefProvNotes(rs.getString("REF_CH_NOTES"));
						printVO.setRefProvRefReason(null);
						printVO.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						printVO.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						printVO.setPatienEmailId(rs.getString("PATIENT_EMAIL"));
						String dob = rs.getString("PATIENT_DOB");
						dob = dob.substring(0, 11);
						printVO.setPatientDob(dob);
						String Gender = rs.getString("PATIENT_GENDER");
						if (Gender.equals("M")) {
							Gender = "Male";
						} else if (Gender.equals("F")) {
							Gender = "Female";
						} else {
							Gender = "";
						}
						printVO.setPatientGender(Gender);
						printVO.setPracSplDesc(rs.getString("PRAC_SPL_DESC"));
						printVO.setPracticeName(rs.getString("PRACTICE_NAME"));
						printVO.setProviderFirstName(rs
								.getString("PROVIDER_FIRST_NAME"));
						printVO.setProviderLastName(rs
								.getString("PROVIDER_LAST_NAME"));
						printVO.setPatInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						printVO.setPatInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						printVO.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						printVO.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						printVO.setLocCity(rs.getString("LOC_CITY"));
						printVO.setCreationDate(rs.getString("CREATION_DATE"));
						return printVO;
					}

				});

		printVO1 = jdbcTemplateObject.query(fetchPrintFromProviderAction,
				new RowMapper<PrintVO>() {

					public PrintVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PrintVO printVO = new PrintVO();

						printVO.setRefproviderFirstName(rs
								.getString("REFPROVIDER_FIRST_NAME"));
						printVO.setRefProviderLastName(rs
								.getString("REFPROVIDER_LAST_NAME"));
						printVO.setRefPracticeName(rs
								.getString("REFPRACTICE_NAME"));
						printVO.setRefProvDiagCode(rs
								.getString("REF_PROV_DIAG_CODE"));
						printVO.setRefProvNotes(rs.getString("REF_PROV_NOTES"));
						printVO.setRefProvRefReason(rs
								.getString("REF_PROV_REF_REASON"));
						printVO.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						printVO.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						printVO.setPatienEmailId(rs.getString("PATIENT_EMAIL"));
						String dob = rs.getString("PATIENT_DOB");
						dob = dob.substring(0, 11);
						printVO.setPatientDob(dob);
						String Gender = rs.getString("PATIENT_GENDER");
						if (Gender.equals("M")) {
							Gender = "Male";
						} else if (Gender.equals("F")) {
							Gender = "Female";
						} else {
							Gender = "";
						}
						printVO.setPatientGender(Gender);
						printVO.setPracSplDesc(rs.getString("PRAC_SPL_DESC"));
						printVO.setPracticeName(rs.getString("PRACTICE_NAME"));
						printVO.setProviderFirstName(rs
								.getString("PROVIDER_FIRST_NAME"));
						printVO.setProviderLastName(rs
								.getString("PROVIDER_LAST_NAME"));
						printVO.setPatInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						printVO.setPatInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						printVO.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						printVO.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						printVO.setLocCity(rs.getString("LOC_CITY"));
						printVO.setCreationDate(rs.getString("CREATION_DATE"));
						return printVO;
					}

				});

		printVOlist.addAll(printVO2);
		printVOlist.addAll(printVO1);
		if (printVOlist.size() != 0) {
			Collections.sort(printVOlist);
			printVO = printVOlist.get(0);
		}
		printVO2=null;
		printVO1=null;
		return printVO;

	}

	public DoctorTimingVO findProviderPatientSchedule(Integer refId,
			Integer withProvId) {
		DoctorTimingVO doctorTimingVO = new DoctorTimingVO();
List<DoctorTimingVO> doctorTimingVOs=new ArrayList<DoctorTimingVO>();
		String fetchScheduleDate = "SELECT SHEDULE_DATE,SHEDULE_TIME,CREATION_DATE FROM rad_referral_provider_shedule WHERE REFFERAL_ID="
				+ refId
				+ " AND PROV_ID="
				+ withProvId
				+ " ORDER BY CREATION_DATE DESC LIMIT 1";

		doctorTimingVOs = jdbcTemplateObject.query(fetchScheduleDate,
				new RowMapper<DoctorTimingVO>() {

					public DoctorTimingVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						DoctorTimingVO doctorTimingVO = new DoctorTimingVO();
						doctorTimingVO.setCreationDate(rs
								.getString("SHEDULE_DATE"));
						doctorTimingVO.setStartTime(rs
								.getString("SHEDULE_TIME"));
						return doctorTimingVO;
					}
				});
		
		if(doctorTimingVOs.size()!=0){
			doctorTimingVO=doctorTimingVOs.get(0);
		}
		return doctorTimingVO;
	}

	public List<PateintAndInsuranceInfoVO> checkPhoneAvaibility(
			String searchValue) {
		List<PateintAndInsuranceInfoVO> searchList = new ArrayList<PateintAndInsuranceInfoVO>();

		// String fetchScheduleinfo =
		// "SELECT  PATIENT_ID, PATIENT_FIRST_NAME, PATIENT_LAST_NAME, PATIENT_EMAIL, PATIENT_GENDER, PATIENT_DOB FROM ReferADrQA.rad_patient_info WHERE PHONE_NO = '"+searchValue+"' ";

		String fetchScheduleinfo = "SELECT  rpi.PATIENT_ID, rpi.PATIENT_FIRST_NAME, rpi.PATIENT_LAST_NAME, rpi.PATIENT_EMAIL, rpi.PATIENT_GENDER, rpi.PATIENT_DOB,"
				+ "rpii.PAT_INSURANCE_ID,rpii.PAT_INSURANCE_GROUP,rii.INSURANCE_COMPANY "
				+ "FROM rad_patient_info rpi "
				+ " LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ " LEFT JOIN  rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ " WHERE PHONE_NO = '"
				+ searchValue
				+ "' ORDER BY rpii.CREATION_DATE DESC LIMIT 1";

		searchList = jdbcTemplateObject.query(fetchScheduleinfo,
				new RowMapper<PateintAndInsuranceInfoVO>() {

					public PateintAndInsuranceInfoVO mapRow(ResultSet rs,
							int rowNumber) throws SQLException {

						PateintAndInsuranceInfoVO pateintAndInsuranceInfoVO = new PateintAndInsuranceInfoVO();
						pateintAndInsuranceInfoVO.setPatientId(rs
								.getInt("PATIENT_ID"));
						pateintAndInsuranceInfoVO.setPateintFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						pateintAndInsuranceInfoVO.setPateintLastName(rs
								.getString("PATIENT_LAST_NAME"));
						pateintAndInsuranceInfoVO.setPateintEmail(rs
								.getString("PATIENT_EMAIL"));
						pateintAndInsuranceInfoVO.setPatientGender(rs
								.getString("PATIENT_GENDER"));

						String dateSample = rs.getString("PATIENT_DOB");

						String oldFormat = "yyyy-MM-dd HH:mm:ss";
						String newFormat = "MM/dd/yyyy";

						SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
						SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

						try {

							pateintAndInsuranceInfoVO.setPatientDob(sdf2
									.format(sdf1.parse(dateSample)));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						pateintAndInsuranceInfoVO.setInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						pateintAndInsuranceInfoVO.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						pateintAndInsuranceInfoVO.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));

						return pateintAndInsuranceInfoVO;
					}
				});

		return searchList;

	}

	public List<PateintInfoVO> getDraftValue(Integer practiceId) {
		// List<RefferalCurrentStatus> referralInfoList = null;
		List<PateintInfoVO> pateintInfoVOList = new ArrayList<PateintInfoVO>();

		String sql1 = "SELECT 	rrpd.DRAFT_ID,"
				+ "rpii.PATIENT_LAST_NAME,"
				+ "rpii.PATIENT_FIRST_NAME,"
				+ "rrpd.PROVIDER_ID, "
				+ "rrpd.PATIENT_ID, "
				+ "rrpd.DRAFT_NOTES,"
				+ "rpi.PROVIDER_FIRST_NAME, "
				+ "rpi.PROVIDER_LAST_NAME, "
				+ "rrpd.CREATION_DATE "
				+ "FROM "
				+ "rad_referral_provider_draft rrpd "
				+ "LEFT JOIN rad_provider_info rpi ON rrpd.PROVIDER_ID=rpi.PROVIDER_ID "
				+ "LEFT JOIN rad_patient_info rpii ON rpii.PATIENT_ID=rrpd.PATIENT_ID "
				+ "WHERE rpi.PROVIDER_PRAC_ID=" + practiceId
				+ " AND IN_DRAFT='YES' ORDER BY rrpd.CREATION_DATE DESC";

		pateintInfoVOList = jdbcTemplate.query(sql1,
				new RowMapper<PateintInfoVO>() {
					public PateintInfoVO mapRow(ResultSet rs1, int rowNumber)
							throws SQLException {
						PateintInfoVO pateintInfoVO = new PateintInfoVO();
						pateintInfoVO.setRefId(rs1.getInt("DRAFT_ID"));
						pateintInfoVO.setPateintFirstName(rs1
								.getString("PATIENT_FIRST_NAME"));
						pateintInfoVO.setPatientId(rs1.getInt("PATIENT_ID"));
						pateintInfoVO.setProviderId(rs1.getInt("PROVIDER_ID"));
						pateintInfoVO.setProNotes(rs1.getString("DRAFT_NOTES"));
						pateintInfoVO.setPracticeName(rs1
								.getString("PROVIDER_FIRST_NAME"));
						pateintInfoVO.setCreationDate(rs1
								.getString("CREATION_DATE"));
						return pateintInfoVO;
					}
				});

		return pateintInfoVOList;
	}
	
	
	public List<String> getNewNotification(Integer practiceId, String startDateTime,String enddateTime){
		List<String> list=new ArrayList<String>();
		List<String> newAndUpdatesList=new ArrayList<String>();
		List<String> newSchedulelist=new ArrayList<String>();
		List<String> referedPatientUpdates=new ArrayList<String>();
		
		
		String sqlQuery = "SELECT 	rpri.PATIENT_REFERRAL_ID,"+
				"rrpa.REF_PROV_REF_REASON, "+
				"rrpa.REF_PROV_DIAG_CODE, "+
				"rrpa.REF_PROV_NOTES, "+
				"rrpa.SCHEDULE_ID "+
				"FROM  "+
				"rad_patient_referral_info rpri   "+
				"LEFT JOIN rad_referral_provider_action rrpa ON rrpa.REFERRAL_ID =rpri.PATIENT_REFERRAL_ID"+
				" WHERE "+
				"PRACTICE_ID="+practiceId+" AND rrpa.CREATION_DATE BETWEEN '"+startDateTime+"' AND '"+enddateTime+"'";

		//System.out.println("sql1"+sqlQuery);
		
		newAndUpdatesList = jdbcTemplate.query(sqlQuery,
				new RowMapper<String>() {
					public String mapRow(ResultSet rs1, int rowNumber)
							throws SQLException {
					String value="";
						if(rs1.getString("REF_PROV_REF_REASON")!=null && rs1.getString("REF_PROV_DIAG_CODE")!=null && rs1.getString("REF_PROV_NOTES")!=null && rs1.getInt("SCHEDULE_ID")==0)
						{
							
							value="newReferal";
						}
						if(rs1.getString("REF_PROV_REF_REASON")==null && rs1.getString("REF_PROV_DIAG_CODE")==null && rs1.getString("REF_PROV_NOTES")!=null && rs1.getInt("SCHEDULE_ID")==0)
						{
							
							value="update";
						}
						
						return value;
					}
				});
		
		
		
		
		String sqlQueryScheduleNotification = "SELECT 	rrps.SHEDULE_ID, "+
	"rrps.CREATION_DATE, "+
"	rrpa.REF_PROV_REF_REASON,  "+
	"rrpa.REF_PROV_DIAG_CODE,  "+
"	rrpa.REF_PROV_NOTES, "+
"	rrpa.SCHEDULE_ID "+
"	FROM  "+
"	rad_referral_provider_shedule rrps "+
"	LEFT JOIN rad_referral_provider_action rrpa ON rrpa.SCHEDULE_ID=rrps.SHEDULE_ID "+
"	WHERE  "+
"	rrps.PROV_ID IN(SELECT rpi.PROVIDER_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_PRAC_ID = "+practiceId+" ) "+
"	AND rrps.CREATION_DATE BETWEEN '"+startDateTime+"' AND '"+enddateTime+"'";

		//System.out.println("sqlQueryScheduleNotification"+sqlQueryScheduleNotification);
		
		
		newSchedulelist = jdbcTemplate.query(sqlQueryScheduleNotification,
				new RowMapper<String>() {
					public String mapRow(ResultSet rs1, int rowNumber)
							throws SQLException {
					String value="";
						if( rs1.getInt("SCHEDULE_ID")!=0)
						{
							
							value="schedule";
						}
						
						
						return value;
					}
				});
		
		
		
		
/*		String sqlQueryReferedPatientScheduled = "SELECT 	REF_PROV_REF_REASON, "+
	"REF_PROV_DIAG_CODE,  "+
	"REF_PROV_NOTES,  "+
	"CREATION_DATE,  "+
	"SCHEDULE_ID "+
	"FROM  "+
	"rad_referral_provider_action  "+
	"WHERE  "+
	"REF_PROVIDER_ID IN(SELECT rpi.PROVIDER_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_PRAC_ID = "+practiceId+" ) "+
	" AND CREATION_DATE BETWEEN '"+startDateTime+"' AND '"+enddateTime+"'";

		//System.out.println("sqlQueryReferedPatientUpdates"+sqlQueryReferedPatientUpdates);
		
		
		referedPatientUpdates = jdbcTemplate.query(sqlQueryReferedPatientScheduled,
				new RowMapper<String>() {
					public String mapRow(ResultSet rs1, int rowNumber)
							throws SQLException {
					String value="";
					
					if(rs1.getString("REF_PROV_REF_REASON")==null && rs1.getString("REF_PROV_DIAG_CODE")==null && rs1.getString("REF_PROV_NOTES")!=null && rs1.getInt("SCHEDULE_ID")==0)
					{
						value="referedPatientGotUpdated";
					}
					if( rs1.getInt("SCHEDULE_ID")!=0)
					{
						value="ReferedPatientGotScheduled";
					}	
						
						return value;
					}
				});
		*/
		
		
		
		String sqlQueryReferedPatientUpdates = "	SELECT 	REF_PROV_REF_REASON, "+
	"REF_PROV_DIAG_CODE,   "+
	"REF_PROV_NOTES,   "+
	"CREATION_DATE,   "+
	"SCHEDULE_ID, "+
	"REF_PROVIDER_ID  "+
	"FROM   "+
	"rad_referral_provider_action   "+
	"WHERE   "+
	"REFERRAL_ID IN(SELECT  REFERRAL_ID FROM  "+
	"rad_referral_provider_action  "+
	"WHERE REF_PROVIDER_ID IN(SELECT rpi.PROVIDER_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_PRAC_ID = "+practiceId+" ))  "+
	"AND REF_PROVIDER_ID NOT IN(SELECT rpi.PROVIDER_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_PRAC_ID = "+practiceId+" )	 "+
	"AND CREATION_DATE BETWEEN '"+startDateTime+"' AND '"+enddateTime+"'"; 

		//System.out.println("sqlQueryReferedPatientUpdates"+sqlQueryReferedPatientUpdates);
		
		
		referedPatientUpdates = jdbcTemplate.query(sqlQueryReferedPatientUpdates,
				new RowMapper<String>() {
					public String mapRow(ResultSet rs1, int rowNumber)
							throws SQLException {
					String value="";
					
					if(rs1.getString("REF_PROV_REF_REASON")==null && rs1.getString("REF_PROV_DIAG_CODE")==null && rs1.getString("REF_PROV_NOTES")!=null && rs1.getInt("SCHEDULE_ID")==0)
					{
						value="referedPatientGotUpdated";
					}
					if( rs1.getInt("SCHEDULE_ID")!=0)
					{
						value="ReferedPatientGotScheduled";
					}
						
						return value;
					}
				});
		
		
		
		
		
		
		list.addAll(newAndUpdatesList);
		list.addAll(newSchedulelist);
		list.addAll(referedPatientUpdates);
		//System.out.println("list="+list);
		return list;
	}

	public List<PatientReferralInfo> getSelectedLabServicesRepo(PatientReferralInfo patientReferralInfo) {
		String sqlFetchLabSerives="SELECT LAB_SERVICE_MAP_ID FROM rad_lab_referral_selected_services WHERE LAB_REFERRAL_ID="+patientReferralInfo.getLabReffId() +" AND LAB_SERVICE_MAP_ID IS NOT NULL";
		
	List<PatientReferralInfo>	labServiesList = jdbcTemplate.query(sqlFetchLabSerives,
				new RowMapper<PatientReferralInfo>() {
			public PatientReferralInfo mapRow(ResultSet rs1, int rowNumber)
					throws SQLException {
				PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
				patientReferralInfo.setLab_service_map_id(rs1.getInt("LAB_SERVICE_MAP_ID"));
			
				return patientReferralInfo;
			}
		});
		return labServiesList;
	}
	
	public List<PatientReferralInfo> getSelectedLabSubServicesRepo(PatientReferralInfo patientReferralInfo) {
		String sqlFetchLabSubSerives="SELECT LAB_SUB_SERVICE_MAP_ID FROM rad_lab_referral_selected_services WHERE LAB_REFERRAL_ID="+patientReferralInfo.getLabReffId() +" AND LAB_SUB_SERVICE_MAP_ID IS NOT NULL";
		
	List<PatientReferralInfo>	labSubServiesList = jdbcTemplate.query(sqlFetchLabSubSerives,
				new RowMapper<PatientReferralInfo>() {
			public PatientReferralInfo mapRow(ResultSet rs1, int rowNumber)
					throws SQLException {
				PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
				patientReferralInfo.setLab_sub_service_map_id(rs1.getInt("LAB_SUB_SERVICE_MAP_ID"));
			
				return patientReferralInfo;
			}
		});
		return labSubServiesList;
	}

	public PatientReferralInfo getSelectedLabNotesRepo(PatientReferralInfo patientReferralInfo) {
		
		String sqlFetchLabSubSerives="SELECT LAB_REF_NOTES,LAB_REF_ADD_NOTES,LAB_REF_PHY,LAB_REFERRAL_ID,LAB_REF_PROVIDER_ID,REF_CD_PRAC_CAT_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE FROM rad_lab_referral_action WHERE LAB_REFERRAL_ID="+patientReferralInfo.getLabReffId()+" ORDER BY CREATED_BY DESC LIMIT 1";
		
		List<PatientReferralInfo>	patientReferralInfos = jdbcTemplate.query(sqlFetchLabSubSerives,
					new RowMapper<PatientReferralInfo>() {
				public PatientReferralInfo mapRow(ResultSet rs1, int rowNumber)
						throws SQLException {
					PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
					patientReferralInfo.setLabNotes(rs1.getString("LAB_REF_NOTES"));
					patientReferralInfo.setLabICDNote(rs1.getString("LAB_REF_ADD_NOTES"));
					patientReferralInfo.setLabRefPhy(rs1.getString("LAB_REF_PHY"));
				
					return patientReferralInfo;
				}
			});
		PatientReferralInfo patientReferralInfo2=new PatientReferralInfo();
		if(!patientReferralInfos.isEmpty()){
			patientReferralInfo2=patientReferralInfos.get(0);
		}
		return patientReferralInfo2;
	}

	public List<PatientReferralInfo> getLabPatientInfoRepo(PatientReferralInfo patientReferralInfo) {
		
		String querySql="SELECT rlpri.LAB_REFERRAL_ID,rlra.LAB_REF_NOTES,rpi.PATIENT_FIRST_NAME,rpi.PATIENT_LAST_NAME, rpipp.PRACTICE_NAME,rlpri.CREATION_DATE" 
		+" FROM rad_lab_patient_referral_info rlpri LEFT JOIN rad_lab_referral_action rlra ON rlra.LAB_REFERRAL_ID=rlpri.LAB_REFERRAL_ID"
	    +" LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rlpri.PATIENT_ID"
        +" LEFT JOIN rad_provider_info rpip ON rpip.PROVIDER_ID=rlra.LAB_REF_PROVIDER_ID"
        +" LEFT JOIN rad_practice_info rpipp ON rpipp.PRACTICE_ID=rpip.PROVIDER_PRAC_ID"
        +" WHERE rlpri.LAB_ID=1 ORDER BY rlpri.CREATION_DATE DESC";
		    
	        List<PatientReferralInfo> labPatientInfo = jdbcTemplate.query(querySql,
				new RowMapper<PatientReferralInfo>() {
			public PatientReferralInfo mapRow(ResultSet rs1, int rowNumber)
					throws SQLException {
				PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
				patientReferralInfo.setLabReffId(rs1.getInt("LAB_REFERRAL_ID"));
				patientReferralInfo.setLabNotes(rs1.getString("LAB_REF_NOTES"));
				PatientInfo patientInfo=new PatientInfo();
				patientInfo.setPatientFirstName(rs1.getString("PATIENT_FIRST_NAME"));
				patientInfo.setPatientLastName(rs1.getString("PATIENT_LAST_NAME"));
				patientReferralInfo.setPatientInfo(patientInfo);
				PracticeInfo practiceInfo=new PracticeInfo();
				practiceInfo.setPracticeName(rs1.getString("PRACTICE_NAME"));
				patientReferralInfo.setPracticeInfo(practiceInfo);
				patientReferralInfo.setReffCreationDate(rs1.getString("CREATION_DATE").substring(0,10));
				return patientReferralInfo;
			}
		});
		
		return labPatientInfo;
	}
	

}
