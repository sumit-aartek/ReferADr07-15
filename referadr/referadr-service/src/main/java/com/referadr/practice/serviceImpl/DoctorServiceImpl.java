package com.referadr.practice.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadBoardCertifications;
import com.referadr.practice.model.RadCodes;
import com.referadr.practice.model.RadLanguage;
import com.referadr.practice.model.RadProviderSpeciality;
import com.referadr.practice.model.RadSpecialityVisitReasons;
import com.referadr.practice.model.RadSuffix;
import com.referadr.practice.repository.DoctorRepository;
import com.referadr.practice.service.DoctorService;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ImageFormat;
import com.referadr.practice.util.ReferadrUtils;
@Service
public class DoctorServiceImpl implements DoctorService{
@Autowired
private DoctorRepository doctorRepository;
	public List<RadCodes> getAllProviderRole() {
		 List<RadCodes> list = null;//new ArrayList<RadCodes>();
		    list = doctorRepository.getAllProviderRole();

		    return list;
		
		
	}
	public List saveAllProviderInfo(ProviderInfo providerInfo,String fromEmail) {
	//	List providerInfoList=doctorRepository.saveAllProviderInfo(providerInfo);
		List providerInfoList=null;
		Integer code=providerInfo.getRadCodes().getCodeId();
		if(code==477||code==478){
		/*String toEmailId=providerInfo.getProviderEmail();
		String subject="This is for Create a provider";
		String body="Sender of people is logged people";
		System.out.println("email id from== "+fromEmail);
		System.out.println("to Email Id== "+toEmailId);
		System.out.println("subject== "+subject);
		System.out.println("body== "+body);*/
			int practiceId=providerInfo.getPracticeInfo().getPracticeId();
		PracticeInfo practiceName=	doctorRepository.getPracticeName(practiceId);
			System.out.println("practiceName"+practiceName.getPracticeName());
		
			String toEmailId=providerInfo.getProviderEmail();
			String providerName=providerInfo.getProviderFirstName();
			  String subject="ReferADr Registration";
			  String body="Hello "+providerName+" \n \n Your name has been added to the ReferADr platform as one of the administrators for "+practiceName.getPracticeName()+" here Please use this link - http://default-environment-fv2kbsasei.elasticbeanstalk.com/providerRegistration.do?practiceId="+practiceId+"&staffId="+code+" to register in order for you to start using the platform.\n \n \n \n Best \n \n";
			 ReferadrUtils.sendMail( fromEmail,toEmailId, subject, body);
			  
		}else{
			 providerInfoList=doctorRepository.saveAllProviderInfo(providerInfo);
			
		}
		
			return providerInfoList;
		
	}
/*	public List<ProviderInfo> editProviderInfo() {
		
		return null;
	}*/
	public List<RadSuffix> getAllRadSuffix() {
		 List<RadSuffix> radsuffixlist = null;//new ArrayList<RadSuffix>();
		 radsuffixlist = doctorRepository.getAllRadSuffix();

		    return radsuffixlist;
}
	public List<RadProviderSpeciality> getAllRadProviderSpeciality() {
		 List<RadProviderSpeciality> radProviderSpecialitylist = null;//new ArrayList<RadSuffix>();
		 radProviderSpecialitylist = doctorRepository.getAllRadProviderSpeciality();

		    return radProviderSpecialitylist;
	}
	public List<RadLanguage> getAllRadLanguage() {
		 List<RadLanguage> radLanguagelist = null;//new ArrayList<RadSuffix>();
		 radLanguagelist = doctorRepository.getAllRadLanguage();

		    return radLanguagelist;
}
	public List<RadBoardCertifications> getAllRadBoardCertifications() {
		 List<RadBoardCertifications> radBoardCertificationslist = null;//new ArrayList<RadSuffix>();
		 radBoardCertificationslist = doctorRepository.getAllRadBoardCertifications();

		    return radBoardCertificationslist;
}
	public List<RadSpecialityVisitReasons> getVisitorReason(Integer radioId) {

		List<RadSpecialityVisitReasons> visitorReasonList = new ArrayList<RadSpecialityVisitReasons>();
		visitorReasonList = doctorRepository.getVisitorReason(radioId);
	    return visitorReasonList;
	}
	
	
	
