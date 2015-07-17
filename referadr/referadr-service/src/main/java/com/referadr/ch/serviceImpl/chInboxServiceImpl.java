package com.referadr.ch.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.ch.repository.chInboxRepository;
import com.referadr.ch.service.chInboxService;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.ChInfoPracticeMapping;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.Referral_Provider_Action;
/**
 * This service  class is created for display All The functionality of Practice inbox
 *
 * @author Aartek Software Solutions
 * @version 1.0
 * @since 2015-01-05
 */
@Service
public class chInboxServiceImpl implements chInboxService {
	 @Autowired
	 private chInboxRepository chInboxRepository;
	public List<PateintInfoVO> getInboxValue(int clearHouseId) {
		
		 List<PateintInfoVO> infoList = chInboxRepository.getInboxValue(clearHouseId);
		    return infoList;
	}
	public PatientReferralInfo getProviderId(Integer patientReferralId) {
		PatientReferralInfo providerIdList=null;
		providerIdList=chInboxRepository.getProviderId(patientReferralId);
		return providerIdList;
	}
	
	public PatientReferralInfo getFromProviderId(Integer patientReferralId) {
		PatientReferralInfo providerIdList=null;
		providerIdList=chInboxRepository.getFromProviderId(patientReferralId);
		return providerIdList;
	}
	public List<ProviderInfo> getProviderInfo(int providePracticeId) {
		 List<ProviderInfo> providerInfoList = new ArrayList<ProviderInfo>();
		    providerInfoList = chInboxRepository.getProviderInfo(providePracticeId);
		    return providerInfoList;
	}
	public List<CHInfo> getclearingHouseName(int chId) {
		 List<CHInfo> list =null; //new ArrayList<ChInfoPracticeMapping>();
		    list = chInboxRepository.getclearingHouseName(chId);
		    return list;
	}
	public boolean updateRefferInformation(
			PatientReferralInfo patientReferralInfo, int chId, String fromEmail,int chAdminId,int fromProviderId) {
		 boolean status = chInboxRepository.updateRefferInformation(patientReferralInfo,chId,fromEmail,chAdminId,fromProviderId);
		    return status;
	}
	public PatientReferralInfo getChIDAndSpecilityId(Integer patientReferralId) {
		PatientReferralInfo list=null;
		list=chInboxRepository.getChIDAndSpecilityId(patientReferralId);
		return list;
	}
	 public void updateMessage(Referral_Provider_Action referral_Provider_Action,ProviderInfo providerInfo,int chAdminId,int chId) {
		 chInboxRepository.updateMessage(referral_Provider_Action,providerInfo,chAdminId,chId);
		  }

}
