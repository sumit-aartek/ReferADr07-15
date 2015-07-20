package com.referadr.practice.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.referadr.practice.repository.ReferPatientRepository;
import com.referadr.practice.service.ReferPatientService;
import com.referadr.practice.util.DateUtil;

@Service
public class ReferPatientServiceImpl implements ReferPatientService {
  @Autowired
  private ReferPatientRepository referPatientRepository;
  public List<PatientInfo> searchPatient(PatientInfo patientInfo) {

    List<PatientInfo> allPatientInfo = null;
    if (patientInfo.getPatientLastName() != null && !patientInfo.getPatientLastName().isEmpty()
        && patientInfo.getPatientDob() != null && !patientInfo.getPatientDob().isEmpty()
        && patientInfo.getPatientSSN() != null && !patientInfo.getPatientSSN().isEmpty()) {

      allPatientInfo = referPatientRepository.getAllPatientInfo(patientInfo.getPatientLastName(),
          patientInfo.getPatientDob(), patientInfo.getPatientSSN());

    } else if (patientInfo.getPatientLastName() != null && !patientInfo.getPatientLastName().isEmpty()
        && patientInfo.getPatientDob() != null && !patientInfo.getPatientDob().isEmpty()) {

      allPatientInfo = referPatientRepository.getAllPatientByNameDob(patientInfo.getPatientLastName(),
          patientInfo.getPatientDob());
    } else if (patientInfo.getPatientLastName() != null && !patientInfo.getPatientLastName().isEmpty()
        && patientInfo.getPatientSSN() != null && !patientInfo.getPatientSSN().isEmpty()) {
      allPatientInfo = referPatientRepository.getAllPatientByNameSsn(patientInfo.getPatientLastName(),
          patientInfo.getPatientSSN());
    } else if (patientInfo.getPatientDob() != null && !patientInfo.getPatientDob().isEmpty()
        && patientInfo.getPatientSSN() != null && !patientInfo.getPatientSSN().isEmpty()) {
      allPatientInfo = referPatientRepository.searchByPatientDobSsn(patientInfo.getPatientDob(),
          patientInfo.getPatientSSN());
    } else if (patientInfo.getPatientLastName() != null && !patientInfo.getPatientLastName().isEmpty()) {
      allPatientInfo = referPatientRepository.searchByName(patientInfo.getPatientLastName());
    } else if (patientInfo.getPatientDob() != null && !patientInfo.getPatientDob().isEmpty()) {
      allPatientInfo = referPatientRepository.searchByDob(patientInfo.getPatientDob());
    } else if (patientInfo.getPatientSSN() != null && !patientInfo.getPatientSSN().isEmpty()) {
      allPatientInfo = referPatientRepository.searchBySsn(patientInfo.getPatientSSN());
    }

    
    try
    { System.out.println(allPatientInfo);
    System.out.println(patientInfo);
    int i=allPatientInfo.size();
    if(i!=0){
    	for(int j=0;j<i;j++){
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date tempDate=simpleDateFormat.parse(allPatientInfo.get(j).getPatientDob());
       SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");           
       System.out.println("Output date is = "+outputDateFormat.format(tempDate));
       allPatientInfo.get(j).setPatientDob(outputDateFormat.format(tempDate));
    	} 
    }
    } catch (ParseException ex) 
     {
           System.out.println("Parse Exception");
     }
    
    System.out.println(patientInfo);
    System.out.println(allPatientInfo);
    
    return allPatientInfo;
  }

  public List<RadLocation> getAllAddress1(Integer providePracticeId) {
    List list =null; //new ArrayList<RadLocation>();
    list = referPatientRepository.getAllAddress1(providePracticeId);
    return list;
  }
  public List<RadLocation> getAllAddress1() {
	    List<RadLocation> list = new ArrayList<RadLocation>();
	    list = referPatientRepository.getAllAddress1();
	    return list;
	  }

  public List<RadLocation> getFullAddress(String address1) {
    
    return referPatientRepository.getFullAddress(address1);
  }

