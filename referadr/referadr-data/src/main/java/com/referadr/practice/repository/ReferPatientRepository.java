package com.referadr.practice.repository;

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

public interface ReferPatientRepository {

 
  /*
   * List<PatientInfo> getAllPatientInfo(PatientInfo patientInfo);
   */
  List<PatientInfo> getAllPatientInfo(String patientFirstName, String patientDateOfBirth, String patientSsn);

  List<PatientInfo> getAllPatientByNameDob(String patientFirstName, String patientDateOfBirth);

  List<PatientInfo> getAllPatientByNameSsn(String patientFirstName, String patientSsn);

  List<PatientInfo> searchByPatientDobSsn(String patientDateOfBirth, String patientSsn);

  List<PatientInfo> searchByName(String patientFirstName);

  List<PatientInfo> searchByDob(String patientDateOfBirth);

  List<PatientInfo> searchBySsn(String patientSsn);

  public List<RadLocation> getAllAddress1();
  public List<RadLocation> getAllAddress1(Integer providePracticeId);

  public List<RadLocation> getFullAddress(String address1);

  public List<ChInfoPracticeMapping> getclearingHouseName();
  public List<CHInfo> getclearingHouseName(Integer providePracticeId);
  
  public List<PracticeSpecialty> getPraticeSpecialty1(Integer practiceId);

  public List<PracticeSpecialty> getPraticeSpecialty(Integer chearingHouseId);

  public List<PracticeInfo> getPraticeInfo(Integer practiceSpectialtyId,Integer chearingHouseId);

  public List<ProviderInfo> getProviderInfo(Integer practiceInfoId);

  public boolean saveAllInformation(PatientReferralInfo patientReferralInfo,Integer providePracticeId,String fromEmail, ArrayList<FileMeta> fileList,ArrayList<FileMeta> insFileList, Integer loggedInProvideId);

  public List<RedState> getAllStateName();
  PatientReferralInfo getProviderReferralAction(Integer patientReferralId);

  
  
List<ProviderInfo> searchAllInsurance(String searchValue,Integer practiceID);
List<ProviderInfo> searchSpl(String searchValue, Integer chID);


List<DoctorTimingVO> getproviderDayOffDetails(Integer practiceInfoId);

List<DoctorTimingVO> getproviderTimeSlots(Integer practiceInfoId,String scheduleDate);

List<String> getproviderSchedulesList(Integer practiceInfoId, String scheduleDate);

boolean savePatientCheckinInfo(PatientReferralInfo patientReferralInfo);

List<LabVO> getAllLabCatagory();

List<LabVO> getLabInfoList(int subCatId);

List<LabVO> getLabSubCatByCatagory(int catagoryid);

boolean saveLabInformation(PatientReferralInfo patientReferralInfo,
		Integer providePracticeId, String fromEmail,
		ArrayList<FileMeta> fileList, ArrayList<FileMeta> insFileList,
		Integer loggedInProvideId);




}
