package com.referadr.practice.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.practice.model.InsuranceVO;
import com.referadr.practice.repository.InsuranceRepository;
import com.referadr.practice.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	private InsuranceRepository insuranceRepository;

	
	public List<InsuranceVO> getAllInsuranceList(){
		 List<InsuranceVO> allInsuranceList = null;//new ArrayList<RadSuffix>();
		 allInsuranceList = insuranceRepository.getAllInsuranceList();

		    return allInsuranceList;
		
	}
	public List<Integer> getSelectedInsuranceList(Integer providePracticeId){
		List<Integer> list=new ArrayList<Integer>();
		list=insuranceRepository.getSelectedInsuranceList(providePracticeId);
		return list;
	}
	
	public List<Integer> insertPatientIns(Integer providePracticeId,Integer insId){
		List<Integer> list=new ArrayList<Integer>();
		list=insuranceRepository.insertPatientIns(providePracticeId,insId);
		
		return list;
	}
	
	public List<Integer> deletePatientIns(Integer providePracticeId,Integer insId){
		List<Integer> list=new ArrayList<Integer>();
		list=insuranceRepository.deletePatientIns(providePracticeId,insId);
		
		return list;
	}
	
}
