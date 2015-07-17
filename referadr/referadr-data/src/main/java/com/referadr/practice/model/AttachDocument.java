package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attach_document")
public class AttachDocument extends AbstractEntity  implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "DOCOMENT_ID")
  private Integer refProviderActionId;

  @ManyToOne
  @JoinColumn(name = "PAT_INS_INFO_ID",updatable=false)
  private PatientInsuranceInfo patientInsuranceInfo ;

  @Column(name = "DOCUMENT_NAME")
  private String documentName;

  public Integer getRefProviderActionId() {
    return refProviderActionId;
  }

  public void setRefProviderActionId(Integer refProviderActionId) {
    this.refProviderActionId = refProviderActionId;
  }

  public String getDocumentName() {
    return documentName;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  public PatientInsuranceInfo getPatientInsuranceInfo() {
    return patientInsuranceInfo;
  }

  public void setPatientInsuranceInfo(PatientInsuranceInfo patientInsuranceInfo) {
    this.patientInsuranceInfo = patientInsuranceInfo;
  }

}
