package com.referadr.practice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_practice_speciality")
public class PracticeSpecialty extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PRAC_SPL_ID")
  private int praticeSplID;

  @Column(name = "PRAC_SPL_DESC")
  private String praticeSplDesc;

  @Column(name = "PRAC_SPL_STATUS")
  private Character pracSplStatus;

  /*
   * @LazyCollection(LazyCollectionOption.FALSE)
   * 
   * @OneToMany(cascade = CascadeType.ALL)
   * 
   * @JoinColumn(name = "PRAC_SPL_ID", updatable = false) private
   * List<PracticeInfo> practiceInfoList;
   */

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "PRAC_SPL_ID", updatable = false)
  private List<PatientReferralInfo> patientReferralInfoList;

  /*
   * @ManyToOne
   * 
   * @JoinColumn(name = "CH_ID") private CHInfo chInfo ;
   */

  /* Mapping for combo box start */
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CH_PRACSPL_ID", referencedColumnName = "PRAC_SPL_ID", insertable = false)
  private List<ChInfoSpecialtyMapping> chInfoSpecialtyMappingList;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "PRACTICE_SPL_ID", referencedColumnName = "PRAC_SPL_ID", insertable = false)
  private List<PracticeSpecialtyMapping> practiceSpecialtyMappingList;

  /* End */

  public int getPraticeSplID() {
    return praticeSplID;
  }

  public void setPraticeSplID(int praticeSplID) {
    this.praticeSplID = praticeSplID;
  }

  public String getPraticeSplDesc() {
    return praticeSplDesc;
  }

  public void setPraticeSplDesc(String praticeSplDesc) {
    this.praticeSplDesc = praticeSplDesc;
  }

  /*  *//**
   * @return the practiceInfoList
   */
  /*
   * @JsonIgnore public List<PracticeInfo> getPracticeInfoList() { return
   * practiceInfoList; }
   *//**
   * @param practiceInfoList
   *          the practiceInfoList to set
   */
  /*
   * public void setPracticeInfoList(List<PracticeInfo> practiceInfoList) {
   * this.practiceInfoList = practiceInfoList; }
   */

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

  public Character getPracSplStatus() {
    return pracSplStatus;
  }

  public void setPracSplStatus(Character pracSplStatus) {
    this.pracSplStatus = pracSplStatus;
  }

  @JsonIgnore
  public List<ChInfoSpecialtyMapping> getChInfoSpecialtyMappingList() {
    return chInfoSpecialtyMappingList;
  }

  public void setChInfoSpecialtyMappingList(List<ChInfoSpecialtyMapping> chInfoSpecialtyMappingList) {
    this.chInfoSpecialtyMappingList = chInfoSpecialtyMappingList;
  }
  @JsonIgnore
  public List<PracticeSpecialtyMapping> getPracticeSpecialtyMappingList() {
    return practiceSpecialtyMappingList;
  }

  public void setPracticeSpecialtyMappingList(List<PracticeSpecialtyMapping> practiceSpecialtyMappingList) {
    this.practiceSpecialtyMappingList = practiceSpecialtyMappingList;
  }

  /*
   * public CHInfo getChInfo() { return chInfo; }
   * 
   * public void setChInfo(CHInfo chInfo) { this.chInfo = chInfo; }
   */

}
