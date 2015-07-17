package com.referadr.practice.repository;

import java.util.List;

import com.referadr.practice.model.ReportVO;

public interface ReportRepository {

	List<ReportVO> searchReport(ReportVO repVO);

}
