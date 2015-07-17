package com.referadr.practice.util;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class RADCodeUtils {
	private static JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		RADCodeUtils.jdbcTemplateObject = new  JdbcTemplate(dataSource);
	}
	
	/**
	 * Get the CodeId based on CodeType and CodeValue
	 * @author Dileep
	 * @param codeType
	 * @param codeValue
	 * @return
	 */
	public static int getCodeId(String codeType, String codeValue) {
		int codeId = 0;
		try {
			String fetchProviderPracticeIdSQL="select CODE_ID from rad_codes where CODE_TYPE = ?  and CODE_VALUE = ?";
			Object[] params=new Object[]{codeType, codeValue};
			codeId =  jdbcTemplateObject.queryForInt(fetchProviderPracticeIdSQL, params);
			
		} catch (Exception ex) {
			System.out.println("Exception while getting codeId: "+ex.getMessage());
		}
		return codeId;
	}

}
