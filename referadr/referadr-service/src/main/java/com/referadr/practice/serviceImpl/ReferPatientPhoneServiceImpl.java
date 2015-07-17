package com.referadr.practice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.repository.ReferPatientPhoneRepository;
import com.referadr.practice.service.ReferPatientPhoneService;

@Service
public class ReferPatientPhoneServiceImpl implements ReferPatientPhoneService {
	@Autowired
	private ReferPatientPhoneRepository referPatientPhoneRepository;

	
	public int saveReferAPatientDraft(PatientReferralInfo patientReferralInfo){
		
		int status=referPatientPhoneRepository.saveReferAPatientDraft(patientReferralInfo);
		return status;
	}
	
	
	public PatientReferralInfo fetchDraftInfo(Integer draftId){
		PatientReferralInfo referralInfo= new PatientReferralInfo();
		referralInfo=referPatientPhoneRepository.fetchDraftInfo(draftId);
		
		
		return referralInfo;
	}
	
}
