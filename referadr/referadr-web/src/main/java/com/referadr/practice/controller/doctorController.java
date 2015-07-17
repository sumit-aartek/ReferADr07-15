package com.referadr.practice.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.referadr.login.model.Login;
import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.Document;
import com.referadr.practice.model.FileMeta;
import com.referadr.practice.model.PrintVO;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadBoardCertifications;
import com.referadr.practice.model.RadCodes;
import com.referadr.practice.model.RadLanguage;
import com.referadr.practice.model.RadProviderSpeciality;
import com.referadr.practice.model.RadSpecialityVisitReasons;
import com.referadr.practice.model.RadSuffix;
import com.referadr.practice.service.DoctorService;
import com.referadr.practice.service.ReferPatientService;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;
@Controller
public class doctorController {
	 @Autowired
	  private ReferPatientService referPatientService;
	 @Autowired
	private DoctorService doctorService; 
	 @RequestMapping(value="/doctor", method = { RequestMethod.GET, RequestMethod.POST })
	  public String showDoctorPage(@ModelAttribute("ProviderInfo") ProviderInfo providerInfo,
		      Map<String, Object> map, Model model, HttpServletRequest request) 
	 {
		 try {
			 HttpSession session=request.getSession();
			 Login login = (Login) session.getAttribute("login");
			 if(login!=null){
			 List<ProviderInfo> providerInfoList = null;
			
			// if(session.getAttribute("providePracticeId")==null){
			 int providePracticeId    = ((Integer)session.getAttribute("providePracticeId"))  ;
			 providerInfoList = doctorService.getAllProviderInfo(providePracticeId);
				if (providerInfoList != null) {
					model.addAttribute("ProviderInfoList", providerInfoList);
				}
			 List<RadCodes> providerRole = null;
			 providerRole = doctorService.getAllProviderRole();
				if (providerRole != null) {
					model.addAttribute("RadCodes", providerRole);
				}
			 }else{
				 model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
					return "redirect:/logout.do";  
			 }
		    /*return "doctor";*/
			/* return "doctorNew";*/
			 return "doctor7_15";
			 
		 }catch(Exception e)
		 {
				StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::doctor", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
	  }
	 
	 
	 /*
	 this method is for doctor timing availability
	 */
	 @RequestMapping(value="/doctorTiming",method = { RequestMethod.GET, RequestMethod.POST })
	  public String doctorTiming(@ModelAttribute("DoctorTimingVO") DoctorTimingVO doctorTimingVO,
			  Map<String, Object> map, Model model,HttpServletRequest request){
		 HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
		try {
			if(login!=null){
				 String Id=request.getParameter("provId");
				 int provId=0;
					if(Id!=null){
					 provId=Integer.parseInt(Id);
				model.addAttribute("providerID", provId);
				List<DoctorTimingVO> doctorTimingVOs=new ArrayList<DoctorTimingVO>();
				doctorTimingVOs=doctorService.getProviderTiming(provId);
				model.addAttribute("doctorTimingVOs", doctorTimingVOs);
				
				List<DoctorTimingVO> DayOffdoctorTimingVOs=new ArrayList<DoctorTimingVO>();
				DayOffdoctorTimingVOs=doctorService.DayOffDetails(provId);
				model.addAttribute("DayOffdoctorTimingVOs", DayOffdoctorTimingVOs);
					}
			
			}else{
				   model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
					return "redirect:/logout.do"; 
			   }
//				    return "doctorTiming";
			return "doctorTimingNew";
		}
		catch(Exception e)
		 {
				StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Edit Doctor", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
	  }
			
	 
	 
	 @RequestMapping(value="/editDoctor",method = { RequestMethod.GET, RequestMethod.POST })
	  public String showEditDoctorPage(@ModelAttribute("ProviderInfo") ProviderInfo providerInfo,
			  Map<String, Object> map, Model model,HttpServletRequest request){
		 HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
		try {
		
			if(login!=null){
			 map.put("ProviderInfo", new ProviderInfo());
			 
				//---------------
					 String Id=request.getParameter("provId");
					 int provId=0;
						if(Id!=null){
						 provId=Integer.parseInt(Id);
							 List<ProviderInfo> editproviderInfo = null;
							 List<Integer> editproviderInfoSuffixId = null;
							 List<Integer> editproviderVisitRsnId = null;
							 List<Integer> editproviderLangSpokenId = null;
							 List<Integer> editproviderCertificationId = null;
							 editproviderInfo = doctorService.getvalueProviderInfo(provId);
							 editproviderInfoSuffixId = doctorService.getvalueProviderInfoSuffixId(provId);
							 editproviderVisitRsnId = doctorService.getvalueProviderVisitRsnId(provId);
							 editproviderLangSpokenId = doctorService.getvalueProviderLangSpokenId(provId); 
							 editproviderCertificationId = doctorService.getvalueProviderCertificationId(provId);
							 
								if (editproviderInfo != null) {
									model.addAttribute("list", editproviderInfo.get(0));
									model.addAttribute("listSuffixId", editproviderInfoSuffixId);
									model.addAttribute("listVisitId",editproviderVisitRsnId);
									model.addAttribute("listLangSpokenId",editproviderLangSpokenId);
									model.addAttribute("listCertificationId",editproviderCertificationId);
								} 
						}else{
							List list= doctorService.findLastProvedr();
							 model.addAttribute("list",list.get(0));
								model.addAttribute("listSuffixId", "null");
								model.addAttribute("listVisitId","null");
								model.addAttribute("listLangSpokenId","null");
								model.addAttribute("listCertificationId","null");
						}
					/* List<ProviderInfo> editProviderValue=null;
					 editProviderValue = doctorService.editProviderInfo();
					 if(editProviderValue !=null){
						 model.addAttribute("ProviderInfo",editProviderValue);
					 }*/
					 
					 List<RadSuffix> radSuffix = null;
					 radSuffix = doctorService.getAllRadSuffix();
						if (radSuffix != null) {
							model.addAttribute("RadSuffix", radSuffix);
						}
					
					 List<RadProviderSpeciality> radProviderSpeciality = null;
					 radProviderSpeciality = doctorService.getAllRadProviderSpeciality();
						if (radProviderSpeciality != null) {
							model.addAttribute("RadProviderSpeciality", radProviderSpeciality);
					}
				    
				  	List<RadLanguage> radLanguage = null;
				        	radLanguage = doctorService.getAllRadLanguage();
							if (radLanguage != null) {
								model.addAttribute("RadLanguage", radLanguage);
						}

					List<RadBoardCertifications> radBoardCertifications = null;
					radBoardCertifications = doctorService.getAllRadBoardCertifications();
							if (radLanguage != null) {
								model.addAttribute("RadBoardCertifications", radBoardCertifications);
						}
				   
								//======================================================== 
							ArrayList<Document> docList = CommonUtils.getDocumentListByProviderId(provId);		
							model.addAttribute("docList", docList);
							if(docList.size()!=0){
							System.out.println(docList.get(0).getDocPath());
						String imagePath=IConstant.SITE_URL+docList.get(0).getDocPath();
						model.addAttribute("imagePath", imagePath);
							 
							}
			}else{
				   model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
					return "redirect:/logout.do"; 
			   }
				    return "editDoctor";
		}
		catch(Exception e)
		 {
				StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Edit Doctor", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
	  }
//===============Method for show Visitor Resons by Ajax=======
	 @RequestMapping(value = "/visitorreason", method = RequestMethod.GET)
	 @ResponseBody
	 public Map<String, List<RadSpecialityVisitReasons>> visitorreason(@RequestParam Integer radioId)
	 
	 {
		
		  List<RadSpecialityVisitReasons> VisitorReasonList = new ArrayList<RadSpecialityVisitReasons>();
	      Map<String, List<RadSpecialityVisitReasons>> VisitorReasonMap = new HashMap<String, List<RadSpecialityVisitReasons>>();
	     if (radioId != null) {
	    	VisitorReasonList = doctorService.getVisitorReason(radioId);
	    	VisitorReasonMap.put("visitorreason", VisitorReasonList);
	      }
	    return VisitorReasonMap;
	  }

		 
	 /*
	  * to save dr. availability timing
	  * */
	 @RequestMapping(value = "/SaveDoctorTiming", method = { RequestMethod.GET, RequestMethod.POST })
	  public String SaveDoctorTiming(@ModelAttribute("DoctorTimingVO") DoctorTimingVO doctorTimingVO,
		      Map<String, Object> map, Model model, HttpServletRequest request) 
	 
	 {
		
			 HttpSession session = request.getSession();
				Login login = (Login) session.getAttribute("login");
			try {
			
				if(login!=null){
			
			 map.put("DoctorTimingVO", new DoctorTimingVO());
			
			 
			 int status=doctorService.SaveDoctorTiming(doctorTimingVO);
			 
			/* HttpSession session=request.getSession();
			  Login loginMember = (Login) session.getAttribute("login");
			  String fromEmail= loginMember.getUserName();
			  List list =doctorService.saveAllProviderInfo(providerInfo,fromEmail);
			  if(list!=null){
			  model.addAttribute("list",list.get(0));
			
			  // return "redirect:/editDoctor.do";
			  }
			  else{
				  return "redirect:/ doctor.do";
			  }
			  return "redirect:/editDoctor.do";*/
		
				}else{
					   model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
						return "redirect:/logout.do"; 
				   }
				return "redirect:/ doctor.do";
				}
		catch(Exception e)
		 {
				StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::save Provider", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
	  }
	
	 
	 
	 @RequestMapping(value = "/saveDoctorInfo", method = { RequestMethod.GET, RequestMethod.POST })
	  public String saveDoctorPage(@ModelAttribute("ProviderInfo") ProviderInfo providerInfo,
		      Map<String, Object> map, Model model, HttpServletRequest request) 
	 
	 {
		try {
			 map.put("ProviderInfo", new ProviderInfo());
			 HttpSession session=request.getSession();
			  Login loginMember = (Login) session.getAttribute("login");
			  String fromEmail= loginMember.getUserName();
			  List list =doctorService.saveAllProviderInfo(providerInfo,fromEmail);
			  if(list!=null){
			  model.addAttribute("list",list.get(0));
			
			  // return "redirect:/editDoctor.do";
			  }
			  else{
				  return "redirect:/ doctor.do";
			  }
			  return "redirect:/editDoctor.do";
		}
		catch(Exception e)
		 {
				StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::save Provider", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
	  }
	 
	 
	 @SuppressWarnings("unused")
	@RequestMapping(value = "/saveAllProviderInfo", method = { RequestMethod.GET, RequestMethod.POST })
	  public String saveProviderInfo(@ModelAttribute("ProviderInfo") ProviderInfo providerInfo,
		      Map<String, Object> map, Model model, HttpServletRequest request,@RequestParam("image_file") MultipartFile[] image_file) 
	 {
		try {
			 map.put("ProviderInfo", new ProviderInfo());
				
			  boolean list =doctorService.saveFinalProvider(providerInfo);
			  ArrayList<FileMeta> fileList = new ArrayList<FileMeta>();
			    if (image_file != null && image_file.length > 0) {
			    	fileList = ReferadrUtils.getFiles(image_file,fileList,IConstant.FILE_PROVIDER_INFO);
			    }
			    
			    CommonUtils.uploadAttachments(fileList,providerInfo.getProviderId(), true);  
			  
			 // model.addAttribute("list",list.get(0));
		    return "redirect:/doctor.do";//here i changed
		}
		catch(Exception e)
		 {
				StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Save Provider", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
	  }
	 
}
