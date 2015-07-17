package com.referadr.practice.repository;

import java.util.List;

import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadBoardCertifications;
import com.referadr.practice.model.RadCodes;
import com.referadr.practice.model.RadLanguage;
import com.referadr.practice.model.RadProviderSpeciality;
import com.referadr.practice.model.RadSpecialityVisitReasons;
import com.referadr.practice.model.RadSuffix;

public interface DoctorRepository {

	List<RadCodes> getAllProviderRole();

	List saveAllProviderInfo(ProviderInfo providerInfo);

	List<RadSuffix> getAllRadSuffix();

	List<RadProviderSpeciality> getAllRadProviderSpeciality();

	List<RadLanguage> getAllRadLanguage();

	List<RadBoardCertifications> getAllRadBoardCertifications();

	List<RadSpecialityVisitReasons> getVisitorReason(Integer radioId);

	List findLastProvedr();
	List<ProviderInfo> getAllProviderInfo(int providerPracticeId);
//21 fab
	List<ProviderInfo> getvalueProviderInfo(int provId);
	List<Integer> getvalueProviderInfoSuffixId(int provId);
	
	List<Integer> getvalueProviderVisitRsnId(int provId);
	List<Integer> getvalueProviderLangSpokenId(int provId);
	List<Integer> getvalueProviderCertificationId(int provId);
	
	boolean saveFinalProvider(ProviderInfo providerInfo);

//	PracticeInfo getPracticeName();

	PracticeInfo getPracticeName(int practiceId);

	int SaveDoctorTiming(DoctorTimingVO doctorTimingVO);

	List<DoctorTimingVO> getProviderTiming(int provId);

	List<DoctorTimingVO> DayOffDetails(int provId);

}
