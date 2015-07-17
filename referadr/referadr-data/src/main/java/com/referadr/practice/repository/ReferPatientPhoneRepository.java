package com.referadr.practice.repository;

import com.referadr.practice.model.PatientReferralInfo;

public interface ReferPatientPhoneRepository {

	int saveReferAPatientDraft(PatientReferralInfo patientReferralInfo);

	PatientReferralInfo fetchDraftInfo(Integer draftId);

}
