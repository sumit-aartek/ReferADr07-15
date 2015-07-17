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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_patient_referral_info")
public class PatientReferralInfo extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PATIENT_REFERRAL_ID")
  private Integer refId;

  @ManyToOne
  @JoinColumn(name = "CH_ID")
  private CHInfo chInfo;

  @ManyToOne
  @JoinColumn(name = "PRAC_SPL_ID")
  private PracticeSpecialty practiceSpecialty;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Patient_ID")
  private PatientInfo patientInfo;

  @ManyToOne
  @JoinColumn(name = "Practice_ID", nullable = true)
  private PracticeInfo practiceInfo;

  @ManyToOne
  @JoinColumn(name = "Provider_ID" , nullable = true)
  private ProviderInfo providerInfo;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany
  @JoinColumn(name = "REFERRAL_ID", referencedColumnName = "PATIENT_REFERRAL_ID", updatable = false, insertable = false)
  private List<Referral_Provider_Action> referral_Provider_ActionList;

  @Transient
  private Integer proId;

  @Transient
  private String imageName;

  @Transient
  private Integer curStatusId;

  @Transient
  private String docAttachment;
  
  @Transient
  private Integer practiceId;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "REFERRAL_ID", referencedColumnName = "PATIENT_REFERRAL_ID", updatable = false)
  private List<RefferalCurrentStatus> refferalCurrentStatuList;

  /*
   * @Column(name = "CLAIM") private String claim;
   */

  /**
   * @return the refId
   */
  public Integer getRefId() {
    return refId;
  }

  /**
   * @param refId
   *          the refId to set
   */
  public void setRefId(Integer refId) {
    this.refId = refId;
  }

  /**
   * @return the chInfo
   */
  public CHInfo getChInfo() {
    return chInfo;
  }

  /**
   * @param chInfo
   *          the chInfo to set
   */
  public void setChInfo(CHInfo chInfo) {
    this.chInfo = chInfo;
  }

  /**
   * @return the practiceSpecialty
   */
  public PracticeSpecialty getPracticeSpecialty() {
    return practiceSpecialty;
  }

  /**
   * @param practiceSpecialty
   *          the practiceSpecialty to set
   */
  public void setPracticeSpecialty(PracticeSpecialty practiceSpecialty) {
    this.practiceSpecialty = practiceSpecialty;
  }

  /**
   * @return the patientInfo
   */
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

  /**
   * @return the practiceInfo
   */
  public PracticeInfo getPracticeInfo() {
    return practiceInfo;
  }

  /**
   * @param practiceInfo
   *          the practiceInfo to set
   */
  public void setPracticeInfo(PracticeInfo practiceInfo) {
    this.practiceInfo = practiceInfo;
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

  public List<Referral_Provider_Action> getReferral_Provider_ActionList() {
    return referral_Provider_ActionList;
  }

  public void setReferral_Provider_ActionList(List<Referral_Provider_Action> referral_Provider_ActionList) {
    this.referral_Provider_ActionList = referral_Provider_ActionList;
  }

  public Integer getProId() {
    return proId;
  }

  public void setProId(Integer proId) {
    this.proId = proId;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public List<RefferalCurrentStatus> getRefferalCurrentStatuList() {
    return refferalCurrentStatuList;
  }

  public void setRefferalCurrentStatuList(List<RefferalCurrentStatus> refferalCurrentStatuList) {
    this.refferalCurrentStatuList = refferalCurrentStatuList;
  }

  public Integer getCurStatusId() {
    return curStatusId;
  }

  public void setCurStatusId(Integer curStatusId) {
    this.curStatusId = curStatusId;
  }

  public String getDocAttachment() {
    return docAttachment;
  }

  public void setDocAttachment(String docAttachment) {
    this.docAttachment = docAttachment;
  }

public Integer getPracticeId() {
	return practiceId;
}

public void setPracticeId(Integer practiceId) {
	this.practiceId = practiceId;
}


String scheduleDate;
String scheduleTime;
String scheduleNotes;
String labNotes,labICDNote;
int labReffId;
String labRefPhy;
public String getLabRefPhy() {
	return labRefPhy;
}

public void setLabRefPhy(String labRefPhy) {
	this.labRefPhy = labRefPhy;
}


boolean schedulFlag;
int lab_category_id;
String lab_category_desc;
int lab_sub_category_id;
String lab_sub_category_desc;
int lab_service_id;
String lab_service_desc;
int lab_sub_service_id;
String lab_sub_service_desc;
int lab_sub_service_category_id;
String lab_sub_service_category_desc;
int lab_id;
String lab_desc;
int location_id;
int lab_sub_category_map_id;
int lab_service_map_id;
int lab_sub_service_map_id;
int lab_sub_service_category_map_id;
String reffCreationDate;
public String getReffCreationDate() {
	return reffCreationDate;
}

public void setReffCreationDate(String reffCreationDate) {
	this.reffCreationDate = reffCreationDate;
}


String referToRadio; // True for Practice or CH and False for lab 


public String getLabNotes() {
	return labNotes;
}

public void setLabNotes(String labNotes) {
	this.labNotes = labNotes;
}

public String getLabICDNote() {
	return labICDNote;
}

public void setLabICDNote(String labICDNote) {
	this.labICDNote = labICDNote;
}

public int getLabReffId() {
	return labReffId;
}

public void setLabReffId(int labReffId) {
	this.labReffId = labReffId;
}

public int getLab_category_id() {
	return lab_category_id;
}

public void setLab_category_id(int lab_category_id) {
	this.lab_category_id = lab_category_id;
}

public String getLab_category_desc() {
	return lab_category_desc;
}

public void setLab_category_desc(String lab_category_desc) {
	this.lab_category_desc = lab_category_desc;
}

public int getLab_sub_category_id() {
	return lab_sub_category_id;
}

public void setLab_sub_category_id(int lab_sub_category_id) {
	this.lab_sub_category_id = lab_sub_category_id;
}

public String getLab_sub_category_desc() {
	return lab_sub_category_desc;
}

public void setLab_sub_category_desc(String lab_sub_category_desc) {
	this.lab_sub_category_desc = lab_sub_category_desc;
}

public int getLab_service_id() {
	return lab_service_id;
}

public void setLab_service_id(int lab_service_id) {
	this.lab_service_id = lab_service_id;
}

public String getLab_service_desc() {
	return lab_service_desc;
}

public void setLab_service_desc(String lab_service_desc) {
	this.lab_service_desc = lab_service_desc;
}

public int getLab_sub_service_id() {
	return lab_sub_service_id;
}

public void setLab_sub_service_id(int lab_sub_service_id) {
	this.lab_sub_service_id = lab_sub_service_id;
}

public String getLab_sub_service_desc() {
	return lab_sub_service_desc;
}

public void setLab_sub_service_desc(String lab_sub_service_desc) {
	this.lab_sub_service_desc = lab_sub_service_desc;
}

public int getLab_sub_service_category_id() {
	return lab_sub_service_category_id;
}

public void setLab_sub_service_category_id(int lab_sub_service_category_id) {
	this.lab_sub_service_category_id = lab_sub_service_category_id;
}

public String getLab_sub_service_category_desc() {
	return lab_sub_service_category_desc;
}

public void setLab_sub_service_category_desc(
		String lab_sub_service_category_desc) {
	this.lab_sub_service_category_desc = lab_sub_service_category_desc;
}

public int getLab_id() {
	return lab_id;
}

public void setLab_id(int lab_id) {
	this.lab_id = lab_id;
}

public String getLab_desc() {
	return lab_desc;
}

public void setLab_desc(String lab_desc) {
	this.lab_desc = lab_desc;
}

public int getLocation_id() {
	return location_id;
}

public void setLocation_id(int location_id) {
	this.location_id = location_id;
}

public int getLab_sub_category_map_id() {
	return lab_sub_category_map_id;
}

public void setLab_sub_category_map_id(int lab_sub_category_map_id) {
	this.lab_sub_category_map_id = lab_sub_category_map_id;
}

public int getLab_service_map_id() {
	return lab_service_map_id;
}

public void setLab_service_map_id(int lab_service_map_id) {
	this.lab_service_map_id = lab_service_map_id;
}

public int getLab_sub_service_map_id() {
	return lab_sub_service_map_id;
}

public void setLab_sub_service_map_id(int lab_sub_service_map_id) {
	this.lab_sub_service_map_id = lab_sub_service_map_id;
}

public int getLab_sub_service_category_map_id() {
	return lab_sub_service_category_map_id;
}

public void setLab_sub_service_category_map_id(
		int lab_sub_service_category_map_id) {
	this.lab_sub_service_category_map_id = lab_sub_service_category_map_id;
}








public String getReferToRadio() {
	return referToRadio;
}

public void setReferToRadio(String referToRadio) {
	this.referToRadio = referToRadio;
}

public boolean isSchedulFlag() {
	return schedulFlag;
}

public void setSchedulFlag(boolean schedulFlag) {
	this.schedulFlag = schedulFlag;
}

public String getScheduleDate() {
	return scheduleDate;
}

public void setScheduleDate(String scheduleDate) {
	this.scheduleDate = scheduleDate;
}

public String getScheduleTime() {
	return scheduleTime;
}

public void setScheduleTime(String scheduleTime) {
	this.scheduleTime = scheduleTime;
}

public String getScheduleNotes() {
	return scheduleNotes;
}

public void setScheduleNotes(String scheduleNotes) {
	this.scheduleNotes = scheduleNotes;
} 



}
