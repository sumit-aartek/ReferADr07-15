package com.referadr.ch.repositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.model.CHPSPBean;
import com.referadr.ch.model.RadCodes;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.CHSPMappingBean;
import com.referadr.practice.model.ChSpMapping;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.RedState;
import com.referadr.login.model.Login;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.login.model.RadRole;
import com.referadr.ch.repository.CHAdminDAO;

@Repository
public class CHAdminDAOImpl implements CHAdminDAO {
	
	private JdbcTemplate jdbcTemplateObject;	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new  JdbcTemplate(dataSource);
	}

	public int addCHAdmin(CHAdmin chAdmin) throws Exception{
		String sql="insert into rad_ch_admin(ch_admin_role_first_name,CH_ADMIN_ROLE_LAST_NAME,CH_ADMIN_ROLE_PASSWORD,CH_ADMIN_ROLE_PHONE,CH_ADMIN_ROLE_EMAIL,CH_ADMIN_CH_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?,?,?,?)";
	
		Object[] params = new Object[] { chAdmin.getFirstName(), chAdmin.getLastName(), chAdmin.getPassword(), chAdmin.getContact(),chAdmin.getEmaiId(),chAdmin.getChId(),chAdmin.getFirstName()+" "+chAdmin.getLastName(),new Date(),chAdmin.getFirstName()+" "+chAdmin.getLastName(),new Date()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(sql,params,types);
		return row;
		}

	
	public int updateCHInfo(CHInfo chInfo,Login login) throws Exception{
		String updateCHInfoSQL="update rad_ch_info set CN_NAME=?,CH_DEC=?,ch_loc_id=?,UPDATED_BY=?,UPDATED_DATE=? where CH_ID=?";
		
		Object[] params =new Object[]{chInfo.getName(),chInfo.getDescription(),chInfo.getRadLocation().getLocId(),login.getUserName(),new Date(),chInfo.getId()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP,Types.INTEGER};
		int row=jdbcTemplateObject.update(updateCHInfoSQL,params,types);
		return row;
		
	}


	public CHAdmin fetchChAdmin(String userName) throws Exception{
		String fetchChAdminSQL="select * from rad_ch_admin where ch_admin_role_email=?";
		Object[] params=new Object[]{userName};
		CHAdmin chAdmin=new CHAdmin(); 
		List<CHAdmin> chAdmins=new ArrayList<CHAdmin>();
		chAdmins= jdbcTemplateObject.query(fetchChAdminSQL, params, new RowMapper<CHAdmin>(){

			public CHAdmin mapRow(ResultSet rs, int rowNumber) throws SQLException {
				CHAdmin chAdmin=new CHAdmin();
				chAdmin.setChAdminId(rs.getInt("CH_ADMIN_ID"));
				chAdmin.setFirstName(rs.getString("CH_ADMIN_ROLE_FIRST_NAME"));
				chAdmin.setLastName(rs.getString("CH_ADMIN_ROLE_LAST_NAME"));
				chAdmin.setPassword(rs.getString("CH_ADMIN_ROLE_PASSWORD"));
				chAdmin.setContact(rs.getString("CH_ADMIN_ROLE_PHONE"));
				chAdmin.setEmaiId(rs.getString("CH_ADMIN_ROLE_EMAIL"));
				chAdmin.setChId(rs.getInt("CH_ADMIN_CH_ID"));
			
				
				return chAdmin;
			}
			
		});
		 if(!chAdmins.isEmpty()){
			 chAdmin=chAdmins.get(0);
		 }
		 return chAdmin;
	}
	
	
	public CHAdmin editChAdmin(int chAdminId) throws Exception{
		String fetchChAdminSQL="select * from rad_ch_admin where ch_admin_id=?";
		Object[] params=new Object[]{chAdminId};
		CHAdmin chAdmin=new CHAdmin();
		List<CHAdmin> chAdmins=new ArrayList<CHAdmin>();
		chAdmins= jdbcTemplateObject.query(fetchChAdminSQL, params, new RowMapper<CHAdmin>(){

			public CHAdmin mapRow(ResultSet rs, int rowNumber) throws SQLException {
				CHAdmin chAdmin=new CHAdmin();
				chAdmin.setChAdminId(rs.getInt("CH_ADMIN_ID"));
				chAdmin.setFirstName(rs.getString("CH_ADMIN_ROLE_FIRST_NAME"));
				chAdmin.setLastName(rs.getString("CH_ADMIN_ROLE_LAST_NAME"));
				chAdmin.setPassword(rs.getString("CH_ADMIN_ROLE_PASSWORD"));
				chAdmin.setContact(rs.getString("CH_ADMIN_ROLE_PHONE"));
				chAdmin.setEmaiId(rs.getString("CH_ADMIN_ROLE_EMAIL"));
				chAdmin.setChId(rs.getInt("CH_ADMIN_CH_ID"));
			
				
				return chAdmin;
			}
			
		});
		if(!chAdmins.isEmpty()){
			chAdmin=chAdmins.get(0);
		}
		return chAdmin;
		
	}



	public int updateCHAdmin(CHAdmin chAdmin) throws Exception{
		String updateCHAdminSQL="update rad_ch_admin set CH_ADMIN_ROLE_FIRST_NAME=?,CH_ADMIN_ROLE_LAST_NAME=?,CH_ADMIN_ROLE_PHONE=?,UPDATED_BY=?,UPDATED_DATE=? where CH_ADMIN_ID=?";
		
		Object[] params =new Object[]{chAdmin.getFirstName(),chAdmin.getLastName(),chAdmin.getContact(),chAdmin.getFirstName()+" "+chAdmin.getLastName(),new Date(),chAdmin.getChAdminId()};
		
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,Types.INTEGER};
		int row=jdbcTemplateObject.update(updateCHAdminSQL,params,types);
		return row;
		
	}

	
	public List<PracticeSpecialty> fetchPracticeSpecialtyInfo() throws Exception{
		String fetchPracticeSpecialtySQL="select PRAC_SPL_ID,PRAC_SPL_DESC from rad_practice_speciality";
		return jdbcTemplateObject.query(fetchPracticeSpecialtySQL,  new RowMapper<PracticeSpecialty>(){
			public PracticeSpecialty mapRow(ResultSet rs, int rowNumber) throws SQLException {
				PracticeSpecialty practiceSpecialty=new PracticeSpecialty();
				practiceSpecialty.setPraticeSplID(rs.getInt("PRAC_SPL_ID"));
				practiceSpecialty.setPraticeSplDesc(rs.getString("PRAC_SPL_DESC"));
				return practiceSpecialty;
			}
		});
	}

	public List<ChSpMapping> fetchCHPracticeSpecialtyMapping(int chId) throws Exception{
		String fetchCHPracticeSpecialityMappingQuery="select ch_id,CH_PRACSPL_ID,CH_SPL_MAP_ID,CH_SPL_STATUS from rad_ch_specality_mapping where ch_id=?";
	
		Object[] params=new Object[]{chId};
		return jdbcTemplateObject.query(fetchCHPracticeSpecialityMappingQuery,params,  new RowMapper<ChSpMapping>(){
			public ChSpMapping mapRow(ResultSet rs, int rowNumber) throws SQLException {
				ChSpMapping chSpMapping=new ChSpMapping();
	
				chSpMapping.setCh_id(rs.getInt("ch_id"));
				chSpMapping.setPratice_Spl_ID(rs.getInt("CH_PRACSPL_ID"));
				chSpMapping.setCh_sp_mapping_id(rs.getInt("CH_SPL_MAP_ID"));
				chSpMapping.setChSplStatus(rs.getString("CH_SPL_STATUS"));
				return chSpMapping;
			}
		});
	}

	public String getPracticeSpecialty(int chspid) throws Exception{
		List<PracticeSpecialty> ps=new ArrayList<PracticeSpecialty>();
		//ps=sessionFactory.getCurrentSession().createQuery("from PracticeSpecialty where Pratice_Spl_ID=?").setParameter(0, chspid).list();
		if(!ps.isEmpty()){
		return ps.get(0).getPraticeSplDesc();
		}
		else{
			return null;
		}
	}

	public int getPracticeSpecialtyId(String spDesc) throws Exception{
		
		List<PracticeSpecialty> ps=new ArrayList<PracticeSpecialty>();
		
		//ps=sessionFactory.getCurrentSession().createQuery("from PracticeSpecialty").list();
		if(!ps.isEmpty()){
		return ps.get(0).getPraticeSplID();
	}else{
		return 0;
	}
		}

	public int updateCHSPMapping(CHSPMappingBean chsp) throws Exception{
		//sessionFactory.getCurrentSession().createQuery("delete from ChSpMapping");
		for(int i=0;i<chsp.getSp().size();i++)
		{
			CHAdminDAOImpl chAdminDAO=new CHAdminDAOImpl();
			ChSpMapping chspmapping=new ChSpMapping();
			chspmapping.setCh_id(chsp.getChId());
		
			chspmapping.setPratice_Spl_ID(chAdminDAO.getPracticeSpecialtyId(chsp.getSp().get(i)));
			//sessionFactory.getCurrentSession().saveOrUpdate(chspmapping);
		}
		return 0;
	}

	

	public CHAdmin fetchChAdmin(Login login) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	public List<RedState> fetchRadStates() throws Exception{
		String fetchStatesSQL="select * from rad_state";
		return jdbcTemplateObject.query(fetchStatesSQL,  new RowMapper<RedState>(){
			public RedState mapRow(ResultSet rs, int rowNumber) throws SQLException {
				RedState radState=new RedState();
				radState.setStateId(rs.getInt("state_id"));
				radState.setStateCode(rs.getString("state_code"));
				radState.setStateName(rs.getString("state_name"));
				return radState;
			}
		});
	}
	
	public long insertLocationDetails(CHInfo chInfo,String userName) throws Exception
	{
		final CHInfo ch=chInfo;
		final String user=userName;
		final String insertLocationDetailsSQL="insert into rad_location(LOC_ADDRESS1,LOC_ADDRESS2,LOC_CITY,LOC_STATE_ID,LOC_ZIP,LOC_PHONE,LOC_FAX,LOC_WEBSITE,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		long result=0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int row=jdbcTemplateObject.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                    PreparedStatement ps =connection.prepareStatement(insertLocationDetailsSQL,Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, ch.getRadLocation().getLocAddress1());
                    ps.setString(2, ch.getRadLocation().getLocAddress2());
                    ps.setString(3, ch.getRadLocation().getLocCity());
                    ps.setInt(4, ch.getRadLocation().getRedState().getStateId());
                    ps.setString(5, ch.getRadLocation().getLocZip());
                    ps.setString(6, ch.getRadLocation().getLocPhone());
                    ps.setString(7, ch.getRadLocation().getLocFax());
                    ps.setString(8, ch.getRadLocation().getLocWebsite());
                    ps.setString(9, user);
                    Date d=new Date();
                    ps.setDate(10, new java.sql.Date(d.getTime()));
                    ps.setString(11, user);
                    ps.setDate(12, new java.sql.Date(d.getTime()) );
                    return ps;
                }
            },keyHolder);
		
		if(row>0)
		{
			result=keyHolder.getKey().longValue();
		}
			
			return result;	
	}

	public int updateLocationDetails(CHInfo chInfo, String userName) throws Exception{
		String updateLocationDetailsSQL="update rad_location set LOC_ADDRESS1=?,LOC_ADDRESS2 = ?,LOC_CITY=?,LOC_STATE_ID=?,LOC_ZIP =?,LOC_PHONE = ?,LOC_FAX=?,LOC_WEBSITE =?, UPDATED_BY =?,UPDATED_DATE=? where loc_id=?";
		Object[] params = new Object[] { chInfo.getRadLocation().getLocAddress1(), chInfo.getRadLocation().getLocAddress2(), chInfo.getRadLocation().getLocCity(), chInfo.getRadLocation().getRedState().getStateId(),chInfo.getRadLocation().getLocZip(),chInfo.getRadLocation().getLocPhone(),chInfo.getRadLocation().getLocFax(),chInfo.getRadLocation().getLocWebsite(),userName,new Date(),chInfo.getRadLocation().getLocId()};
		
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.INTEGER};
		int row=jdbcTemplateObject.update(updateLocationDetailsSQL,params,types);
		return row;
	}

	public List<CHAdmin> fetchChAdmins(int chId) throws Exception{
		String fetchChAdminsSQL="select * from rad_ch_admin where ch_admin_ch_id=?";
		Object[] params=new Object[]{chId};
		return jdbcTemplateObject.query(fetchChAdminsSQL, params, new RowMapper<CHAdmin>(){

			public CHAdmin mapRow(ResultSet rs, int rowNumber) throws SQLException {
				CHAdmin chAdmin=new CHAdmin();
				chAdmin.setChAdminId(rs.getInt("CH_ADMIN_ID"));
				chAdmin.setFirstName(rs.getString("CH_ADMIN_ROLE_FIRST_NAME"));
				chAdmin.setLastName(rs.getString("CH_ADMIN_ROLE_LAST_NAME"));
				chAdmin.setPassword(rs.getString("CH_ADMIN_ROLE_PASSWORD"));
				chAdmin.setContact(rs.getString("CH_ADMIN_ROLE_PHONE"));
				chAdmin.setEmaiId(rs.getString("CH_ADMIN_ROLE_EMAIL"));
				chAdmin.setChId(rs.getInt("CH_ADMIN_CH_ID"));
			
				
				return chAdmin;
			}
			
		});

	}

	public List<RadCodes> fetchCodes(String codeType) throws Exception{
		String fetchCodesSQL="select * from rad_codes where code_type=?;";
		Object[] params=new Object[]{codeType};
		return jdbcTemplateObject.query(fetchCodesSQL, params, new RowMapper<RadCodes>(){

			public RadCodes mapRow(ResultSet rs, int rowNumber) throws SQLException {
				RadCodes radCodes=new RadCodes();
				radCodes.setCodeId(rs.getInt("CODE_ID"));
				radCodes.setCodeType(rs.getString("CODE_TYPE"));
				radCodes.setCodeValue(rs.getString("CODE_VALUE"));
				radCodes.setCodeDesc(rs.getString("CODE_DESC"));
				return radCodes;
			}
			
		});
	}

	public int fetchPracSpecId(String pracsplDesc) throws Exception{
		String fetchPracSpecIdSQL="select prac_spl_id from rad_practice_speciality where prac_spl_desc=?";
	
		Object[] params=new Object[]{pracsplDesc};
		String pracSpId= (String)jdbcTemplateObject.queryForObject(fetchPracSpecIdSQL,params,String.class);
		return Integer.parseInt(pracSpId);
	}

	public long addPracticeInfo(CHPSPBean chpsp) throws Exception{
		final CHPSPBean chpspInfo=chpsp;
		final String insertPracticeInfoSQL="insert into rad_practice_info(PRACTICE_NAME,PRACTICE_CD_PRCSTATUS_ID,PRACTICE_CD_PRCCAT_ID,PRACTICE_CD_ANSSERVICES_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?,?)";
		long result=0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int row=jdbcTemplateObject.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                    PreparedStatement ps =connection.prepareStatement(insertPracticeInfoSQL,Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, chpspInfo.getName() );
                    ps.setInt(2, 1);
                    ps.setInt(3, chpspInfo.getCategoryId());
                    ps.setInt(4, 1);
                    ps.setString(5, "clearing house");
                    ps.setDate(6, new java.sql.Date(new Date().getTime()));
                    ps.setString(7,"CH");
                    ps.setDate(8, new java.sql.Date(new Date().getTime()));
                    return ps;
                }
            },keyHolder);
		
		if(row>0)
		{
			result=keyHolder.getKey().longValue();
		}
		return result;
	}

	public int insertPSPMapping(List<Integer> spId,long newPracticeId) throws Exception{
		String insertPSPMappingQuery="insert into rad_practice_spl_mapping(PRACTICE_ID,PRACTICE_SPL_ID,PRACTICE_SPL_STATUS,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?)";
		Object[] params;
		int row=0;
		int cnt=0;
		int[] types = new int[] { Types.INTEGER, Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		for(int i=0;i<spId.size();i++)
		{
			params=new Object[]{newPracticeId,spId.get(i),'Y',"CH",new java.sql.Date(new Date().getTime()),"CH",new java.sql.Date(new Date().getTime())};
			row=jdbcTemplateObject.update(insertPSPMappingQuery,params,types);
			cnt++;
		}
		return cnt;
	}

	public int insertCHPracticeMapping(int practiceId, int chId) throws Exception{
		String insertCHPracticeMappingQuery="insert into rad_ch_practice_mapping(CH_ID,PRACTICE_ID,CH_PRAC_STATUS,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?)";
		Object[] params=new Object[]{chId,practiceId,"Y","CH",new java.sql.Date(new Date().getTime()),"CH",new java.sql.Date(new Date().getTime())};
		int[] types = new int[] { Types.INTEGER, Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		return jdbcTemplateObject.update(insertCHPracticeMappingQuery, params, types);
	}

	public List<CHPSPBean> fetchPracticesInfo(int chId) throws Exception{
		String fetchPracticesQuery="select b.practice_id practice_id,b.PRACTICE_NAME PRACTICE_NAME,c.CODE_DESC CODE_DESC from rad_practice_info b,rad_ch_practice_mapping a,rad_codes c where a.PRACTICE_ID=b.PRACTICE_ID and c.code_id=b.PRACTICE_CD_PRCCAT_ID and a.CH_ID=?";
		Object[] params=new Object[]{chId};
		return jdbcTemplateObject.query(fetchPracticesQuery, params, new RowMapper<CHPSPBean>(){

			public CHPSPBean mapRow(ResultSet rs, int rowNumber) throws SQLException {
				CHPSPBean chpsp=new CHPSPBean();
				chpsp.setName(rs.getString("PRACTICE_NAME"));
				chpsp.setCategory(rs.getString("CODE_DESC"));
				chpsp.setPracticeID(rs.getInt("practice_id"));
				//System.out.println(fetchPracSpecialities(rs.getInt("practice_id")));
				try{
				chpsp.setSp(fetchPracSpecialities(rs.getInt("practice_id")));
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Failed fetching practice specialities-->"+e.getMessage());
				}
				return chpsp;
			}
			
		});

	}

	public List<String> fetchPracSpecialities(int practiceId) throws Exception{
		String fetchPracticSpecilitiesQuery="select d.PRAC_SPL_DESC PRAC_SPL_DESC from rad_practice_info b,rad_practice_spl_mapping c,rad_practice_speciality d where b.PRACTICE_ID=c.PRACTICE_ID  and d.PRAC_SPL_ID=c.PRACTICE_SPL_ID and b.practice_id=?";
		Object[] params=new Object[]{practiceId};
		return jdbcTemplateObject.query(fetchPracticSpecilitiesQuery, params, new RowMapper<String>(){

			public String mapRow(ResultSet rs, int rowNumber) throws SQLException {
				return rs.getString("PRAC_SPL_DESC");
			}
			
		});

	}

	public int updateCHSpMappingFlag(List<Integer> ids, int chId,String flag) throws Exception{
		//String sql="delete from rad_ch_specality_mapping where CH_PRACSPL_ID=? and ch_id=? ";
		String sql="update rad_ch_specality_mapping set CH_SPL_STATUS=?  where CH_PRACSPL_ID=? and ch_id=?";
		int count=0;
		for(int i=0;i<ids.size();i++)
		{
		Object[] params = new Object[] {flag,ids.get(i),chId };
		int[] types = new int[] { Types.VARCHAR,Types.INTEGER, Types.INTEGER};
		int row=jdbcTemplateObject.update(sql,params,types);
		count=count+row;
		}
		return count;	
		
		}
	
	public int insertCHSpMapping(List<Integer> checkedIds, int chId,String userName) throws Exception {
		String sql="insert into rad_ch_specality_mapping(CH_ID,CH_PRACSPL_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?)";
		int count=0;
		for(int i=0;i<checkedIds.size();i++)
		{
		Object[] params = new Object[] {chId,checkedIds.get(i),userName,new Date(),userName,new Date() };
		int[] types = new int[] { Types.INTEGER, Types.INTEGER,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(sql,params,types);
		count=count+row;
		}
		return count;
	}

	

	public int insertNewSpeciality(String serviceName, String userName)
			throws Exception {
		String sql="insert into rad_practice_speciality(PRAC_SPL_DESC,PRAC_SPL_STATUS,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?)";
		Object[] params = new Object[] { serviceName, "Y",userName ,new Date(),userName,new Date()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(sql,params,types);
		return row;
	}
}
