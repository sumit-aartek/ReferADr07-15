package com.referadr.practice.repository;

import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeLocations;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;

public interface PracticeRepository {
	public int insertProviderInfo(ProviderInfo providerInfo) throws Exception;
	public PracticeInfo fetchPracticeInfo(int practiceId) throws Exception;
	public PracticeLocations fetchPracticeLocations(int practiceId);
	public RadLocation fetchPracticeLocation(int locId);
	public int updatePracticeInfo(PracticeInfo practiceInfo,String userName) throws Exception;
	public int insertPracLocMapping(int practiceId, int locId,String userName);
	public int updatePracLocDetails(RadLocation radLocation,String userName);
}
