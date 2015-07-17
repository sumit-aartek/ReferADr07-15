package com.referadr.practice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.login.repository.LoginDAO;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.repository.PracticeRepository;
import com.referadr.practice.service.PracticeService;
@Service
public class PracticeServiceImpl implements PracticeService {

	@Autowired
	private PracticeRepository practiceRepository;
	
	public int insertProviderInfo(ProviderInfo providerInfo) throws Exception {
		System.out.println("providerInfo firstname-->"+providerInfo.getProviderFirstName());
		return practiceRepository.insertProviderInfo(providerInfo);
	}

	public PracticeInfo fetchPracticeInfo(int practiceId) throws Exception {
		
		return practiceRepository.fetchPracticeInfo(practiceId);
	}

	public int updatePracticeInfo(PracticeInfo practiceInfo, String userName)
			throws Exception {
		return practiceRepository.updatePracticeInfo(practiceInfo, userName);
	}

	public int insertPracLocMapping(int practiceId, int locId) {
		String insertPracLocMappingQuery="insert into rad_practice_locations(practice_id,loc_id,CREATED_BY,CREATION_DATE,UPDATED_BY,UPDATED_DATE) values(?,?,?,?,?,?)";
		
		return 0;
	}

}
