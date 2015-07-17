package com.referadr.practice.model;

import java.util.Date;


public class  PateintAndInsuranceInfoVO {
	private int stateId;
	private String pateintFirstName;
	private String pateintLastName;
	private String pateintEmail;
	private String patientSSN;
	  private String patientDob;
	  private String patientGender;
	  private Integer patientId;
	  private Integer locId;
	  private String locFax;
	  private String locZip;
	  private String locPhone;
	  private String locCity;
	  private String locAddress2;
	  private String locAddress1;
	  private int currentStatusId;
		private int refId;
		  private Integer patientInsuranceInfoId;
		  private String patientInsuranceGroup;
		  private String patientPreAuthContactName;
		  private String patientPreauthConfirmation;
		  private String patientInsuranceNotes;
		  private Date patientPreAuthStartDate;
		  private Date patientPreAuthEndDate;
		  private String patientInsurancePhone;
		  private String patientPreAuthId;
		  private String insuranceId;
		  private String patientPre1AuthReq;
		  private String insuranceCompany;
			public String getPateintEmail() {
				return pateintEmail;
			}
			public void setPateintEmail(String pateintEmail) {
				this.pateintEmail = pateintEmail;
			}
		  public String getPateintLastName() {
				return pateintLastName;
			}
			public void setPateintLastName(String pateintLastName) {
				this.pateintLastName = pateintLastName;
			}
		public String getInsuranceCompany() {
			return insuranceCompany;
		}
		public void setInsuranceCompany(String insuranceCompany) {
			this.insuranceCompany = insuranceCompany;
		}
		public String getPateintFirstName() {
			return pateintFirstName;
		}
		public void setPateintFirstName(String pateintFirstName) {
			this.pateintFirstName = pateintFirstName;
		}
		public String getPatientSSN() {
			return patientSSN;
		}
		public void setPatientSSN(String patientSSN) {
			this.patientSSN = patientSSN;
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
		public Integer getPatientId() {
			return patientId;
		}
		public void setPatientId(Integer patientId) {
			this.patientId = patientId;
		}
		public Integer getLocId() {
			return locId;
		}
		public void setLocId(Integer locId) {
			this.locId = locId;
		}
		public String getLocFax() {
			return locFax;
		}
		public void setLocFax(String locFax) {
			this.locFax = locFax;
		}
		public String getLocZip() {
			return locZip;
		}
		public void setLocZip(String locZip) {
			this.locZip = locZip;
		}
		public String getLocCity() {
			return locCity;
		}
		public void setLocCity(String locCity) {
			this.locCity = locCity;
		}
		public String getLocAddress2() {
			return locAddress2;
		}
		public void setLocAddress2(String locAddress2) {
			this.locAddress2 = locAddress2;
		}
		public String getLocAddress1() {
			return locAddress1;
		}
		public void setLocAddress1(String locAddress1) {
			this.locAddress1 = locAddress1;
		}
		public int getCurrentStatusId() {
			return currentStatusId;
		}
		public void setCurrentStatusId(int currentStatusId) {
			this.currentStatusId = currentStatusId;
		}
		public int getRefId() {
			return refId;
		}
		public void setRefId(int refId) {
			this.refId = refId;
		}
		public Integer getPatientInsuranceInfoId() {
			return patientInsuranceInfoId;
		}
		public void setPatientInsuranceInfoId(Integer patientInsuranceInfoId) {
			this.patientInsuranceInfoId = patientInsuranceInfoId;
		}
		public String getPatientInsuranceGroup() {
			return patientInsuranceGroup;
		}
		public void setPatientInsuranceGroup(String patientInsuranceGroup) {
			this.patientInsuranceGroup = patientInsuranceGroup;
		}
		public String getPatientPreAuthContactName() {
			return patientPreAuthContactName;
		}
		public void setPatientPreAuthContactName(String patientPreAuthContactName) {
			this.patientPreAuthContactName = patientPreAuthContactName;
		}
		public String getPatientPreauthConfirmation() {
			return patientPreauthConfirmation;
		}
		public void setPatientPreauthConfirmation(String patientPreauthConfirmation) {
			this.patientPreauthConfirmation = patientPreauthConfirmation;
		}
		public String getPatientInsuranceNotes() {
			return patientInsuranceNotes;
		}
		public void setPatientInsuranceNotes(String patientInsuranceNotes) {
			this.patientInsuranceNotes = patientInsuranceNotes;
		}
		public Date getPatientPreAuthStartDate() {
			return patientPreAuthStartDate;
		}
		public void setPatientPreAuthStartDate(Date patientPreAuthStartDate) {
			this.patientPreAuthStartDate = patientPreAuthStartDate;
		}
		public Date getPatientPreAuthEndDate() {
			return patientPreAuthEndDate;
		}
		public void setPatientPreAuthEndDate(Date patientPreAuthEndDate) {
			this.patientPreAuthEndDate = patientPreAuthEndDate;
		}
		public String getPatientInsurancePhone() {
			return patientInsurancePhone;
		}
		public void setPatientInsurancePhone(String patientInsurancePhone) {
			this.patientInsurancePhone = patientInsurancePhone;
		}
		public String getPatientPreAuthId() {
			return patientPreAuthId;
		}
		public void setPatientPreAuthId(String patientPreAuthId) {
			this.patientPreAuthId = patientPreAuthId;
		}
		public String getInsuranceId() {
			return insuranceId;
		}
		public void setInsuranceId(String insuranceId) {
			this.insuranceId = insuranceId;
		}
		public String getPatientPre1AuthReq() {
			return patientPre1AuthReq;
		}
		public void setPatientPre1AuthReq(String patientPre1AuthReq) {
			this.patientPre1AuthReq = patientPre1AuthReq;
		}
	
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		public int getStateId() {
			return stateId;
		}
		public void setStateId(int stateId) {
			this.stateId = stateId;
		}
		
		
		public String getLocPhone() {
			return locPhone;
		}
		public void setLocPhone(String locPhone) {
			this.locPhone = locPhone;
		}
}