package com.referadr.ch.repository;

import java.util.List;

import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.Referral_Provider_Action;

public interface chInboxRepository {

	List<PateintInfoVO> getInboxValue(int clearHouseId);

	PatientReferralInfo getProviderId(Integer patientReferralId);
	
	PatientReferralInfo getFromProviderId(Integer patientReferralId);
	List<ProviderInfo> getProviderInfo(int providePracticeId);

	List<CHInfo> getclearingHouseName(int chId);

	boolean updateRefferInformation(PatientReferralInfo patientReferralInfo,
			int chId, String fromEmail, int chAdminId,int fromProviderId);

	PatientReferralInfo getChIDAndSpecilityId(Integer patientReferralId);

	void updateMessage(Referral_Provider_Action referral_Provider_Action,
			ProviderInfo providerInfo, int chAdminId, int chId);

}
