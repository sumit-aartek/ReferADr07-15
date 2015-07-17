package com.referadr.practice.repository;

import java.util.List;

import com.referadr.ch.model.CHAdmin;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.CHSPMappingBean;
import com.referadr.practice.model.ChSpMapping;
import com.referadr.practice.model.PracticeSpecialty;

public interface CHAdminDAO {

	public int addCHAdmin(CHAdmin chAdmin);
	public void updateCHInfo(CHInfo chInfo);
	public void updateCHAdmin(CHAdmin chAdmin);
	public CHAdmin fetchChAdmin(String userName);
	public List<PracticeSpecialty> fetchPracticeSpecialtyInfo();
	public List<ChSpMapping> fetchCHPracticeSpecialtyMapping(int chId);
	public String getPracticeSpecialty(int chspid);
	public int getPracticeSpecialtyId(String spDesc);
	public int updateCHSPMapping(CHSPMappingBean chsp);
	
}
