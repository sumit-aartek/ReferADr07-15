package com.referadr.practice.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.service.ReferPatientPhoneService;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

@Controller
public class ReferPatientPhoneController {
	@Autowired
	private ReferPatientPhoneService referPatientPhoneService;
	
	/**
	 *This method is used for open ReferpatientByPhone.jsp  
	 * 
	 */
	@RequestMapping("/ReferpatientByPhone")
	public String ReferpatientByPhone(Map<String, Object> map,ModelMap model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("practiceId", 9);
		session.setAttribute("providerId", 49);
		session.setAttribute("chId", 5);
		try {
			map.put("PatientReferralInfo", new PatientReferralInfo());
			
			return "ReferpatientByPhoneForm";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::Practice Inbox", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}


	
	/**
	 *This method is used for Save info at draft 
	 * 
	 */
	@RequestMapping("/saveReferAPatientDraft")
	public String saveReferAPatientDraft(@ModelAttribute("PatientReferralInfo") PatientReferralInfo patientReferralInfo, BindingResult result,Map<String, Object> map,ModelMap model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.setAttribute("practiceId", 9);
		session.setAttribute("providerId", 49);
		session.setAttribute("chId", 5);
		try {
			map.put("PatientReferralInfo", new PatientReferralInfo());
			System.out.print("into saveReferAPatientDraft action ");
			int status=referPatientPhoneService.saveReferAPatientDraft(patientReferralInfo);
			
			
			
			return "ReferpatientByPhoneForm";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::Practice Inbox", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}
	
	
	
	/**
	 *This method is used for refer detail from draft to refer a patient
	 * 
	 */
	@RequestMapping("/sendPatientToReferPatient")
	public String sendPatientToReferPatient(@ModelAttribute("PatientReferralInfo") PatientReferralInfo patientReferralInfo, BindingResult result,Map<String, Object> map,ModelMap model,HttpServletRequest request,@RequestParam Integer draftId) {
		HttpSession session=request.getSession();
	/*	session.setAttribute("practiceId", 9);
		session.setAttribute("providerId", 49);
		session.setAttribute("chId", 5);*/
		try {
			map.put("PatientReferralInfo", new PatientReferralInfo());
			PatientReferralInfo referralInfo=new PatientReferralInfo();
			//System.out.print("into saveReferAPatientDraft action ");
			referralInfo=referPatientPhoneService.fetchDraftInfo(draftId);
			session.setAttribute("referralInfo", referralInfo);
			
			
			return "redirect:/referPatient.do?sendBy=draft";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::Practice Inbox", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}
	
	
	
}