  public List<ChInfoPracticeMapping> getclearingHouseName() {
    List<ChInfoPracticeMapping> list = new ArrayList<ChInfoPracticeMapping>();
    list = referPatientRepository.getclearingHouseName();
    return list;
  }
  public List<CHInfo> getclearingHouseName(Integer providePracticeId) {
	    List<CHInfo> list =null; //new ArrayList<ChInfoPracticeMapping>();
	    list = referPatientRepository.getclearingHouseName(providePracticeId);
	    return list;
	  }
  
  public PatientReferralInfo getProviderReferralAction(Integer patientReferralId)
  {
	  return referPatientRepository.getProviderReferralAction(patientReferralId);
  }

  public List<PracticeSpecialty> getPraticeSpecialty(Integer chearingHouseId) {
	  List<PracticeSpecialty> practiceSplList =null; //new ArrayList<Object>();
    practiceSplList = referPatientRepository.getPraticeSpecialty(chearingHouseId);
    return practiceSplList;
  }

  //to fetch spl list according to practice loggedin
  public List<PracticeSpecialty> getPraticeSpecialty1(Integer practiceId) {
      List<PracticeSpecialty> practiceSplList =null; //new ArrayList<Object>();
    practiceSplList = referPatientRepository.getPraticeSpecialty1(practiceId);
    return practiceSplList;
  }
  
  public List<PracticeInfo> getPraticeInfo(Integer practiceSpectialtyId,Integer chearingHouseId) {
    List<PracticeInfo> practiceInfoList = null;//new ArrayList<Object>();
    practiceInfoList = referPatientRepository.getPraticeInfo(practiceSpectialtyId,chearingHouseId);
    return practiceInfoList;
  }

  public  List<ProviderInfo> getProviderInfo(Integer practiceInfoId) {
    List<ProviderInfo> providerInfoList = new ArrayList<ProviderInfo>();
    providerInfoList = referPatientRepository.getProviderInfo(practiceInfoId);
    return providerInfoList;
  }

  public boolean saveAllInformation(PatientReferralInfo patientReferralInfo ,Integer providePracticeId,String fromEmail, ArrayList<FileMeta> fileList,ArrayList<FileMeta> insFileList, Integer loggedInProvideId) {
    patientReferralInfo.getPatientInfo().getPatientInsuranceInfoList().get(0).setPatientPreAuthStartDate((DateUtil.convertStringToDate(patientReferralInfo.getPatientInfo().getPatientInsuranceInfoList().get(0).getStartDate())));
    patientReferralInfo.getPatientInfo().getPatientInsuranceInfoList().get(0).setPatientPreAuthEndDate(((DateUtil.convertStringToDate(patientReferralInfo.getPatientInfo().getPatientInsuranceInfoList().get(0).getEndDate()))));
    try
    {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy");
       Date tempDate=simpleDateFormat.parse( patientReferralInfo.getPatientInfo().getPatientDob());
       SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");           
       System.out.println("Output date is = "+outputDateFormat.format(tempDate));
       patientReferralInfo.getPatientInfo().setPatientDob(outputDateFormat.format(tempDate));
     } catch (ParseException ex) 
     {
           System.out.println("Parse Exception");
     }
    boolean status=false;
    if(patientReferralInfo.getReferToRadio().equals("yellow")) {
    status = referPatientRepository.saveAllInformation(patientReferralInfo,providePracticeId,fromEmail, fileList,insFileList,loggedInProvideId);
    }else {
		//save for lab
    	status=referPatientRepository.saveLabInformation(patientReferralInfo,providePracticeId,fromEmail, fileList,insFileList,loggedInProvideId);
	}
    
    
    return status;
  }

