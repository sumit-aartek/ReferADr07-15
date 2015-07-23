package com.referadr.practice.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.referadr.practice.model.ReportVO;
import com.referadr.practice.repository.ReportRepository;
import com.referadr.practice.util.CommonUtils;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

	public List<ReportVO> searchReport(ReportVO repVO) {
		List<ReportVO> reportList = null;
		// main logic

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat1 = new SimpleDateFormat("MMMM d, yyyy");
		String start = repVO.getStartDate();
		String end = repVO.getEndDate();
	
		if(!start.equals("")){
	       Date tempDate=null;
	 	  Date tempDateEnd=null;
		try {
			tempDate = dateFormat1.parse(start);
			
			if(!end.equals("")){
				tempDateEnd=dateFormat1.parse(end);
				end=dateFormat.format(tempDateEnd);
			
			}
			 start=dateFormat.format(tempDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		final boolean flag=repVO.isFlag();
		// if both are not selected
		if (start.equals("") && end.equals("")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -30);
			Date result = cal.getTime();
			start = "" + dateFormat.format(result);
			Date current = new Date();
			end = "" + dateFormat.format(current);
		}

		// if only start date is selected
		if (!start.equals("") && end.equals("")) {
			try {
				Date enddatee = dateFormat.parse(start);
				Calendar c = Calendar.getInstance();
				c.setTime(enddatee);
				c.add(Calendar.DATE, 30);
				enddatee = c.getTime();
				end = "" + dateFormat.format(enddatee);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// if only end date is selected
		if (start.equals("") && !end.equals("")) {
			try {
				Date enddatee = dateFormat.parse(end);
				Calendar c = Calendar.getInstance();
				c.setTime(enddatee);
				c.add(Calendar.DATE, -30);
				enddatee = c.getTime();
				start = "" + dateFormat.format(enddatee);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (!end.equals("")) {
			try {
				Date enddate = dateFormat.parse(end);
				Calendar c = Calendar.getInstance();
				c.setTime(enddate);
				c.add(Calendar.DATE, 1);
				enddate = c.getTime();
				end = "" + dateFormat.format(enddate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
		String fetchPractSplSQL = null;


		if (repVO.getKeyToSearch().equals("Speciality")) {
if(flag){		
		fetchPractSplSQL=" SELECT rci.CN_NAME,r.FROM_PROVIDER_ID,r.REFFERAL_ID,r.FROM_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE FROM report r LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID= r.REFFERAL_ID LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID =rpi.PATIENT_ID  LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID =rpii.PAT_INS_ID  LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.FROM_PRACTICE_ID LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.FROM_CH_ID WHERE r.FROM_PRACTICE_ID IN(SELECT rpsm.PRACTICE_ID FROM rad_practice_spl_mapping rpsm WHERE rpsm.PRACTICE_SPL_ID="+repVO.getSplId()+" ) AND r.TO_PRACTICE_ID="+repVO.getPracticeId()+" AND r.CREATION_DATE  BETWEEN '"+start+"' AND '"+end+"' ORDER BY r.CREATION_DATE DESC";
}else{
	fetchPractSplSQL="SELECT rci.CN_NAME,r.TO_PROVIDER_ID,r.REFFERAL_ID,r.TO_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE  FROM report r  LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID= r.REFFERAL_ID  LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID  LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID =rpi.PATIENT_ID   LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID =rpii.PAT_INS_ID   LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.TO_PRACTICE_ID  LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.TO_CH_ID  WHERE  r.FROM_PRACTICE_ID="+repVO.getPracticeId()+"  AND r.TO_SPL_ID="+repVO.getSplId()+" AND r.CREATION_DATE  BETWEEN '"+start+"' AND '"+end+"' ORDER BY r.CREATION_DATE DESC";
}		
}

		if (repVO.getKeyToSearch().equals("Practice")) {
			if(flag){	fetchPractSplSQL = "SELECT rci.CN_NAME,r.FROM_PROVIDER_ID,r.REFFERAL_ID,r.FROM_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE FROM report r LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID= r.REFFERAL_ID LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID =rpi.PATIENT_ID LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID =rpii.PAT_INS_ID LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.FROM_PRACTICE_ID LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.FROM_CH_ID WHERE r.FROM_PRACTICE_ID IN(SELECT PRACTICE_ID FROM rad_practice_info WHERE PRACTICE_NAME LIKE '%"
					+ repVO.getValueToSearch()
					+ "%') AND r.TO_PRACTICE_ID="
					+ repVO.getPracticeId()
					+ "   AND r.CREATION_DATE  BETWEEN '"
					+ start
					+ "' AND '"
					+ end + "' ORDER BY r.CREATION_DATE DESC ";
			}else{
				fetchPractSplSQL="SELECT rci.CN_NAME,r.TO_PROVIDER_ID,r.REFFERAL_ID,r.TO_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME , rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE  FROM report r  LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID= r.REFFERAL_ID  LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID  LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID =rpi.PATIENT_ID  LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID =rpii.PAT_INS_ID  LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.TO_PRACTICE_ID  LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.TO_CH_ID  WHERE r.TO_PRACTICE_ID IN(SELECT PRACTICE_ID FROM rad_practice_info WHERE PRACTICE_NAME LIKE '%"+repVO.getValueToSearch()+"%')   AND r.FROM_PRACTICE_ID="+repVO.getPracticeId()+"   AND r.CREATION_DATE  BETWEEN '"+start+"' AND '"+end+"' ORDER BY r.CREATION_DATE DESC ";
			}
		}

		if (repVO.getKeyToSearch().equals("Provider")) {
			if(flag){	fetchPractSplSQL = "SELECT rci.CN_NAME,r.FROM_PROVIDER_ID,r.REFFERAL_ID,r.FROM_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE FROM report r LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID= r.REFFERAL_ID LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID =rpi.PATIENT_ID LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID =rpii.PAT_INS_ID LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.FROM_PRACTICE_ID LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.FROM_CH_ID WHERE r.FROM_PROVIDER_ID IN(SELECT PROVIDER_ID FROM rad_provider_info WHERE PROVIDER_FIRST_NAME LIKE '%"
					+ repVO.getValueToSearch()
					+ "%' OR PROVIDER_LAST_NAME LIKE '%"+repVO.getValueToSearch()+"%') AND r.TO_PRACTICE_ID="
					+ repVO.getPracticeId()
					+ "  AND r.CREATION_DATE  BETWEEN '"
					+ start
					+ "' AND '"
					+ end + "' ORDER BY r.CREATION_DATE DESC ";
			}else{
				fetchPractSplSQL = "SELECT rci.CN_NAME,r.TO_PROVIDER_ID,r.REFFERAL_ID,r.TO_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE     FROM report r     LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_REFERRAL_ID= r.REFFERAL_ID     LEFT JOIN rad_patient_info rpi ON rpi.PATIENT_ID=rpri.PATIENT_ID     LEFT JOIN rad_patient_insurance_info rpii ON rpii.PATIENT_ID =rpi.PATIENT_ID     LEFT JOIN rad_insurance_info rii ON rii.INSURANCE_ID =rpii.PAT_INS_ID     LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.TO_PRACTICE_ID     LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.TO_CH_ID     WHERE r.TO_PROVIDER_ID IN(SELECT PROVIDER_ID FROM rad_provider_info WHERE PROVIDER_FIRST_NAME LIKE '%"+repVO.getValueToSearch()+"%' OR PROVIDER_LAST_NAME LIKE '%"+repVO.getValueToSearch()+"%')     AND r.FROM_PRACTICE_ID="+repVO.getPracticeId()+"       AND r.CREATION_DATE  BETWEEN '"+start+"' AND '"+end+"' ORDER BY r.CREATION_DATE DESC ";
			}
			
		}

		if (repVO.getKeyToSearch().equals("Insurance")) {
		if(flag){	fetchPractSplSQL = "SELECT rci.CN_NAME,r.FROM_PROVIDER_ID,r.REFFERAL_ID,r.FROM_PRACTICE_ID,rppi.PRACTICE_NAME,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE FROM rad_insurance_info rii LEFT JOIN rad_patient_insurance_info rpii ON rpii.PAT_INS_ID=rii.INSURANCE_ID LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_ID=rpii.PATIENT_ID LEFT JOIN rad_patient_info rpi ON rpii.PATIENT_ID=rpi.PATIENT_ID LEFT JOIN report r ON r.REFFERAL_ID=rpri.PATIENT_REFERRAL_ID LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.FROM_PRACTICE_ID LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.FROM_CH_ID  WHERE rii.INSURANCE_COMPANY LIKE '%"
					+ repVO.getValueToSearch()
					+ "%' AND r.TO_PRACTICE_ID="
					+ repVO.getPracticeId()
					+ " AND r.CREATION_DATE  BETWEEN'"
					+ start
					+ "' AND '"
					+ end
					+ "' ORDER BY r.CREATION_DATE DESC ";
		}else{
			fetchPractSplSQL="SELECT rci.CN_NAME,r.TO_PROVIDER_ID,r.REFFERAL_ID,r.TO_PRACTICE_ID,rppi.PRACTICE_NAME    ,rpi.PATIENT_FIRST_NAME ,rpi.PATIENT_LAST_NAME,rii.INSURANCE_ID,rii.INSURANCE_COMPANY,r.CREATION_DATE     FROM rad_insurance_info rii      LEFT JOIN rad_patient_insurance_info rpii ON rpii.PAT_INS_ID=rii.INSURANCE_ID      LEFT JOIN rad_patient_referral_info rpri ON rpri.PATIENT_ID=rpii.PATIENT_ID      LEFT JOIN rad_patient_info rpi ON rpii.PATIENT_ID=rpi.PATIENT_ID      LEFT JOIN report r ON r.REFFERAL_ID=rpri.PATIENT_REFERRAL_ID      LEFT JOIN rad_practice_info rppi ON rppi.PRACTICE_ID =r.TO_PRACTICE_ID      LEFT JOIN rad_ch_info rci ON rci.CH_ID=r.TO_CH_ID        WHERE rii.INSURANCE_COMPANY LIKE '%"+repVO.getValueToSearch()+"%'        AND r.FROM_PRACTICE_ID="+repVO.getPracticeId()+"        AND r.CREATION_DATE  BETWEEN'"+start+"' AND '"+end+"' ORDER BY r.CREATION_DATE DESC ";
		}
		}

		reportList = jdbcTemplate.query(fetchPractSplSQL,
				new RowMapper<ReportVO>() {
					public ReportVO mapRow(ResultSet rs, int rowNumber)
							throws SQLException {
						ReportVO repVO = new ReportVO();
						String strCreationDate = rs.getString("CREATION_DATE"); 
						strCreationDate=strCreationDate.substring(0, 10);
						repVO.setCreationDate(strCreationDate);
						repVO.setRefferalId(rs.getInt("REFFERAL_ID"));
						repVO.setPatientFirstName(rs
								.getString("PATIENT_FIRST_NAME"));
						repVO.setPatientLastName(rs
								.getString("PATIENT_LAST_NAME"));
						if (rs.getString("PRACTICE_NAME") == null) {
							if(flag){
							//repVO.setPracticeName(rs.getString("CN_NAME"));
							//For Practice name by provider Id if Practice name is not available 
							List<ReportVO> reportListofPractice = null;
							JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
							String fetchDrName="";
							
								fetchDrName="SELECT rpi.PROVIDER_PRAC_ID, rpii.PRACTICE_NAME FROM rad_provider_info rpi LEFT JOIN rad_practice_info rpii ON rpii.PRACTICE_ID= rpi.PROVIDER_PRAC_ID WHERE rpi.PROVIDER_ID="+rs.getInt("FROM_PROVIDER_ID");
							
									
							
							
							reportListofPractice = jdbcTemplate.query(fetchDrName,
									new RowMapper<ReportVO>() {
										public ReportVO mapRow(ResultSet rs2, int rowNumber)
												throws SQLException {
											ReportVO repVODr = new ReportVO();
											if(rs2.getString("PRACTICE_NAME").equals(null)){
												repVODr.setPracticeName("");
											}else{
											repVODr.setPracticeName(rs2.getString("PRACTICE_NAME"));
											}
											
													return repVODr;
											}
										});
							if(reportListofPractice.size()!=0){
								if(!reportListofPractice.get(0).getPracticeName().equals(null)){
							repVO.setPracticeName(reportListofPractice.get(0).getPracticeName());
								}
							}
							}else{
								repVO.setPracticeName(rs.getString("CN_NAME"));
							}
							////
							
						} else {
							repVO.setPracticeName(rs.getString("PRACTICE_NAME"));
							//System.out.print(">>>>>>>>>>>>>"+rs.getInt("TO_PROVIDER_ID"));
						}
						repVO.setInsuranceCompany(rs
								.getString("INSURANCE_COMPANY"));
						
						//For Dr.(Provider) Name
						if(flag){
						List<ReportVO> reportListofDr = null;
						JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
						String fetchDrName="";
							fetchDrName="SELECT rproi.PROVIDER_FIRST_NAME,rproi.PROVIDER_LAST_NAME FROM rad_provider_info rproi WHERE rproi.PROVIDER_ID ="+rs.getInt("FROM_PROVIDER_ID");
							//fetchDrName="SELECT rproi.PROVIDER_FIRST_NAME,rproi.PROVIDER_LAST_NAME FROM rad_provider_info rproi WHERE rproi.PROVIDER_ID ="+rs.getInt("TO_PROVIDER_ID");
						reportListofDr = jdbcTemplate.query(fetchDrName,
								new RowMapper<ReportVO>() {
									public ReportVO mapRow(ResultSet rs1, int rowNumber)
											throws SQLException {
										ReportVO repVODr = new ReportVO();
										repVODr.setDoctorFirstName(rs1.getString("PROVIDER_FIRST_NAME"));
										repVODr.setDoctorLastName(rs1.getString("PROVIDER_LAST_NAME"));
												return repVODr;
										}
									});
						if(reportListofDr.size()!=0){
							if(!reportListofDr.get(0).getDoctorFirstName().equals(null)){
						repVO.setDoctorFirstName("Dr. "+reportListofDr.get(0).getDoctorFirstName());
							}else{
								repVO.setDoctorFirstName("Dr. ");
							}
							if(!reportListofDr.get(0).getDoctorLastName().equals(null)){
							repVO.setDoctorLastName(reportListofDr.get(0).getDoctorLastName());
							}else{
								repVO.setDoctorLastName(" ");
							}
							
						}
						}
						else if(rs.getInt("TO_PROVIDER_ID")>0){
							List<ReportVO> reportListofDr = null;
							JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
							String fetchDrName="";
							
								//fetchDrName="SELECT rproi.PROVIDER_FIRST_NAME,rproi.PROVIDER_LAST_NAME FROM rad_provider_info rproi WHERE rproi.PROVIDER_ID ="+rs.getInt("FROM_PROVIDER_ID");
							
								fetchDrName="SELECT rproi.PROVIDER_FIRST_NAME,rproi.PROVIDER_LAST_NAME FROM rad_provider_info rproi WHERE rproi.PROVIDER_ID ="+rs.getInt("TO_PROVIDER_ID");
							
							reportListofDr = jdbcTemplate.query(fetchDrName,
									new RowMapper<ReportVO>() {
										public ReportVO mapRow(ResultSet rs1, int rowNumber)
												throws SQLException {
											ReportVO repVODr = new ReportVO();
											repVODr.setDoctorFirstName(rs1.getString("PROVIDER_FIRST_NAME"));
											repVODr.setDoctorLastName(rs1.getString("PROVIDER_LAST_NAME"));
													return repVODr;
											}
										});
							if(reportListofDr.size()!=0){
								if(!reportListofDr.get(0).getDoctorFirstName().equals(null)){
							repVO.setDoctorFirstName("Dr. "+reportListofDr.get(0).getDoctorFirstName());
								}else{
									repVO.setDoctorFirstName("Dr. ");
								}
								if(!reportListofDr.get(0).getDoctorLastName().equals(null)){
								repVO.setDoctorLastName(reportListofDr.get(0).getDoctorLastName());
								}else{
									repVO.setDoctorLastName(" ");
								}
								
							}
							}
						else{
							repVO.setDoctorFirstName("--  -- ");
							repVO.setDoctorLastName("--  -- ");
						}
						////
						return repVO;
					}
				});
		return reportList;
	}
}
