package com.referadr.practice.service;

import java.util.List;

import com.referadr.practice.model.ReportVO;

public interface ReportService {

	List<ReportVO> searchReport(ReportVO repVO);

}
