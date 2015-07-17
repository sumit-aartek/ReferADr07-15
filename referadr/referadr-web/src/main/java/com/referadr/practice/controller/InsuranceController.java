package com.referadr.practice.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.referadr.login.model.Login;
import com.referadr.practice.model.InsuranceVO;
import com.referadr.practice.model.ReportVO;
import com.referadr.practice.service.InsuranceService;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

@Controller
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;

	// redirect to practiceInsurance.jsp
	@RequestMapping(value = "/practiceInsurance", method = RequestMethod.GET)
	public String practiceInsurance(Map<String, Object> map, Model model,
			HttpServletRequest request) {

		List<InsuranceVO> allInsuranceList = null;
		List<Integer> inslist=new ArrayList<Integer>();
		List<Integer> selectedinslist=new ArrayList<Integer>();
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			try{
			Integer providePracticeId = 0;
			if (session.getAttribute("providePracticeId") != null)
			providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
		allInsuranceList = insuranceService.getAllInsuranceList();
		selectedinslist=insuranceService.getSelectedInsuranceList(providePracticeId);
		for(InsuranceVO insuranceVO:allInsuranceList){
			inslist.add(insuranceVO.getInsuranceId());
		}
		if (allInsuranceList != null) {
			model.addAttribute("allInsuranceList", allInsuranceList);
			model.addAttribute("inslist",inslist);
			model.addAttribute("selectedinslist",selectedinslist);
		}
		
		}catch (Exception e)
		   {
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
		return "practiceInsurance";

	}

	// to insert patientinsurance Detail
	@RequestMapping(value = "/insertPacticeIns", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> insertPacticeIns(HttpServletRequest request,@RequestParam Integer insId) {
		List<Integer> insuranceList = new ArrayList<Integer>();
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			Integer providePracticeId = 0;
			if (session.getAttribute("providePracticeId") != null)
			providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
			insuranceList = insuranceService.insertPatientIns(providePracticeId,insId);
			return insuranceList;
		} else {
			return insuranceList;
		}
	}
	
	
	// to delete patientinsurance Detail
	@RequestMapping(value = "/deletePacticeIns", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> deletePacticeIns(HttpServletRequest request,@RequestParam Integer insId) {
		List<Integer> insuranceList = new ArrayList<Integer>();
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			Integer providePracticeId = 0;
			if (session.getAttribute("providePracticeId") != null)
			providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
			insuranceList = insuranceService.deletePatientIns(providePracticeId,insId);
			return insuranceList;
		} else {
			return insuranceList;
		}
	}
	

}
