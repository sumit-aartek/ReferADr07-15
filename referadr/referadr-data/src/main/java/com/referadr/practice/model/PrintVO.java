package com.referadr.practice.model;

public class PrintVO implements Comparable<PrintVO>{

	
	String refproviderFirstName;
	 String refProviderLastName;
	 String refPracticeName;
	 String refProvDiagCode;
	 String refProvNotes;
	 String refProvRefReason;
	 String patientFirstName;
	 String patientLastName;
	 String patienEmailId;
	 String patientDob;
	 String patientGender;
	 String pracSplDesc;
	 String practiceName;
	 String providerFirstName;
	 String providerLastName;
	 String patInsuranceId;
	  String patInsuranceGroup;
	  String insuranceCompany;
	  String locAddress1;
	  String locCity;
	  private String creationDate;
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getPatInsuranceId() {
		return patInsuranceId;
	}
	public void setPatInsuranceId(String patInsuranceId) {
		this.patInsuranceId = patInsuranceId;
	}
	public String getPatInsuranceGroup() {
		return patInsuranceGroup;
	}
	public void setPatInsuranceGroup(String patInsuranceGroup) {
		this.patInsuranceGroup = patInsuranceGroup;
	}
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getLocAddress1() {
		return locAddress1;
	}
	public void setLocAddress1(String locAddress1) {
		this.locAddress1 = locAddress1;
	}
	public String getLocCity() {
		return locCity;
	}
	public void setLocCity(String locCity) {
		this.locCity = locCity;
	}
	public String getRefproviderFirstName() {
		return refproviderFirstName;
	}
	public void setRefproviderFirstName(String refproviderFirstName) {
		this.refproviderFirstName = refproviderFirstName;
	}
	public String getRefProviderLastName() {
		return refProviderLastName;
	}
	public void setRefProviderLastName(String refProviderLastName) {
		this.refProviderLastName = refProviderLastName;
	}
	public String getRefPracticeName() {
		return refPracticeName;
	}
	public void setRefPracticeName(String refPracticeName) {
		this.refPracticeName = refPracticeName;
	}
	public String getRefProvDiagCode() {
		return refProvDiagCode;
	}
	public void setRefProvDiagCode(String refProvDiagCode) {
		this.refProvDiagCode = refProvDiagCode;
	}
	public String getRefProvNotes() {
		return refProvNotes;
	}
	public void setRefProvNotes(String refProvNotes) {
		this.refProvNotes = refProvNotes;
	}
	public String getRefProvRefReason() {
		return refProvRefReason;
	}
	public void setRefProvRefReason(String refProvRefReason) {
		this.refProvRefReason = refProvRefReason;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getPatienEmailId() {
		return patienEmailId;
	}
	public void setPatienEmailId(String patienEmailId) {
		this.patienEmailId = patienEmailId;
	}
	public String getPatientDob() {
		return patientDob;
	}
	public void setPatientDob(String patientDob) {
		this.patientDob = patientDob;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getPracSplDesc() {
		return pracSplDesc;
	}
	public void setPracSplDesc(String pracSplDesc) {
		this.pracSplDesc = pracSplDesc;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	public String getProviderFirstName() {
		return providerFirstName;
	}
	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}
	public String getProviderLastName() {
		return providerLastName;
	}
	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}
	public int compareTo(PrintVO o) {
		// TODO Auto-generated method stub
		return o.creationDate.compareTo(creationDate);
	}
	
}
