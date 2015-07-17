package com.referadr.practice.repository;

import java.util.List;

import com.referadr.practice.model.InsuranceVO;

public interface InsuranceRepository {

	List<InsuranceVO> getAllInsuranceList();

	List<Integer> insertPatientIns(Integer providePracticeId,Integer insId);
	List<Integer> deletePatientIns(Integer providePracticeId,Integer insId);

	List<Integer> getSelectedInsuranceList(Integer providePracticeId);
}
