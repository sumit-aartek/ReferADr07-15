package com.referadr.practice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.referadr.login.model.Login;
import com.referadr.practice.model.ReportVO;
import com.referadr.practice.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;

	// to fetch search results
	@RequestMapping(value = "/reportSearchAction", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List> reportSearchAction(
			@RequestParam String keyToSearch, String valueToSearch,
			String startDate, String endDate,Boolean flag, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		Map<String, List> ReportVOMap = new HashMap<String, List>();
		if (login != null) {
			Integer providePracticeId = 0;
			if (session.getAttribute("providePracticeId") != null)
				providePracticeId = ((Integer) session
						.getAttribute("providePracticeId"));
			List<ReportVO> reportData = null;
			ReportVO repVO = new ReportVO();
			repVO.setKeyToSearch(keyToSearch);

			if (keyToSearch.equals("Speciality")) {
				repVO.setSplId(Integer.parseInt(valueToSearch));
			}

			repVO.setValueToSearch(valueToSearch);
			repVO.setStartDate(startDate);
			repVO.setEndDate(endDate);
			repVO.setPracticeId(providePracticeId);
			repVO.setFlag(flag);
			if (repVO != null) {
				reportData = reportService.searchReport(repVO);
			}

			if (reportData.size() != 0) {
				ReportVOMap.put("allDetailsList", reportData);
			}

			return ReportVOMap;

		} else {
			return ReportVOMap;
		}
	}

}
