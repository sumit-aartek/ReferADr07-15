package com.referadr.practice.service;

import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;

public interface PracticeService {
	
	public int insertProviderInfo(ProviderInfo providerInfo) throws Exception;
	public PracticeInfo fetchPracticeInfo(int practiceId) throws Exception;
	public int updatePracticeInfo(PracticeInfo practiceInfo,String userName) throws Exception;
}
