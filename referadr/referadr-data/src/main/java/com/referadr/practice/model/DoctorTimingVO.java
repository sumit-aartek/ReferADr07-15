package com.referadr.practice.model;

import java.util.List;

public class DoctorTimingVO {
	int providerID,dayID;
	String startTime,endTime,creationDate,updatedDate;
	boolean checkBox;
	private List<DoctorTimingVO> doctorTimingVO;
	
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	public int getDayID() {
		return dayID;
	}
	public void setDayID(int dayID) {
		this.dayID = dayID;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isCheckBox() {
		return checkBox;
	}
	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}
	public List<DoctorTimingVO> getDoctorTimingVO() {
		return doctorTimingVO;
	}
	public void setDoctorTimingVO(List<DoctorTimingVO> doctorTimingVO) {
		this.doctorTimingVO = doctorTimingVO;
	}

		
}
