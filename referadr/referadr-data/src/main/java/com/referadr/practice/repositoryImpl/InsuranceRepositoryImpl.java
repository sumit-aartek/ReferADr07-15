package com.referadr.practice.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.referadr.practice.model.InsuranceVO;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.repository.InsuranceRepository;
import com.referadr.practice.util.CommonUtils;

@Repository
public class InsuranceRepositoryImpl implements InsuranceRepository{
	JdbcTemplate jdbcTemplate = CommonUtils.getJdbcTemplate();
	
	public List<InsuranceVO> getAllInsuranceList(){
		
		
		List<InsuranceVO> list=null;
		/*List radproviderspecialitylist=null;
		SessionFactory sessionFactory = null;
		Session session = null;
		sessionFactory = hibernateTemplate.getSessionFactory();
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery("select PROV_SPL_ID,PROV_SPL_DESC from rad_provider_speciality ");
		radproviderspecialitylist=query.list();
		return radproviderspecialitylist;*/
		String fetchPractSplSQL="SELECT 	INSURANCE_ID,INSURANCE_COMPANY FROM  rad_insurance_info  ";
		list= jdbcTemplate.query(fetchPractSplSQL, new RowMapper<InsuranceVO>(){
				public InsuranceVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
					InsuranceVO insuranceVO=new InsuranceVO();  
					insuranceVO.setInsuranceId(rs.getInt("INSURANCE_ID"));
					insuranceVO.setInsuranceName(rs.getString("INSURANCE_COMPANY"));
	    return insuranceVO;
	}
		 });
		return list;
		
		
	}
	
	
	public List<Integer> getSelectedInsuranceList(Integer providePracticeId){
		List<Integer> insIdList=new ArrayList<Integer>();
		List<InsuranceVO> inslist=new ArrayList<InsuranceVO>();
		//=====================================================
		String fetchChAdminSQL="SELECT INSURANCE_ID FROM rad_practice_ins_mapping WHERE PRACTICE_ID="+providePracticeId;
		inslist= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<InsuranceVO>(){

			public InsuranceVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
				InsuranceVO insVO=new InsuranceVO();
				insVO.setInsuranceId(rs.getInt("INSURANCE_ID"));				
				return insVO;
			}
		});
		for(int i=0;i<inslist.size();i++){
			insIdList.add(inslist.get(i).getInsuranceId());
		}
		
		return insIdList;
	}
	
	
	public List<Integer> insertPatientIns(Integer providePracticeId,Integer insId){
		List<Integer> insIdList=new ArrayList<Integer>();
		
		String sql="INSERT INTO rad_practice_ins_mapping(PRACTICE_ID,INSURANCE_ID)	VALUES	(?,?)";
		Object[] params = new Object[] {providePracticeId,insId};
		int[] types = new int[] {Types.INTEGER,Types.INTEGER};
	int row=jdbcTemplate.update(sql,params,types);
	
/*	
	List<InsuranceVO> inslist=new ArrayList<InsuranceVO>();
	//=====================================================
	String fetchChAdminSQL="SELECT SUFFIX_ID FROM rad_provider_suffix_mapping WHERE PROVIDER_ID ="+providePracticeId;
	inslist= jdbcTemplate.query(fetchChAdminSQL, new RowMapper<InsuranceVO>(){

		public InsuranceVO mapRow(ResultSet rs, int rowNumber) throws SQLException {
			InsuranceVO insVO=new InsuranceVO();
			insVO.setInsuranceId(rs.getInt("SUFFIX_ID"));				
			return insVO;
		}
	});
	for(int i=0;i<inslist.size();i++){
		insIdList.add(inslist.get(i).getInsuranceId());
	}*/
		
		
		return insIdList;
	}
	
	public List<Integer> deletePatientIns(Integer providePracticeId,Integer insId){
		List<Integer> insIdList=new ArrayList<Integer>();
		
		 String sql="DELETE FROM rad_practice_ins_mapping WHERE PRACTICE_ID="+providePracticeId+" AND INSURANCE_ID="+insId;
	      int rowBoard=jdbcTemplate.update(sql);
		return insIdList;
	}

}
