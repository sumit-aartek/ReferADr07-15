package com.referadr.practice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.referadr.login.model.Login;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.ChInfoPracticeMapping;
import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.Document;
import com.referadr.practice.model.FileMeta;
import com.referadr.practice.model.LabVO;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PracticeSpecialty;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadLocation;
import com.referadr.practice.model.RedState;
import com.referadr.practice.service.ReferPatientService;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

@Controller
public class ReferPatientController {
  LinkedList<FileMeta> files = new LinkedList<FileMeta>();
  FileMeta fileMeta = null;
  LinkedList<FileMeta> referFiles = new LinkedList<FileMeta>();
  FileMeta referFileMeta = null;

  @Autowired
  private ReferPatientService referPatientService;
  @RequestMapping("/referPatient")
  public String showReferPatientPage(Map<String, Object> map, Model model,HttpServletRequest request,@RequestParam String sendBy)
{
    /* To remove the old contains of files */
	  HttpSession session = request.getSession();
	  
		Login login = (Login) session.getAttribute("login");

		if(sendBy.equals("menu")){
			session.removeAttribute("referralInfo");
		}
		
		
   try  {
	   if(login!=null){
	   files.removeAll(files);
	    referFiles.removeAll(referFiles);
	    map.put("PatientReferralInfo", new PatientReferralInfo());
	    List<ProviderInfo> providerInfoList = null;
	//    HttpSession session=request.getSession();
	    int providePracticeId=0;
	    if(session.getAttribute("providePracticeId")!=null)
	     providePracticeId    = ((Integer)session.getAttribute("providePracticeId"))  ;
	    providerInfoList = referPatientService.getProviderInfo(providePracticeId);
	    if (providerInfoList != null) {
	      model.addAttribute("ProviderInfo", providerInfoList);
	      if(providerInfoList.size()==1){
	      model.addAttribute("providerSize", providerInfoList.size());
	      model.addAttribute("providerId", providerInfoList.get(0).getProviderId());
	     // model.addAttribute("providerFirstName", ((Object[])providerInfoList.get(0))[1]);
	      providerInfoList=null;
	      }
	    }
	    List<RadLocation> address1List = null;
	    address1List = referPatientService.getAllAddress1(providePracticeId);
	    if (address1List != null) {
	    	  List<RadLocation> radLocationList=address1List;
	    	  RadLocation radLocation=radLocationList.get(0);
	      model.addAttribute("address1List", radLocationList);
	      if(address1List.size()==1){
	    	
	      
	     
	      model.addAttribute("size", address1List.size());
	      model.addAttribute("locId",radLocation.getLocId() );
	      
	      model.addAttribute("address1",radLocation.getLocAddress1());
	      address1List=null;//kill list
	      radLocation=null;//kill obj
	      }
	    }
	    List<CHInfo> chlearingHouseList = null;
	    chlearingHouseList = referPatientService.getclearingHouseName(providePracticeId);
	   /* if (chlearingHouseList != null) {
	      model.addAttribute("chlearingHouseList", chlearingHouseList);
	    }*/
	    if (chlearingHouseList != null) {
	        model.addAttribute("chlearingHouseList", chlearingHouseList);
	        if(chlearingHouseList.size()==1){
	         model.addAttribute("clearHouselistSize", chlearingHouseList.size()); 
	         model.addAttribute("chid", chlearingHouseList.get(0).getId()); 
	         chlearingHouseList=null;//kill list
	        }
	      }
	    List<RedState> stateList = referPatientService.getAllStateName();
	    if (stateList != null) {
	      model.addAttribute("stateList", stateList);
	      stateList=null;//kill list
	    }
   }else{
	   model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
		return "redirect:/logout.do"; 
   }
//	    return "referPatient";
//	   return "referPatientNew";
	   return "referPatient7_15";
   }
   catch (Exception e)
   {
	   StringWriter errors = new StringWriter();
	    e.printStackTrace(new PrintWriter(errors));
		ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Refer a Patient", errors.toString());
		model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
		return "redirect:/logout.do";
	   
   }

   }
  @RequestMapping(value = "/patientSearchAction", method = { RequestMethod.GET, RequestMethod.POST })
  public String showPatientSearchActionPage(@ModelAttribute("PatientInfo") PatientInfo patientInfo,
      Map<String, Object> map, Model model, HttpServletRequest request) 
  {
   try {
	   
	   map.put("PatientInfo", new PatientInfo());
	    List<PatientInfo> allPatientInfo = null;
	    if (patientInfo != null) {
	      allPatientInfo = referPatientService.searchPatient(patientInfo);

	    }
	    model.addAttribute("allPatientInfoList", allPatientInfo);
	    
	    return "patientSearch";
   }
   catch (Exception e)
   {
	   StringWriter errors = new StringWriter();
	    e.printStackTrace(new PrintWriter(errors));
		ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Refer a Patient", errors.toString());
		model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
		return "redirect:/logout.do";
	   
   }
  }

