package com.referadr.practice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referadr.practice.model.ReportVO;
import com.referadr.practice.repository.ReportRepository;
import com.referadr.practice.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportRepository reportRepository;

	public List<ReportVO> searchReport(ReportVO repVO) {
		List<ReportVO> reportList = null;
		reportList = reportRepository.searchReport(repVO);
		return reportList;
	}

}
