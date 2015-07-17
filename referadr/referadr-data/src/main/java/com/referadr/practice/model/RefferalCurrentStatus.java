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
@Table(name = "rad_refferal_current_status")
public class RefferalCurrentStatus extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "REF_CURR_STS_ID")
  private Integer refCurrStsId;

  @ManyToOne
  @JoinColumn(name = "REFERRAL_ID",referencedColumnName="PATIENT_REFERRAL_ID")
  private PatientReferralInfo patientReferralInfo;
  
  @ManyToOne
  @JoinColumn(name = "PRACTICE_ID")
  private PracticeInfo practiceInfo ;
  
  @ManyToOne
  @JoinColumn(name ="CH_ID")
  private CHInfo chInfo ;

  
  public Integer getRefCurrStsId() {
    return refCurrStsId;
  }

  public void setRefCurrStsId(Integer refCurrStsId) {
    this.refCurrStsId = refCurrStsId;
  }

  public PatientReferralInfo getPatientReferralInfo() {
    return patientReferralInfo;
  }

  public void setPatientReferralInfo(PatientReferralInfo patientReferralInfo) {
    this.patientReferralInfo = patientReferralInfo;
  }

  public PracticeInfo getPracticeInfo() {
    return practiceInfo;
  }

  public void setPracticeInfo(PracticeInfo practiceInfo) {
    this.practiceInfo = practiceInfo;
  }

public CHInfo getChInfo() {
	return chInfo;
}

public void setChInfo(CHInfo chInfo) {
	this.chInfo = chInfo;
}

}
