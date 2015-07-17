package com.referadr.ch.repository;

import java.util.List;

import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.model.CHPSPBean;
import com.referadr.ch.model.RadCodes;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.CHSPMappingBean;
import com.referadr.practice.model.ChSpMapping;
import com.referadr.practice.model.RedState;
import com.referadr.login.model.Login;
import com.referadr.practice.model.PracticeSpecialty;

public interface CHAdminDAO {

	public int addCHAdmin(CHAdmin chAdmin) throws Exception;
	public int updateCHInfo(CHInfo chInfo,Login login) throws Exception;
	public int updateCHAdmin(CHAdmin chAdmin) throws Exception;
	public CHAdmin fetchChAdmin(String userName) throws Exception;
	public CHAdmin editChAdmin(int chAdminId) throws Exception;
	public List<PracticeSpecialty> fetchPracticeSpecialtyInfo() throws Exception;
	public List<ChSpMapping> fetchCHPracticeSpecialtyMapping(int chId) throws Exception;
	public String getPracticeSpecialty(int chspid) throws Exception;
	public int getPracticeSpecialtyId(String spDesc) throws Exception;
	public int updateCHSPMapping(CHSPMappingBean chsp) throws Exception;
	public List<RedState> fetchRadStates() throws Exception;
	public long insertLocationDetails(CHInfo chInfo,String userName) throws Exception;
	public int updateLocationDetails(CHInfo chInfo,String userName) throws Exception;
	public List<CHAdmin> fetchChAdmins(int chId) throws Exception;
	public List<RadCodes> fetchCodes(String codeType) throws Exception;
	public int fetchPracSpecId(String pracsplDesc) throws Exception;
	public long addPracticeInfo(CHPSPBean chpsp) throws Exception;
	public int insertPSPMapping(List<Integer> spId,long newPracticeId) throws Exception;
	public int insertCHPracticeMapping(int practiceId,int chId) throws Exception;
	public List<CHPSPBean> fetchPracticesInfo(int chId) throws Exception;
	public List<String> fetchPracSpecialities(int practiceId) throws Exception;
	public int updateCHSpMappingFlag(List<Integer> ids, int chId,String flag) throws Exception;
	public int insertCHSpMapping(List<Integer> checkedIds,int chId,String userName) throws Exception;
	public int insertNewSpeciality(String serviceName,String userName) throws Exception;

	
}
