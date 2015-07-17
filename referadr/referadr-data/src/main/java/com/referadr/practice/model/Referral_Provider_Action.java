package com.referadr.practice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rad_referral_provider_action")
public class Referral_Provider_Action extends AbstractEntity implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "REF_PROV_ACTION_ID")
  private Integer refProviderActionId;

  @ManyToOne
  @JoinColumn(name = "REF_PROVIDER_ID", referencedColumnName = "Provider_ID")
  private ProviderInfo providerInfo;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "REFERRAL_ID") private PatientReferralInfo
   * patientReferralInfo;
   */

  @Column(name = "REF_PROV_REF_REASON")
  private String providerRefReasons;

  @Column(name = "REF_PROV_DIAG_CODE")
  private String providerDiagCode;

  @Column(name = "REF_PROV_NOTES")
  private String providerNotes;

  @Column(name = "REF_PROV_ATTACHMENT_ID")
  private Integer refProvAttachmentId;

  @Column(name = "REF_PROV_ACTION_TIMESTAMP")
  private Date refProvActionTimeStamp;

  @Transient
  private String imageName;
  
  @Transient
  private ArrayList<Document> docList = new ArrayList<Document>();
  
  public ArrayList<Document> getDocList() {
	return docList;
}

public void setDocList(ArrayList<Document> docList) {
	this.docList = docList;
}

@ManyToOne
  @JoinColumn(name = "REFERRAL_ID" ,referencedColumnName="PATIENT_REFERRAL_ID", updatable = false)
  private PatientReferralInfo patientReferralInfo;

  public Date getRefProvActionTimeStamp() {
    return refProvActionTimeStamp;
  }

  public void setRefProvActionTimeStamp(Date refProvActionTimeStamp) {
    this.refProvActionTimeStamp = refProvActionTimeStamp;
  }

  /**
   * @return the refProviderActionId
   */
  public Integer getRefProviderActionId() {
    return refProviderActionId;
  }

  /**
   * @param refProviderActionId
   *          the refProviderActionId to set
   */
  public void setRefProviderActionId(Integer refProviderActionId) {
    this.refProviderActionId = refProviderActionId;
  }

  /**
   * @return the providerInfo
   */
  public ProviderInfo getProviderInfo() {
    return providerInfo;
  }

  /**
   * @param providerInfo
   *          the providerInfo to set
   */
  public void setProviderInfo(ProviderInfo providerInfo) {
    this.providerInfo = providerInfo;
  }

  /*  *//**
   * @return the patientReferralInfo
   */
  /*
   * public PatientReferralInfo getPatientReferralInfo() { return
   * patientReferralInfo; }
   *//**
   * @param patientReferralInfo
   *          the patientReferralInfo to set
   */
  /*
   * public void setPatientReferralInfo(PatientReferralInfo patientReferralInfo)
   * { this.patientReferralInfo = patientReferralInfo; }
   */

  public Integer getRefProvAttachmentId() {
    return refProvAttachmentId;
  }

  public void setRefProvAttachmentId(Integer refProvAttachmentId) {
    this.refProvAttachmentId = refProvAttachmentId;
  }

  public PatientReferralInfo getPatientReferralInfo() {
    return patientReferralInfo;
  }

  public void setPatientReferralInfo(PatientReferralInfo patientReferralInfo) {
    this.patientReferralInfo = patientReferralInfo;
  }

  public String getProviderRefReasons() {
    return providerRefReasons;
  }

  public void setProviderRefReasons(String providerRefReasons) {
    this.providerRefReasons = providerRefReasons;
  }

  public String getProviderDiagCode() {
    return providerDiagCode;
  }

  public void setProviderDiagCode(String providerDiagCode) {
    this.providerDiagCode = providerDiagCode;
  }

  public String getProviderNotes() {
    return providerNotes;
  }

  public void setProviderNotes(String providerNotes) {
    this.providerNotes = providerNotes;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

}
