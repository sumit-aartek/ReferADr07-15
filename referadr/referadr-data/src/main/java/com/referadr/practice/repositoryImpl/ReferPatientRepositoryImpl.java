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

import com.referadr.practice.model.CHAdmin;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.FileMeta;
import com.referadr.practice.model.InsuranceInfo;
import com.referadr.practice.model.LabVO;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PatientInsuranceInfo;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeLocations;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.RedState;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.model.RefferalCurrentStatus;
import com.referadr.practice.repository.ReferPatientRepository;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.ReferadrUtils;

@SuppressWarnings("unchecked")
@Repository
public class ReferPatientRepositoryImpl implements ReferPatientRepository {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	private JdbcTemplate jdbcTemplateObject;
	private int insinfoid = 0;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<PatientInfo> getAllPatientInfo(String patientFirstName,
			String patientDateOfBirth, String patientSsn) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_LAST_NAME LIKE '%" + patientFirstName
				+ "%' AND PATIENT_DOB LIKE '%" + patientDateOfBirth
				+ "%' AND PATIENT_SSN=" + patientSsn;

		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {

					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();

						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);

						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);

						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);

						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString(
								"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<PracticeSpecialty> getPraticeSpecialty1(Integer PracticeId) {

		List<PracticeSpecialty> practiceSplList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// showing all spl according to ch
		String fetchPractSplSQL = "SELECT PRAC_SPL_ID,PRAC_SPL_DESC FROM rad_practice_speciality WHERE PRAC_SPL_ID IN (SELECT CH_PRACSPL_ID FROM rad_ch_specality_mapping WHERE CH_ID IN (SELECT CH_ID FROM rad_ch_practice_mapping WHERE PRACTICE_ID="
				+ PracticeId + "))";
		practiceSplList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PracticeSpecialty>() {
					public PracticeSpecialty mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PracticeSpecialty practiceSpecialty = new PracticeSpecialty();
						practiceSpecialty.setPraticeSplID(rs
								.getInt("PRAC_SPL_ID"));
						practiceSpecialty.setPraticeSplDesc(rs
								.getString("PRAC_SPL_DESC"));
						return practiceSpecialty;
					}
				});
		return practiceSplList;
	}

	public List<PatientInfo> getAllPatientByNameDob(String patientFirstName,
			String patientDateOfBirth) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_LAST_NAME LIKE '%" + patientFirstName
				+ "%' AND PATIENT_DOB LIKE '%" + patientDateOfBirth + "%'";
		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {

					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();

						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);

						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);
						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);
						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString(
								"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<PatientInfo> getAllPatientByNameSsn(String patientFirstName,
			String patientSsn) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_LAST_NAME LIKE '%" + patientFirstName
				+ "%' AND PATIENT_SSN =" + patientSsn;
		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {

					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();

						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);

						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);
						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);
						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString(
								"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<PatientInfo> searchByPatientDobSsn(String patientDateOfBirth,
			String patientSsn) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_SSN =" + patientSsn
				+ " AND PATIENT_DOB LIKE '%" + patientDateOfBirth + "%'";
		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {
					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();
						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);
						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);
						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);
						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString(
								"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<PatientInfo> searchByName(String patientFirstName) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_LAST_NAME LIKE '%" + patientFirstName + "%'";
		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {

					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();

						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);

						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);

						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);

						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						if (rs.getString("PATIENT_GENDER") != null)
							patientInfo.setPatientGender(rs.getString(
									"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<PatientInfo> searchByDob(String patientDateOfBirth) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_DOB ='" + patientDateOfBirth + "'";
		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {

					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();

						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);

						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);
						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);
						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString(
								"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<PatientInfo> searchBySsn(String patientSsn) {
		List<PatientInfo> patientInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_patient_info rpi "
				+ "LEFT JOIN rad_location rl ON rl.LOC_ID =rpi.PATIENT_LOC_ID "
				+ "LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID=rpi.PATIENT_ID "
				+ "LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID=rpii.PAT_INS_ID "
				+ "WHERE PATIENT_SSN =" + patientSsn;
		patientInfoList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PatientInfo>() {

					public PatientInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PatientInfo patientInfo = new PatientInfo();

						RadLocation radLocation = new RadLocation();
						patientInfo.setRadLocation(radLocation);
						RedState redState = new RedState();
						radLocation.setRedState(redState);
						List<PatientInsuranceInfo> patientInsuranceInfoList = new ArrayList<PatientInsuranceInfo>();
						PatientInsuranceInfo patientInsuranceInfo = new PatientInsuranceInfo();
						InsuranceInfo insuranceInfo = new InsuranceInfo();
						patientInsuranceInfoList.add(patientInsuranceInfo);
						patientInfo
								.setPatientInsuranceInfoList(patientInsuranceInfoList);
						patientInfo.setPatientId(rs.getInt("PATIENT_ID"));
						patientInfo.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						patientInfo.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						patientInfo.setPatientDob(rs.getString("PATIENT_DOB"));
						patientInfo.setPatientEmail(rs
								.getString("PATIENT_EMAIL"));
						patientInfo.setPatientGender(rs.getString(
								"PATIENT_GENDER").charAt(0));
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						patientInsuranceInfo.setPatientInsuranceInfoId(rs
								.getInt("PAT_INS_INFO_ID"));
						patientInsuranceInfo.setPatientInsuranceId(rs
								.getString("PAT_INSURANCE_ID"));
						patientInsuranceInfo.setPatientInsuranceGroup(rs
								.getString("PAT_INSURANCE_GROUP"));
						patientInsuranceInfo.setInsuranceInfo(insuranceInfo);
						insuranceInfo.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						insuranceInfo.setInsuranceId(rs.getInt("INSURANCE_ID"));
						return patientInfo;
					}
				});
		return patientInfoList;
	}

	public List<RadLocation> getAllAddress1(Integer providePracticeId) {
		/*
		 * List address1List =null; SessionFactory sessionFactory = null;
		 * Session session = null; try{ sessionFactory =
		 * hibernateTemplate.getSessionFactory(); session =
		 * sessionFactory.openSession(); Query query = session.createSQLQuery(
		 * "SELECT LOC_ID,LOC_ADDRESS1 FROM rad_location WHERE LOC_ID IN (SELECT LOC_ID FROM rad_practice_locations WHERE PRACTICE_ID="
		 * +providePracticeId+")"); address1List=query.list(); } catch
		 * (Exception e) { e.printStackTrace(); } finally{session.close();}
		 * return address1List;
		 */

		List<RadLocation> address1List = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT LOC_ID,LOC_ADDRESS1 FROM rad_location WHERE LOC_ID IN (SELECT LOC_ID FROM rad_practice_locations WHERE PRACTICE_ID="
				+ providePracticeId + ")";
		address1List = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<RadLocation>() {
					public RadLocation mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						RadLocation address1List = new RadLocation();
						address1List.setLocId(rs.getInt("LOC_ID"));
						address1List.setLocAddress1(rs
								.getString("LOC_ADDRESS1"));
						return address1List;
					}

				});
		return address1List;

	}

	public List<RadLocation> getAllAddress1() {
		List<RadLocation> address1List = hibernateTemplate
				.find("from RadLocation");
		return address1List;
	}

	public List<RadLocation> getFullAddress(String address1) {
		List<RadLocation> radLocations = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT * FROM rad_location WHERE LOC_ID ="
				+ address1;
		radLocations = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<RadLocation>() {
					public RadLocation mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						RadLocation radLocation = new RadLocation();
						RedState redState = new RedState();
						radLocation.setRedState(redState);
						radLocation.setLocId(rs.getInt("LOC_ID"));
						radLocation.setLocAddress1(rs.getString("LOC_ADDRESS1"));
						radLocation.setLocAddress2(rs.getString("LOC_ADDRESS2"));
						radLocation.setLocCity(rs.getString("LOC_CITY"));
						radLocation.setLocWebsite(rs.getString("LOC_WEBSITE"));
						radLocation.setLocFax(rs.getString("LOC_FAX"));
						radLocation.setLocPhone(rs.getString("LOC_PHONE"));
						radLocation.setLocZip(rs.getString("LOC_ZIP"));
						radLocation.setLocCity(rs.getString("LOC_CITY"));
						radLocation.setLocAddress2(rs.getString("LOC_ADDRESS2"));
						radLocation.getRedState().setStateId(
								rs.getInt("LOC_STATE_ID"));
						return radLocation;
					}

				});
		return radLocations;
	}

	public List getclearingHouseName() {
		List clearingHouseList = hibernateTemplate
				.find("from ChInfoPracticeMapping");
		return clearingHouseList;
	}

	public List<CHInfo> getclearingHouseName(Integer providePracticeId) {
		List<CHInfo> CHIdList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchPracticeSQL = "SELECT a.CH_ID FROM rad_ch_practice_mapping a WHERE PRACTICE_ID="
				+ providePracticeId;
		CHIdList = jdbcTemplate.query(fetchPracticeSQL,
				new RowMapper<CHInfo>() {

					public CHInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						CHInfo CHIdList = new CHInfo();
						CHIdList.setId(rs.getInt("CH_ID"));
						return CHIdList;
					}

				});
		List<CHInfo> clearingHouseList = null;
		if (!CHIdList.isEmpty() || CHIdList.size() != 0) {
			if (CHIdList.get(0) != null) {
				JdbcTemplate jdbcTemplate1 = CommonUtils.getJdbcTemplate();
				// =====================================================
				String fetchChIdSQL = "SELECT CH_ID ,CN_NAME FROM rad_ch_info WHERE CH_ID IN (SELECT CH_ID_2 FROM rad_ch_ch_mapping WHERE CH_ID_1="
						+ CHIdList.get(0).getId()
						+ ") OR CH_ID="
						+ CHIdList.get(0).getId();
				clearingHouseList = jdbcTemplate1.query(fetchChIdSQL,
						new RowMapper<CHInfo>() {

							public CHInfo mapRow(ResultSet rs, int rowNumber)
									throws SQLException {
								CHInfo clearingHouseList = new CHInfo();
								clearingHouseList.setId(rs.getInt("CH_ID"));
								clearingHouseList.setName(rs
										.getString("CN_NAME"));
								return clearingHouseList;
							}

						});
			}
		}
		return clearingHouseList;
	}

	public List<PracticeSpecialty> getPraticeSpecialty(Integer chearingHouseId) {

		List<PracticeSpecialty> practiceSplList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = "SELECT PRAC_SPL_ID,PRAC_SPL_DESC FROM rad_practice_speciality WHERE PRAC_SPL_ID IN (SELECT CH_PRACSPL_ID FROM rad_ch_specality_mapping WHERE CH_ID="
				+ chearingHouseId + ")";
		practiceSplList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<PracticeSpecialty>() {
					public PracticeSpecialty mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PracticeSpecialty practiceSpecialty = new PracticeSpecialty();
						practiceSpecialty.setPraticeSplID(rs
								.getInt("PRAC_SPL_ID"));
						practiceSpecialty.setPraticeSplDesc(rs
								.getString("PRAC_SPL_DESC"));
						return practiceSpecialty;
					}
				});
		return practiceSplList;
	}

	public List<PracticeInfo> getPraticeInfo(Integer practiceSpectialtyId,
			Integer chearingHouseId) {

		List<PracticeInfo> practiceInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPracticeSQL = "SELECT PRACTICE_ID,PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID IN (SELECT PRACTICE_ID FROM rad_practice_spl_mapping WHERE PRACTICE_SPL_ID="
				+ practiceSpectialtyId + ")";
		practiceInfoList = jdbcTemplate.query(fetchPracticeSQL,
				new RowMapper<PracticeInfo>() {
					public PracticeInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						PracticeInfo practiceInfo = new PracticeInfo();
						practiceInfo.setPracticeId(rs.getInt("Practice_ID"));
						practiceInfo.setPracticeName(rs
								.getString("Practice_Name"));
						return practiceInfo;
					}
				});
		return practiceInfoList;
	}

	public List<ProviderInfo> getProviderInfo(Integer practiceInfoId) {
		List<ProviderInfo> providerInfoList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPracticeSQL = "SELECT PROVIDER_ID ,PROVIDER_FIRST_NAME,PROVIDER_LAST_NAME FROM rad_provider_info WHERE PROVIDER_PRAC_ID ="
				+ practiceInfoId + "&& PROVIDER_CD_PRVROLE_ID NOT IN (477,478)";
		providerInfoList = jdbcTemplate.query(fetchPracticeSQL,
				new RowMapper<ProviderInfo>() {
					public ProviderInfo mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ProviderInfo providerInfo = new ProviderInfo();
						providerInfo.setProviderId(rs.getInt("Provider_ID"));
						providerInfo.setProviderFirstName("Dr."
								+ rs.getString("Provider_First_Name"));
						providerInfo.setProviderLastName(rs
								.getString("PROVIDER_LAST_NAME"));
						return providerInfo;
					}
				});
		return providerInfoList;
	}

	public boolean saveAllInformation(PatientReferralInfo patientReferralInfo,
			Integer providePracticeId, String fromEmail,
			ArrayList<FileMeta> fileList, ArrayList<FileMeta> insFileList,
			Integer loggedInProvideId) {
		int provideIdLast = patientReferralInfo.getProviderInfo()
				.getProviderId();
		System.out.println("" + provideIdLast);
		int refProvActionId = 0;
		Integer iToprovider = patientReferralInfo.getProviderInfo()
				.getProviderId();
		Integer insuraceId = patientReferralInfo.getPatientInfo()
				.getPatientInsuranceInfoList().get(0).getInsuranceInfo()
				.getInsuranceId();
		System.out.println(insuraceId);
		if (patientReferralInfo != null) {
			String companyName = null;
			InsuranceInfo insuranceInfo = new InsuranceInfo();
			if (patientReferralInfo != null) {
				for (int j = 0; j < patientReferralInfo.getPatientInfo()
						.getPatientInsuranceInfoList().size(); j++) {
					companyName = patientReferralInfo.getPatientInfo()
							.getPatientInsuranceInfoList().get(j)
							.getInsuranceInfo().getInsuranceCompany();
					insuranceInfo.setInsuranceCompany(companyName);
					PatientInfo patientInfo = new PatientInfo();
					if (patientReferralInfo.getPatientInfo().getPatientId() != null) {
						String sqlQury = "UPDATE rad_patient_info SET   PATIENT_EMAIL = ?, PHONE_NO =?  WHERE PATIENT_ID = ? ";
						Object[] params1 = new Object[] {
								patientReferralInfo.getPatientInfo()
										.getPatientEmail(),
								patientReferralInfo.getPatientInfo()
										.getRadLocation().getLocPhone(),
								patientReferralInfo.getPatientInfo()
										.getPatientId() };
						int[] types1 = new int[] { Types.VARCHAR,
								Types.VARCHAR, Types.INTEGER };
						int row1 = jdbcTemplateObject.update(sqlQury, params1,
								types1);

						String sqlQury44 = "UPDATE rad_referral_provider_draft SET IN_DRAFT = 'NO' WHERE PROVIDER_ID = ? AND	PATIENT_ID = ? ";
						Object[] params44 = new Object[] {
								patientReferralInfo.getProId(),
								patientReferralInfo.getPatientInfo()
										.getPatientId() };
						int[] types44 = new int[] { Types.INTEGER,
								Types.INTEGER };
						int row44 = jdbcTemplateObject.update(sqlQury44,
								params44, types44);

					}
					System.out.println(insuranceInfo.getInsuranceId());
					patientReferralInfo.getPatientInfo()
							.getPatientInsuranceInfoList().get(j)
							.setInsuranceInfo(insuranceInfo);
				}
			}
			PracticeLocations locations = new PracticeLocations();
			locations.setRadLocation(patientReferralInfo.getPracticeInfo()
					.getPracticeLocations().getRadLocation());
			locations.setCreationDate(patientReferralInfo.getCreationDate());
			locations.setCreatedBy(patientReferralInfo.getCreatedBy());
			locations.setUpdatedBy(patientReferralInfo.getUpdatedBy());
			locations.setUpdatedDate(patientReferralInfo.getUpdatedDate());
			locations.setPracticeInfo(patientReferralInfo.getPracticeInfo());
			PatientInfo patientInfo = new PatientInfo();
			patientInfo.setPatientFirstName(patientReferralInfo
					.getPatientInfo().getPatientFirstName());
			patientInfo.setPatientLastName(patientReferralInfo.getPatientInfo()
					.getPatientLastName());
			patientInfo.setPatientGender(patientReferralInfo.getPatientInfo()
					.getPatientGender());
			patientInfo.setPatientSSN(patientReferralInfo.getPatientInfo()
					.getPatientSSN());
			patientInfo.setPatientDob(patientReferralInfo.getPatientInfo()
					.getPatientDob());
			patientInfo.setRadLocation(patientReferralInfo.getPatientInfo()
					.getRadLocation());
			patientInfo.setCreatedBy(patientReferralInfo.getPatientInfo()
					.getCreatedBy());
			patientInfo.setUpdatedBy(patientReferralInfo.getPatientInfo()
					.getUpdatedBy());
			patientInfo.setCreationDate(patientReferralInfo.getPatientInfo()
					.getCreationDate());
			patientInfo.setUpdatedDate(patientReferralInfo.getPatientInfo()
					.getUpdatedDate());
			if (patientReferralInfo.getPatientInfo().getPatientId() != null) {
				System.out.println("inside patient");
				patientInfo.setPatientId(patientReferralInfo.getPatientInfo()
						.getPatientId());
			}
			if (patientReferralInfo.getPatientInfo().getPatientId() == null) {
				String sql = "INSERT INTO rad_location (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE, LOC_ADDRESS1, LOC_ADDRESS2, LOC_CITY, LOC_FAX, LOC_PHONE, LOC_ZIP, LOC_STATE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				Object[] params = new Object[] { patientInfo.getCreatedBy(),
						patientInfo.getCreationDate(),
						patientInfo.getUpdatedBy(),
						patientInfo.getUpdatedDate(), null, null, null, null,
						patientInfo.getRadLocation().getLocPhone(), null, null };
				int[] types = new int[] { Types.VARCHAR, Types.TIMESTAMP,
						Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
				int row = jdbcTemplateObject.update(sql, params, types);
			}
			List<RadLocation> maxLocId = null;
			JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
			// =====================================================
			String fetchChAdminSQL = "SELECT LOC_ID FROM rad_location WHERE LOC_ID = (SELECT MAX(LOC_ID) FROM rad_location)";
			maxLocId = jdbcTemplate.query(fetchChAdminSQL,
					new RowMapper<RadLocation>() {

						public RadLocation mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							RadLocation maxLocId = new RadLocation();
							maxLocId.setLocId(rs.getInt("LOC_ID"));
							return maxLocId;
						}

					});

			if (patientReferralInfo.getPatientInfo().getPatientId() == null) {
				String sqlQury = "INSERT INTO rad_patient_info (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE, PATIENT_DOB,  PATIENT_FIRST_NAME, PATIENT_GENDER, PATIENT_LAST_NAME, PATIENT_SSN, PATIENT_LOC_ID,PATIENT_EMAIL,PHONE_NO) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				Object[] params1 = new Object[] {
						patientInfo.getCreatedBy(),
						patientInfo.getCreationDate(),
						patientInfo.getUpdatedBy(),
						patientInfo.getUpdatedDate(),
						patientInfo.getPatientDob(),
						patientInfo.getPatientFirstName(),
						patientInfo.getPatientGender(),
						patientInfo.getPatientLastName(),
						null,
						maxLocId.get(0).getLocId(),
						patientReferralInfo.getPatientInfo().getPatientEmail(),
						patientReferralInfo.getPatientInfo().getRadLocation()
								.getLocPhone() };
				int[] types1 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
						Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP,
						Types.VARCHAR, Types.CHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR };
				int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);
			} else {
				String sqlQury = "UPDATE rad_location SET LOC_ADDRESS1 = ? ,LOC_ADDRESS2 = ? ,LOC_CITY = ? , LOC_ZIP = ? , LOC_PHONE = ? , LOC_FAX = ? ,LOC_STATE_ID=? WHERE LOC_ID = ? ;";
				Object[] params1 = new Object[] { null, null, null, null,
						patientInfo.getRadLocation().getLocPhone(), null, null,
						patientInfo.getRadLocation().getLocId() };
				int[] types1 = new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.INTEGER };

				int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);

			}
			for (int j = 0; j < patientReferralInfo.getPatientInfo()
					.getPatientInsuranceInfoList().size(); j++) {
				PatientInfo patientInfo2 = new PatientInfo();
				patientInfo2.setPatientId(patientInfo.getPatientId());
				patientReferralInfo.getPatientInfo()
						.getPatientInsuranceInfoList().get(j)
						.setPatientInfo(patientInfo2);
			}
			patientReferralInfo.getPatientInfo().setPatientId(
					(patientInfo.getPatientId()));
			List<InsuranceInfo> maxInsId = null;
			JdbcTemplate jdbcTemplate1 = CommonUtils.getJdbcTemplate();
			// =====================================================
			String fetchMaxInsuIdSQL = "SELECT INSURANCE_ID FROM rad_insurance_info WHERE INSURANCE_COMPANY='"
					+ companyName + "'";
			maxInsId = jdbcTemplate.query(fetchMaxInsuIdSQL,
					new RowMapper<InsuranceInfo>() {

						public InsuranceInfo mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							InsuranceInfo maxLInsuraId = new InsuranceInfo();
							maxLInsuraId.setInsuranceId(rs
									.getInt("INSURANCE_ID"));
							return maxLInsuraId;
						}

					});
			List<PatientInfo> maxPatId = null;
			if (patientReferralInfo.getPatientInfo().getPatientId() == null) {
				JdbcTemplate jdbcTemplate11 = CommonUtils.getJdbcTemplate();
				// =====================================================
				String fetchMaxPatIdSQL = "SELECT PATIENT_ID FROM rad_patient_info WHERE PATIENT_ID = (SELECT MAX(PATIENT_ID) FROM rad_patient_info)";
				maxPatId = jdbcTemplate.query(fetchMaxPatIdSQL,
						new RowMapper<PatientInfo>() {

							public PatientInfo mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								PatientInfo maxPatientId = new PatientInfo();
								maxPatientId.setPatientId(rs
										.getInt("Patient_ID"));
								return maxPatientId;
							}

						});
				if (maxPatId.size() != 0) {
					patientReferralInfo.getPatientInfo().setPatientId(
							maxPatId.get(0).getPatientId());
				}
			}
			if (insuraceId == null) {
				if (maxInsId.size() != 0) {
					String sqlQury1 = "INSERT INTO rad_patient_insurance_info (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,PAT_INS_ID, Patient_ID, PAT_INSURANCE_GROUP, PAT_INSURANCE_NOTES, PAT_INSURANCE_PHONE, PAT_PREAUTH_REQ, PAT_PREAUTH_CONTACT_NAME, PAT_PREAUTH_END_DATE, PAT_PREAUTH_ID, PAT_PREAUTH_START_DATE, PAT_PREAUTH_CONFIRMATIOn,PAT_INSURANCE_ID) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					Object[] params11 = new Object[] {
							patientInfo.getCreatedBy(),
							patientInfo.getCreationDate(),
							patientInfo.getUpdatedBy(),
							patientInfo.getUpdatedDate(),
							maxInsId.get(0).getInsuranceId(),
							patientReferralInfo.getPatientInfo().getPatientId(),
							patientReferralInfo.getPatientInfo()
									.getPatientInsuranceInfoList().get(0)
									.getPatientInsuranceGroup(),
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							patientReferralInfo.getPatientInfo()
									.getPatientInsuranceInfoList().get(0)
									.getPatientInsuranceId() };
					int[] types11 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
							Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR, Types.CHAR, Types.VARCHAR,
							Types.TIMESTAMP, Types.INTEGER, Types.TIMESTAMP,
							Types.VARCHAR, Types.VARCHAR };
					int row11 = jdbcTemplateObject.update(sqlQury1, params11,
							types11);
					// =====================================================
					List<PatientInsuranceInfo> patinsinfoid = null;
					String sqlinsinfoid = "SELECT PAT_INS_INFO_ID FROM rad_patient_insurance_info WHERE Patient_ID ="
							+ patientReferralInfo.getPatientInfo()
									.getPatientId()
							+ " ORDER BY PAT_INS_INFO_ID DESC";
					patinsinfoid = jdbcTemplate.query(sqlinsinfoid,
							new RowMapper<PatientInsuranceInfo>() {

								public PatientInsuranceInfo mapRow(
										ResultSet rs1, int rowNumber)
										throws SQLException {
									PatientInsuranceInfo newpatinsinfoid = new PatientInsuranceInfo();
									newpatinsinfoid
											.setPatientInsuranceInfoId(rs1
													.getInt("PAT_INS_INFO_ID"));
									return newpatinsinfoid;
								}

							});
					insinfoid = patinsinfoid.get(0).getPatientInsuranceInfoId();
				}
			} else {

				PatientInsuranceInfo patientInsuranceInfo = patientReferralInfo
						.getPatientInfo().getPatientInsuranceInfoList().get(0);
				System.out.println("its PAT_INS_INFO_ID "
						+ patientInsuranceInfo.getPatientInsuranceInfoId());

				String sqlQury = "UPDATE rad_patient_insurance_info SET UPDATED_BY = ? ,UPDATED_DATE = ? ,PAT_INS_ID = ? , Patient_ID = ? , PAT_INSURANCE_GROUP = ? , PAT_INSURANCE_NOTES = ?,PAT_INSURANCE_PHONE=?,PAT_PREAUTH_REQ=?,PAT_PREAUTH_CONTACT_NAME=?,PAT_PREAUTH_END_DATE=?,PAT_PREAUTH_ID=?,PAT_PREAUTH_START_DATE=?,PAT_PREAUTH_CONFIRMATIOn=?,PAT_INSURANCE_ID=? WHERE PAT_INS_INFO_ID = ? ;";

				Object[] params1 = new Object[] {
						patientReferralInfo.getUpdatedBy(),
						patientReferralInfo.getUpdatedDate(), insuraceId,
						patientReferralInfo.getPatientInfo().getPatientId(),
						patientInsuranceInfo.getPatientInsuranceGroup(),
						patientInsuranceInfo.getPatientInsuranceNotes(),
						patientInsuranceInfo.getPatientInsurancePhone(),
						patientInsuranceInfo.getPatientPre1AuthReq(),
						patientInsuranceInfo.getPatientPreAuthContactName(),
						patientInsuranceInfo.getPatientPreAuthEndDate(),
						patientInsuranceInfo.getPatientPreAuthId(),
						patientInsuranceInfo.getPatientPreAuthStartDate(),
						patientInsuranceInfo.getPatientPreauthConfirmation(),
						patientInsuranceInfo.getPatientInsuranceId(),
						patientInsuranceInfo.getPatientInsuranceInfoId() };
				int[] types1 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.CHAR,
						Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
						Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER };
				insinfoid = patientInsuranceInfo.getPatientInsuranceInfoId();
				int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);

			}
			Integer practiceId = null;
			if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
				practiceId = patientReferralInfo.getPracticeInfo()
						.getPracticeId();
			}
			Integer providerId = null;
			if (patientReferralInfo.getProviderInfo().getProviderId() != 0) {
				providerId = patientReferralInfo.getProviderInfo()
						.getProviderId();
			}
			String sqlQury11 = "INSERT INTO rad_patient_referral_info (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,CH_ID,PATIENT_ID,PRAC_SPL_ID,PRACTICE_ID,PROVIDER_ID) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?)";
			Object[] params111 = new Object[] {
					patientInfo.getCreatedBy(),
					patientInfo.getCreationDate(),
					patientInfo.getUpdatedBy(),
					patientInfo.getUpdatedDate(),
					patientReferralInfo.getChInfo().getId(),
					patientReferralInfo.getPatientInfo().getPatientId(),
					patientReferralInfo.getPracticeSpecialty()
							.getPraticeSplID(), practiceId, providerId };
			int[] types111 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
					Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,
					Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER };
			int rowPatReferInfo = jdbcTemplateObject.update(sqlQury11,
					params111, types111);

			List<PatientReferralInfo> maxPatRefId = null;
			JdbcTemplate jdbcTemplateRef = CommonUtils.getJdbcTemplate();
			// =====================================================
			String fetchMaxPatRefIdSQL = "SELECT PATIENT_REFERRAL_ID FROM rad_patient_referral_info WHERE PATIENT_REFERRAL_ID = (SELECT MAX(PATIENT_REFERRAL_ID) FROM rad_patient_referral_info)";
			maxPatRefId = jdbcTemplate.query(fetchMaxPatRefIdSQL,
					new RowMapper<PatientReferralInfo>() {

						public PatientReferralInfo mapRow(ResultSet rs,
								int rowNumber) throws SQLException {
							PatientReferralInfo maxPatientRefId = new PatientReferralInfo();
							maxPatientRefId.setRefId(rs
									.getInt("PATIENT_REFERRAL_ID"));
							return maxPatientRefId;
						}

					});

			String refProAct = "INSERT INTO rad_referral_provider_action (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,REF_PROV_REF_REASON,REF_PROV_DIAG_CODE,REF_PROV_NOTES,REF_PROVIDER_ID,REFERRAL_ID) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?)";
			Object[] paramsProAct = new Object[] {
					patientInfo.getCreatedBy(),
					patientInfo.getCreationDate(),
					patientInfo.getUpdatedBy(),
					patientInfo.getUpdatedDate(),
					patientReferralInfo.getReferral_Provider_ActionList()
							.get(0).getProviderRefReasons(),
					patientReferralInfo.getReferral_Provider_ActionList()
							.get(0).getProviderDiagCode(),
					patientReferralInfo.getReferral_Provider_ActionList()
							.get(0).getProviderNotes(),
					patientReferralInfo.getProId(),
					maxPatRefId.get(0).getRefId() };
			int[] typesProvAc = new int[] { Types.VARCHAR, Types.TIMESTAMP,
					Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER };
			int rowRefProAct = jdbcTemplateObject.update(refProAct,
					paramsProAct, typesProvAc);

			String refLifeCycle = "INSERT INTO rad_referral_life_cycle(CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE,PRACTICE_ID,REF_CD_REFSTS_ID,PATIENT_STATUS_TIMESTAMP,REFERRAL_ID)VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
			Object[] paramsLifeCycle = new Object[] {
					patientInfo.getCreatedBy(), patientInfo.getCreationDate(),
					patientInfo.getUpdatedBy(), patientInfo.getUpdatedDate(),
					providePracticeId, 1, patientInfo.getUpdatedDate(),
					maxPatRefId.get(0).getRefId() };
			int[] typesLifeCycle = new int[] { Types.VARCHAR, Types.TIMESTAMP,
					Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,
					Types.INTEGER, Types.TIMESTAMP, Types.INTEGER };
			int rowLifeCycle = jdbcTemplateObject.update(refLifeCycle,
					paramsLifeCycle, typesLifeCycle);

			if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
				String refCurreSt = "INSERT INTO rad_refferal_current_status(CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE,REFERRAL_ID,PRACTICE_ID)VALUES ( ?, ?, ?, ?, ?, ?)";
				Object[] paramsCurSta = new Object[] {
						patientInfo.getCreatedBy(),
						patientInfo.getCreationDate(),
						patientInfo.getUpdatedBy(),
						patientInfo.getUpdatedDate(),
						maxPatRefId.get(0).getRefId(),
						patientReferralInfo.getPracticeInfo().getPracticeId() };
				int[] typesCurrSta = new int[] { Types.VARCHAR,
						Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,
						Types.INTEGER, Types.INTEGER };
				int rowCurrStatus = jdbcTemplateObject.update(refCurreSt,
						paramsCurSta, typesCurrSta);
			} else {
				String refCurreSt = "INSERT INTO rad_refferal_current_status(CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE,REFERRAL_ID,CH_ID)VALUES ( ?, ?, ?, ?, ?, ?)";
				Object[] paramsCurSta = new Object[] {
						patientInfo.getCreatedBy(),
						patientInfo.getCreationDate(),
						patientInfo.getUpdatedBy(),
						patientInfo.getUpdatedDate(),
						maxPatRefId.get(0).getRefId(),
						patientReferralInfo.getChInfo().getId() };
				int[] typesCurrSta = new int[] { Types.VARCHAR,
						Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,
						Types.INTEGER, Types.INTEGER };
				int rowCurrStatus = jdbcTemplateObject.update(refCurreSt,
						paramsCurSta, typesCurrSta);
			}
			patientReferralInfo.getProviderInfo().setProviderId(
					patientReferralInfo.getProId());
			for (int k = 0; k < patientReferralInfo
					.getReferral_Provider_ActionList().size(); k++) {
				String s = patientReferralInfo
						.getReferral_Provider_ActionList().get(k)
						.getProviderDiagCode();
				String ss[] = s.split(",");
				String[] daigCode = patientReferralInfo
						.getReferral_Provider_ActionList().get(k)
						.getProviderDiagCode().split(",");
				String[] reasons = patientReferralInfo
						.getReferral_Provider_ActionList().get(k)
						.getProviderRefReasons().split(",");
				String[] notes = patientReferralInfo
						.getReferral_Provider_ActionList().get(k)
						.getProviderNotes().split(",");
				if (ss.length > 1) {
					for (int i = 0; i < ss.length; i++) {
						if (i != 0 && i != 1) {
							Referral_Provider_Action referral_Provider_Action = new Referral_Provider_Action();
							referral_Provider_Action
									.setPatientReferralInfo(patientReferralInfo);
							referral_Provider_Action
									.setProviderDiagCode(daigCode[i]);
							referral_Provider_Action
									.setProviderRefReasons(reasons[i]);
							referral_Provider_Action.setProviderNotes(notes[i]);
							referral_Provider_Action
									.setCreatedBy(patientReferralInfo
											.getReferral_Provider_ActionList()
											.get(k).getCreatedBy());
							referral_Provider_Action
									.setCreationDate(patientReferralInfo
											.getReferral_Provider_ActionList()
											.get(k).getCreationDate());
							referral_Provider_Action
									.setUpdatedBy(patientReferralInfo
											.getReferral_Provider_ActionList()
											.get(k).getUpdatedBy());
							referral_Provider_Action
									.setUpdatedDate(patientReferralInfo
											.getReferral_Provider_ActionList()
											.get(k).getUpdatedDate());
							referral_Provider_Action
									.setProviderInfo(patientReferralInfo
											.getProviderInfo());
						}
					}
				} else {
					Referral_Provider_Action referral_Provider_Action = new Referral_Provider_Action();
					referral_Provider_Action
							.setPatientReferralInfo(patientReferralInfo);
					referral_Provider_Action
							.setProviderDiagCode(patientReferralInfo
									.getReferral_Provider_ActionList().get(k)
									.getProviderDiagCode());
					referral_Provider_Action
							.setProviderRefReasons(patientReferralInfo
									.getReferral_Provider_ActionList().get(k)
									.getProviderRefReasons());
					referral_Provider_Action
							.setProviderNotes(patientReferralInfo
									.getReferral_Provider_ActionList().get(k)
									.getProviderNotes());
					referral_Provider_Action.setCreatedBy(patientReferralInfo
							.getReferral_Provider_ActionList().get(k)
							.getCreatedBy());
					referral_Provider_Action
							.setCreationDate(patientReferralInfo
									.getReferral_Provider_ActionList().get(k)
									.getCreationDate());
					referral_Provider_Action.setUpdatedBy(patientReferralInfo
							.getReferral_Provider_ActionList().get(k)
							.getUpdatedBy());
					referral_Provider_Action.setUpdatedDate(patientReferralInfo
							.getReferral_Provider_ActionList().get(k)
							.getUpdatedDate());
					referral_Provider_Action
							.setProviderInfo(patientReferralInfo
									.getProviderInfo());
					referral_Provider_Action
							.setRefProvActionTimeStamp(new Date());

					// Added by Dileep
					if (referral_Provider_Action.getRefProviderActionId() != null) {
						refProvActionId = referral_Provider_Action
								.getRefProviderActionId();
					}
				}
			}

			RefferalCurrentStatus currentStatus = new RefferalCurrentStatus();
			currentStatus.setPatientReferralInfo(patientReferralInfo);
			currentStatus
					.setPracticeInfo(patientReferralInfo.getPracticeInfo());
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
			CommonUtils.uploadAttachments(insFileList, patientReferralInfo
					.getPatientInfo().getPatientId(), maxPatRefId.get(0)
					.getRefId(), maxRefProvActId.get(0)
					.getRefProviderActionId(), true, insinfoid);
			CommonUtils.uploadAttachments(fileList, patientReferralInfo
					.getPatientInfo().getPatientId(), maxPatRefId.get(0)
					.getRefId(), maxRefProvActId.get(0)
					.getRefProviderActionId(), true);
			if (patientReferralInfo.getPracticeInfo().getPracticeId() != 0) {
				try {
					String lastName = null;
					List<ProviderInfo> emailIdlist = null;
					String fetchMaxrefIdSQL11 = "SELECT PROVIDER_EMAIL,PROVIDER_FIRST_NAME,PROVIDER_LAST_NAME FROM rad_provider_info WHERE PROVIDER_PRAC_ID= "
							+ patientReferralInfo.getPracticeInfo()
									.getPracticeId()
							+ " AND (PROVIDER_CD_PRVROLE_ID=477 OR PROVIDER_CD_PRVROLE_ID=478)";
					emailIdlist = jdbcTemplate.query(fetchMaxrefIdSQL11,
							new RowMapper<ProviderInfo>() {

								public ProviderInfo mapRow(ResultSet rs,
										int rowNumber) throws SQLException {
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
						providerList = jdbcTemplate.query(fetchMaxrefIdSQL1,
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
							lastName = providerList.get(0)
									.getProviderLastName();
							System.out.println("===" + lastName);
						}
					}
					// //code for change select query
					List<PracticeInfo> practicelist = null;
					String fetchMaxrefIdSQL1 = "SELECT PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID="
							+ providePracticeId;
					practicelist = jdbcTemplate.query(fetchMaxrefIdSQL1,
							new RowMapper<PracticeInfo>() {

								public PracticeInfo mapRow(ResultSet rs,
										int rowNumber) throws SQLException {
									PracticeInfo practicelist = new PracticeInfo();
									practicelist.setPracticeName(rs
											.getString("PRACTICE_NAME"));
									return practicelist;
								}
							});

					String rpo = "INSERT INTO rad_referral_rec_tracking ( REFERRAL_ID, REF_PROVIDER_ID, REF_CD_REFACTION_ID, REF_PAT_REC_ACTN_TIMESTAMP,    CREATED_BY, CREATION_DATE,UPDATED_BY, UPDATED_DATE, CH_ID, PRACTICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?)";
					Object[] paramsrpo = new Object[] {
							maxPatRefId.get(0).getRefId(), loggedInProvideId,
							maxRefProvActId.get(0).getRefProviderActionId(),
							patientInfo.getUpdatedDate(),
							patientInfo.getCreatedBy(),
							patientInfo.getCreationDate(),
							patientInfo.getUpdatedBy(),
							patientInfo.getUpdatedDate(), null,
							providePracticeId };
					int[] typesrpo = new int[] { Types.INTEGER, Types.INTEGER,
							Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR,
							Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP,
							Types.INTEGER, Types.INTEGER };
					int rowCurrStatus = jdbcTemplateObject.update(rpo,
							paramsrpo, typesrpo);

					String practiceName = null;
					String toMail = null;
					if (!practicelist.isEmpty())
						practiceName = (String) practicelist.get(0)
								.getPracticeName();
					String subject = "Patient Referral";
					for (int i = 0; i < emailIdlist.size(); i++) {
						String body = null;
						String providerName = null;
						if (lastName == null) {
							providerName = emailIdlist.get(i)
									.getProviderLastName();
						} else {
							providerName = lastName;
						}
						body = "Dear Dr. "
								+ providerName
								+ " \n  "
								+ practiceName
								+ ". has referred a patient to your practice. Please login here - www.tinyurl.com/referadr to check the patient details. \n Best \n ReferADr  ";
						toMail = emailIdlist.get(i).getProviderEmail();// (String)practiceAdmin[0];
						if (!patientReferralInfo.isSchedulFlag()) {
							ReferadrUtils.sendMail(fromEmail, toMail, subject,
									body);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					List<CHAdmin> emailIdlist = null;
					String fetchMaxrefIdSQLAdminRole = "SELECT CH_ADMIN_ROLE_FIRST_NAME, CH_ADMIN_ROLE_LAST_NAME, CH_ADMIN_ROLE_EMAIL	FROM rad_ch_admin WHERE CH_ADMIN_CH_ID="
							+ patientReferralInfo.getChInfo().getId();
					emailIdlist = jdbcTemplate.query(fetchMaxrefIdSQLAdminRole,
							new RowMapper<CHAdmin>() {

								public CHAdmin mapRow(ResultSet rs,
										int rowNumber) throws SQLException {
									CHAdmin emailIdlist = new CHAdmin();
									emailIdlist.setFirstName(rs
											.getString("CH_ADMIN_ROLE_FIRST_NAME"));
									emailIdlist.setLastName(rs
											.getString("CH_ADMIN_ROLE_LAST_NAME"));
									emailIdlist.setEmaiId(rs
											.getString("CH_ADMIN_ROLE_EMAIL"));
									return emailIdlist;
								}
							});
					List<PracticeInfo> practicelist = null;
					String fetchMaxrefIdSQL1 = "SELECT PRACTICE_NAME FROM rad_practice_info WHERE PRACTICE_ID="
							+ providePracticeId;
					practicelist = jdbcTemplate.query(fetchMaxrefIdSQL1,
							new RowMapper<PracticeInfo>() {

								public PracticeInfo mapRow(ResultSet rs,
										int rowNumber) throws SQLException {
									PracticeInfo practicelist = new PracticeInfo();
									practicelist.setPracticeName(rs
											.getString("PRACTICE_NAME"));
									return practicelist;
								}
							});
					String lastNameP = emailIdlist.get(0).getLastName();
					String practiceName = null;
					String toMail = null;
					if (!practicelist.isEmpty())
						practiceName = (String) practicelist.get(0)
								.getPracticeName();
					if (!emailIdlist.isEmpty())
						toMail = emailIdlist.get(0).getEmaiId();
					String subject = "Patient Referral";
					String body = "Dear Dr. "
							+ lastNameP
							+ " \n  "
							+ practiceName
							+ ". has referred a patient to your practice. Please login here - www.tinyurl.com/referadr to check the patient details. \n Best \n ReferADr  ";
					System.out
							.println("========ReferPatientRepositoryImpl=======");
					ReferadrUtils.sendMail(fromEmail, toMail, subject, body);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// session.close();
				}
			}
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
			Integer iFromprovider = patientReferralInfo.getProviderInfo()
					.getProviderId();
			if (iFromprovider == 0) {
				iFromprovider = null;
			}

			if (iToprovider == 0) {
				iToprovider = null;
			}
			Integer iToCH = patientReferralInfo.getChInfo().getId();
			if (iToCH == 0) {
				iToCH = null;
			}
			// for report table
			System.out.println(maxPatRefId.get(0).getRefId());
			String rpo = "INSERT INTO report (FROM_PROVIDER_ID, TO_PROVIDER_ID, TO_SPL_ID, FROM_PRACTICE_ID, TO_PRACTICE_ID, FROM_CH_ID, TO_CH_ID,CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,REFFERAL_ID)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			Object[] paramsrpo = new Object[] { iFromprovider, iToprovider,
					iTospl, iFromPractice, iToPractice, null, iToCH,
					patientInfo.getCreatedBy(), patientInfo.getCreationDate(),
					patientInfo.getUpdatedBy(), patientInfo.getUpdatedDate(),
					maxPatRefId.get(0).getRefId() };
			int[] typesrpo = new int[] { Types.INTEGER, Types.INTEGER,
					Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER,
					Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,
					Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
			int rowCurrStatus = jdbcTemplateObject.update(rpo, paramsrpo,
					typesrpo);

			// call save Schedule method
			// int respond=
			// InboxRepositoryImpl.saveShedule(dateTime,refId,provId,withProvId,notes,fromEmail);

			// int
			// result=inboxRepository.saveShedule(dateTime,refId,provId,withProvId,notes,fromEmail);

			if (patientReferralInfo.isSchedulFlag()) {

				String schrduledate = patientReferralInfo.getScheduleDate();
				String schrduletime = patientReferralInfo.getScheduleTime();
				int refId = maxPatRefId.get(0).getRefId();
				int provId = patientReferralInfo.getProId();
				int withProvId = provideIdLast;
				String notes = patientReferralInfo.getScheduleNotes();
				fromEmail = "";

				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String currentDate = "" + dateFormat.format(date);

				// to save in shedule table
				String saveSchedule = "INSERT INTO rad_referral_provider_shedule(REFFERAL_ID,PROV_ID,SHEDULE_DATE,SHEDULE_TIME,CREATION_DATE,UPDATED_DATE)VALUES(?,?,?,?,?,?)";
				Object[] paramsShedule = new Object[] { refId, withProvId,
						schrduledate, schrduletime, currentDate, currentDate };
				int[] typesShedule = new int[] { Types.INTEGER, Types.INTEGER,
						Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
						Types.TIMESTAMP };
				int rowCurrStatus5 = jdbcTemplateObject.update(saveSchedule,
						paramsShedule, typesShedule);

				// to get schedule id
				String sqlgetscheduleid = "SELECT	SHEDULE_ID FROM rad_referral_provider_shedule WHERE CREATION_DATE='"
						+ currentDate
						+ "' AND REFFERAL_ID="
						+ refId
						+ " AND PROV_ID=" + withProvId + "";
				Integer scheduleId = null;
				try {
					scheduleId = jdbcTemplateObject.query(sqlgetscheduleid,
							new RowMapper<Integer>() {

								public Integer mapRow(ResultSet rs,
										int rowNumber) throws SQLException {
									Integer providerId = rs
											.getInt("SHEDULE_ID");
									return providerId;
								}

							}).get(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.print("schedule id" + scheduleId);

				// to save in provider action table
				String saveactionProv = "INSERT INTO rad_referral_provider_action(REF_PROV_NOTES,REFERRAL_ID,REF_PROVIDER_ID,CREATION_DATE,UPDATED_DATE,SCHEDULE_ID)VALUES(?,?,?,?,?,?)";
				Object[] params = new Object[] { notes, refId, provId,
						currentDate, currentDate, scheduleId };
				int[] types = new int[] { Types.VARCHAR, Types.INTEGER,
						Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP,
						Types.INTEGER };
				int rowCurrStatus1 = jdbcTemplateObject.update(saveactionProv,
						params, types);

				String referingDrEmailId = "SELECT rpii.PROVIDER_FIRST_NAME,rpii.PROVIDER_LAST_NAME,rpii.PROVIDER_EMAIL FROM rad_provider_info rpii WHERE (PROVIDER_CD_PRVROLE_ID=477 OR PROVIDER_CD_PRVROLE_ID=478) AND PROVIDER_PRAC_ID=(SELECT rpi.PROVIDER_PRAC_ID FROM rad_provider_info rpi WHERE rpi.PROVIDER_ID="
						+ withProvId + ")";
				List<ProviderInfo> referingDocInfo = jdbcTemplateObject.query(
						referingDrEmailId, new RowMapper<ProviderInfo>() {

							public ProviderInfo mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								ProviderInfo providerInfo = new ProviderInfo();
								providerInfo.setProviderEmail(rs
										.getString("PROVIDER_EMAIL"));
								providerInfo.setProviderLastName(rs
										.getString("PROVIDER_LAST_NAME"));
								return providerInfo;
							}

						});

				String patientNameInfo = "SELECT PATIENT_FIRST_NAME,PATIENT_EMAIL,PATIENT_HIPAA_AGREEMENT FROM rad_patient_referral_info  rpri LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID = rpri.PATIENT_ID WHERE PATIENT_REFERRAL_ID="
						+ refId + "";
				PatientInfo patientInfo2 = new PatientInfo();
				List<PatientInfo> patientInfos = new ArrayList<PatientInfo>();
				patientInfos = jdbcTemplateObject.query(patientNameInfo,
						new RowMapper<PatientInfo>() {

							public PatientInfo mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								PatientInfo info = new PatientInfo();
								info.setPatientFirstName(rs
										.getString("PATIENT_FIRST_NAME"));
								info.setPatientEmail(rs
										.getString("PATIENT_EMAIL"));
								info.setPatientHipaaAgreement(rs
										.getString("PATIENT_HIPAA_AGREEMENT"));
								return info;
							}
						});
				if (!patientInfos.isEmpty()) {
					patientInfo2 = patientInfos.get(0);
				}
				String patientName = patientInfo2.getPatientFirstName();

				/*
				 * String referedDocName=
				 * "SELECT PROVIDER_LAST_NAME  FROM rad_provider_info WHERE PROVIDER_ID="
				 * +withProvId+""; String referedDrName =
				 * jdbcTemplateObject.query(referedDocName, new
				 * RowMapper<String>() {
				 * 
				 * public String mapRow(ResultSet rs, int rowNumber) throws
				 * SQLException { String referedDrName =
				 * rs.getString("PROVIDER_LAST_NAME"); return referedDrName; }
				 * 
				 * }).get(0);
				 */
				String providerNameAndPracticename = "SELECT rpi.PROVIDER_LAST_NAME ,rprai.PRACTICE_NAME,rl.LOC_ADDRESS1,rl.LOC_ADDRESS2,rl.LOC_CITY,rs.STATE_CODE,rl.LOC_ZIP,rl.LOC_PHONE FROM rad_provider_info rpi LEFT JOIN rad_practice_info rprai ON rprai.PRACTICE_ID=rpi.PROVIDER_PRAC_ID LEFT JOIN rad_practice_locations rpl ON rpl.PRACTICE_ID=rprai.PRACTICE_ID LEFT JOIN rad_location rl ON rl.LOC_ID= rpl.LOC_ID LEFT JOIN rad_state rs ON rs.STATE_ID=rl.LOC_STATE_ID WHERE rpi.PROVIDER_ID="
						+ withProvId + "";
				ProviderInfo referedandPracticeInfo = new ProviderInfo();
				List<ProviderInfo> providerInfos = jdbcTemplateObject.query(
						providerNameAndPracticename,
						new RowMapper<ProviderInfo>() {

							public ProviderInfo mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								ProviderInfo referedandPracticeInfo = new ProviderInfo();
								RadLocation radLocation = new RadLocation();
								PracticeLocations practiceLocations = new PracticeLocations();
								practiceLocations.setRadLocation(radLocation);
								PracticeInfo practiceInfo = new PracticeInfo();
								practiceInfo
										.setPracticeLocations(practiceLocations);
								referedandPracticeInfo
										.setPracticeInfo(practiceInfo);
								referedandPracticeInfo.getPracticeInfo()
										.setPracticeName(
												rs.getString("PRACTICE_NAME"));

								referedandPracticeInfo
										.getPracticeInfo()
										.getPracticeLocations()
										.getRadLocation()
										.setLocAddress1(
												rs.getString("LOC_ADDRESS1"));
								referedandPracticeInfo
										.getPracticeInfo()
										.getPracticeLocations()
										.getRadLocation()
										.setLocAddress2(
												rs.getString("LOC_ADDRESS2"));
								referedandPracticeInfo.getPracticeInfo()
										.getPracticeLocations()
										.getRadLocation()
										.setLocCity(rs.getString("LOC_CITY"));
								referedandPracticeInfo
										.getPracticeInfo()
										.getPracticeLocations()
										.getRadLocation()
										.setLocState(rs.getString("STATE_CODE"));
								referedandPracticeInfo.getPracticeInfo()
										.getPracticeLocations()
										.getRadLocation()
										.setLocZip(rs.getString("LOC_ZIP"));
								referedandPracticeInfo.getPracticeInfo()
										.getPracticeLocations()
										.getRadLocation()
										.setLocPhone(rs.getString("LOC_PHONE"));

								referedandPracticeInfo.setProviderLastName(rs
										.getString("PROVIDER_LAST_NAME"));
								return referedandPracticeInfo;
							}

						});

				if (!providerInfos.isEmpty()) {
					referedandPracticeInfo = providerInfos.get(0);
					// referedandPracticeInfo.getPracticeInfo().getPracticeLocations();
				}

				for (ProviderInfo providerInfo : referingDocInfo) {
					String toMail = providerInfo.getProviderEmail();

					// String fromEmail="patidarsandeep991@gmail.com";
					String subject = "Refrall Update";
					String body = "Dear Dr. "
							+ providerInfo.getProviderLastName()
							+ "\n\n Your patient "
							+ patientName
							+ ". has been scheduled to see "
							+ referedandPracticeInfo.getProviderLastName()
							+ " on "
							+ schrduledate
							+ " "
							+ schrduletime
							+ ". We will keep you posted about the prognosis.\n \n Best \n "
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeName() + " ";

					try {
						ReferadrUtils
								.sendMail(fromEmail, toMail, subject, body);
					} catch (Exception e) {
						// e.printStackTrace();
						System.out.println("mail error");
					}
				}

				// patientInfo2.getPatientHipaaAgreement().equals("YES") &&
				if (!patientInfo2.getPatientEmail().equals(null)
						&& !patientInfo2.getPatientEmail().equals("")) {
					String subject1 = "Schedule Update";
					// String
					// body1="Dear "+patientName+"\n\n You are scheduled with Dr. "+referedandPracticeInfo.getProviderLastName()+"  on "+schrduledate+" "+schrduletime+"\n\n "+referedandPracticeInfo.getPracticeInfo().getPracticeName();

					// String
					// body1="Dear "+patientName+"\n\n You are scheduled with Dr. "+referedandPracticeInfo.getProviderLastName()+"  on "+dateTime+"\n\n "+referedandPracticeInfo.getPracticeInfo().getPracticeName();
					String body1 = "Dear "
							+ patientName
							+ "\n\n You have been scheduled to see Dr. "
							+ referedandPracticeInfo.getProviderLastName()
							+ " on "
							+ schrduledate
							+ " at "
							+ schrduletime
							+ " at "
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeName()
							+ ". The address for "
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeName()
							+ " is "
							+ ""
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeLocations().getRadLocation()
									.getLocAddress1()
							+ ", "
							+ ""
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeLocations().getRadLocation()
									.getLocAddress2()
							+ ", "
							+ ""
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeLocations().getRadLocation()
									.getLocCity()
							+ ", "
							+ ""
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeLocations().getRadLocation()
									.getLocState()
							+ ", "
							+ ""
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeLocations().getRadLocation()
									.getLocZip()
							+ ""
							+ ".\n\n Please call us ("
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeLocations().getRadLocation()
									.getLocPhone()
							+ ") if you would like to re-schedule or if you have any more questions. \n\n Best \n "
							+ referedandPracticeInfo.getPracticeInfo()
									.getPracticeName() + " ";

					try {
						ReferadrUtils
								.sendMail(fromEmail,
										patientInfo2.getPatientEmail(),
										subject1, body1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			return true;
		} else {
			return false;
		}
	}

	public List<RedState> getAllStateName() {
		List<RedState> stateList = null;
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchChAdminSQL = "SELECT * FROM rad_state";
		// Object[] params=new Object[]{userName};
		stateList = jdbcTemplate.query(fetchChAdminSQL,
				new RowMapper<RedState>() {

					public RedState mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						RedState redState = new RedState();
						redState.setStateId(rs.getInt("STATE_ID"));
						redState.setStateName(rs.getString("STATE_NAME"));
						redState.setStateCode(rs.getString("STATE_CODE"));
						return redState;
					}
				});
		return stateList;
	}

	public boolean saveAllProviderInfo() {
		hibernateTemplate.saveOrUpdate("ProviderInfo");
		return true;
	}

	public PatientReferralInfo getProviderReferralAction(
			Integer patientReferralId) {
		PatientReferralInfo patientReferralInfo = hibernateTemplate.get(
				PatientReferralInfo.class, patientReferralId);
		return patientReferralInfo;
	}

	public List<DoctorTimingVO> getproviderDayOffDetails(Integer practiceInfoId) {
		List<DoctorTimingVO> doctorTimingVOs = new ArrayList<DoctorTimingVO>();

		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchDayOffSQL = "SELECT PROVIDER_ID,DAY_ID,IS_OFF FROM rad_provider_day_off_map WHERE PROVIDER_ID="
				+ practiceInfoId;
		doctorTimingVOs = jdbcTemplate.query(fetchDayOffSQL,
				new RowMapper<DoctorTimingVO>() {

					public DoctorTimingVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						DoctorTimingVO doctorTimingVO = new DoctorTimingVO();
						doctorTimingVO.setDayID(rs.getInt("DAY_ID"));
						doctorTimingVO.setCheckBox(rs.getBoolean("IS_OFF"));
						return doctorTimingVO;
					}
				});

		return doctorTimingVOs;
	}

	public List<ProviderInfo> searchAllInsurance(String searchValue,
			Integer practiceID) {
		SessionFactory sessionFactory = null;
		Session session = null;
		sessionFactory = hibernateTemplate.getSessionFactory();
		session = sessionFactory.openSession();
		String additionalSql = "";

		if (practiceID != 0) {
			additionalSql = "SELECT i.INSURANCE_COMPANY FROM rad_practice_ins_mapping rpim LEFT JOIN rad_insurance_info i ON i.INSURANCE_ID=rpim.INSURANCE_ID WHERE rpim.PRACTICE_ID="
					+ practiceID
					+ " AND  rpim.INSURANCE_ID IN( SELECT DISTINCT j.INSURANCE_ID FROM rad_insurance_info j WHERE j.INSURANCE_COMPANY LIKE '"
					+ searchValue + "')";
		} else {
			additionalSql = "SELECT DISTINCT i.INSURANCE_COMPANY FROM rad_insurance_info i WHERE i.INSURANCE_COMPANY LIKE '"
					+ searchValue + "'";
		}
		// Query query1 =
		// session.createSQLQuery("select DISTINCT i.INSURANCE_COMPANY from rad_insurance_info i where i.INSURANCE_COMPANY LIKE '"+searchValue+"'");
		Query query1 = session.createSQLQuery(additionalSql);
		List searchDataList = query1.list();
		return searchDataList;

	}

	public List<ProviderInfo> searchSpl(String searchValue, Integer chID) {
		SessionFactory sessionFactory = null;
		Session session = null;
		sessionFactory = hibernateTemplate.getSessionFactory();
		session = sessionFactory.openSession();
		String additionalSql = "";

		if (chID != 0) {
			additionalSql = "SELECT DISTINCT rps.PRAC_SPL_DESC FROM rad_ch_practice_mapping rcpm LEFT JOIN rad_practice_spl_mapping rpsm ON rpsm.PRACTICE_ID=rcpm.PRACTICE_ID LEFT JOIN rad_practice_speciality rps ON rps.PRAC_SPL_ID=rpsm.PRACTICE_SPL_ID WHERE  rcpm.CH_ID="
					+ chID
					+ " AND rps.PRAC_SPL_DESC LIKE '"
					+ searchValue
					+ "'";
		} else {
			additionalSql = "SELECT PRAC_SPL_DESC FROM rad_practice_speciality WHERE PRAC_SPL_DESC LIKE '"
					+ searchValue + "'";
		}
		// Query query1 =
		// session.createSQLQuery("select DISTINCT i.INSURANCE_COMPANY from rad_insurance_info i where i.INSURANCE_COMPANY LIKE '"+searchValue+"'");
		Query query1 = session.createSQLQuery(additionalSql);
		List searchDataList = query1.list();
		return searchDataList;

	}

	public List<String> getproviderSchedulesList(Integer practiceInfoId,
			String scheduleDate) {
		List<String> scheduletimes = new ArrayList<String>();
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchScheduledTimeSQL = "SELECT SHEDULE_TIME FROM rad_referral_provider_shedule WHERE PROV_ID="
				+ practiceInfoId + " AND SHEDULE_DATE='" + scheduleDate + "'";
		scheduletimes = jdbcTemplate.query(fetchScheduledTimeSQL,
				new RowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						String scheduletime = null;
						scheduletime = rs.getString("SHEDULE_TIME");

						return scheduletime;
					}
				});

		return scheduletimes;
	}

	public List<DoctorTimingVO> getproviderTimeSlots(Integer practiceInfoId,
			String dayOfWeek) {
		List<DoctorTimingVO> doctorTimingVOs = new ArrayList<DoctorTimingVO>();
		if (dayOfWeek.equals("0")) {
			dayOfWeek = "7";
		}

		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchStartEndTimeSQL = "SELECT START_TIME,END_TIME FROM rad_provider_availability WHERE PROVIDER_ID ="
				+ practiceInfoId + " AND DAY_ID =" + dayOfWeek;
		doctorTimingVOs = jdbcTemplate.query(fetchStartEndTimeSQL,
				new RowMapper<DoctorTimingVO>() {

					public DoctorTimingVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						DoctorTimingVO doctorTimingVO = new DoctorTimingVO();
						doctorTimingVO.setStartTime(rs.getString("START_TIME"));
						doctorTimingVO.setEndTime(rs.getString("END_TIME"));
						return doctorTimingVO;
					}
				});

		return doctorTimingVOs;
	}

	public List<LabVO> getAllLabCatagory() {
		List<LabVO> labVOs = new ArrayList<LabVO>();
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchChAdminSQL = "SELECT 	LAB_CATEGORY_ID,LAB_CATEGORY_DESC FROM rad_lab_category";
		// Object[] params=new Object[]{userName};
		labVOs = jdbcTemplate.query(fetchChAdminSQL, new RowMapper<LabVO>() {

			public LabVO mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				LabVO labVO = new LabVO();
				labVO.setLab_category_id(rs.getInt("LAB_CATEGORY_ID"));
				labVO.setLab_category_desc(rs.getString("LAB_CATEGORY_DESC"));

				return labVO;
			}
		});
		return labVOs;
	}

	public boolean saveLabInformation(PatientReferralInfo patientReferralInfo,
			Integer providePracticeId, String fromEmail,
			ArrayList<FileMeta> fileList, ArrayList<FileMeta> insFileList,
			Integer loggedInProvideId) {

		int provideIdLast = patientReferralInfo.getProviderInfo()
				.getProviderId();
		System.out.println("" + provideIdLast);
		int refProvActionId = 0;
		Integer iToprovider = patientReferralInfo.getProviderInfo()
				.getProviderId();
		Integer insuraceId = patientReferralInfo.getPatientInfo()
				.getPatientInsuranceInfoList().get(0).getInsuranceInfo()
				.getInsuranceId();
		System.out.println(insuraceId);
		if (patientReferralInfo != null) {
			String companyName = null;
			InsuranceInfo insuranceInfo = new InsuranceInfo();
			if (patientReferralInfo != null) {
				for (int j = 0; j < patientReferralInfo.getPatientInfo()
						.getPatientInsuranceInfoList().size(); j++) {
					companyName = patientReferralInfo.getPatientInfo()
							.getPatientInsuranceInfoList().get(j)
							.getInsuranceInfo().getInsuranceCompany();
					insuranceInfo.setInsuranceCompany(companyName);
					PatientInfo patientInfo = new PatientInfo();
					if (patientReferralInfo.getPatientInfo().getPatientId() != null) {
						String sqlQury = "UPDATE rad_patient_info SET   PATIENT_EMAIL = ?, PHONE_NO =?  WHERE PATIENT_ID = ? ";
						Object[] params1 = new Object[] {
								patientReferralInfo.getPatientInfo()
										.getPatientEmail(),
								patientReferralInfo.getPatientInfo()
										.getRadLocation().getLocPhone(),
								patientReferralInfo.getPatientInfo()
										.getPatientId() };
						int[] types1 = new int[] { Types.VARCHAR,
								Types.VARCHAR, Types.INTEGER };
						int row1 = jdbcTemplateObject.update(sqlQury, params1,
								types1);

						String sqlQury44 = "UPDATE rad_referral_provider_draft SET IN_DRAFT = 'NO' WHERE PROVIDER_ID = ? AND	PATIENT_ID = ? ";
						Object[] params44 = new Object[] {
								patientReferralInfo.getProId(),
								patientReferralInfo.getPatientInfo()
										.getPatientId() };
						int[] types44 = new int[] { Types.INTEGER,
								Types.INTEGER };
						int row44 = jdbcTemplateObject.update(sqlQury44,
								params44, types44);

					}
					System.out.println(insuranceInfo.getInsuranceId());
					patientReferralInfo.getPatientInfo()
							.getPatientInsuranceInfoList().get(j)
							.setInsuranceInfo(insuranceInfo);
				}
			}
			PracticeLocations locations = new PracticeLocations();
			locations.setRadLocation(patientReferralInfo.getPracticeInfo()
					.getPracticeLocations().getRadLocation());
			locations.setCreationDate(patientReferralInfo.getCreationDate());
			locations.setCreatedBy(patientReferralInfo.getCreatedBy());
			locations.setUpdatedBy(patientReferralInfo.getUpdatedBy());
			locations.setUpdatedDate(patientReferralInfo.getUpdatedDate());
			locations.setPracticeInfo(patientReferralInfo.getPracticeInfo());
			PatientInfo patientInfo = new PatientInfo();
			patientInfo.setPatientFirstName(patientReferralInfo
					.getPatientInfo().getPatientFirstName());
			patientInfo.setPatientLastName(patientReferralInfo.getPatientInfo()
					.getPatientLastName());
			patientInfo.setPatientGender(patientReferralInfo.getPatientInfo()
					.getPatientGender());
			patientInfo.setPatientSSN(patientReferralInfo.getPatientInfo()
					.getPatientSSN());
			patientInfo.setPatientDob(patientReferralInfo.getPatientInfo()
					.getPatientDob());
			patientInfo.setRadLocation(patientReferralInfo.getPatientInfo()
					.getRadLocation());
			patientInfo.setCreatedBy(patientReferralInfo.getPatientInfo()
					.getCreatedBy());
			patientInfo.setUpdatedBy(patientReferralInfo.getPatientInfo()
					.getUpdatedBy());
			patientInfo.setCreationDate(patientReferralInfo.getPatientInfo()
					.getCreationDate());
			patientInfo.setUpdatedDate(patientReferralInfo.getPatientInfo()
					.getUpdatedDate());
			if (patientReferralInfo.getPatientInfo().getPatientId() != null) {
				System.out.println("inside patient");
				patientInfo.setPatientId(patientReferralInfo.getPatientInfo()
						.getPatientId());
			}
			if (patientReferralInfo.getPatientInfo().getPatientId() == null) {
				String sql = "INSERT INTO rad_location (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE, LOC_ADDRESS1, LOC_ADDRESS2, LOC_CITY, LOC_FAX, LOC_PHONE, LOC_ZIP, LOC_STATE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				Object[] params = new Object[] { patientInfo.getCreatedBy(),
						patientInfo.getCreationDate(),
						patientInfo.getUpdatedBy(),
						patientInfo.getUpdatedDate(), null, null, null, null,
						patientInfo.getRadLocation().getLocPhone(), null, null };
				int[] types = new int[] { Types.VARCHAR, Types.TIMESTAMP,
						Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
				int row = jdbcTemplateObject.update(sql, params, types);
			}
			List<RadLocation> maxLocId = null;
			JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
			// =====================================================
			String fetchChAdminSQL = "SELECT LOC_ID FROM rad_location WHERE LOC_ID = (SELECT MAX(LOC_ID) FROM rad_location)";
			maxLocId = jdbcTemplate.query(fetchChAdminSQL,
					new RowMapper<RadLocation>() {

						public RadLocation mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							RadLocation maxLocId = new RadLocation();
							maxLocId.setLocId(rs.getInt("LOC_ID"));
							return maxLocId;
						}

					});

			if (patientReferralInfo.getPatientInfo().getPatientId() == null) {
				String sqlQury = "INSERT INTO rad_patient_info (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE, PATIENT_DOB,  PATIENT_FIRST_NAME, PATIENT_GENDER, PATIENT_LAST_NAME, PATIENT_SSN, PATIENT_LOC_ID,PATIENT_EMAIL,PHONE_NO) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				Object[] params1 = new Object[] {
						patientInfo.getCreatedBy(),
						patientInfo.getCreationDate(),
						patientInfo.getUpdatedBy(),
						patientInfo.getUpdatedDate(),
						patientInfo.getPatientDob(),
						patientInfo.getPatientFirstName(),
						patientInfo.getPatientGender(),
						patientInfo.getPatientLastName(),
						null,
						maxLocId.get(0).getLocId(),
						patientReferralInfo.getPatientInfo().getPatientEmail(),
						patientReferralInfo.getPatientInfo().getRadLocation()
								.getLocPhone() };
				int[] types1 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
						Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP,
						Types.VARCHAR, Types.CHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR };
				int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);
			} else {
				String sqlQury = "UPDATE rad_location SET LOC_ADDRESS1 = ? ,LOC_ADDRESS2 = ? ,LOC_CITY = ? , LOC_ZIP = ? , LOC_PHONE = ? , LOC_FAX = ? ,LOC_STATE_ID=? WHERE LOC_ID = ? ;";
				Object[] params1 = new Object[] { null, null, null, null,
						patientInfo.getRadLocation().getLocPhone(), null, null,
						patientInfo.getRadLocation().getLocId() };
				int[] types1 = new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.INTEGER };

				int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);

			}
			for (int j = 0; j < patientReferralInfo.getPatientInfo()
					.getPatientInsuranceInfoList().size(); j++) {
				PatientInfo patientInfo2 = new PatientInfo();
				patientInfo2.setPatientId(patientInfo.getPatientId());
				patientReferralInfo.getPatientInfo()
						.getPatientInsuranceInfoList().get(j)
						.setPatientInfo(patientInfo2);
			}
			patientReferralInfo.getPatientInfo().setPatientId(
					(patientInfo.getPatientId()));
			List<InsuranceInfo> maxInsId = null;
			JdbcTemplate jdbcTemplate1 = CommonUtils.getJdbcTemplate();
			// =====================================================
			String fetchMaxInsuIdSQL = "SELECT INSURANCE_ID FROM rad_insurance_info WHERE INSURANCE_COMPANY='"
					+ companyName + "'";
			maxInsId = jdbcTemplate.query(fetchMaxInsuIdSQL,
					new RowMapper<InsuranceInfo>() {

						public InsuranceInfo mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							InsuranceInfo maxLInsuraId = new InsuranceInfo();
							maxLInsuraId.setInsuranceId(rs
									.getInt("INSURANCE_ID"));
							return maxLInsuraId;
						}

					});
			List<PatientInfo> maxPatId = null;
			if (patientReferralInfo.getPatientInfo().getPatientId() == null) {
				JdbcTemplate jdbcTemplate11 = CommonUtils.getJdbcTemplate();
				// =====================================================
				String fetchMaxPatIdSQL = "SELECT PATIENT_ID FROM rad_patient_info WHERE PATIENT_ID = (SELECT MAX(PATIENT_ID) FROM rad_patient_info)";
				maxPatId = jdbcTemplate.query(fetchMaxPatIdSQL,
						new RowMapper<PatientInfo>() {

							public PatientInfo mapRow(ResultSet rs,
									int rowNumber) throws SQLException {
								PatientInfo maxPatientId = new PatientInfo();
								maxPatientId.setPatientId(rs
										.getInt("Patient_ID"));
								return maxPatientId;
							}

						});
				if (maxPatId.size() != 0) {
					patientReferralInfo.getPatientInfo().setPatientId(
							maxPatId.get(0).getPatientId());
				}
			}
			if (insuraceId == null) {
				if (maxInsId.size() != 0) {
					String sqlQury1 = "INSERT INTO rad_patient_insurance_info (CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,PAT_INS_ID, Patient_ID, PAT_INSURANCE_GROUP, PAT_INSURANCE_NOTES, PAT_INSURANCE_PHONE, PAT_PREAUTH_REQ, PAT_PREAUTH_CONTACT_NAME, PAT_PREAUTH_END_DATE, PAT_PREAUTH_ID, PAT_PREAUTH_START_DATE, PAT_PREAUTH_CONFIRMATIOn,PAT_INSURANCE_ID) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					Object[] params11 = new Object[] {
							patientInfo.getCreatedBy(),
							patientInfo.getCreationDate(),
							patientInfo.getUpdatedBy(),
							patientInfo.getUpdatedDate(),
							maxInsId.get(0).getInsuranceId(),
							patientReferralInfo.getPatientInfo().getPatientId(),
							patientReferralInfo.getPatientInfo()
									.getPatientInsuranceInfoList().get(0)
									.getPatientInsuranceGroup(),
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							null,
							patientReferralInfo.getPatientInfo()
									.getPatientInsuranceInfoList().get(0)
									.getPatientInsuranceId() };
					int[] types11 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
							Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER,
							Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR, Types.CHAR, Types.VARCHAR,
							Types.TIMESTAMP, Types.INTEGER, Types.TIMESTAMP,
							Types.VARCHAR, Types.VARCHAR };
					int row11 = jdbcTemplateObject.update(sqlQury1, params11,
							types11);
					// =====================================================
					List<PatientInsuranceInfo> patinsinfoid = null;
					String sqlinsinfoid = "SELECT PAT_INS_INFO_ID FROM rad_patient_insurance_info WHERE Patient_ID ="
							+ patientReferralInfo.getPatientInfo()
									.getPatientId()
							+ " ORDER BY PAT_INS_INFO_ID DESC";
					patinsinfoid = jdbcTemplate.query(sqlinsinfoid,
							new RowMapper<PatientInsuranceInfo>() {

								public PatientInsuranceInfo mapRow(
										ResultSet rs1, int rowNumber)
										throws SQLException {
									PatientInsuranceInfo newpatinsinfoid = new PatientInsuranceInfo();
									newpatinsinfoid
											.setPatientInsuranceInfoId(rs1
													.getInt("PAT_INS_INFO_ID"));
									return newpatinsinfoid;
								}

							});
					insinfoid = patinsinfoid.get(0).getPatientInsuranceInfoId();
				}
			} else {

				PatientInsuranceInfo patientInsuranceInfo = patientReferralInfo
						.getPatientInfo().getPatientInsuranceInfoList().get(0);
				System.out.println("its PAT_INS_INFO_ID "
						+ patientInsuranceInfo.getPatientInsuranceInfoId());

				String sqlQury = "UPDATE rad_patient_insurance_info SET UPDATED_BY = ? ,UPDATED_DATE = ? ,PAT_INS_ID = ? , Patient_ID = ? , PAT_INSURANCE_GROUP = ? , PAT_INSURANCE_NOTES = ?,PAT_INSURANCE_PHONE=?,PAT_PREAUTH_REQ=?,PAT_PREAUTH_CONTACT_NAME=?,PAT_PREAUTH_END_DATE=?,PAT_PREAUTH_ID=?,PAT_PREAUTH_START_DATE=?,PAT_PREAUTH_CONFIRMATIOn=?,PAT_INSURANCE_ID=? WHERE PAT_INS_INFO_ID = ? ;";

				Object[] params1 = new Object[] {
						patientReferralInfo.getUpdatedBy(),
						patientReferralInfo.getUpdatedDate(), insuraceId,
						patientReferralInfo.getPatientInfo().getPatientId(),
						patientInsuranceInfo.getPatientInsuranceGroup(),
						patientInsuranceInfo.getPatientInsuranceNotes(),
						patientInsuranceInfo.getPatientInsurancePhone(),
						patientInsuranceInfo.getPatientPre1AuthReq(),
						patientInsuranceInfo.getPatientPreAuthContactName(),
						patientInsuranceInfo.getPatientPreAuthEndDate(),
						patientInsuranceInfo.getPatientPreAuthId(),
						patientInsuranceInfo.getPatientPreAuthStartDate(),
						patientInsuranceInfo.getPatientPreauthConfirmation(),
						patientInsuranceInfo.getPatientInsuranceId(),
						patientInsuranceInfo.getPatientInsuranceInfoId() };
				int[] types1 = new int[] { Types.VARCHAR, Types.TIMESTAMP,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR, Types.CHAR,
						Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR,
						Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER };
				insinfoid = patientInsuranceInfo.getPatientInsuranceInfoId();
				int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);

			}

			String sqlForLabReferral = "INSERT INTO rad_lab_patient_referral_info (PATIENT_ID, LAB_CATEGORY_ID, LAB_SUB_CATEGORY_ID, LAB_ID, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE)VALUES(?,?,?,?,?,?,?,?)";
			Object[] parameters = new Object[] {
					patientReferralInfo.getPatientInfo().getPatientId(),
					patientReferralInfo.getLab_category_id(),
					patientReferralInfo.getLab_sub_category_id(),
					patientReferralInfo.getLab_id(),
					patientInfo.getCreatedBy(), patientInfo.getCreationDate(),
					patientInfo.getUpdatedBy(), patientInfo.getUpdatedDate() };
			int[] types007 = new int[] { Types.INTEGER, Types.INTEGER,
					Types.INTEGER, Types.INTEGER, Types.VARCHAR,
					Types.TIMESTAMP, Types.VARCHAR, Types.TIMESTAMP };
			int rowPatientLabReferInfo = jdbcTemplateObject.update(
					sqlForLabReferral, parameters, types007);

			String fetchMaxLabReferralIdSQL = "SELECT MAX(LAB_REFERRAL_ID) as LabReffId FROM rad_lab_patient_referral_info ";
			List<PatientReferralInfo> maxLabReferallId = jdbcTemplate.query(
					fetchMaxLabReferralIdSQL,
					new RowMapper<PatientReferralInfo>() {
						public PatientReferralInfo mapRow(ResultSet rs,
								int rowNumber) throws SQLException {
							PatientReferralInfo patientReferralInfo = new PatientReferralInfo();
							patientReferralInfo.setLabReffId(rs
									.getInt("LabReffId"));
							return patientReferralInfo;
						}
					});
			
			
			  String refProAct=	"INSERT INTO rad_lab_referral_action ( LAB_REF_NOTES,LAB_REF_ADD_NOTES,LAB_REF_PHY,LAB_REFERRAL_ID,LAB_REF_PROVIDER_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE)VALUES	(?,?,?,?,?,?,?,?,?)";
			  Object[] paramsProAct = new Object[] {patientReferralInfo.getLabNotes(),patientReferralInfo.getLabICDNote(),patientReferralInfo.getLabRefPhy(),maxLabReferallId.get(0).getLabReffId(),patientReferralInfo.getProId(),patientInfo.getCreatedBy(),patientInfo.getCreationDate(),patientInfo.getUpdatedBy(),patientInfo.getUpdatedDate()};
			  int[] typesProvAc = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
			  int rowRefProAct=jdbcTemplateObject.update(refProAct,paramsProAct,typesProvAc);
			
			  List<PatientReferralInfo> prilist=new ArrayList<PatientReferralInfo>();
			  List<PatientReferralInfo> prilist1=new ArrayList<PatientReferralInfo>();
			 
			  if(patientReferralInfo.getLab_service_desc()!=null)
			  {
				 String serviceIdes[]=patientReferralInfo.getLab_service_desc().split(",");
				 for(int i=0;i<serviceIdes.length;i++){
					 PatientReferralInfo pri=new PatientReferralInfo();
				      pri.setLab_service_id(Integer.parseInt(serviceIdes[i]));
				      prilist.add(pri);
				 				}
			  }
				 
			  if(patientReferralInfo.getLab_sub_service_desc()!=null)
			  {
				 String subserviceIdes[]=patientReferralInfo.getLab_sub_service_desc().split(",");
				 for(int i=0;i<subserviceIdes.length;i++){
					 PatientReferralInfo pri=new PatientReferralInfo();
				      pri.setLab_sub_service_id(Integer.parseInt(subserviceIdes[i]));
				      prilist1.add(pri);
				 				}
			  }
				 
				for(PatientReferralInfo info: prilist) 
				{
					  String InsertSelectedServices="INSERT INTO rad_lab_referral_selected_services (LAB_REFERRAL_ID, LAB_SERVICE_MAP_ID, LAB_SUB_SERVICE_MAP_ID, LAB_SUB_SERVICE_CATEGORY_MAP_ID, TEST_STATUS, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE)VALUES(?,?,?,?,?,?,?,?,?)";
					  Object[] params = new Object[] {maxLabReferallId.get(0).getLabReffId(),info.getLab_service_id(),null,null,"PENDING",patientInfo.getCreatedBy(),patientInfo.getCreationDate(),patientInfo.getUpdatedBy(),patientInfo.getUpdatedDate()};
					  int[] types = new int[] {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
					  int row=jdbcTemplateObject.update(InsertSelectedServices,params,types);
				}
				
				for(PatientReferralInfo info: prilist1) 
				{
					  String InsertSelectedServices="INSERT INTO rad_lab_referral_selected_services (LAB_REFERRAL_ID, LAB_SERVICE_MAP_ID, LAB_SUB_SERVICE_MAP_ID, LAB_SUB_SERVICE_CATEGORY_MAP_ID, TEST_STATUS, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE)VALUES(?,?,?,?,?,?,?,?,?)";
					  Object[] params = new Object[] {maxLabReferallId.get(0).getLabReffId(),null,info.getLab_sub_service_id(),null,"PENDING",patientInfo.getCreatedBy(),patientInfo.getCreationDate(),patientInfo.getUpdatedBy(),patientInfo.getUpdatedDate()};
					  int[] types = new int[] {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP };
					  int row=jdbcTemplateObject.update(InsertSelectedServices,params,types);
				}
			

			return true;
		} else {
			return false;
		}
	}

	public List<LabVO> getLabSubCatByCatagory(int catagoryid) {
		List<LabVO> labVOs = new ArrayList<LabVO>();
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchChAdminSQL = "select 	LAB_SUB_CATEGORY_ID,LAB_SUB_CATEGORY_DESC,LAB_CATEGORY_ID from rad_lab_sub_category WHERE LAB_CATEGORY_ID="
				+ catagoryid;
		// Object[] params=new Object[]{userName};
		labVOs = jdbcTemplate.query(fetchChAdminSQL, new RowMapper<LabVO>() {

			public LabVO mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				LabVO labVO = new LabVO();
				labVO.setLab_sub_category_id(rs.getInt("LAB_SUB_CATEGORY_ID"));
				labVO.setLab_sub_category_desc(rs
						.getString("LAB_SUB_CATEGORY_DESC"));
				return labVO;
			}
		});
		return labVOs;
	}

	public List<LabVO> getLabInfoList(int subCatId) {
		List<LabVO> labVOs = new ArrayList<LabVO>();
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		// =====================================================
		String fetchChAdminSQL = "SELECT DISTINCT rli.LAB_ID,rli.LAB_DESC	FROM rad_lab_sub_category_map scm LEFT JOIN rad_lab_info rli ON rli.LAB_ID=scm.LAB_ID WHERE scm.LAB_SUB_CATEGORY_ID="
				+ subCatId;
		// Object[] params=new Object[]{userName};
		labVOs = jdbcTemplate.query(fetchChAdminSQL, new RowMapper<LabVO>() {

			public LabVO mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				LabVO labVO = new LabVO();
				labVO.setLab_id(rs.getInt("LAB_ID"));
				labVO.setLab_desc(rs.getString("LAB_DESC"));
				return labVO;
			}
		});
		return labVOs;
	}

	public boolean savePatientCheckinInfo(
			PatientReferralInfo patientReferralInfo) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate = "" + dateFormat.format(date);
		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String hippaAgr = "NO";
		if (patientReferralInfo.isSchedulFlag()) {
			hippaAgr = "YES";
		}

		String fetchInsIDSQL = "SELECT INSURANCE_ID FROM rad_insurance_info WHERE INSURANCE_COMPANY='"
				+ patientReferralInfo.getPatientInfo()
						.getPatientInsuranceInfoList().get(0)
						.getInsuranceInfo().getInsuranceCompany() + "'";
		List<Integer> insIdlist = new ArrayList<Integer>();
		insIdlist = jdbcTemplate.query(fetchInsIDSQL, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				int insId = rs.getInt("INSURANCE_ID");
				return insId;
			}
		});

		/*
		 * //select patient id by phone number
		 * //===================================================== //String
		 * fetchpatientIDSQL=
		 * "SELECT rpi.PATIENT_ID FROM rad_patient_info rpi LEFT JOIN rad_location rl ON rl.LOC_ID=rpi.PATIENT_LOC_ID WHERE rl.LOC_PHONE='"
		 * +
		 * patientReferralInfo.getPatientInfo().getRadLocation().getLocPhone()+"'"
		 * ; String fetchpatientIDSql=
		 * "SELECT rpi.PATIENT_ID FROM rad_patient_info rpi WHERE rpi.PHONE_NO='"
		 * +patientReferralInfo.getPatientInfo().getRadLocation().getLocPhone()+
		 * "' ORDER BY rpi.CREATION_DATE DESC LIMIT 1"; List<Integer>
		 * patientIdslist=new ArrayList<Integer>(); patientIdslist=
		 * jdbcTemplate.query(fetchpatientIDSql, new RowMapper<Integer>(){
		 * public Integer mapRow(ResultSet rs, int rowNumber) throws
		 * SQLException { int practiceid=rs.getInt("PATIENT_ID") ; return
		 * practiceid; } });
		 */

		System.out.print("check"
				+ patientReferralInfo.getPatientInfo().getPatientId());
		if (patientReferralInfo.getPatientInfo().getPatientId() == null) {

			String phone = patientReferralInfo.getPatientInfo()
					.getRadLocation().getLocPhone();
			// patientReferralInfo.getPatientInfo().getPatientHipaaAgreement();
			String sqlQury = "INSERT INTO rad_patient_info(PATIENT_FIRST_NAME,PATIENT_LAST_NAME,PATIENT_EMAIL,PATIENT_GENDER,PATIENT_DOB,PATIENT_HIPAA_AGREEMENT,PHONE_NO,CREATION_DATE,UPDATED_DATE)VALUES(?,?,?,?,?,?,?,?,?)";
			Object[] params1 = new Object[] {
					patientReferralInfo.getPatientInfo().getPatientFirstName(),
					patientReferralInfo.getPatientInfo().getPatientLastName(),
					patientReferralInfo.getPatientInfo().getPatientEmail(),
					patientReferralInfo.getPatientInfo().getPatientGender(),
					patientReferralInfo.getPatientInfo().getPatientDob(),
					hippaAgr, phone, currentDate, currentDate };
			int[] types1 = new int[] { Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.CHAR, Types.TIMESTAMP, Types.VARCHAR,
					Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP };
			int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);

			String fetchpatientIDSQL = "SELECT PATIENT_ID FROM rad_patient_info WHERE PHONE_NO='"
					+ patientReferralInfo.getPatientInfo().getRadLocation()
							.getLocPhone() + "'";
			List<Integer> patientIdlist = new ArrayList<Integer>();
			patientIdlist = jdbcTemplate.query(fetchpatientIDSQL,
					new RowMapper<Integer>() {
						public Integer mapRow(ResultSet rs, int rowNumber)
								throws SQLException {
							int practiceid = rs.getInt("PATIENT_ID");
							return practiceid;
						}
					});

			if (insIdlist.size() != 0) {
				String sqlQury22 = "INSERT INTO rad_patient_insurance_info(PAT_INSURANCE_ID,PAT_INSURANCE_GROUP,PAT_INS_ID,PATIENT_ID,CREATION_DATE,UPDATED_DATE) VALUES(?,?,?,?,?,?)";
				Object[] params22 = new Object[] {
						patientReferralInfo.getPatientInfo()
								.getPatientInsuranceInfoList().get(0)
								.getPatientInsuranceId(),
						patientReferralInfo.getPatientInfo()
								.getPatientInsuranceInfoList().get(0)
								.getPatientInsuranceGroup(), insIdlist.get(0),
						patientIdlist.get(patientIdlist.size() - 1),
						currentDate, currentDate };
				int[] types22 = new int[] { Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER, Types.INTEGER, Types.TIMESTAMP,
						Types.TIMESTAMP };
				int row22 = jdbcTemplateObject.update(sqlQury22, params22,
						types22);

			}
			// patientReferralInfo.getPracticeId()

			if (patientIdlist.size() != 0) {
				String sqlQury11 = "INSERT INTO rad_patient_checkin(PATIENT_ID,PRACTICE_ID)VALUES(?,?);";
				Object[] params11 = new Object[] {
						patientIdlist.get(patientIdlist.size() - 1),
						patientReferralInfo.getPracticeInfo().getPracticeId() };
				int[] types11 = new int[] { Types.INTEGER, Types.INTEGER };
				int row11 = jdbcTemplateObject.update(sqlQury11, params11,
						types11);
			}

		} else {
			System.out.print("Update");
			// update patient info
			String sqlQury = "UPDATE rad_patient_info SET  PATIENT_DOB = ?, PHONE_NO = ? , PATIENT_EMAIL = ? WHERE PATIENT_ID = ? ";
			Object[] params1 = new Object[] {
					patientReferralInfo.getPatientInfo().getPatientDob(),
					patientReferralInfo.getPatientInfo().getRadLocation()
							.getLocPhone(),
					patientReferralInfo.getPatientInfo().getPatientEmail(),
					patientReferralInfo.getPatientInfo().getPatientId() };
			int[] types1 = new int[] { Types.TIMESTAMP, Types.VARCHAR,
					Types.VARCHAR, Types.INTEGER };
			int row1 = jdbcTemplateObject.update(sqlQury, params1, types1);

			// update patient insurance info
			String inssqlQury = "UPDATE rad_patient_insurance_info SET PAT_INSURANCE_ID=?,PAT_INSURANCE_GROUP =?  WHERE PATIENT_ID =?";
			Object[] insparams1 = new Object[] {
					patientReferralInfo.getPatientInfo()
							.getPatientInsuranceInfoList().get(0)
							.getPatientInsuranceId(),
					patientReferralInfo.getPatientInfo()
							.getPatientInsuranceInfoList().get(0)
							.getPatientInsuranceGroup(),
					patientReferralInfo.getPatientInfo().getPatientId() };
			int[] instypes1 = new int[] { Types.VARCHAR, Types.VARCHAR,
					Types.INTEGER };
			int insrow1 = jdbcTemplateObject.update(inssqlQury, insparams1,
					instypes1);

			// insert patient check in

			String sqlQury11 = "INSERT INTO rad_patient_checkin(PATIENT_ID,PRACTICE_ID)VALUES(?,?);";
			Object[] params11 = new Object[] {
					patientReferralInfo.getPatientInfo().getPatientId(),
					patientReferralInfo.getPracticeInfo().getPracticeId() };
			int[] types11 = new int[] { Types.INTEGER, Types.INTEGER };
			int row11 = jdbcTemplateObject.update(sqlQury11, params11, types11);

		}

		/*
		 * //find insurance id
		 * 
		 * //=====================================================patientInfo.
		 * patientInsuranceInfoList[0].insuranceInfo.insuranceCompany String
		 * fetchInsIDSQL
		 * ="SELECT 	INSURANCE_ID FROM rad_insurance_info WHERE INSURANCE_COMPANY='"
		 * +
		 * patientReferralInfo.getPatientInfo().getPatientInsuranceInfoList().get
		 * (0).getInsuranceInfo().getInsuranceCompany()+"'"; List<Integer>
		 * insIdlist=new ArrayList<Integer>(); insIdlist=
		 * jdbcTemplate.query(fetchInsIDSQL, new RowMapper<Integer>(){ public
		 * Integer mapRow(ResultSet rs, int rowNumber) throws SQLException { int
		 * insId=rs.getInt("INSURANCE_ID") ; return insId; } });
		 * 
		 * 
		 * String
		 * insid=patientReferralInfo.getPatientInfo().getPatientInsuranceInfoList
		 * ().get(0).getInsuranceInfo().getInsuranceCompany();
		 * 
		 * 
		 * //select patient id by phone number
		 * //===================================================== String
		 * fetchpatientIDSQL=
		 * "SELECT rpi.PATIENT_ID FROM rad_patient_info rpi LEFT JOIN rad_location rl ON rl.LOC_ID=rpi.PATIENT_LOC_ID WHERE rl.LOC_PHONE='"
		 * +
		 * patientReferralInfo.getPatientInfo().getRadLocation().getLocPhone()+"'"
		 * ; List<Integer> patientIdlist=new ArrayList<Integer>();
		 * patientIdlist= jdbcTemplate.query(fetchpatientIDSQL, new
		 * RowMapper<Integer>(){ public Integer mapRow(ResultSet rs, int
		 * rowNumber) throws SQLException { int
		 * practiceid=rs.getInt("PATIENT_ID") ; return practiceid; } });
		 * 
		 * //if find then update & insurance id if(patientIdlist.size()!=0){
		 * String sqlQury=
		 * "UPDATE rad_patient_info SET   PATIENT_EMAIL = ? WHERE PATIENT_ID = ? "
		 * ; Object[] params1 = new Object[]
		 * {patientReferralInfo.getPatientInfo(
		 * ).getPatientEmail(),patientIdlist.get(0)}; int[] types1 = new int[] {
		 * Types.VARCHAR,Types.INTEGER}; int
		 * row1=jdbcTemplateObject.update(sqlQury,params1,types1);
		 * 
		 * 
		 * 
		 * String inssqlQury=
		 * "UPDATE rad_patient_insurance_info SET PAT_INSURANCE_ID=?,PAT_INSURANCE_GROUP =? ,PAT_INS_ID =? WHERE PATIENT_ID =?"
		 * ; Object[] insparams1 = new Object[]
		 * {patientReferralInfo.getPatientInfo
		 * ().getPatientInsuranceInfoList().get
		 * (0).getPatientInsuranceId(),patientReferralInfo
		 * .getPatientInfo().getPatientInsuranceInfoList
		 * ().get(0).getPatientInsuranceGroup
		 * (),insIdlist.get(0),patientIdlist.get(0)}; int[] instypes1 = new
		 * int[] { Types.VARCHAR,Types.INTEGER}; int
		 * insrow1=jdbcTemplateObject.update(inssqlQury,insparams1,instypes1);
		 * 
		 * 
		 * } //else insert // in insurance map else{
		 * 
		 * String sql=
		 * "INSERT INTO rad_location ( CREATION_DATE, UPDATED_DATE,  LOC_PHONE) VALUES (?, ?, ?)"
		 * ; Object[] params = new Object[] { currentDate,
		 * currentDate,patientReferralInfo
		 * .getPatientInfo().getRadLocation().getLocPhone()}; int[] types = new
		 * int[] { Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR}; int
		 * row=jdbcTemplateObject.update(sql,params,types);
		 * 
		 * 
		 * 
		 * String fetchlocidSQL=
		 * "SELECT rl.LOC_ID FROM rad_location rl WHERE rl.LOC_PHONE='"
		 * +patientReferralInfo
		 * .getPatientInfo().getRadLocation().getLocPhone()+"'"; List<Integer>
		 * patientlocIdlist=new ArrayList<Integer>(); patientlocIdlist=
		 * jdbcTemplate.query(fetchpatientIDSQL, new RowMapper<Integer>(){
		 * public Integer mapRow(ResultSet rs, int rowNumber) throws
		 * SQLException { int locid=rs.getInt("LOC_ID"); return locid; } });
		 * 
		 * String sqlQury=
		 * "INSERT INTO rad_patient_info(PATIENT_FIRST_NAME,PATIENT_LAST_NAME,PATIENT_EMAIL,PATIENT_GENDER,PATIENT_DOB,PATIENT_HIPAA_AGREEMENT,PATIENT_LOC_ID,CREATION_DATE,UPDATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?)"
		 * ; Object[] params1 = new Object[] {
		 * patientReferralInfo.getPatientInfo().getPatientFirstName(),
		 * patientReferralInfo.getPatientInfo().getPatientLastName(),
		 * patientReferralInfo
		 * .getPatientInfo().getPatientEmail(),patientReferralInfo
		 * .getPatientInfo
		 * ().getPatientGender(),patientReferralInfo.getPatientInfo
		 * ().getPatientDob
		 * (),patientReferralInfo.getPatientInfo().getPatientHipaaAgreement
		 * (),patientlocIdlist.get(0),currentDate,currentDate}; int[] types1 =
		 * new int[] { Types.VARCHAR,
		 * Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types
		 * .TIMESTAMP,Types.VARCHAR,Types
		 * .INTEGER,Types.TIMESTAMP,Types.TIMESTAMP}; int
		 * row1=jdbcTemplateObject.update(sqlQury,params1,types1); }
		 * 
		 * //find patient id by phone number
		 * 
		 * List<Integer> patIdlist=new ArrayList<Integer>(); patIdlist=
		 * jdbcTemplate.query(fetchpatientIDSQL, new RowMapper<Integer>(){
		 * public Integer mapRow(ResultSet rs, int rowNumber) throws
		 * SQLException { int practiceid=rs.getInt("PATIENT_ID") ; return
		 * practiceid; } });
		 * 
		 * //insert into map
		 * 
		 * String sqlQury=
		 * "INSERT INTO rad_patient_checkin(PATIENT_ID,PRACTICE_ID)VALUES(?,?);"
		 * ; Object[] params1 = new Object[]
		 * {patIdlist.get(0),patientReferralInfo.getPracticeId()}; int[] types1
		 * = new int[] { Types.INTEGER,Types.INTEGER}; int
		 * row1=jdbcTemplateObject.update(sqlQury,params1,types1);
		 */

		return true;
	}

}
