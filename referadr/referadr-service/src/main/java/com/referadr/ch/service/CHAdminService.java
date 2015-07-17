package com.referadr.ch.service;

import java.util.List;

import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.model.CHPSPBean;
import com.referadr.ch.model.RadCodes;
import com.referadr.login.model.Login;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.ChSpMapping;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.RedState;

public interface CHAdminService {
	
	public int addCHAdmin(CHAdmin chAdmin) throws Exception;
	public CHAdmin fetchCHAdmin(String userName) throws Exception;
	public CHAdmin editCHAdmin(int chAdminId) throws Exception;
	public List<RedState> fetchStates() throws Exception;
	public long insertLocationDetails(CHInfo chInfo,String user) throws Exception;
	public int updateLocationDetails(CHInfo chInfo,String userName) throws Exception;
	public int updateCHInfo(CHInfo chInfo,Login login) throws Exception;
	public int updateCHAdmin(CHAdmin chAdmin) throws Exception;
	public List<CHAdmin> fetchCHAdmins(int chId) throws Exception;
	public List<PracticeSpecialty> fetchPracticeSpecialtyInfo() throws Exception;
	public List<RadCodes> fetchCodes(String codeType) throws Exception;
	public int fetchPracSpecId(String pracsplDesc) throws Exception;
	public long addPracticeInfo(CHPSPBean chpsp) throws Exception;
	public int insertPSPMapping(List<Integer> spId,long newPracticeId) throws Exception;
	public int insertCHPracticeMapping(int practiceId,int chId) throws Exception;
	public List<CHPSPBean> fetchPracticesInfo(int chId) throws Exception;
	public List<ChSpMapping> fetchCHPracticeSpecialtyMapping(int chId) throws Exception;
	public int updateCHSpMappingFlag(List<Integer> deleteUnCheckedIds,int chId,String flag) throws Exception;
	public int insertCHSpMapping(List<Integer> checkedIds,int chId,String userName) throws Exception;
	public int insertNewSpeciality(String serviceName, String userName) throws Exception;

}