	public int SaveDoctorTiming(DoctorTimingVO doctorTimingVO){
		
		int status=doctorRepository.SaveDoctorTiming(doctorTimingVO);
		return status;
	}
	
	
	
	public List<DoctorTimingVO> getProviderTiming(int provId){
		List<DoctorTimingVO>doctorTimingVOs=new ArrayList<DoctorTimingVO>();
		doctorTimingVOs=doctorRepository.getProviderTiming(provId);
		return doctorTimingVOs;
	}
	
	public List<DoctorTimingVO> DayOffDetails(int provId){
	List<DoctorTimingVO>doctorTimingVOs=new ArrayList<DoctorTimingVO>();
	doctorTimingVOs=doctorRepository.DayOffDetails(provId);
	return doctorTimingVOs;
}
	
	public boolean saveFinalProvider(ProviderInfo providerInfo) {
		// TODO Auto-generated method stub
		  BufferedImage newImg;
	        String imageData = providerInfo.getImgPath().replaceFirst("^data:image/[^;]*;base64,?", "");
	        newImg = ImageFormat.decodeToImage(imageData);
	        String imageName=null;
	        if (newImg != null) {
	          try {
	            File f = new File(IConstant.docImagePath);
	            f.mkdirs();
	            imageName=providerInfo.getProviderFirstName()+"_"+providerInfo.getProviderId() + ".png";
	            ImageIO.write(newImg, "png", new File(IConstant.docImagePath +imageName ));
	             
	          } catch (IOException e) {
	            e.printStackTrace();
	          }
	        }
	        providerInfo.setProviderPicLink(imageName);
		return doctorRepository.saveFinalProvider(providerInfo);
	}
	public List findLastProvedr() {
		List list=null;
		list= doctorRepository.findLastProvedr();
		return list;
	}
	public List<ProviderInfo> getAllProviderInfo(int providerPracticeId) {
			 List<ProviderInfo> list = null;//new ArrayList<RadCodes>();
			    list = doctorRepository.getAllProviderInfo(providerPracticeId);

			    return list;

	}
	public List<ProviderInfo> getvalueProviderInfo(int provId) {
		List<ProviderInfo> providerInfo = null;//new ArrayList<RadCodes>();
		providerInfo = doctorRepository.getvalueProviderInfo(provId);
	      /* for(Object a:providerInfo)
	       {
	    	   Object[] obj =( Object[])a;
	    	   
	    	   System.out.println(obj[16]);
	       }*/
		
	    return providerInfo;

	}
	public List<Integer> getvalueProviderInfoSuffixId(int provId) {
		List<Integer> providerInfo = null;//new ArrayList<RadCodes>();
		providerInfo = doctorRepository.getvalueProviderInfoSuffixId(provId);
	      /* for(Object a:providerInfo)
	       {
	    	   Object[] obj =( Object[])a;
	    	   
	    	   System.out.println(obj[16]);
	       }*/
		
	    return providerInfo;

	}
	
	
	public List<Integer> getvalueProviderVisitRsnId(int provId) {
		List<Integer> providerInfo = null;//new ArrayList<RadCodes>();
		providerInfo = doctorRepository.getvalueProviderVisitRsnId(provId);
	      /* for(Object a:providerInfo)
	       {
	    	   Object[] obj =( Object[])a;
	    	   
	    	   System.out.println(obj[16]);
	       }*/
		
	    return providerInfo;

	}
	
	public List<Integer> getvalueProviderLangSpokenId(int provId) {
		List<Integer> providerInfo = null;//new ArrayList<RadCodes>();
		providerInfo = doctorRepository.getvalueProviderLangSpokenId(provId);
	      /* for(Object a:providerInfo)
	       {
	    	   Object[] obj =( Object[])a;
	    	   
	    	   System.out.println(obj[16]);
	       }*/
		
	    return providerInfo;

	}
	
	
	public List<Integer> getvalueProviderCertificationId(int provId) {
		List<Integer> providerInfo = null;//new ArrayList<RadCodes>();
		providerInfo = doctorRepository.getvalueProviderCertificationId(provId);
	      /* for(Object a:providerInfo)
	       {
	    	   Object[] obj =( Object[])a;
	    	   
	    	   System.out.println(obj[16]);
	       }*/
		
	    return providerInfo;

	}
	
}
