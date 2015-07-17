package com.referadr.practice.model;

public class ReportVO {
	private int refferalId;
	private int practiceId;
	private int insId;
	private String practiceName;
	private String patientFirstName;
	private String patientLastName;
	private String doctorFirstName;
	private String doctorLastName;
	
	private String insuranceCompany;
	private String creationDate;
	private String keyToSearch;
	private String valueToSearch;
private boolean flag;
	private String startDate;
	private String endDate;
	private int splId;
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	public String getDoctorLastName() {
		return doctorLastName;
	}
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getValueToSearch() {
		return valueToSearch;
	}
	public void setValueToSearch(String valueToSearch) {
		this.valueToSearch = valueToSearch;
	}
	public int getRefferalId() {
		return refferalId;
	}
	public void setRefferalId(int refferalId) {
		this.refferalId = refferalId;
	}
	public int getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}
	public int getInsId() {
		return insId;
	}
	public void setInsId(int insId) {
		this.insId = insId;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getKeyToSearch() {
		return keyToSearch;
	}
	public void setKeyToSearch(String keyToSearch) {
		this.keyToSearch = keyToSearch;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getSplId() {
		return splId;
	}
	public void setSplId(int splId) {
		this.splId = splId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private String searchValue;

}
