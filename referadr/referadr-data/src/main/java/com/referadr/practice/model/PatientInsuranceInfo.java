package com.referadr.practice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_patient_insurance_info")
public class PatientInsuranceInfo extends AbstractEntity implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PAT_INS_INFO_ID")
  private Integer patientInsuranceInfoId;

  @ManyToOne
  @JoinColumn(name = "PAT_INS_ID" , referencedColumnName="INSURANCE_ID")
  private InsuranceInfo insuranceInfo;

  @ManyToOne
  @JoinColumn(name = "Patient_ID")
  private PatientInfo patientInfo;

  @Column(name = "PAT_INSURANCE_ID")
  private String patientInsuranceId;

  @Column(name = "PAT_INSURANCE_GROUP")
  private String patientInsuranceGroup;

  @Column(name = "PAT_INSURANCE_PHONE")
  private String patientInsurancePhone;

  @Column(name = "PAT_INSURANCE_IDCARD_1")
  private String patientInsuranceIdCard;

  @Column(name = "PAT_INSURANCE_IDCARD_2")
  private Character patientInsuranceIdCard2;

  @Column(name = "PAT_PREAUTH_REQ")
  private Character patientPre1AuthReq;

  @Column(name = "PAT_PREAUTH_ID")
  private String patientPreAuthId;

  @Column(name = "PAT_PREAUTH_START_DATE")
  private Date patientPreAuthStartDate;

  @Column(name = "PAT_PREAUTH_END_DATE")
  private Date patientPreAuthEndDate;

  @Column(name = "PAT_PREAUTH_CONTACT_NAME")
  private String patientPreAuthContactName;
  
  @Column(name = "PAT_PREAUTH_CONFIRMATIOn")
  private String patientPreauthConfirmation;

  /*
   * @Column(name = "PATIENT_PRE-AUTH_CONFIRMATION") private
   * PatientPreAuthConfirmation;
   */
  @Column(name = "PAT_INSURANCE_NOTES")
  private String patientInsuranceNotes;
  
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany
  @JoinColumn(name = "PAT_INS_INFO_ID",updatable=false)
  private List<AttachDocument> attachDocumentList;
  
  @Transient
  private String startDate;
  
  
  @Transient
  private String endDate;

  /**
   * @return the insuranceInfo
   */
  public InsuranceInfo getInsuranceInfo() {
    return insuranceInfo;
  }

  /**
   * @param insuranceInfo
   *          the insuranceInfo to set
   */
  public void setInsuranceInfo(InsuranceInfo insuranceInfo) {
    this.insuranceInfo = insuranceInfo;
  }

  /**
   * @return the patientInfo
   */
  @JsonIgnore
  public PatientInfo getPatientInfo() {
    return patientInfo;
  }

  /**
   * @param patientInfo
   *          the patientInfo to set
   */
  public void setPatientInfo(PatientInfo patientInfo) {
    this.patientInfo = patientInfo;
  }

  public Integer getPatientInsuranceInfoId() {
    return patientInsuranceInfoId;
  }

  public void setPatientInsuranceInfoId(Integer patientInsuranceInfoId) {
    this.patientInsuranceInfoId = patientInsuranceInfoId;
  }

  public String getPatientInsuranceId() {
    return patientInsuranceId;
  }

  public void setPatientInsuranceId(String patientInsuranceId) {
    this.patientInsuranceId = patientInsuranceId;
  }

  public String getPatientInsuranceGroup() {
    return patientInsuranceGroup;
  }

  public void setPatientInsuranceGroup(String patientInsuranceGroup) {
    this.patientInsuranceGroup = patientInsuranceGroup;
  }

  public String getPatientInsurancePhone() {
    return patientInsurancePhone;
  }

  public void setPatientInsurancePhone(String patientInsurancePhone) {
    this.patientInsurancePhone = patientInsurancePhone;
  }

  public String getPatientInsuranceIdCard() {
    return patientInsuranceIdCard;
  }

  public void setPatientInsuranceIdCard(String patientInsuranceIdCard) {
    this.patientInsuranceIdCard = patientInsuranceIdCard;
  }

  public Character getPatientInsuranceIdCard2() {
    return patientInsuranceIdCard2;
  }

  public void setPatientInsuranceIdCard2(Character patientInsuranceIdCard2) {
    this.patientInsuranceIdCard2 = patientInsuranceIdCard2;
  }

  public Character getPatientPre1AuthReq() {
    return patientPre1AuthReq;
  }

  public void setPatientPre1AuthReq(Character patientPre1AuthReq) {
    this.patientPre1AuthReq = patientPre1AuthReq;
  }

  public String getPatientPreAuthId() {
    return patientPreAuthId;
  }

  public void setPatientPreAuthId(String patientPreAuthId) {
    this.patientPreAuthId = patientPreAuthId;
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

  public String getPatientPreAuthContactName() {
    return patientPreAuthContactName;
  }

  public void setPatientPreAuthContactName(String patientPreAuthContactName) {
    this.patientPreAuthContactName = patientPreAuthContactName;
  }

  public String getPatientInsuranceNotes() {
    return patientInsuranceNotes;
  }

  public void setPatientInsuranceNotes(String patientInsuranceNotes) {
    this.patientInsuranceNotes = patientInsuranceNotes;
  }

  public String getPatientPreauthConfirmation() {
    return patientPreauthConfirmation;
  }

  public void setPatientPreauthConfirmation(String patientPreauthConfirmation) {
    this.patientPreauthConfirmation = patientPreauthConfirmation;
  }

  @JsonIgnore
  public List<AttachDocument> getAttachDocumentList() {
    return attachDocumentList;
  }

  public void setAttachDocumentList(List<AttachDocument> attachDocumentList) {
    this.attachDocumentList = attachDocumentList;
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

}
