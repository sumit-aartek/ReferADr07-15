package com.referadr.practice.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.PateintAndInsuranceInfoVO;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PrintVO;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.ReferralProviderActionVO;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.model.ScheduleVO;
import com.referadr.practice.repository.InboxRepository;
import com.referadr.practice.service.InboxService;

@Service
public class InboxServiceImpl implements InboxService {
	@Autowired
	private InboxRepository inboxRepository;

	public List<PateintInfoVO> getInboxValue(int providePracticeId) {
		List<PateintInfoVO> infoList = inboxRepository
				.getInboxValue(providePracticeId);
		return infoList;
	}
	
	public List<PateintInfoVO> getDraftValue(Integer practiceId){
		List<PateintInfoVO> infoList = inboxRepository
				.getDraftValue(practiceId);
		return infoList;
		
	}
	
	
	
	
	

	public PateintAndInsuranceInfoVO getPatientInformation(
			Integer patientRefInfo) {
		return inboxRepository.getPatientInformation(patientRefInfo);

	}

	public List<ReferralProviderActionVO> getReferPatientList(int refId) {
		List<ReferralProviderActionVO> infos = inboxRepository
				.getReferPatientList(refId);
		return infos;
	}

	public boolean updateRefferInformation(
			PatientReferralInfo patientReferralInfo, Integer providePracticeId,
			String fromEmail,int fromProviderId, Integer loggedInProvideId) {
		boolean status = inboxRepository.updateRefferInformation(
				patientReferralInfo, providePracticeId, fromEmail,fromProviderId, loggedInProvideId);
		return status;
	}

	public Referral_Provider_Action updateAdditionalBotes(Integer providerId) {
		List<Object> list = new ArrayList<Object>();
		list = inboxRepository.updateAdditionalBotes(providerId);
		if (list != null) {
			Referral_Provider_Action action = (Referral_Provider_Action) list
					.get(0);
			return action;
		}
		return null;
	}

	public void updateMessage(
			Referral_Provider_Action referral_Provider_Action,
			ProviderInfo providerInfo, int practiceId, Integer loggedInProvideId) {
		inboxRepository.updateMessage(referral_Provider_Action, providerInfo,practiceId,loggedInProvideId);
	}

	public PatientReferralInfo getProviderId(Integer patientReferralId) {
		PatientReferralInfo providerIdList = null;
		providerIdList = inboxRepository.getProviderId(patientReferralId);
		return providerIdList;
	}
	
	public PatientReferralInfo getFromProviderId(Integer patientReferralId) {
		PatientReferralInfo providerIdList=null;
		providerIdList=inboxRepository.getFromProviderId(patientReferralId);
		return providerIdList;
	}

	public List<PateintInfoVO> searchByPracticeOrPatientName(
			List<PateintInfoVO> referralInfoList, String practiceOrPatientName,
			String selectedType, String identifierVar) {
		List<PateintInfoVO> PateintInfoVOList = new ArrayList<PateintInfoVO>();

		if (referralInfoList != null) {
			for (PateintInfoVO infoVO : referralInfoList) {
				if (selectedType.equals("PatientName")) {

					if (infoVO.getPateintFirstName().toLowerCase()
							.contains(practiceOrPatientName.toLowerCase())) {
						PateintInfoVOList.add(infoVO);
					}
				} else {
					if (infoVO.getPracticeName().toLowerCase()
							.contains(practiceOrPatientName.toLowerCase())) {
						PateintInfoVOList.add(infoVO);
					}
				}
			}
		}
		return PateintInfoVOList;
	}
	
	public int saveShedule(String dateTime, Integer refId, Integer provId,Integer withProvId,
			String notes, String fromEmail) throws ParseException{
				
		int result=inboxRepository.saveShedule(dateTime,refId,provId,withProvId,notes,fromEmail);
		return result;
	}
	
	public int findWithProvider(Integer refId){
				
		int result=inboxRepository.findWithProvider(refId);
		return result;
	}
	
	public List<ScheduleVO> getscheduleList(Integer patientRefInfo){
		 List<ScheduleVO> list=new ArrayList<ScheduleVO>();
		 list=inboxRepository.getscheduleList(patientRefInfo);
		return list;
	}
	
	public PrintVO getPrintInfo(Integer patientReferralId){
		
		PrintVO printVO=new PrintVO();
		printVO=inboxRepository.getPrintInfo(patientReferralId);
		return printVO;
	}
	
	
	public DoctorTimingVO findProviderPatientSchedule(Integer refId,Integer withProvId){
		DoctorTimingVO doctorTimingVO=new DoctorTimingVO();
		doctorTimingVO=inboxRepository.findProviderPatientSchedule(refId,withProvId);
		
		return doctorTimingVO;
	}
	
	 public List<PateintAndInsuranceInfoVO> checkPhoneAvaibility(String searchValue) {
		    List<PateintAndInsuranceInfoVO> searchList = inboxRepository.checkPhoneAvaibility(searchValue);
		      return searchList;
		 }
	 
	 public List<String> getUpdatesNotifications(Integer practiceId,String startDateTime, String enddateTime){
		 List<String> list=new ArrayList<String>();
		 list=inboxRepository.getNewNotification(practiceId,startDateTime,enddateTime);
		 return list;
	 }

	public List<PatientReferralInfo> getSelectedLabServices(PatientReferralInfo patientReferralInfo) {
	      List<PatientReferralInfo> selectedLabServicesList=inboxRepository.getSelectedLabServicesRepo(patientReferralInfo);
		return selectedLabServicesList;
	}
	 
	public List<PatientReferralInfo> getSelectedLabSubServices(PatientReferralInfo patientReferralInfo) {
	      List<PatientReferralInfo> selectedLabSubServicesList=inboxRepository.getSelectedLabSubServicesRepo(patientReferralInfo);
		return selectedLabSubServicesList;
	}

	
	public PatientReferralInfo getSelectedLabNotes(PatientReferralInfo patientReferralInfo) {
	      PatientReferralInfo selectedLabNotes=inboxRepository.getSelectedLabNotesRepo(patientReferralInfo);
		return selectedLabNotes;
	}
	
	public List<PatientReferralInfo> getLabPatientInfo(PatientReferralInfo patientReferralInfo) {
	      List<PatientReferralInfo> labPatientInfo=inboxRepository.getLabPatientInfoRepo(patientReferralInfo);
		return labPatientInfo;
	}
	
	public List<PateintInfoVO> getSendboxValue(int providePracticeId){
		List<PateintInfoVO> pateintInfoVOs=new ArrayList<PateintInfoVO>();
		pateintInfoVOs=inboxRepository.getSendboxValue(providePracticeId);
		return pateintInfoVOs;
	}
}