  public List<RedState> getAllStateName() {
    List<RedState> list = new ArrayList<RedState>();
    list = referPatientRepository.getAllStateName();
    return list;
  }

public boolean saveAllProviderInfo() {
	
	 //referPatientRepository.saveAllProviderInfo();
	return true;
}

public List<ProviderInfo> searchAllInsurance(String searchValue,Integer practiceID) {
	 System.out.println("Inside ServiceImpl..");
	 searchValue = searchValue.trim();
	 searchValue =  searchValue + '%';
	    List<ProviderInfo> searchList = referPatientRepository.searchAllInsurance(searchValue,practiceID);
	    return searchList;
	
}

public List<ProviderInfo> searchSpl(String searchValue, Integer chID){
	
	 searchValue = searchValue.trim();
	 searchValue =  searchValue + '%';
	    List<ProviderInfo> searchList = referPatientRepository.searchSpl(searchValue,chID);
	    return searchList;
	
}


public List<DoctorTimingVO> getproviderDayOffDetails(Integer practiceInfoId){
	List<DoctorTimingVO> doctorTimingVOs=new ArrayList<DoctorTimingVO>();
	doctorTimingVOs=referPatientRepository.getproviderDayOffDetails(practiceInfoId);
	return doctorTimingVOs;
}


public List<String> getProviderTimeSlots(Integer practiceInfoId,String scheduleDate) throws ParseException{
	List<String> list=new ArrayList<String>();
	List<String> scheduleslist=new ArrayList<String>();
	List<DoctorTimingVO> doctorTimingVOs=new ArrayList<DoctorTimingVO>();
	
	
	String input = scheduleDate;
	DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyy-MM-dd" );
	LocalDate localDate = formatter.parseLocalDate( input );
	int dayOfWeek = localDate.getDayOfWeek();
	
	
	/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date date = formatter.parse(scheduleDate)*/;
	
	doctorTimingVOs=referPatientRepository.getproviderTimeSlots(practiceInfoId,""+dayOfWeek);
	scheduleslist=referPatientRepository.getproviderSchedulesList(practiceInfoId,scheduleDate);
	
	
	for(DoctorTimingVO doctorTimingVO:doctorTimingVOs){
	String startTime=doctorTimingVO.getStartTime();
    String endTime=doctorTimingVO.getEndTime();
    String a=startTime.substring(0, 2);
    String b=startTime.substring(3, 5);
    int startingtimeinMin=Integer.parseInt(a)*60+Integer.parseInt(b);
    String c=endTime.substring(0, 2);
    String d=endTime.substring(3, 5);
    int endingtimeinMin=Integer.parseInt(c)*60+Integer.parseInt(d);
   // int current=startingtimeinMin;
    
    for(int current=startingtimeinMin;current<endingtimeinMin;){
    	//System.out.println(current/60+":"+current%60);
    	String times=""+current/60;
    	if((current%60)==0){
    		times=times+":00";	
    	}else{
    		times=times+":"+current%60;
    	}
    	//System.out.println(times);
    	list.add(times);
    	
    	current=current+30;
    }
	}
	
	//System.out.println("now removing times>>>>>");
	
	for(String atime:scheduleslist){
	if(list.contains(atime)){
		list.remove(atime);
	}
	}
	//System.out.println("final list>>>>>"+list);
	return list;
}


public boolean savePatientCheckinInfo(PatientReferralInfo patientReferralInfo) throws ParseException{
	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date tempDate=simpleDateFormat.parse( patientReferralInfo.getPatientInfo().getPatientDob());
      SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");           
      System.out.println("Output date is = "+outputDateFormat.format(tempDate));
      patientReferralInfo.getPatientInfo().setPatientDob(outputDateFormat.format(tempDate));
	boolean status=referPatientRepository.savePatientCheckinInfo(patientReferralInfo);
	return status;
}

public List<LabVO> getAllLabCatagory(){
	List<LabVO> labVOs=new ArrayList<LabVO>();
	labVOs=referPatientRepository.getAllLabCatagory();
	return labVOs;
	
}


public List<LabVO> getLabInfoList(int subCatId){
	List<LabVO> labVOs=new ArrayList<LabVO>();
	labVOs=referPatientRepository.getLabInfoList(subCatId);
	return labVOs;
}


public List<LabVO> getLabSubCatByCatagory(int catagoryId){

List<LabVO> labVOs=new ArrayList<LabVO>();
labVOs=referPatientRepository.getLabSubCatByCatagory(catagoryId);
return labVOs;
}




}
