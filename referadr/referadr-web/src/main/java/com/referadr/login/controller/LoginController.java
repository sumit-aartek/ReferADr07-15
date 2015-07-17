package com.referadr.login.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.referadr.Exception.ReferadrException;
import com.referadr.ch.model.CHAdmin;
import com.referadr.ch.service.CHAdminService;
import com.referadr.ch.service.chInboxService;
import com.referadr.login.model.Login;
import com.referadr.login.service.LoginService;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RedState;
import com.referadr.practice.model.RefferalCurrentStatus;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private CHAdminService chAdminService;
	@Autowired
	private chInboxService clearingInboxService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request,
			@RequestParam(required = false) String errorMsg) {

		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("login", new Login());

		HttpSession session = request.getSession();
		// session.setAttribute("login", loggedIn);
		Login loggedIn = (Login) session.getAttribute("login");
		if (loggedIn != null) {
			if (loggedIn.getRoleId() == 1) {
				return "redirect:/clearingHouse.do";

			} else {
				return "redirect:/practice.do";

			}
		} else {

			//return "Login";
			return "LoginNew";
		}

		// return "Login";
	}

	@RequestMapping(value = "/exlogin", method = RequestMethod.GET)
	public String exlogin(Model model) {

		model.addAttribute("login", new Login());
		return "exLogin";
	}

	@RequestMapping(value = "/validateCredentials", method = RequestMethod.POST)
	public String validateUser(@ModelAttribute("login") Login login,
			BindingResult result, HttpServletRequest request, Model model) {
		//String ipAddress = request.getRemoteHost();
		String ipAddress = request.getRemoteAddr();
		HttpSession session = request.getSession();
		String homePage = "loginFailure";
		String password = login.getPassword();
		login.setPassword(ReferadrUtils.sha1(password));
		try {
			Login loggedIn = loginService.validateCredentials(login);
			
			int roleId=0;
			if(loggedIn!=null){
				roleId=loggedIn.getRoleId();
			}
			int loginAttemptsStatus=0;
			if(roleId != 3){
			 loginAttemptsStatus=loginService.saveLoginTrack(roleId,login,ipAddress);
			}
			session.setAttribute("login", loggedIn);
			if(loginAttemptsStatus==1){
				model.addAttribute("errorMsg", "You have exceeded login attempts. You are Blocked for next 30 minuts");
				return "redirect:/logout.do";
			}
			/*else if(loginAttemptsStatus==0){
				model.addAttribute("errorMsg", "Invalid User Name OR Password");
				return "redirect:/logout.do";
			}*/
			else if (loggedIn != null) {
				if(loggedIn.getRoleId() == 3){
					homePage = "labInbox";
				}
			else if (loggedIn.getRoleId() == 1) {
					homePage = "clearingHouse";
				} else {
					homePage = "practice";
				}
			} else {
				model.addAttribute("errorMsg", "Invalid User Name OR Password");
				return "redirect:/logout.do";
			}
			return "redirect:/" + homePage + ".do";
		} catch (Exception e) {
			/*
			 * e.printStackTrace(); StringWriter errors = new StringWriter();
			 * e.printStackTrace(new PrintWriter(errors));
			 * ReferadrUtils.sendMail("ramanviswanadha@gmail.com",
			 * "referadr@gmail.com", "Failed::Login", errors.toString());
			 * //throw new ReferadrException(
			 * "Failed due to wrong credentials.Please <a href=\"login.do\">Login</a> again."
			 * ); //return "redirect:/"+"login.do"; return
			 * "redirect:/logout.do";
			 */
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::Login controller", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}

	}

	@RequestMapping(value = "/ChHome", method = RequestMethod.GET)
	public String chHome(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		CHAdmin chAdmin = null;
		try {
			if (login != null) {
				chAdmin = chAdminService.fetchCHAdmin(login.getUserName());
				session.setAttribute("clearingHouseId", chAdmin.getChId());
				CHInfo ch = populateCHInfo(chAdmin.getChId());
				List<RedState> states = chAdminService.fetchStates();
				Map<Integer, String> statesMap = new HashMap<Integer, String>();
				for (RedState state : states) {
					statesMap.put(state.getStateId(), state.getStateCode());
				}
				session.setAttribute("states", states);
				model.addAttribute("chInfo", ch);
				model.addAttribute("states", states);
			} else {
				model.addAttribute(
						"errorMsg",
						"You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
		} catch (Exception e) {
			/*
			 * System.out.println("Exception in chHome method-->"+e.getMessage())
			 * ; throw new ReferadrException(
			 * "Error occured dislaying Clearing House page.Please contact administrator"
			 * );
			 */
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Login controller", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}

		return "ChHome";
	}

	@RequestMapping(value = "/clearingHouse", method = RequestMethod.GET)
	public String clearingInboxShow(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		CHAdmin chAdmin = null;
		try {
			if (login != null) {
				chAdmin = chAdminService.fetchCHAdmin(login.getUserName());
				session.setAttribute("clearingHouseId", chAdmin.getChId());
				session.setAttribute("chAdminId", chAdmin.getChAdminId());
			} else {
				model.addAttribute(
						"errorMsg",
						"You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
		} catch (Exception e) {
			/*
			 * System.out.println("Exception in chHome method-->"+e.getMessage())
			 * ; throw new ReferadrException(
			 * "Error occured dislaying Clearing House page.Please contact administrator"
			 * );
			 */
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::Login controller", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
		// int clearHouseId=(Integer) session.getAttribute("clearingHouseId");
		List<PateintInfoVO> referralInfoList = null;
		referralInfoList = clearingInboxService
				.getInboxValue(chAdmin.getChId());
		session.setAttribute("referralInfoList", referralInfoList);
		session.setAttribute("param", "chInbox");
		model.addAttribute("referralInfoList", referralInfoList);
		return "clearingHouseInbox";
		
	}

	/*
	 * @RequestMapping(value = "/PracticeHome", method = RequestMethod.GET)
	 * public ModelAndView practiceHome(HttpServletRequest request) {
	 * HttpSession session=request.getSession(); Login
	 * login=(Login)session.getAttribute("login"); ProviderInfo
	 * provideInfo=loginService.fetchProviderPracticeId(login.getUserName());
	 * int providePracticeId=provideInfo.getPracticeInfo().getPracticeId();
	 * PracticeInfo
	 * practiceName=loginService.getPracticeName(providePracticeId); //String
	 * practiceName=practiceName. return new ModelAndView("firstScreen"); }
	 */

	@RequestMapping(value = "/loginFailure", method = RequestMethod.GET)
	public ModelAndView loginFailure(
			@ModelAttribute("CHInfocommand") CHInfo chInfo, BindingResult result) {
		return new ModelAndView("Login");
	}

	public CHInfo populateCHInfo(int chId) throws Exception {
		return loginService.fetchCHInfo(chId);
	}

	@RequestMapping(value = "/supportAction", method = RequestMethod.GET)
	@ResponseBody
	public String supportMail(@RequestParam String subjecName,
			String descriptions, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		String fromEmailId = login.getUserName();
		return loginService.supportMail(fromEmailId, subjecName, descriptions);
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotpassword(Model model, Map<String, Object> map) {
		map.put("login", new Login());
		return "forgotpasswordNew";
	}

	@RequestMapping(value = "/forgotpasswordSendMail", method = RequestMethod.POST)
	public String forgotpasswordSendMail(@ModelAttribute("login") Login login,
			BindingResult result, Model model, Map<String, Object> map) throws Exception {

		int status=loginService.getloginID(login.getUserName());
		model.addAttribute("successmsg", status);
		//System.out.print("into forget pass");
		return "forgotpasswordNew";
	}

	@RequestMapping(value = "/recoverPassword", method = RequestMethod.GET)
	public String recoverPassword(@ModelAttribute("login") Login login,
			BindingResult result, Model model, Map<String, Object> map,
			@RequestParam(required = false) String loginId) throws Exception {
		String decrId=ReferadrUtils.decrypt(loginId);
	//System.out.println("decrId="+decrId);
		map.put("login", new Login());
		model.addAttribute("loginId", decrId);
		return "recoverPasswordNew";
	}
	
	
	@RequestMapping(value = "/UpdatePassword", method = RequestMethod.POST)
	public String UpdatePassword(@ModelAttribute("login") Login login,
			BindingResult result, Model model, Map<String, Object> map) {

	loginService.updatePassword(login.getId(),login.getPassword());

		//System.out.print("into forget pass");
		return "redirect:/logout.do";
	}
	

}
