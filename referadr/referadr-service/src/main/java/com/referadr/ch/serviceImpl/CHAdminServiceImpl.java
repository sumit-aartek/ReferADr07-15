package com.referadr.ch.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.model.CHPSPBean;
import com.referadr.ch.model.RadCodes;
import com.referadr.ch.service.CHAdminService;
import com.referadr.ch.repository.CHAdminDAO;
import com.referadr.login.model.Login;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.ChSpMapping;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.RedState;

@Service
public class CHAdminServiceImpl implements CHAdminService{

	@Autowired
	private CHAdminDAO chAdminDAO;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int  addCHAdmin(CHAdmin chAdmin) throws Exception{
		return chAdminDAO.addCHAdmin(chAdmin);
	}

	public CHAdmin fetchCHAdmin(String userName) throws Exception{
		return chAdminDAO.fetchChAdmin(userName);
	}

	public List<RedState> fetchStates() throws Exception{
		return chAdminDAO.fetchRadStates();
	}

	public long insertLocationDetails(CHInfo chInfo, String user) throws Exception {
		return chAdminDAO.insertLocationDetails(chInfo, user);
	}

	public int updateLocationDetails(CHInfo chInfo, String userName) throws Exception{
		return chAdminDAO.updateLocationDetails(chInfo, userName);
	}

	public int updateCHInfo(CHInfo chInfo, Login login) throws Exception{
		return chAdminDAO.updateCHInfo(chInfo, login);
	}

	public int updateCHAdmin(CHAdmin chAdmin) throws Exception{
		return chAdminDAO.updateCHAdmin(chAdmin);
	}

	public List<CHAdmin> fetchCHAdmins(int chId) throws Exception{
		return chAdminDAO.fetchChAdmins(chId);
	}

	public CHAdmin editCHAdmin(int chAdminId) throws Exception{
		return chAdminDAO.editChAdmin(chAdminId);
	}

	public List<PracticeSpecialty> fetchPracticeSpecialtyInfo() throws Exception{
		return chAdminDAO.fetchPracticeSpecialtyInfo();
	}

	public List<RadCodes> fetchCodes(String codeType) throws Exception{
		return chAdminDAO.fetchCodes(codeType);
	}

	public int fetchPracSpecId(String pracsplDesc) throws Exception{
		return chAdminDAO.fetchPracSpecId(pracsplDesc);
	}

	public long addPracticeInfo(CHPSPBean chpsp) throws Exception{
		return chAdminDAO.addPracticeInfo(chpsp);
	}

	public int insertPSPMapping(List<Integer> spId, long newPracticeId) throws Exception{
		return chAdminDAO.insertPSPMapping(spId, newPracticeId);
	}

	public int insertCHPracticeMapping(int practiceId, int chId) throws Exception{
		return chAdminDAO.insertCHPracticeMapping(practiceId, chId);
	}

	public List<CHPSPBean> fetchPracticesInfo(int chId) throws Exception{
		return chAdminDAO.fetchPracticesInfo(chId);
	}

	public List<ChSpMapping> fetchCHPracticeSpecialtyMapping(int chId)
			throws Exception {
		return chAdminDAO.fetchCHPracticeSpecialtyMapping(chId);
	}

	public int updateCHSpMappingFlag(List<Integer> deleteUnCheckedIds, int chId,String flag)
			throws Exception {
		return chAdminDAO.updateCHSpMappingFlag(deleteUnCheckedIds, chId,flag);
	}

	public int insertCHSpMapping(List<Integer> checkedIds, int chId,
			String userName) throws Exception {
		return chAdminDAO.insertCHSpMapping(checkedIds, chId, userName);
	}

	public int insertNewSpeciality(String serviceName, String userName)
			throws Exception {
		return chAdminDAO.insertNewSpeciality(serviceName, userName);
	}
}
