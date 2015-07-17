package com.referadr.practice.service;

import com.referadr.practice.model.PatientReferralInfo;

public interface ReferPatientPhoneService {

	int saveReferAPatientDraft(PatientReferralInfo patientReferralInfo);

	PatientReferralInfo fetchDraftInfo(Integer draftId);

}
