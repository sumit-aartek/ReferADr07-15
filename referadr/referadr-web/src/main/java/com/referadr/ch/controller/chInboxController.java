package com.referadr.ch.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.referadr.ch.service.chInboxService;
import com.referadr.login.model.Login;
import com.referadr.login.service.LoginService;
import com.referadr.practice.model.CHInfo;
import com.referadr.practice.model.FileMeta;
import com.referadr.practice.model.PatientReferralInfo;
import com.referadr.practice.model.ProviderInfo;
import com.referadr.practice.model.Referral_Provider_Action;
import com.referadr.practice.util.CommonUtils;
import com.referadr.practice.util.IConstant;
import com.referadr.practice.util.ReferadrUtils;

/**
 * This class is created for display All The functionality of CH inbox
 *
 * @author Aartek Software Solutions
 * @version 1.0
 * @since 2015-01-05
 */
@Controller
public class chInboxController {
	@Autowired
	private chInboxService chInboxService;

	@Autowired
	private LoginService loginService;

	LinkedList<FileMeta> updateNameFiles = new LinkedList<FileMeta>();
	FileMeta updateNameFilesMeta = null;

	/**
	 * This method is used for Display or show the chRefralReasons.jsp
	 * 
	 */
	@RequestMapping("/refralReasonsCh")
	public String showRefralReasonsPage(Map<String, Object> map,
			ModelMap model, HttpServletRequest request,
			@RequestParam Integer referCurrentStatusId,
			Integer patientReferralId) {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		try {
			if (login != null) {
				PatientReferralInfo providerIdList = null;
				providerIdList = chInboxService
						.getProviderId(patientReferralId);
				model.addAttribute("providerId", providerIdList.getProId());
				
				//from provider ID
				PatientReferralInfo fromProviderIdList = null;
				fromProviderIdList = chInboxService
						.getFromProviderId(patientReferralId);
				model.addAttribute("fromProviderId", fromProviderIdList.getProId());
				
				
				PatientReferralInfo patientReferralInfo = chInboxService
						.getChIDAndSpecilityId(patientReferralId);

				map.put("PatientReferralInfo", new PatientReferralInfo());
				map.put("PatientReferralInfo", patientReferralInfo);// this is
																	// for ch id
																	// and
																	// speciality
																	// id
				model.addAttribute("referCurrentStatusId", referCurrentStatusId);
				model.addAttribute("patientReferralId", patientReferralId);

				int providePracticeId = 0;
				int chId = 0;
				if (session.getAttribute("providePracticeId") != null) {
					providePracticeId = ((Integer) session
							.getAttribute("providePracticeId"));

				}
				chId = ((Integer) session.getAttribute("clearingHouseId"));
				// ===
				List<ProviderInfo> providerInfoList = null;

				providerInfoList = chInboxService
						.getProviderInfo(providePracticeId);
				if (providerInfoList != null) {
					model.addAttribute("ProviderInfo", providerInfoList);

				}
				List<CHInfo> chlearingHouseList = null;
				chlearingHouseList = chInboxService.getclearingHouseName(chId);

				if (chlearingHouseList != null) {
					model.addAttribute("chlearingHouseList", chlearingHouseList);
					if (chlearingHouseList.size() == 1) {
						model.addAttribute("clearHouselistSize",
								chlearingHouseList.size());
						model.addAttribute("chid", chlearingHouseList.get(0)
								.getId());
					}
				}
			} else {
				model.addAttribute(
						"errorMsg",
						"You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			return "chRefralReasons";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::chInbox Controller", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for refer a patient from ch Inbox
	 * 
	 */
	@RequestMapping(value = "/chUpdateRefferal", method = RequestMethod.POST)
	public String businessUpdateInfo(
			@ModelAttribute("PatientReferralInfo") PatientReferralInfo patientReferralInfo,
			BindingResult result, ModelMap model, Map<String, Object> map,
			HttpServletRequest request,
			@RequestParam("files") MultipartFile[] files,int fromProviderId) {
		try {
			//System.out.print("fromProviderId================================="+fromProviderId);
			HttpSession session = request.getSession();
			Login loginMember = (Login) session.getAttribute("login");
			String fromEmail = loginMember.getUserName();
			int refId = patientReferralInfo.getRefId();
			int patId = CommonUtils.getPatientIdByReferralId(refId);
			Integer providePracticeId = null;

			int chId = 0;
			int chAdminId;
			chId = ((Integer) session.getAttribute("clearingHouseId"));
			chAdminId = ((Integer) session.getAttribute("chAdminId"));
			if (session.getAttribute("providePracticeId") != null) {
				providePracticeId = (Integer) session
						.getAttribute("providePracticeId");
			}
			boolean status = chInboxService.updateRefferInformation(
					patientReferralInfo, chId, fromEmail, chAdminId,fromProviderId);
			ArrayList<FileMeta> fileList = new ArrayList<FileMeta>();
			try {
				fileList = ReferadrUtils.getFiles(files, fileList,
						IConstant.FILE_REF_PROV_ACTION);

				int refCHActnId = CommonUtils
						.getLatestRefCHActionIdByRefId(refId);
				CommonUtils.uploadAttachments(fileList, patId, refId,
						refCHActnId, false);
			} catch (Exception ex) {
				System.out.println("Exception in FIle Upload Process: "
						+ ex.toString());
			}
			return "redirect:/clearingHouse.do";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::chInbox Controller", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}

	}

	/**
	 * This method is used for Display or show the chUpdate.jsp
	 * 
	 */
	@RequestMapping("/chUpdateRefralView")
	public String showUpdatePage(
			@ModelAttribute("Referral_Provider_Action") Referral_Provider_Action referral_Provider_Action,
			BindingResult result, ModelMap model, HttpServletRequest request,
			@RequestParam(required = false) Integer patientReferralId,
			Integer providerId) {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");

		try {
			if (login != null) {
				updateNameFiles.removeAll(updateNameFiles);
				model.addAttribute("patientReferralId", patientReferralId);

				model.addAttribute("providerActionId", providerId);
				model.put("Referral_Provider_Action",
						new Referral_Provider_Action());
			} else {
				model.addAttribute(
						"errorMsg",
						"You have been logged out of the system for security reasons. Please log back in");
				return "redirect:/logout.do";
			}
			return "chUpdate";
		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			ReferadrUtils.sendMail(IConstant.FROM_MAIL, IConstant.TO_MAIL,
					"Failed::chInbox Controller", errors.toString());
			model.addAttribute(
					"errorMsg",
					"You have been logged out of the system for security reasons. Please log back in");
			return "redirect:/logout.do";
		}
	}

	/**
	 * This method is used for update the additional notes on chInbox
	 * 
	 */
	@RequestMapping(value = "/chUpdateAdditionalNotes", method = RequestMethod.POST)
	public String updateNote(
			@ModelAttribute("Referral_Provider_Action") Referral_Provider_Action referral_Provider_Action,
			BindingResult result, ModelMap model,
			@RequestParam("updatefiles") MultipartFile[] updatefiles,
			HttpServletRequest request)

	{
		HttpSession session = request.getSession();
		ProviderInfo providerInfo = (ProviderInfo) session
				.getAttribute("providerInfo");
		int chAdminId = ((Integer) session.getAttribute("chAdminId"));
		int chId = ((Integer) session.getAttribute("clearingHouseId"));
		chInboxService.updateMessage(referral_Provider_Action, providerInfo,
				chAdminId, chId);
		// Add Code for uploading attachments - DIleep
		ArrayList<FileMeta> fileList = new ArrayList<FileMeta>();
		try {
			fileList = ReferadrUtils.getFiles(updatefiles, fileList,
					IConstant.FILE_REF_PROV_ACTION);

			int refId = referral_Provider_Action.getPatientReferralInfo()
					.getRefId();
			int patId = CommonUtils.getPatientIdByReferralId(refId);
			int refCHActionId = CommonUtils
					.getLatestRefCHActionIdByRefId(refId);

			CommonUtils.uploadAttachments(fileList, patId, refId,
					refCHActionId, false);
		} catch (Exception ex) {
			System.out.println("Exception in FIle Upload Process: "
					+ ex.getStackTrace());
		}
		if (session.getAttribute("clearingHouseId") != null) {
			return "redirect:/clearingHouse.do";
		} else {
			return "redirect:/practice.do";
		}
	}
}
