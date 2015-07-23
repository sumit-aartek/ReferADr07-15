package com.referadr.practice.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.referadr.login.model.Login;
import com.referadr.login.service.LoginService;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.DoctorTimingVO;
import com.referadr.practice.model.Document;
import com.referadr.practice.model.FileMeta;
import com.referadr.practice.model.PateintAndInsuranceInfoVO;
import com.referadr.practice.model.PateintInfoVO;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.PracticeInfo;
import com.referadr.practice.model.PrintVO;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.RedState;
import com.referadr.practice.model.ReferralProviderActionVO;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.model.RefferalCurrentStatus;
import com.referadr.practice.model.ScheduleVO;
import com.referadr.practice.service.InboxService;
import com.referadr.practice.service.ReferPatientService;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

/**
 * This class is created for display All The functionality of Practice inbox
 * 
 * @author Aartek Software Solutions
 * @version 1.0
 * @since 2015-01-05
 */

@Controller
public class InboxController {
	@Autowired
	private InboxService inboxService;
	@Autowired
	private ReferPatientService referPatientService;

	@Autowired
	private LoginService loginService;

	LinkedList<FileMeta> patientNameFiles = new LinkedList<FileMeta>();
	FileMeta patientNameFilesMeta = null;

	LinkedList<FileMeta> updateNameFiles = new LinkedList<FileMeta>();
	FileMeta updateNameFilesMeta = null;

