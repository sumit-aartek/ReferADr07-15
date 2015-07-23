package com.referadr.practice.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.referadr.Exception.ReferadrException;
import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.service.CHAdminService;
import com.referadr.login.model.Login;
import com.referadr.login.service.LoginService;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RadCodes;
import com.referadr.practice.model.RedState;
import com.referadr.practice.service.PracticeService;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

@Controller
public class PracticeController {

	@Autowired
	private PracticeService practiceService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CHAdminService chAdminService;
	
	@RequestMapping(value = "/providerRegistration", method = RequestMethod.GET) 
	public String addProvider(@RequestParam("practiceId") int practiceId,@RequestParam("staffId") int staffId,Model model)
	{
		try {
			ProviderInfo providerInfo=new ProviderInfo();
			PracticeInfo practiceInfo=new PracticeInfo();
			RadCodes radCodes=new RadCodes();
			radCodes.setCodeId(staffId);
			providerInfo.setRadCodes(radCodes);
			practiceInfo.setPracticeId(practiceId);
			providerInfo.setPracticeInfo(practiceInfo);
		model.addAttribute("provider",providerInfo);
		return "PracticeAdminRegister";
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
		    e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Provider Registratio", errors.toString());
			model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}
	
	@RequestMapping(value = "/saveProvider", method = RequestMethod.POST) 
	public String saveProvider(@ModelAttribute("provider")ProviderInfo providerInfo){
		try {
			Login login=new Login(providerInfo.getProviderEmail(),ReferadrUtils.sha1(providerInfo.getProviderPwd()),2);
			int loginRow=loginService.insertLoginCredentials(login);
			System.out.println("loginRow-->"+loginRow);
			if(loginRow>0)
			{
			//RadCodes radCodes=new RadCodes();
			//radCodes.setCodeId(477);
			//providerInfo.setRadCodes(radCodes);
			providerInfo.setProviderPwd(ReferadrUtils.sha1(providerInfo.getProviderPwd()));
			int row=practiceService.insertProviderInfo(providerInfo);
			System.out.println("row-->"+row);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL,IConstant.TO_MAIL, "Failed::Provider Registration", errors.toString());
			throw new ReferadrException("Failed saving provider information");
		}
		return "PracticeAdminRegister";
	}
	@RequestMapping(value = "/PracticeInfoFromCh", method = RequestMethod.GET)
	public  String practiceInfo(HttpServletRequest request,Model model,Integer practiceId)
	{
		/*HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		System.out.println("userName-->"+login.getUserName());
		ProviderInfo providePracticeId=loginService.fetchProviderPracticeId(login.getUserName());
		System.out.println("providePracticeId-->"+providePracticeId);*/
		HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		try {
			if(login!=null){
			PracticeInfo practiceInfo=practiceService.fetchPracticeInfo(practiceId);
			List<RedState> states=chAdminService.fetchStates();
			model.addAttribute("states", states);
			model.addAttribute("practiceInfo", practiceInfo);
			}else{
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			/*e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail("ramanviswanadha@gmail.com", "referadr@gmail.com", "Failed::Fetching Practice information", errors.toString());
			throw new ReferadrException("Failed fetching Practice Information");*/
			 StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Refer a Patient", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
		}
		return "PracticeInfo";
	}

	@RequestMapping(value = "/PracticeInfo", method = RequestMethod.GET)
	public  String practiceInfo(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		if(login!=null){
		ProviderInfo providePracticeId=loginService.fetchProviderPracticeId(login.getUserName());
		try {
			PracticeInfo practiceInfo=practiceService.fetchPracticeInfo(providePracticeId.getPracticeInfo().getPracticeId());
			List<RedState> states=chAdminService.fetchStates();
			model.addAttribute("states", states);
			model.addAttribute("practiceInfo", practiceInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			/*e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail("ramanviswanadha@gmail.com", "referadr@gmail.com", "Failed::Fetching Practice information", errors.toString());
			throw new ReferadrException("Failed fetching Practice Information");*/
			 StringWriter errors = new StringWriter();
			    e.printStackTrace(new PrintWriter(errors));
				ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL, "Failed::Refer a Patient", errors.toString());
				model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
		}
		}else{
			model.addAttribute("errorMsg", "You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
		/*return "PracticeInfo";*/
		/*return "PracticeInfoNew";*/
		return "PracticeInfo7_15";
	}
	@RequestMapping(value = "/updatePracticeInfo", method = RequestMethod.POST) 
	public String updatePracticeInfo(@ModelAttribute("practiceInfo")PracticeInfo practiceInfo,HttpServletRequest request,Model model,@RequestParam("fileToUpload") MultipartFile[] fileToUpload)
	{
		HttpSession session=request.getSession();
		Login login=(Login)session.getAttribute("login");
		try {
			int row=practiceService.updatePracticeInfo(practiceInfo, login.getUserName());
			List<RedState> states=chAdminService.fetchStates();
			model.addAttribute("states", states);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL,IConstant.TO_MAIL, "Failed::Updating Practice information", errors.toString());
			throw new ReferadrException("Failed updating practice information");
		}
		/*return "PracticeInfo";*/
		/*return "PracticeInfoNew";*/
		return "PracticeInfo7_15";
		
	}	
}