  @RequestMapping(value = "/patientSearchAction", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, List> providerInfoByPracticeInfo(@RequestParam String searchKeyword, String dob,
      String patientSsn)
      {
	  
	
    List<PatientInfo> allPatientInfo = null;
    PatientInfo patientInfo = new PatientInfo();
    patientInfo.setPatientLastName(searchKeyword);
    patientInfo.setPatientDob(dob);
    patientInfo.setPatientSSN(patientSsn);
    Map<String, List> providerInfoMap = new HashMap<String, List>();
    if (patientInfo != null) {
      allPatientInfo = referPatientService.searchPatient(patientInfo);
      
    /*  ArrayList<Document> insdocList= CommonUtils.getDocumentListByRefProvActionId(allPatientInfo.get(0).getPatientInsuranceInfoList().get(0).getPatientInsuranceInfoId());
    		     
    		      if(insdocList.size()!=0){
    		    	  providerInfoMap.put("insdocList", insdocList);
    		     }*/
      
      ArrayList allInsDocList = new ArrayList();
      for(PatientInfo info:allPatientInfo)
      {
      ArrayList<Document> insdocList= CommonUtils.getDocumentListByRefProvActionId(info.getPatientInsuranceInfoList().get(0).getPatientInsuranceInfoId());
      allInsDocList.add(insdocList);
      }      
            if(allInsDocList.size()!=0){
             providerInfoMap.put("allInsDocList", allInsDocList);
           }
            
            
      providerInfoMap.put("practiceInfoList", allPatientInfo);
    }
    return providerInfoMap; 
	  
	
  }

  @RequestMapping(value = "/fullAddressByaddress1", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,  List<RadLocation>> getFullAddressByAddress1(@RequestParam String address1)
  
  {
	 
	  List<RadLocation> fullAddressList =null; //new ArrayList<Object>();
    Map<String, List<RadLocation>> fullAddressMap = new HashMap<String,  List<RadLocation>>();
    if (address1 != null) {
      fullAddressList = referPatientService.getFullAddress(address1);
      fullAddressMap.put("fullAddressByAddress1", fullAddressList);
    }
    return fullAddressMap;
	
  }

  @RequestMapping(value = "/practiceSpecialtyBychearingHouse", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, List<PracticeSpecialty>> getPraticeSpecialtyByChearingHouse(@RequestParam Integer chearingHouseId,Model model)
  {
	 
		 List<PracticeSpecialty> practiceSpecialtyList = null;//new ArrayList<Object>();
		    Map<String, List<PracticeSpecialty>> practiceSplMap = new HashMap<String, List<PracticeSpecialty>>();
		    if (chearingHouseId != null) {
		      practiceSpecialtyList = referPatientService.getPraticeSpecialty(chearingHouseId);
		      practiceSplMap.put("practiceSpecialtyList", practiceSpecialtyList);
		      if(practiceSpecialtyList.size()==1){
		    	  practiceSplMap.put("practiceSpecialtySize", practiceSpecialtyList); 
		    	  
		      }
		      
		    }
		    return practiceSplMap;
	 
  }

  //to fetch spl related to a practice logged in.
  @RequestMapping(value = "/practiceSpecialtyByPractice", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, List<PracticeSpecialty>> practiceSpecialtyByPractice(HttpServletRequest request)
  {
      HttpSession session = request.getSession();
        Login login = (Login) session.getAttribute("login");
        
//                    HttpSession session=request.getSession();
                    Integer providePracticeId=0;
                    if(session.getAttribute("providePracticeId")!=null)
                     providePracticeId    = ((Integer)session.getAttribute("providePracticeId"))  ;
                    
         List<PracticeSpecialty> practiceSpecialtyList = null;//new ArrayList<Object>();
            Map<String, List<PracticeSpecialty>> practiceSplMap = new HashMap<String, List<PracticeSpecialty>>();
            if (providePracticeId !=null) {
              practiceSpecialtyList = referPatientService.getPraticeSpecialty1(providePracticeId);
              practiceSplMap.put("practiceSpecialtyList", practiceSpecialtyList);
              if(practiceSpecialtyList.size()==1){
                  practiceSplMap.put("practiceSpecialtySize", practiceSpecialtyList); 
                  
              }
              
            }
            
            return practiceSplMap;
     
  }

  
  @RequestMapping(value = "/PracticeInfoBypracticeSpecialty", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, List<PracticeInfo>> practiceSpecialtyByPracticeInfo(@RequestParam Integer practiceSpectialtyId,Integer chearingHouseId )
  {
    
    	List<PracticeInfo> practiceInfoList = new ArrayList<PracticeInfo>();
        Map<String,  List<PracticeInfo>> practiceInfoMap = new HashMap<String,  List<PracticeInfo>>();
        if (practiceSpectialtyId != null) {
          practiceInfoList = referPatientService.getPraticeInfo(practiceSpectialtyId,chearingHouseId);
          practiceInfoMap.put("practiceInfoList", practiceInfoList);
          if(practiceInfoList.size()!=1){
        	  practiceInfoMap.put("practiceSize", practiceInfoList); 
          }
          
        }
        return practiceInfoMap;
   
  }

  @RequestMapping(value = "/providerInfoByPracticeInfo", method = RequestMethod.GET)
  @ResponseBody
  public Map<String, List<ProviderInfo>> providerInfoByPracticeInfo(@RequestParam Integer practiceInfoId) 
  
  {
 
	   List<ProviderInfo> practiceInfoList = new ArrayList<ProviderInfo>();
	    Map<String, List<ProviderInfo>> providerInfoMap = new HashMap<String, List<ProviderInfo>>();
	    if (practiceInfoId != null) {
	      practiceInfoList = referPatientService.getProviderInfo(practiceInfoId);
	      providerInfoMap.put("practiceInfoList", practiceInfoList);
	    }
	    return providerInfoMap;

  }

  @RequestMapping(value = "/saveAllDetails", method = RequestMethod.POST)
  public String saveAllPatientIfnormation(
      @ModelAttribute("PatientReferralInfo") PatientReferralInfo patientReferralInfo, BindingResult result,
      ModelMap model, Map<String, Object> map, HttpServletRequest request, @RequestParam("insfiles") MultipartFile[] insfiles, 
		@RequestParam("referfiles") MultipartFile[] referfiles) throws IllegalStateException, IOException
  
  { 
  try {
	  List<ProviderInfo> providerInfoList = null;
	    HttpSession session=request.getSession();
	    session.removeAttribute("referralInfo");
	    int providePracticeId    = ((Integer)session.getAttribute("providePracticeId"))  ;
	    Login loginMember = (Login) session.getAttribute("login");
	    String fromEmail= loginMember.getUserName();
	    /*providerInfoList = referPatientService.getAllProviderInfo(providePracticeId);
	    if (providerInfoList != null) {
	      model.addAttribute("ProviderInfo", providerInfoList);
	    }*/

	   /* List<RadLocation> address1List = null;
	    address1List = referPatientService.getAllAddress1();
	    if (address1List != null) {
	      model.addAttribute("address1List", address1List);
	    }*/
	   /* List<ChInfoPracticeMapping> chlearingHouseList = null;
	    chlearingHouseList = referPatientService.getclearingHouseName();
	    if (chlearingHouseList != null) {
	      model.addAttribute("chlearingHouseList", chlearingHouseList);
	    }*/
	   /* List<RedState> stateList = referPatientService.getAllStateName();
	    if (stateList != null) {
	      model.addAttribute("stateList", stateList);
	    }*/
	    patientReferralInfo.setPracticeId(providePracticeId);
	    
	    //Attachments
	    ArrayList<FileMeta> fileList = new ArrayList<FileMeta>();
	    ArrayList<FileMeta> insFileList = new ArrayList<FileMeta>();
	    if (insfiles != null && insfiles.length > 0) {
	    	insFileList = ReferadrUtils.getFiles(insfiles,insFileList,IConstant.FILE_REF_PROV_ACTION);
	    }
	    if (referfiles != null && referfiles.length > 0) {
	    	fileList = ReferadrUtils.getFiles(referfiles,fileList,IConstant.FILE_REF_PROV_ACTION);
	    }
	    
	      
        
//                    HttpSession session=request.getSession();
                    Integer loggedInProvideId=0;
                   
                    loggedInProvideId    = ((Integer)session.getAttribute("loggedInProvideId"))  ;
	    
	    
	    
	    boolean status = referPatientService.saveAllInformation(patientReferralInfo,providePracticeId,fromEmail, fileList,insFileList , loggedInProvideId);
	    System.out.println(status);
	   /* return "redirect:/referPatient.do";*/
	    return "redirect:/practice.do";
  }catch(Exception e)
  {
		StringWriter errors = new StringWriter();
	    e.printStackTrace(new PrintWriter(errors));
		ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Refer a Patient", errors.toString());
		model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
		return "redirect:/logout.do";
	}
  }

  /**
   * This Method will add uploaded files to List.
   * @author Dileep
   * @param files
   * @return
   */
  private ArrayList<FileMeta> getFiles(MultipartFile[] files,  ArrayList<FileMeta> fileList, String keyName) {
	  FileMeta fileMeta = new FileMeta();
	  
	  try {
		  if (files != null && files.length > 0) {

			  for (MultipartFile file : files) {
				  if (file.getSize() > 0) {
					  fileMeta = new FileMeta();
					  fileMeta.setFileName(file.getOriginalFilename());
					  fileMeta.setFile(file);
					  fileMeta.setFileKey(keyName);
					  fileMeta.setFilePath(keyName+"/"+file.getOriginalFilename());
					  fileList.add(fileMeta);
				  }
			  }
		  }
	  } catch (Exception ex) {
		  // Replace this with Log.error in future
		  System.out.println(ex.getMessage());
	  }
	  return fileList;
  }
  
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody
  LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) 
  
  {
    Iterator<String> itr = request.getFileNames();
    MultipartFile mpf = null;
    while (itr.hasNext()) {
      mpf = request.getFile(itr.next());
      System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());
      if (files.size() >= 10)
        files.pop();
      fileMeta = new FileMeta();
      fileMeta.setFileName(mpf.getOriginalFilename());
      fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
      fileMeta.setFileType(mpf.getContentType());
      try {
        fileMeta.setBytes(mpf.getBytes());
      
      } catch (IOException e) {
        e.printStackTrace();
      }
      files.add(fileMeta);
    }
    return files;

  }
  
  @RequestMapping(value="/uploadReferDoc", method = RequestMethod.POST)
  public @ResponseBody LinkedList<FileMeta> referUpload(MultipartHttpServletRequest request, HttpServletResponse response)
  
  {

    //1. build an iterator
     Iterator<String> itr =  request.getFileNames();
     MultipartFile mpf = null;

     //2. get each file
     while(itr.hasNext()){
       
       //2.1 get next MultipartFile
       mpf = request.getFile(itr.next()); 
       System.out.println(mpf.getOriginalFilename() +" uploaded! "+referFiles.size());

       //2.2 if files > 10 remove the first from the list
       if(referFiles.size() >= 10)
         referFiles.pop();
       
       //2.3 create new fileMeta
       referFileMeta = new FileMeta();
       referFileMeta.setFileName(mpf.getOriginalFilename());
       referFileMeta.setFileSize(mpf.getSize()/1024+" Kb");
       referFileMeta.setFileType(mpf.getContentType());
       
       try {
         referFileMeta.setBytes(mpf.getBytes());
     
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        
      }
       //2.4 add to files
       referFiles.add(referFileMeta);
     }
    // result will be like this
    // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
    return referFiles;

  }
  
  @RequestMapping(value = "/searchMap", method = RequestMethod.GET)
  @ResponseBody
  public List<ProviderInfo> searchAddress(@RequestParam String searchValue,Integer practiceID) {
    List<ProviderInfo> searchList = new ArrayList<ProviderInfo>();
    if (!searchValue.isEmpty()) {
      searchList = referPatientService.searchAllInsurance(searchValue,practiceID);
    }
    return searchList;
  }
  
  
  
  @RequestMapping(value = "/searchSplMap", method = RequestMethod.GET)
  @ResponseBody
  public List<ProviderInfo> searchSplMap(@RequestParam String searchValue,Integer chID) {
    List<ProviderInfo> searchList = new ArrayList<ProviderInfo>();
    if (!searchValue.isEmpty()) {
      searchList = referPatientService.searchSpl(searchValue,chID);
    }
    return searchList;
  }
  
  
  
  @RequestMapping(value = "/practiceInsuranceList", method = RequestMethod.GET)
  @ResponseBody
  public List<ProviderInfo> practiceInsuranceList(@RequestParam String searchValue,Integer practiceID) {
    List<ProviderInfo> searchList = new ArrayList<ProviderInfo>();
 
      searchList = referPatientService.searchAllInsurance(searchValue,practiceID);
   
    return searchList;
  }
  
  
  @RequestMapping(value = "/getproviderDayOffDetails", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,  List<DoctorTimingVO>> getproviderDayOffDetails(@RequestParam Integer practiceInfoId)
  {
	  List<DoctorTimingVO> doctorTimingVOs =null; //new ArrayList<Object>();
    Map<String, List<DoctorTimingVO>> dayOffMap= new HashMap<String,  List<DoctorTimingVO>>();
    if (practiceInfoId != null) {
    	doctorTimingVOs = referPatientService.getproviderDayOffDetails(practiceInfoId);
      dayOffMap.put("fullAddressByAddress1", doctorTimingVOs);
    }
    return dayOffMap;
	
  }
  
  
  @RequestMapping(value = "/getAllLabCatagory", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,  List<LabVO>> getAllLabCatagory()
  {
	  List<LabVO> labVOs =null; //new ArrayList<Object>();
    Map<String, List<LabVO>> labMap= new HashMap<String,  List<LabVO>>();
    
    labVOs = referPatientService.getAllLabCatagory();
      labMap.put("labCatagories", labVOs);
   
    return labMap;
	
  }
  
  
  
  @RequestMapping(value = "/getLabInfoList", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,  List<LabVO>> getLabInfoList(@RequestParam Integer subCatId)
  {
	  List<LabVO> labVOs =null; //new ArrayList<Object>();
    Map<String, List<LabVO>> labMap= new HashMap<String,  List<LabVO>>();
    
    labVOs = referPatientService.getLabInfoList(subCatId);
      labMap.put("LabInfoList", labVOs);
   
    return labMap;
	
  }
  
  
  
  @RequestMapping(value = "/getLabSubCatByCatagory", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,  List<LabVO>> getLabServicesByCatagory(@RequestParam Integer catagoryId)
  {
	  List<LabVO> labVOs =null; //new ArrayList<Object>();
    Map<String, List<LabVO>> labMap= new HashMap<String,  List<LabVO>>();
    
    labVOs = referPatientService.getLabSubCatByCatagory(catagoryId);
      labMap.put("labSubCatList", labVOs);
   
    return labMap;
	
  }
  
  
  
  
  
  
  
  @RequestMapping(value = "/getProviderTimeSlots", method = RequestMethod.GET)
  @ResponseBody
  public Map<String,  List<String>> getProviderTimeSlots(@RequestParam Integer practiceInfoId, String scheduleDate) throws ParseException
  {
	  Map<String, List<String>> timingListMap= new HashMap<String,  List<String>>();
	  List<String> timings =new ArrayList<String>(); //new ArrayList<Object>();
   
    if (practiceInfoId != null) {
    	timings = referPatientService.getProviderTimeSlots(practiceInfoId,scheduleDate);
     
    }
    timingListMap.put("timings", timings);
    return timingListMap;
	
  }
  
  
  
  @RequestMapping(value = "/savePatientCheckin", method = RequestMethod.POST)
  public String savePatientCheckin(
      @ModelAttribute("PatientReferralInfo") PatientReferralInfo patientReferralInfo, BindingResult result,
      ModelMap model, Map<String, Object> map, HttpServletRequest request)
  { 
  try {
	int practiceid= patientReferralInfo.getPracticeInfo().getPracticeId();
	  boolean status = referPatientService.savePatientCheckinInfo(patientReferralInfo);

	    return "redirect:/patientCheckin.do?practiceId="+practiceid;
  }catch(Exception e)
  {
		StringWriter errors = new StringWriter();
	    e.printStackTrace(new PrintWriter(errors));
		ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Refer a Patient", errors.toString());
		model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
		return "redirect:/logout.do";
	}
  }
  
  
  
  
  
}