	/**
	 * This method is used for Display or show the Function of practice Inbox
	 * 
	 */
	@RequestMapping("/practice")
	public String showPracticePage(Map<String, Object> map, Model model,HttpServletRequest request) 
	{
		try
		{
			HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
			if (login != null) 
			{
				ProviderInfo providerInfo = loginService.fetchProviderPracticeId(login.getUserName());
				PracticeInfo practiceName = loginService.getPracticeName(providerInfo.getPracticeInfo().getPracticeId());
				session.setAttribute("practiceName",practiceName.getPracticeName());
				session.setAttribute("providePracticeId", providerInfo.getPracticeInfo().getPracticeId());
				session.setAttribute("providerInfo", providerInfo);
				Integer loggedInProvideId = providerInfo.getProviderId();
				session.setAttribute("loggedInProvideId", loggedInProvideId);
				session.setAttribute("param", "practiceInbox");
				List<PateintInfoVO> referralInfoList = null;
				referralInfoList = inboxService.getInboxValue(providerInfo.getPracticeInfo().getPracticeId());
				session.setAttribute("referralInfoList", referralInfoList);
				List<ProviderInfo> providerInfoList = null;
				providerInfoList = referPatientService.getProviderInfo(providerInfo.getPracticeInfo().getPracticeId());
				if (providerInfoList != null)
				{
					model.addAttribute("ProviderInfoForSchedule",providerInfoList);
					providerInfoList = null;
				}
				model.addAttribute("referralInfoList", referralInfoList);
				referralInfoList = null;
				/*return "practiceNew";*/
				return "practice7_15";
			}
			else
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
		} 
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	
	/**
	 * This method is used for Display or show the Function of practice SendBox
	 * 
	 */
	@RequestMapping("/sendBox")
	public String sendBox(Map<String, Object> map, Model model,HttpServletRequest request) 
	{
		try
		{
			HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
			if (login != null) 
			{
				int providePracticeId=Integer.parseInt(session.getAttribute("providePracticeId").toString());
				List<PateintInfoVO> referralInfoList = null;
				referralInfoList = inboxService.getSendboxValue(providePracticeId);
				model.addAttribute("referralInfoList", referralInfoList);
				referralInfoList = null;
				/*return "sendBox";*/
				return "sendBox7_15";
			}
			else
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
		} 
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}
	
	
	
	
	/**
	 * This method is used for Display or show the Function of practice Drafts
	 * 
	 */
	@RequestMapping("/draft")
	public String draft(Map<String, Object> map, Model model,HttpServletRequest request) 
	{
		try 
		{
			HttpSession session = request.getSession();
			Login login = (Login) session.getAttribute("login");
			if (login != null) 
			{
				ProviderInfo providerInfo = loginService.fetchProviderPracticeId(login.getUserName());
				PracticeInfo practiceName = loginService.getPracticeName(providerInfo.getPracticeInfo().getPracticeId());
				session.setAttribute("practiceName",practiceName.getPracticeName());
				session.setAttribute("providePracticeId", providerInfo.getPracticeInfo().getPracticeId());
				session.setAttribute("providerInfo", providerInfo);
				Integer loggedInProvideId = providerInfo.getProviderId();
				session.setAttribute("loggedInProvideId", loggedInProvideId);
				session.setAttribute("param", "practiceDraft");
				List<PateintInfoVO> referralInfoList = null;
				referralInfoList = inboxService.getDraftValue(providerInfo.getPracticeInfo().getPracticeId());
				session.setAttribute("referralInfoList", referralInfoList);
				model.addAttribute("referralInfoListModel", referralInfoList);
				referralInfoList = null;
				/*return "draftNew";*/
				return "draft7_15";
			} 
			else 
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
		}
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for Display or show the history of patient
	 * 
	 */
	@RequestMapping("/patientNameView")
	public String showPatientNamePage(Map<String, Object> map, ModelMap model,HttpServletRequest request, @RequestParam Integer patientRefInfo) 
	{
		map.put("RefferalCurrentStatus", new RefferalCurrentStatus());
		HttpSession session1 = request.getSession();
		Login loginMember = (Login) session1.getAttribute("login");
		try 
		{
			if (loginMember != null) 
			{
				String fromEmail = loginMember.getUserName();
				List<RedState> stateList = referPatientService.getAllStateName();
				if (stateList != null) 
				{
					model.addAttribute("stateList", stateList);
				}
				PateintAndInsuranceInfoVO pateintAndInsuranceInfoVO = inboxService.getPatientInformation(patientRefInfo);
				model.put("PateintAndInsuranceInfoVO",pateintAndInsuranceInfoVO);
				List<ScheduleVO> scheduleList = null;// inboxService.getscheduleList(patientRefInfo);
				model.addAttribute("scheduleList", scheduleList);
				List<ReferralProviderActionVO> infos = inboxService.getReferPatientList(patientRefInfo);
				for (ReferralProviderActionVO info : infos) 
				{
					ArrayList<Document> docList = CommonUtils.getDocumentListByRefProvActionId(info.getRefProActionId(), info.isFlag());
					info.setDocList(docList);
				}
				ArrayList<Document> insdocList = CommonUtils.getDocumentListByRefProvActionId(pateintAndInsuranceInfoVO.getPatientInsuranceInfoId());
				if (insdocList != null) 
				{
					model.addAttribute("insdocList", insdocList);
					insdocList = null;
				}
				if (infos != null) 
				{
					model.addAttribute("infos", infos);
					infos = null;
				}
				pateintAndInsuranceInfoVO = null;
			}
			else 
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			/* return "patientName"; */
			return "patientNameNew";
		} 
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for Display or show refrral Reasons *
	 */
	@RequestMapping("/refralReasonsView")
	public String showRefralReasonsPage(Map<String, Object> map,ModelMap model, HttpServletRequest request,@RequestParam Integer referCurrentStatusId,Integer patientReferralId) 
	{
		patientNameFiles.removeAll(patientNameFiles);
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try
		{
			if (login != null) 
			{
				int patientReferralInfo = patientReferralId;
				PatientReferralInfo providerIdList = null;
				providerIdList = inboxService.getProviderId(patientReferralInfo);
				model.addAttribute("providerId", providerIdList.getProId());
				PatientReferralInfo fromProviderIdList = null;
				fromProviderIdList = inboxService.getFromProviderId(patientReferralId);
				model.addAttribute("fromProviderId",fromProviderIdList.getProId());
				map.put("PatientReferralInfo", new PatientReferralInfo());
				model.addAttribute("referCurrentStatusId", referCurrentStatusId);
				model.addAttribute("patientReferralId", patientReferralId);
				int providePracticeId = 0;
				if (session.getAttribute("providePracticeId") != null)
				{
					providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
				}
				List<ProviderInfo> providerInfoList = null;
				providerInfoList = referPatientService.getProviderInfo(providePracticeId);
				if (providerInfoList != null) 
				{
					model.addAttribute("ProviderInfo", providerInfoList);
					providerInfoList = null;
				}
				List<CHInfo> chlearingHouseList = null;
				chlearingHouseList = referPatientService.getclearingHouseName(providePracticeId);
				if (chlearingHouseList != null) 
				{
					model.addAttribute("chlearingHouseList", chlearingHouseList);
					if (chlearingHouseList.size() == 1)
					{
						model.addAttribute("clearHouselistSize",chlearingHouseList.size());
						model.addAttribute("chid", chlearingHouseList.get(0).getId());
					}
				}
				providerIdList = null;
				fromProviderIdList = null;
				chlearingHouseList = null;
			} 
			else 
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			/* return "refralReasons"; */
			return "refralReasonsNew";
		} 
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for refere a patient from practice inbox
	 * 
	 */
	@RequestMapping(value = "/updateRefferal", method = RequestMethod.POST)
	public String businessUpdateInfo(@ModelAttribute("PatientReferralInfo") PatientReferralInfo patientReferralInfo,BindingResult result, ModelMap model, Map<String, Object> map,HttpServletRequest request,@RequestParam("files") MultipartFile[] files, int fromProviderId)
	{
		try 
		{
			HttpSession session1 = request.getSession();
			Login loginMember = (Login) session1.getAttribute("login");
			String fromEmail = loginMember.getUserName();
			int refId = patientReferralInfo.getRefId();
			int patId = CommonUtils.getPatientIdByReferralId(refId);
			Integer providePracticeId = null;
			HttpSession session = request.getSession();
			if (session.getAttribute("providePracticeId") != null) 
			{
				providePracticeId = (Integer) session.getAttribute("providePracticeId");
			}
			List<ProviderInfo> providerInfoList = null;
			providerInfoList = referPatientService.getProviderInfo(providePracticeId);
			if (providerInfoList != null)
			{
				model.addAttribute("ProviderInfo", providerInfoList);
			}
			Integer loggedInProvideId = 0;
			loggedInProvideId = ((Integer) session.getAttribute("loggedInProvideId"));
			boolean status = inboxService.updateRefferInformation(patientReferralInfo, providePracticeId, fromEmail,fromProviderId, loggedInProvideId);
			ArrayList<FileMeta> fileList = new ArrayList<FileMeta>();
			try
			{
				fileList = ReferadrUtils.getFiles(files, fileList,IConstant.FILE_REF_PROV_ACTION);
				int refProcActnId = CommonUtils.getLatestRefProvActionIdByRefId(refId);
				CommonUtils.uploadAttachments(fileList, patId, refId,refProcActnId, true);
			} 
			catch (Exception ex)
			{
				System.out.println("Exception in FIle Upload Process: "+ ex.toString());
			}
			return "redirect:/practice.do";
		}
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for open update.jsp of refered patient from practice
	 * inbox
	 * 
	 */
	@RequestMapping("/updateRefralView")
	public String showUpdatePage(@ModelAttribute("Referral_Provider_Action") Referral_Provider_Action referral_Provider_Action,BindingResult result, ModelMap model, HttpServletRequest request,@RequestParam(required = false) Integer patientReferralId,Integer providerId) 
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try
		{
			if (login != null)
			{
				updateNameFiles.removeAll(updateNameFiles);
				model.addAttribute("patientReferralId", patientReferralId);
				model.addAttribute("providerActionId", providerId);
				model.put("Referral_Provider_Action",new Referral_Provider_Action());
			}
			else 
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			/* return "update"; */
			return "updateNew";
		}
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for open print.jsp to print info of refered patient
	 * from practice inbox
	 * 
	 */
	@RequestMapping("/print")
	public String print(@ModelAttribute("Referral_Provider_Action") Referral_Provider_Action referral_Provider_Action,BindingResult result, ModelMap model, HttpServletRequest request,@RequestParam(required = false) Integer patientReferralId,Integer providerId)
    {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try {
			if (login != null)
			{
				PrintVO printVO = inboxService.getPrintInfo(patientReferralId);
				model.put("printVO", printVO);
			} else {
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			return "print";
		}
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for save Shedule of refered patient from practice
	 * inbox
	 * 
	 * @throws ParseException
	 * 
	 */
	@RequestMapping(value = "/sheduleAction", method = RequestMethod.GET)
	@ResponseBody
	public String sheduleAction(@RequestParam String dateTime, Integer refId,Integer provId, String notes, Integer withProvId,HttpServletRequest request) throws ParseException 
    {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login != null) 
		{
			Integer providePracticeId = 0;
			if (session.getAttribute("providePracticeId") != null)
				providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
			String fromEmail = login.getUserName();
			int status = inboxService.saveShedule(dateTime, refId, provId,withProvId, notes, fromEmail);
			return "Success";
		} 
		else
		{
			return "error";
		}
	}

	/**
	 * This method is used for fetch practiceId a patient refered to
	 * 
	 */
	@RequestMapping(value = "/findWithProvider", method = RequestMethod.GET)
	@ResponseBody
	public int findWithProvider(@RequestParam Integer refId,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login != null)
		{
			Integer providePracticeId = 0;
			if (session.getAttribute("providePracticeId") != null)
				providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
			int providerID = inboxService.findWithProvider(refId);
			return providerID;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used for fetch scheduled date
	 * 
	 */
	@RequestMapping(value = "/findProviderPatientSchedule", method = RequestMethod.GET)
	@ResponseBody
	public DoctorTimingVO findProviderPatientSchedule(@RequestParam Integer refId, Integer withProvId,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		DoctorTimingVO doctorTimingVO = new DoctorTimingVO();
		if (login != null)
		{
			doctorTimingVO = inboxService.findProviderPatientSchedule(refId,withProvId);
			return doctorTimingVO;
		}
		else 
		{
			return doctorTimingVO;
		}
	}

	/**
	 * This method is used for open shedule.jsp of refered patient from practice
	 * inbox
	 * 
	 */
	@RequestMapping("/shedule")
	public String shedule(@ModelAttribute("Referral_Provider_Action") Referral_Provider_Action referral_Provider_Action,BindingResult result, ModelMap model, HttpServletRequest request,@RequestParam(required = false) Integer patientReferralId,Integer providerId) 
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try
		{
			if (login != null) 
			{
				updateNameFiles.removeAll(updateNameFiles);
				model.addAttribute("patientReferralId", patientReferralId);
				model.addAttribute("providerActionId", providerId);
				model.put("Referral_Provider_Action",new Referral_Provider_Action());
			}
			else 
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			return "shedule";
		} 
		catch (Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for Additional Notes of refered patient from practice
	 * inbox
	 * 
	 */
	@RequestMapping(value = "/updateAdditionalNotes", method = RequestMethod.POST)
	public String updateNote(@ModelAttribute("Referral_Provider_Action") Referral_Provider_Action referral_Provider_Action,BindingResult result, ModelMap model,@RequestParam("updatefiles") MultipartFile[] updatefiles,HttpServletRequest request)
	{
		try {
			HttpSession session = request.getSession();
			ProviderInfo providerInfo = (ProviderInfo) session.getAttribute("providerInfo");
			int practiceId = ((Integer) session.getAttribute("providePracticeId"));
			Integer loggedInProvideId = 0;
			loggedInProvideId = ((Integer) session.getAttribute("loggedInProvideId"));
			inboxService.updateMessage(referral_Provider_Action, providerInfo,practiceId, loggedInProvideId);
			ArrayList<FileMeta> fileList = new ArrayList<FileMeta>();
			try
			{
				fileList = ReferadrUtils.getFiles(updatefiles, fileList,IConstant.FILE_REF_PROV_ACTION);
				int refId = referral_Provider_Action.getPatientReferralInfo().getRefId();
				int patId = CommonUtils.getPatientIdByReferralId(refId);
				int refProvActionId = CommonUtils.getLatestRefProvActionIdByRefId(refId);
				CommonUtils.uploadAttachments(fileList, patId, refId,refProvActionId, true);
			} 
			catch (Exception ex) 
			{
				System.out.println("Exception in FIle Upload Process: "+ ex.getStackTrace());
			}
			if (session.getAttribute("clearingHouseId") != null)
			{
				return "redirect:/clearingHouse.do";
			} 
			else
			{
				return "redirect:/practice.do";
			}
		} 
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for upload patient docs from practice inbox
	 * 
	 */
	@RequestMapping(value = "/uploadPatientDoc", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<FileMeta> patientUpload(MultipartHttpServletRequest request,HttpServletResponse response)
	{
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		while (itr.hasNext()) 
		{
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! "+ patientNameFiles.size());
			if (patientNameFiles.size() >= 10)
				patientNameFiles.pop();
			patientNameFilesMeta = new FileMeta();
			patientNameFilesMeta.setFileName(mpf.getOriginalFilename());
			patientNameFilesMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			patientNameFilesMeta.setFileType(mpf.getContentType());
			try 
			{
				patientNameFilesMeta.setBytes(mpf.getBytes());

			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			patientNameFiles.add(patientNameFilesMeta);
		}
		return patientNameFiles;
	}

	/**
	 * This method is used for update patient docs from practice inbox
	 * 
	 */
	@RequestMapping(value = "/updateDoc", method = RequestMethod.POST)
	public @ResponseBody
	LinkedList<FileMeta> updateUpload(MultipartHttpServletRequest request,HttpServletResponse response) {
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		// 2. get each file
		while (itr.hasNext())
		{
			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded! "+ updateNameFiles.size());
			// 2.2 if files > 10 remove the first from the list
			if (updateNameFiles.size() >= 10)
				updateNameFiles.pop();
			// 2.3 create new fileMeta
			updateNameFilesMeta = new FileMeta();
			updateNameFilesMeta.setFileName(mpf.getOriginalFilename());
			updateNameFilesMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			updateNameFilesMeta.setFileType(mpf.getContentType());
			try 
			{
				updateNameFilesMeta.setBytes(mpf.getBytes());

			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 2.4 add to files
			updateNameFiles.add(updateNameFilesMeta);
		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return updateNameFiles;
	}
	final int BUFFER_SIZE = 10000000;

	/**
	 * Download the file based on FileName and Path
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,HttpServletResponse response,@RequestParam("filePath") String filePath,@RequestParam("fileName") String fileName) throws IOException 
	{
/*		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJTMFOQQTO6NVQVUA","nZip7jHdQgBWQIajn+Bwi6zvDMGR9nkPik+xcbK0");*/
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(IConstant.ACCESS_KEY_ID,IConstant.SECRET_ACCESS_KEY);
		AmazonS3 s3client = new AmazonS3Client(awsCreds);
		S3Object object = s3client.getObject(new GetObjectRequest("referadr",filePath));
		File downloadFile = new File(fileName);
		S3ObjectInputStream inputStream = object.getObjectContent();
		// get MIME type of the file
		String mimeType = "application/octet-stream";
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		// get output stream of the response
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) 
		{
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}

	/**
	 * This method is used for logout functionality from practice inbox
	 * 
	 */
	@RequestMapping("/logout")
	public String showLogout(Map<String, Object> map, Model model,HttpServletRequest request,@RequestParam(required = false) String errorMsg)
	{
		HttpSession session = request.getSession();
		model.addAttribute("errorMsg", errorMsg);
		session.invalidate();
		return "redirect:/login.do";
	}

	/**
	 * This method is used for search a patient through practice name or patient
	 * name from practice inbox
	 * 
	 */
	@RequestMapping("/searchPracticePatient")
	public String searchPractice(Map<String, Object> map, ModelMap model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		session.setAttribute("param", "search");
		String identifierVar = null;
		if (login != null) 
		{
			identifierVar = request.getParameter("identifireVar");
			String practiceOrPatientName = request.getParameter("keywords");
			String selectedType = request.getParameter("typeSelect");
			List<PateintInfoVO> referralInfoList = null;
			referralInfoList = (List<PateintInfoVO>) session.getAttribute("referralInfoList");
			referralInfoList = inboxService.searchByPracticeOrPatientName(referralInfoList, practiceOrPatientName, selectedType,identifierVar);
			model.addAttribute("referralInfoList", referralInfoList);
		}
		else 
		{
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
		if (login.getRoleId() == 2)
		{
			/*return "practiceNew";*/
			return "practice7_15";
		}
		else
		{
			return "clearingHouseInbox";
		}
		

	
	}

	// redirect to report.jsp
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(Map<String, Object> map, Model model,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try 
		{
			if (login != null) 
			{
				int providePracticeId = 0;
				if (session.getAttribute("providePracticeId") != null)
					providePracticeId = ((Integer) session.getAttribute("providePracticeId"));
				List<CHInfo> chlearingHouseList = null;
				chlearingHouseList = referPatientService.getclearingHouseName(providePracticeId);
				if (chlearingHouseList != null)
				{
					model.addAttribute("chlearingHouseList", chlearingHouseList);
					if (chlearingHouseList.size() == 1) 
					{
						model.addAttribute("clearHouselistSize",chlearingHouseList.size());
						model.addAttribute("chid", chlearingHouseList.get(0).getId());
					}
				}
			} 
			else
			{
				model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			/* return "report"; */
			return "reportNew";
		}
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Refer a Patient", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for open patientCheckin.jsp
	 * 
	 */
	@RequestMapping("/patientCheckin")
	public String patientCheckin(Map<String, Object> map,HttpServletRequest request, ModelMap model,@RequestParam(required = false) Integer practiceId)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		try
		{
			map.put("PatientReferralInfo", new PatientReferralInfo());
			model.put("practiceId", practiceId);
			return "patientCheckinform";
		}
		catch (Exception e) 
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::Practice Inbox", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	@RequestMapping(value = "/checkPhoneAvaibility", method = RequestMethod.GET)
	@ResponseBody
	public List<PateintAndInsuranceInfoVO> searchPhone(@RequestParam String searchValue) 
	{
		List<PateintAndInsuranceInfoVO> searchList = new ArrayList<PateintAndInsuranceInfoVO>();
		if (!searchValue.isEmpty()) 
		{
			searchList = inboxService.checkPhoneAvaibility(searchValue);
		}
		return searchList;
	}

/*	@RequestMapping(value = "/getUpdatesNotifications", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List> getUpdatesNotifications(@RequestParam String userName, String lastDateTime) 
	{
		Map<String, List> notificationlistmap = new HashMap<String, List>();
		List<String> lastDateTimelist = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		if (!userName.equals(""))
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentDate = "" + dateFormat.format(date);
			if (lastDateTime == "" || lastDateTime == null) 
			{
				lastDateTime = currentDate;
			}
			ProviderInfo providerInfo = loginService.fetchProviderPracticeId(userName);
			String startdatetime = lastDateTime;
			String enddateTime = currentDate;
			list = inboxService.getUpdatesNotifications(providerInfo.getPracticeInfo().getPracticeId(), startdatetime,enddateTime);
			lastDateTimelist.add(currentDate);
		}
		notificationlistmap.put("lastDateTimelist", lastDateTimelist);
		notificationlistmap.put("notificationList", list);
		return notificationlistmap;
	}*/

	/*
	 * to Show Diagnostic *
	 */
	@RequestMapping(value = "/diagnostic", method = { RequestMethod.GET,RequestMethod.POST })
	public String diagnostic(Map<String, Object> map, Model model,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try 
		{
			if (login != null)
			{
			}
			else 
			{
				model.addAttribute(
						"errorMsg",
						"You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			return "diagnosticNew";
		}
		catch (Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,"Failed::save Provider", errors.toString());
			model.addAttribute("errorMsg","You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}
	
	@RequestMapping (value="/labInbox",method={RequestMethod.GET,RequestMethod.POST})
	public String labInbox(@ModelAttribute("PatientReferralInfo")PatientReferralInfo patientReferralInfo,ModelMap model){
	    List<PatientReferralInfo> patientReferralInfoList=inboxService.getLabPatientInfo(patientReferralInfo);
	    model.addAttribute("patientReferralList", patientReferralInfoList);
		return "labInbox";
	}
	
	@RequestMapping(value="/showSelectLabServices",method={RequestMethod.GET,RequestMethod.POST})
	public String showSelectLabServices(@ModelAttribute("PatientReferralInfo")PatientReferralInfo patientReferralInfo,ModelMap model){
	     List<PatientReferralInfo> selectedLabSerivesList=inboxService.getSelectedLabServices(patientReferralInfo);
	     
	     List<PatientReferralInfo> selectedLabSubSerivesList=inboxService.getSelectedLabSubServices(patientReferralInfo);
	      
	     PatientReferralInfo patientReferralInfo2=inboxService.getSelectedLabNotes(patientReferralInfo);
	    model.addAttribute("selectedLabSerivesList", selectedLabSerivesList);
	    
	    model.addAttribute("selectedLabSubSerivesList", selectedLabSubSerivesList);
	    
	    model.addAttribute("patientReferralInfo2", patientReferralInfo2);
	    
		return "showSelectLabServices";
	}
	

}
