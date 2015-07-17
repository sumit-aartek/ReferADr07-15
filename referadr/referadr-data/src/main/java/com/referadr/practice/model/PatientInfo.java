package com.referadr.practice.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_patient_info")
public class PatientInfo extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Patient_ID")
  private Integer patientId;

  @Column(name = "PATIENT_FIRST_NAME")
  private String patientFirstName;

  @Column(name = " PATIENT_LAST_NAME")
  private String patientLastName;

  @Column(name = "PATIENT_EMAIL")
  private String patientEmail;

  @Column(name = "PATIENT_GENDER")
  private Character patientGender;

  @Column(name = "PATIENT_SSN")
  private String patientSSN;

  @Column(name = "PATIENT_LICENSE_CARD_1")
  private String patientLicenseCard1;

  @Column(name = "PATIENT_LICENSE_CARD_2")
  private String patientLicenseCard2;

  @Column(name = "PATIENT_HIPAA_AGREEMENT")
  private String patientHipaaAgreement;

  @Column(name = "PATIENT_DOB")
  private String patientDob;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "PATIENT_LOC_ID") private PracticeLocations
   * practiceLocations;
   */

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "PATIENT_LOC_ID", referencedColumnName="LOC_ID")
  private RadLocation radLocation;

/*  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "Patient_ID", updatable = false)
  private List<PatientInfo> patientInfoList;*/

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany
  @JoinColumn(name = "Patient_ID",updatable=false)
  private List<PatientReferralInfo> patientReferralInfoList;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "Patient_ID",updatable=false)
  private List<PatientInsuranceInfo> patientInsuranceInfoList;

  /**
   * @return the patientId
   */
  public Integer getPatientId() {
    return patientId;
  }

  /**
   * @param patientId
   *          the patientId to set
   */
  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  /**
   * @return the patientInfoList
   */
 /* @JsonIgnore
  public List<PatientInfo> getPatientInfoList() {
    return patientInfoList;
  }

  *//**
   * @param patientInfoList
   *          the patientInfoList to set
   *//*
  public void setPatientInfoList(List<PatientInfo> patientInfoList) {
    this.patientInfoList = patientInfoList;
  }*/

  /**
   * @return the patientReferralInfoList
   */
  @JsonIgnore
  public List<PatientReferralInfo> getPatientReferralInfoList() {
    return patientReferralInfoList;
  }

  /**
   * @param patientReferralInfoList
   *          the patientReferralInfoList to set
   */
  public void setPatientReferralInfoList(List<PatientReferralInfo> patientReferralInfoList) {
    this.patientReferralInfoList = patientReferralInfoList;
  }

  /**
   * @return the patientEmail
   */
  public String getPatientEmail() {
    return patientEmail;
  }

  /**
   * @param patientEmail
   *          the patientEmail to set
   */
  public void setPatientEmail(String patientEmail) {
    this.patientEmail = patientEmail;
  }

  /**
   * @return the patientGender
   */
  public Character getPatientGender() {
    return patientGender;
  }

  /**
   * @param patientGender
   *          the patientGender to set
   */
  public void setPatientGender(Character patientGender) {
    this.patientGender = patientGender;
  }

  /**
   * @return the patientSSN
   */
  public String getPatientSSN() {
    return patientSSN;
  }

  /**
   * @param patientSSN
   *          the patientSSN to set
   */
  public void setPatientSSN(String patientSSN) {
    this.patientSSN = patientSSN;
  }

  /**
   * @return the patientLicenseCard1
   */
  public String getPatientLicenseCard1() {
    return patientLicenseCard1;
  }

  /**
   * @param patientLicenseCard1
   *          the patientLicenseCard1 to set
   */
  public void setPatientLicenseCard1(String patientLicenseCard1) {
    this.patientLicenseCard1 = patientLicenseCard1;
  }

  /**
   * @return the patientLicenseCard2
   */
  public String getPatientLicenseCard2() {
    return patientLicenseCard2;
  }

  /**
   * @param patientLicenseCard2
   *          the patientLicenseCard2 to set
   */
  public void setPatientLicenseCard2(String patientLicenseCard2) {
    this.patientLicenseCard2 = patientLicenseCard2;
  }

  /**
   * @return the patientHipaaAgreement
   */
  public String getPatientHipaaAgreement() {
    return patientHipaaAgreement;
  }

  /**
   * @param patientHipaaAgreement
   *          the patientHipaaAgreement to set
   */
  public void setPatientHipaaAgreement(String patientHipaaAgreement) {
    this.patientHipaaAgreement = patientHipaaAgreement;
  }

  /**
   * @return the patientDob
   */
  public String getPatientDob() {
    return patientDob;
  }

  /**
   * @param patientDob
   *          the patientDob to set
   */
  public void setPatientDob(String patientDob) {
    this.patientDob = patientDob;
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

  public RadLocation getRadLocation() {
    return radLocation;
  }

  public void setRadLocation(RadLocation radLocation) {
    this.radLocation = radLocation;
  }

  public List<PatientInsuranceInfo> getPatientInsuranceInfoList() {
    return patientInsuranceInfoList;
  }

  public void setPatientInsuranceInfoList(List<PatientInsuranceInfo> patientInsuranceInfoList) {
    this.patientInsuranceInfoList = patientInsuranceInfoList;
  }
  
  String patientPhoneNo;

public String getPatientPhoneNo() {
	return patientPhoneNo;
}

public void setPatientPhoneNo(String patientPhoneNo) {
	this.patientPhoneNo = patientPhoneNo;
}

}
