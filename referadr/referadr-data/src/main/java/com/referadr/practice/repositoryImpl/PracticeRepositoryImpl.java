package com.referadr.practice.repositoryImpl;

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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.referadr.login.repositoryImpl.LoginDAOImpl;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeLocations;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.repository.PracticeRepository;
@Repository
public class PracticeRepositoryImpl implements PracticeRepository {
	
	private JdbcTemplate jdbcTemplateObject;	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new  JdbcTemplate(dataSource);
	}

	public int insertProviderInfo(ProviderInfo providerInfo) throws Exception{
		String insertProviderInfoQuery="insert into rad_provider_info(PROVIDER_FIRST_NAME,PROVIDER_LAST_NAME,PROVIDER_PWD,PROVIDER_PHONE,PROVIDER_EMAIL,PROVIDER_PRAC_ID,PROVIDER_CD_PRVROLE_ID,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("insertProviderInfoQuery-->"+insertProviderInfoQuery);
		System.out.println(providerInfo.getProviderFirstName()+"--"+ providerInfo.getProviderLastName()+"--"+ providerInfo.getProviderPwd()+"--"+ providerInfo.getProviderPhone()+"--"+providerInfo.getProviderEmail()+"--"+providerInfo.getPracticeInfo().getPracticeId()+"--"+providerInfo.getRadCodes().getCodeId()+"--"+providerInfo.getProviderEmail()+"--"+new Date()+"--"+providerInfo.getProviderEmail()+"--"+new Date());
		Object[] params = new Object[] { providerInfo.getProviderFirstName(), providerInfo.getProviderLastName(), providerInfo.getProviderPwd(), providerInfo.getProviderPhone(),providerInfo.getProviderEmail(),providerInfo.getPracticeInfo().getPracticeId(),providerInfo.getRadCodes().getCodeId(),providerInfo.getProviderEmail(),new Date(),providerInfo.getProviderEmail(),new Date()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(insertProviderInfoQuery,params,types);
		return row;
	}

	public PracticeInfo fetchPracticeInfo(int practiceId) throws Exception {
		String fetchPracticeInfoQuery="select a.practice_name practiceName,a.practice_id practiceId from rad_practice_info a where a.practice_id=?";
		Object[] params=new Object[]{practiceId};
		PracticeInfo practiceInfo=new PracticeInfo();
		List<PracticeInfo> practiceInfos=new ArrayList<PracticeInfo>();
		practiceInfos= jdbcTemplateObject.query(fetchPracticeInfoQuery, params, new RowMapper<PracticeInfo>(){

			public PracticeInfo mapRow(ResultSet rs, int rowNumber) throws SQLException {
				PracticeInfo practiceInfo=new PracticeInfo();
				practiceInfo.setPracticeName(rs.getString("practiceName"));
				practiceInfo.setPracticeId(rs.getInt("practiceId"));
				PracticeLocations pl=fetchPracticeLocations(rs.getInt("practiceId"));
				if(pl!=null)
				{
				practiceInfo.setPracticeLocations(pl);
				}
				else
				{
					pl=new PracticeLocations();
					pl.setPrecticeLocationId(888888);
					practiceInfo.setPracticeLocations(pl);
				}
					
				return practiceInfo;
			}
			
		});
		if(!practiceInfos.isEmpty()){
			practiceInfo=practiceInfos.get(0);
		}
		return practiceInfo;
	}

	public PracticeLocations fetchPracticeLocations(int practiceId)
			{
		String fetchPracticeLocationsQuery="select practice_loc_id,practice_id,loc_id from rad_practice_locations where practice_id=?";
		Object[] params=new Object[]{practiceId};
		PracticeLocations practiceLocations=new PracticeLocations();
		List<PracticeLocations> locations=new ArrayList<PracticeLocations>();
		locations= jdbcTemplateObject.query(fetchPracticeLocationsQuery, params, new RowMapper<PracticeLocations>(){

			public PracticeLocations mapRow(ResultSet rs, int rowNumber) throws SQLException {
				PracticeLocations practiceLocation = new PracticeLocations();
				practiceLocation.setPrecticeLocationId(rs.getInt("practice_loc_id"));
				practiceLocation.setRadLocation(fetchPracticeLocation(rs.getInt("loc_id")));
				
					
				return practiceLocation;
			}
			
		});
		if(!locations.isEmpty()){
			practiceLocations=locations.get(0);
		}
		return practiceLocations;
	}
	
	
	public RadLocation fetchPracticeLocation(int locId) 
	{
		String fetchLocInfoSQL="select a.loc_id loc_id,a.LOC_ADDRESS1 LOC_ADDRESS1,a.LOC_ADDRESS2 LOC_ADDRESS2,a.LOC_CITY LOC_CITY,b.STATE_NAME STATE_NAME,a.LOC_ZIP LOC_ZIP,a.LOC_PHONE LOC_PHONE,a.LOC_FAX LOC_FAX,a.LOC_WEBSITE LOC_WEBSITE from rad_location a,rad_state b where a.loc_id=? and a.LOC_STATE_ID=b.STATE_ID";
		Object[] params=new Object[]{locId};
		try{
		return jdbcTemplateObject.query(fetchLocInfoSQL, params, new RowMapper<RadLocation>(){

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
			
		}).get(0);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public int updatePracticeInfo(PracticeInfo practiceInfo,String userName) throws Exception{
		int row=0;
		if(practiceInfo.getPracticeLocations().getPrecticeLocationId()==888888)
		{
			long locId=insertLocationDetails(practiceInfo.getPracticeLocations().getRadLocation(),userName);
			if(locId > 0 && locId < 888888)
			{
				row=insertPracLocMapping(practiceInfo.getPracticeId(),(int)locId,userName);
			}
		}
		else
		{
			updatePracLocDetails(practiceInfo.getPracticeLocations().getRadLocation(),userName);
		}
		return row;
	}
	
	
	
	public long insertLocationDetails(RadLocation radLocation,String userName) throws Exception
	{
		final RadLocation loc=radLocation;
		final String user=userName;
		final String insertLocationDetailsSQL="insert into rad_location(LOC_ADDRESS1,LOC_ADDRESS2,LOC_CITY,LOC_STATE_ID,LOC_ZIP,LOC_PHONE,LOC_FAX,LOC_WEBSITE,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		long result=0;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int row=jdbcTemplateObject.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                    PreparedStatement ps =connection.prepareStatement(insertLocationDetailsSQL,Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, loc.getLocAddress1());
                    ps.setString(2, loc.getLocAddress2());
                    ps.setString(3, loc.getLocCity());
                    ps.setInt(4, loc.getRedState().getStateId());
                    ps.setString(5, loc.getLocZip());
                    ps.setString(6, loc.getLocPhone());
                    ps.setString(7, loc.getLocFax());
                    ps.setString(8, loc.getLocWebsite());
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

	public int insertPracLocMapping(int practiceId, int locId, String userName) {
		String insertPracLocMappingQuery="insert into rad_practice_locations(practice_id,loc_id,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?)";
		Object[] params = new Object[] { practiceId,locId, userName, new Date(),userName,new Date()};
		int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP};
		int row=jdbcTemplateObject.update(insertPracLocMappingQuery,params,types);
		return row;
	}

	public int updatePracLocDetails(RadLocation radLocation,String userName) {
		String updatePracLocDetails="update rad_location set LOC_ADDRESS1=?,LOC_ADDRESS2=?,LOC_CITY=?,LOC_STATE_ID=?,LOC_ZIP=?,LOC_PHONE=?,LOC_FAX=?,LOC_WEBSITE=?,UPDATED_BY=?,UPDATED_DATE=? where LOC_ID=?";
		Object[] params=new Object[]{radLocation.getLocAddress1(),radLocation.getLocAddress2(),radLocation.getLocCity(),radLocation.getRedState().getStateId(),radLocation.getLocZip(),radLocation.getLocPhone(),radLocation.getLocFax(),radLocation.getLocWebsite(),userName,new Date(),radLocation.getLocId()};
		int[] types = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.INTEGER};
		return jdbcTemplateObject.update(updatePracLocDetails,params,types);
	}
}
