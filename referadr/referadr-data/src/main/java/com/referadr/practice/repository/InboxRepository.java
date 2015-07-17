package com.referadr.practice.repository;

import java.text.ParseException;
import java.util.List;

import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.PateintAndInsuranceInfoVO;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PrintVO;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.ReferralProviderActionVO;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.model.ScheduleVO;

public interface InboxRepository {

   public List<PateintInfoVO> getInboxValue(int providePracticeId);

  public PateintAndInsuranceInfoVO getPatientInformation(Integer patientRefInfo);

  public List<ReferralProviderActionVO> getReferPatientList(int patientId);

  public boolean updateRefferInformation(PatientReferralInfo patientReferralInfo,Integer providePracticeId,String fromEmail,int fromProviderId,Integer loggedInProvideId);

  public List<Object> updateAdditionalBotes(Integer providerId);

  public void updateMessage(Referral_Provider_Action referral_Provider_Action, ProviderInfo providerInfo,int practiceId,Integer loggedInProvideId);

public PatientReferralInfo getProviderId(Integer patientReferralId);
public PatientReferralInfo getFromProviderId(Integer patientReferralId);

public int saveShedule(String dateTime, Integer refId, Integer provId,Integer withProvId,
		String notes, String fromEmail) throws ParseException;

public int findWithProvider( Integer refId);

public List<ScheduleVO> getscheduleList(Integer patientRefInfo);

public PrintVO getPrintInfo(Integer patientReferralId);

public DoctorTimingVO findProviderPatientSchedule(Integer refId,
		Integer withProvId);

public List<PateintAndInsuranceInfoVO> checkPhoneAvaibility(String searchValue);

public List<PateintInfoVO> getDraftValue(Integer practiceId);

public List<String> getNewNotification(Integer practiceId, String startDateTime,String enddateTime);

public List<PatientReferralInfo> getSelectedLabServicesRepo(PatientReferralInfo patientReferralInfo);

public List<PatientReferralInfo> getSelectedLabSubServicesRepo(PatientReferralInfo patientReferralInfo);

public PatientReferralInfo getSelectedLabNotesRepo(PatientReferralInfo patientReferralInfo);

public List<PatientReferralInfo> getLabPatientInfoRepo(PatientReferralInfo patientReferralInfo);

public List<PateintInfoVO> getSendboxValue(int providePracticeId);

}
