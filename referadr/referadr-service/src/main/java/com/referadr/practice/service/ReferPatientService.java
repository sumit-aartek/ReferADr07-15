package com.referadr.practice.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.ChInfoPracticeMapping;
import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.FileMeta;
import com.referadr.practice.model.LabVO;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.RedState;

public interface ReferPatientService {
	
	PatientReferralInfo getProviderReferralAction(Integer patientReferralId);
	List<PatientInfo>searchPatient(PatientInfo patientInfo);
	public List<RadLocation> getAllAddress1(Integer providePracticeId);
	public List<RadLocation> getAllAddress1();

//	public List<Object> getFullAddress(String address1);
	public List<RadLocation> getFullAddress(String address1);

	public List<ChInfoPracticeMapping> getclearingHouseName();
	
	public List<CHInfo> getclearingHouseName(Integer providePracticeId);

	public List<PracticeSpecialty> getPraticeSpecialty(Integer chearingHouseId);

	 public List<PracticeSpecialty> getPraticeSpecialty1(Integer practiceId);
	
	public List<PracticeInfo> getPraticeInfo(Integer practiceSpectialtyId,Integer chearingHouseId);

	public List<ProviderInfo> getProviderInfo(Integer practiceInfoId);
  public boolean saveAllInformation(PatientReferralInfo patientReferralInfo,Integer providePracticeId,String fromEmail, ArrayList<FileMeta> fileList, ArrayList<FileMeta> insFileList, Integer loggedInProvideId);
  public List<RedState> getAllStateName();

List<ProviderInfo> searchAllInsurance(String searchValue,Integer practiceID);
List<ProviderInfo> searchSpl(String searchValue, Integer chID);



List<DoctorTimingVO> getproviderDayOffDetails(Integer practiceInfoId);
List<String> getProviderTimeSlots(Integer practiceInfoId,String scheduleDate) throws ParseException;
boolean savePatientCheckinInfo(PatientReferralInfo patientReferralInfo) throws ParseException;

List<LabVO> getAllLabCatagory();
List<LabVO> getLabInfoList(int subCatId);
List<LabVO> getLabSubCatByCatagory(int catagoryId);

}
