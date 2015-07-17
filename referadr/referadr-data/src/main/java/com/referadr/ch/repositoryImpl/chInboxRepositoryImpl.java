package com.referadr.ch.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.referadr.ch.repository.chInboxRepository;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.model.RefferalCurrentStatus;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.ReferadrUtils;

@Repository
public class chInboxRepositoryImpl implements chInboxRepository {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	JdbcTemplate jdbcTemplateObject = CommonUtils.getJdbcTemplate();
	JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();

	public List<PateintInfoVO> getInboxValue(int clearHouseId) {
		List<RefferalCurrentStatus> referralInfoList = null;
		List<PateintInfoVO> pateintInfoVOList = new ArrayList<PateintInfoVO>();
		List<PateintInfoVO> pateintInfoVOList1 = null;
		String sql1 = "SELECT REFERRAL_ID FROM rad_refferal_current_status WHERE CH_ID="
				+ clearHouseId;
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
				String sql2 = "SELECT rrcs.REF_CURR_STS_ID,rrcs.UPDATED_DATE, rprii.PATIENT_REFERRAL_ID,rrpa.REF_CH_ACTION_ID,rrpa.REF_CH_NOTES,rpi.CH_ADMIN_ROLE_FIRST_NAME ,rpri.CN_NAME ,rrpa.CREATION_DATE, rp.PATIENT_FIRST_NAME, rp.PATIENT_ID "
						+ "FROM rad_referral_ch_action rrpa "
						+ "LEFT JOIN rad_patient_referral_info rprii ON  rprii.PATIENT_REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_patient_info rp ON rp.PATIENT_ID =rprii.PATIENT_ID "
						+ "LEFT JOIN rad_refferal_current_status rrcs ON  rrcs.REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_ch_admin rpi ON rpi.CH_ADMIN_ID=rrpa.REF_CH_ADMIN_ID "
						+ "LEFT JOIN rad_ch_info rpri ON rpri.CH_ID=rpi.CH_ADMIN_CH_ID "
						+ "WHERE rrpa.REFERRAL_ID = "
						+ refid
						+ "  ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";

				String sql3 = "SELECT rrcs.REF_CURR_STS_ID,rrcs.UPDATED_DATE,rprii.PATIENT_REFERRAL_ID, rrpa.REF_PROV_ACTION_ID, rrpa.REF_PROV_REF_REASON,rrpa.REF_PROV_DIAG_CODE,rrpa.REF_PROV_NOTES,rpi.PROVIDER_FIRST_NAME ,rpri.PRACTICE_NAME ,rrpa.CREATION_DATE, rp.PATIENT_FIRST_NAME, rp.PATIENT_ID "
						+ "FROM rad_referral_provider_action rrpa "
						+ "LEFT JOIN rad_patient_referral_info rprii ON  rprii.PATIENT_REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_patient_info rp ON rp.PATIENT_ID =rprii.PATIENT_ID "
						+ "LEFT JOIN rad_refferal_current_status rrcs ON  rrcs.REFERRAL_ID=rrpa.REFERRAL_ID "
						+ "LEFT JOIN rad_provider_info rpi ON rpi.PROVIDER_ID=rrpa.REF_PROVIDER_ID "
						+ "LEFT JOIN rad_practice_info rpri ON rpri.PRACTICE_ID=rpi.PROVIDER_PRAC_ID "
						+ "WHERE rrpa.REFERRAL_ID="
						+ refid
						+ " ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";
				// //////form CH
				pateintInfoVOList2 = jdbcTemplate.query(sql2,
						new RowMapper<PateintInfoVO>() {
							public PateintInfoVO mapRow(ResultSet rs2,
									int rowNumber) throws SQLException {
								PateintInfoVO pateintInfoVO = new PateintInfoVO();
								pateintInfoVO.setCreationDate(rs2
										.getString("CREATION_DATE"));
								pateintInfoVO.setPateintFirstName(rs2
										.getString("PATIENT_FIRST_NAME"));
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
								return pateintInfoVO;
							}
						});
				pateintInfoVOList3 = jdbcTemplate.query(sql3,
						new RowMapper<PateintInfoVO>() {
							public PateintInfoVO mapRow(ResultSet rs3,
									int rowNumber) throws SQLException {
								PateintInfoVO pateintInfoVO = new PateintInfoVO();
								pateintInfoVO.setCreationDate(rs3
										.getString("CREATION_DATE"));
								pateintInfoVO.setPateintFirstName(rs3
										.getString("PATIENT_FIRST_NAME"));
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
								return pateintInfoVO;
							}
						});
				if(pateintInfoVOList3.size()!=0){
				pateintInfoVOList2.addAll(pateintInfoVOList3);
				}
				if(pateintInfoVOList2.size()!=0){
				Collections.sort(pateintInfoVOList2);
				pateintInfoVOList.add(pateintInfoVOList2.get(0));}
			}
		}
		if(pateintInfoVOList.size()!=0){
		Collections.sort(pateintInfoVOList);}
		return pateintInfoVOList;
	}

	public PatientReferralInfo getProviderId(Integer patientReferralId) {
		PatientReferralInfo providerId = null;
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		// =====================================================
		String fetchProviderId = "SELECT PROVIDER_ID FROM rad_patient_referral_info WHERE PATIENT_REFERRAL_ID="
				+ patientReferralId;
		patientReferralInfos = jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<PatientReferralInfo>() {

					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo providerId = new PatientReferralInfo();
						providerId.setProId(rs.getInt("PROVIDER_ID"));
						return providerId;
					}

				});
		if(!patientReferralInfos.isEmpty()){
			providerId=patientReferralInfos.get(0);
		}
		return providerId;
	}
	
	
	public PatientReferralInfo getFromProviderId(Integer patientReferralId) {
		PatientReferralInfo providerId = null;
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		// =====================================================
		String fetchProviderId = "SELECT rrpa.REF_PROVIDER_ID FROM rad_referral_provider_action rrpa	WHERE rrpa.REFERRAL_ID="+patientReferralId+" ORDER BY rrpa.CREATION_DATE DESC LIMIT 1";
				
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
			providerId=patientReferralInfos.get(0);
		}
		return providerId;
	}

	public List<ProviderInfo> getProviderInfo(int providePracticeId) {
		List<ProviderInfo> providerInfoList = null;
		// =====================================================
		String fetchPracticeSQL = "SELECT PROVIDER_ID ,PROVIDER_FIRST_NAME FROM rad_provider_info WHERE PROVIDER_PRAC_ID ="
				+ providePracticeId
				+ "&& PROVIDER_CD_PRVROLE_ID NOT IN (477,478)";
		providerInfoList = jdbcTemplate.query(fetchPracticeSQL,
				new RowMapper<ProviderInfo>() {

					public ProviderInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ProviderInfo providerInfo = new ProviderInfo();
						providerInfo.setProviderId(rs.getInt("Provider_ID"));
						providerInfo.setProviderFirstName(rs
								.getString("Provider_First_Name"));
						return providerInfo;
					}

				});
		return providerInfoList;
	}

	public List<CHInfo> getclearingHouseName(int chId) {
		List<CHInfo> CHIdList = null;
		List<CHInfo> clearingHouseList = null;
		// =====================================================
		String fetchChIdSQL = "SELECT CH_ID ,CN_NAME FROM rad_ch_info WHERE CH_ID IN (SELECT CH_ID_2 FROM rad_ch_ch_mapping WHERE CH_ID_1="
				+ chId + ") OR CH_ID=" + chId;
		clearingHouseList = jdbcTemplate.query(fetchChIdSQL,
				new RowMapper<CHInfo>() {
					public CHInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						CHInfo clearingHouseList = new CHInfo();
						clearingHouseList.setId(rs.getInt("CH_ID"));
						clearingHouseList.setName(rs.getString("CN_NAME"));
						return clearingHouseList;
					}

				});
		return clearingHouseList;
	}

	public boolean updateRefferInformation(
			PatientReferralInfo patientReferralInfo, int chId,
			String fromEmail, int chAdminId,int fromProviderId) {

		int provideIdLast = patientReferralInfo.getProviderInfo()
				.getProviderId();
		// =====================using jdbc======
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
		if(!patientReferralInfos.isEmpty()){
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
		Integer providerId = null;
		if (patientReferralInfo.getProviderInfo().getProviderId() != 0) {
			providerId = patientReferralInfo.getProviderInfo().getProviderId();
		}
		Integer practiceIdd = null;
		if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
			practiceIdd = patientReferralInfo.getPracticeInfo().getPracticeId();
		}
		String insertChAction = "INSERT INTO rad_referral_ch_action(REF_CH_NOTES,REF_CH_ADMIN_ID,REF_PRACTICE_ID, REF_PROVIDER_ID,REFERRAL_ID, REF_CH_ACTION_Timestamp,CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE)VALUES(?,?, ?,?,?,?,?,?,?,?)";
		Object[] insertChActionParams = new Object[] {
				patientReferralInfo.getReferral_Provider_ActionList().get(0)
						.getProviderNotes(), chAdminId, practiceIdd,
				providerId, patientReferralInfo.getRefId(),
				patientReferralInfo.getUpdatedDate(),
				patientReferralInfo.getCreatedBy(),
				patientReferralInfo.getCreationDate(),
				patientReferralInfo.getUpdatedBy(),
				patientReferralInfo.getUpdatedDate() };
		int[] insertChActionParamsTypes = new int[] { Types.VARCHAR,
				Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
				Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
				Types.TIMESTAMP, };
		int insertChActionParamsTypesRow = jdbcTemplateObject
				.update(insertChAction, insertChActionParams,
						insertChActionParamsTypes);

		if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
			String updateRefralCurrentStatus = "UPDATE rad_refferal_current_status SET CH_ID = ?,PRACTICE_ID = ? ,  UPDATED_BY = ? , 	UPDATED_DATE = ? WHERE	REFERRAL_ID = ? ";

			Object[] updateRefralCurrentStatusParams = new Object[] { null,
					patientReferralInfo.getPracticeInfo().getPracticeId(),
					patientReferralInfo.getUpdatedBy(),
					patientReferralInfo.getUpdatedDate(),
					patientReferralInfo.getRefId() };

			int[] updateRefralCurrentStatusParamsTypes = new int[] {
					Types.INTEGER, Types.INTEGER, Types.VARCHAR,
					Types.TIMESTAMP, Types.INTEGER };

			int updateRefralCurrentStatusParamsTypesRow = jdbcTemplateObject
					.update(updateRefralCurrentStatus,
							updateRefralCurrentStatusParams,
							updateRefralCurrentStatusParamsTypes);
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
		
		
		 List<Referral_Provider_Action> maxRefProvActId=null;
			String fetchMaxrefIdSQL="SELECT REF_CH_ACTION_ID FROM rad_referral_ch_action WHERE REF_CH_ACTION_ID = (SELECT MAX(REF_CH_ACTION_ID) FROM rad_referral_ch_action)";
			maxRefProvActId= jdbcTemplate.query(fetchMaxrefIdSQL, new RowMapper<Referral_Provider_Action>(){

				public Referral_Provider_Action mapRow(ResultSet rs, int rowNumber) throws SQLException {
					Referral_Provider_Action maxPatientRefId=new Referral_Provider_Action();
					maxPatientRefId.setRefProviderActionId(rs.getInt("REF_CH_ACTION_ID"));				
					return maxPatientRefId;
				}
			});
		
		 String rpo11="INSERT INTO rad_referral_rec_tracking ( REFERRAL_ID, REF_PROVIDER_ID, REF_CD_REFACTION_ID, REF_PAT_REC_ACTN_TIMESTAMP,	CREATED_BY, CREATION_DATE,UPDATED_BY, UPDATED_DATE,CH_ID,PRACTICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?)";
		 Object[] paramsrpo11 = new Object[] {patientReferralInfo.getRefId(),chAdminId,maxRefProvActId.get(0).getRefProviderActionId(),patientReferralInfo.getUpdatedDate(),patientReferralInfo.getCreatedBy(), patientReferralInfo.getCreationDate(), patientReferralInfo.getUpdatedBy(), patientReferralInfo.getUpdatedDate(),chId,null};
		int[] typesrpo11 = new int[] { Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.TIMESTAMP,Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,Types.INTEGER,Types.INTEGER};
		int rowCurrStatus11=jdbcTemplateObject.update(rpo11,paramsrpo11,typesrpo11); 
		
		
		
		
		
		
		
		Integer logedInChId = null;
		Integer logedInPracticeId = null;
		if (chId != 0) {
			logedInChId = chId;
		} else {
			logedInPracticeId = patientReferralInfo.getPracticeId();
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
		Integer iToprovider = patientReferralInfo.getProviderInfo()
				.getProviderId();
		if (iToprovider == 0) {
			iToprovider = null;
		}
		Integer iToCH = patientReferralInfo.getChInfo().getId();
		if (iToCH == 0) {
			iToCH = null;
		}
		Integer referalId = patientReferralInfo.getRefId();
		String iCreatedBy = patientReferralInfo.getCreatedBy();
		String strUpdatedBy = patientReferralInfo.getUpdatedBy();
		// for report table
		String insertReportInfo = "INSERT INTO report (FROM_PROVIDER_ID, TO_PROVIDER_ID, TO_SPL_ID, FROM_PRACTICE_ID, TO_PRACTICE_ID, FROM_CH_ID, TO_CH_ID,CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,REFFERAL_ID)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		Object[] paramsReport = new Object[] { fromProviderId, iToprovider, iTospl, null,
				iToPractice, logedInChId, iToCH, iCreatedBy,
				patientReferralInfo.getCreationDate(), strUpdatedBy,
				patientReferralInfo.getUpdatedDate(), referalId };
		int[] typesReport = new int[] { Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
				Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
				Types.TIMESTAMP, Types.INTEGER };
		int rowCurrStatus = jdbcTemplateObject.update(insertReportInfo,
				paramsReport, typesReport);

		try {
			String lastName = null;
			List<ProviderInfo> emailIdlist = null;
			String fetchProviderInfol = "SELECT PROVIDER_EMAIL,PROVIDER_FIRST_NAME,PROVIDER_LAST_NAME FROM rad_provider_info WHERE PROVIDER_PRAC_ID= "
					+ patientReferralInfo.getPracticeInfo().getPracticeId()
					+ " AND (PROVIDER_CD_PRVROLE_ID=477 OR PROVIDER_CD_PRVROLE_ID=478)";
			emailIdlist = jdbcTemplateObject.query(fetchProviderInfol,
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
				String fetchProviderInfo = "SELECT PROVIDER_FIRST_NAME ,PROVIDER_LAST_NAME FROM rad_provider_info WHERE PROVIDER_ID="
						+ provideIdLast;
				providerList = jdbcTemplateObject.query(fetchProviderInfo,
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
				}
			}
			List<CHInfo> chlist = null;
			String fetchChName = "SELECT CN_NAME FROM rad_ch_info WHERE CH_ID="
					+ chId;
			chlist = jdbcTemplateObject.query(fetchChName,
					new RowMapper<CHInfo>() {

						public CHInfo mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							CHInfo chlist = new CHInfo();
							chlist.setName(rs.getString("CN_NAME"));
							return chlist;
						}
					});
			String chName = null;
			String toMail = null;
			if (!chlist.isEmpty())
				chName = (String) chlist.get(0).getName();
			String subject = "Patient Referral";
			for (int i = 0; i < emailIdlist.size(); i++) {
				String body = null;
				String providerLastName = null;
				if (lastName == null) {
					providerLastName = emailIdlist.get(i).getProviderLastName();
				} else {
					providerLastName = lastName;
				}
				body = "Dear Dr. "
						+ providerLastName
						+ " \n  "
						+ chName
						+ ". has referred a patient to your practice. Please login here - www.tinyurl.com/referadr to check the patient details. \n Best \n ReferADr  ";
				toMail = emailIdlist.get(i).getProviderEmail();
				ReferadrUtils.sendMail(fromEmail, toMail, subject, body);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public PatientReferralInfo getChIDAndSpecilityId(Integer patientReferralId) {
		PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		String fetchClearingId = "SELECT rpri.CH_ID ,rpri.PRAC_SPL_ID FROM rad_patient_referral_info rpri WHERE rpri.PATIENT_REFERRAL_ID="
				+ patientReferralId;
		patientReferralInfos = jdbcTemplateObject.query(fetchClearingId,
				new RowMapper<PatientReferralInfo>() {

					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
						CHInfo chInfo = new CHInfo();
						PracticeSpecialty practiceSpecialty = new PracticeSpecialty();
						patientReferralInfo
								.setPracticeSpecialty(practiceSpecialty);
						ProviderInfo info = new ProviderInfo();
						chInfo.setId(rs.getInt("CH_ID"));
						practiceSpecialty.setPraticeSplID(rs
								.getInt("PRAC_SPL_ID"));
						patientReferralInfo.setChInfo(chInfo);
						patientReferralInfo.setProviderInfo(info);
						return patientReferralInfo;
					}

				});
		if(!patientReferralInfos.isEmpty()){
			patientReferralInfo=patientReferralInfos.get(0);
		}
		return patientReferralInfo;
	}

	public void updateMessage(Referral_Provider_Action referral_Provider_Action,ProviderInfo providerInfo, int chAdminId, int chId) {

		PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
		List<PatientReferralInfo> patientReferralInfos=new ArrayList<PatientReferralInfo>();
		String fetchProviderId = "SELECT rpri.PRACTICE_ID ,rpri.PROVIDER_ID FROM rad_patient_referral_info rpri WHERE rpri.PATIENT_REFERRAL_ID="
				+ referral_Provider_Action.getPatientReferralInfo().getRefId();
		patientReferralInfos = jdbcTemplateObject.query(fetchProviderId,
				new RowMapper<PatientReferralInfo>() {

					public PatientReferralInfo mapRow(ResultSet rs,
							int rowNumber) throws SQLException {
						PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
						CHInfo chInfo = new CHInfo();
						ProviderInfo info = new ProviderInfo();
						patientReferralInfo.setPracticeId(rs
								.getInt("Practice_ID"));
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
		}
		Integer practiceId = null;
		if (patientReferralInfo.getPracticeId() != 0) {
			practiceId = patientReferralInfo.getPracticeId();
		}

		String refProAct = "INSERT INTO rad_referral_ch_action (REF_CH_NOTES,REF_PRACTICE_ID,REF_CH_ADMIN_ID,REF_PROVIDER_ID,REFERRAL_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE,REF_CH_ACTION_Timestamp) VALUES ( ?,?, ?,?,?, ?,?, ?,?,?)";
		Object[] paramsProAct = new Object[] {
				referral_Provider_Action.getProviderNotes(), practiceId,
				chAdminId, providerId,
				referral_Provider_Action.getPatientReferralInfo().getRefId(),
				referral_Provider_Action.getCreatedBy(),
				referral_Provider_Action.getCreationDate(),
				referral_Provider_Action.getUpdatedBy(),
				referral_Provider_Action.getUpdatedDate(),
				referral_Provider_Action.getUpdatedDate() };
		int[] typesProvAc = new int[] { Types.VARCHAR, Types.INTEGER,
				Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR,
				Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,
				Types.TIMESTAMP };
		int rowRefProAct = jdbcTemplateObject.update(refProAct, paramsProAct,
				typesProvAc);

		String sql = "UPDATE rad_refferal_current_status SET UPDATED_DATE =? WHERE REFERRAL_ID=?; ";
		Object[] param = new Object[] {
				referral_Provider_Action.getUpdatedDate(),
				referral_Provider_Action.getPatientReferralInfo().getRefId() };
		int[] type = new int[] { Types.TIMESTAMP, Types.INTEGER };
		jdbcTemplateObject.update(sql, param, type);
		
		 List<Referral_Provider_Action> maxRefProvActId=null;
			String fetchMaxrefIdSQL="SELECT REF_CH_ACTION_ID FROM rad_referral_ch_action WHERE REF_CH_ACTION_ID = (SELECT MAX(REF_CH_ACTION_ID) FROM rad_referral_ch_action)";
			maxRefProvActId= jdbcTemplate.query(fetchMaxrefIdSQL, new RowMapper<Referral_Provider_Action>(){

				public Referral_Provider_Action mapRow(ResultSet rs, int rowNumber) throws SQLException {
					Referral_Provider_Action maxPatientRefId=new Referral_Provider_Action();
					maxPatientRefId.setRefProviderActionId(rs.getInt("REF_CH_ACTION_ID"));				
					return maxPatientRefId;
				}
			});
		
		 String rpo11="INSERT INTO rad_referral_rec_tracking ( REFERRAL_ID, REF_PROVIDER_ID, REF_CD_REFACTION_ID, REF_PAT_REC_ACTN_TIMESTAMP,	CREATED_BY, CREATION_DATE,UPDATED_BY, UPDATED_DATE,CH_ID,PRACTICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?)";
		 Object[] paramsrpo11 = new Object[] {referral_Provider_Action.getPatientReferralInfo().getRefId(),chAdminId,maxRefProvActId.get(0).getRefProviderActionId(),patientReferralInfo.getUpdatedDate(),patientReferralInfo.getCreatedBy(), patientReferralInfo.getCreationDate(), patientReferralInfo.getUpdatedBy(), patientReferralInfo.getUpdatedDate(),chId,null};
		int[] typesrpo11 = new int[] { Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.TIMESTAMP,Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,Types.INTEGER,Types.INTEGER};
		int rowCurrStatus11=jdbcTemplateObject.update(rpo11,paramsrpo11,typesrpo11); 
	}
}
