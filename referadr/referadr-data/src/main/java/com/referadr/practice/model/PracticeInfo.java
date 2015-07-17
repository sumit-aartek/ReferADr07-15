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
@Table(name = "rad_practice_info")
public class PracticeInfo extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Practice_ID")
  private Integer practiceId;

  @Column(name = "Practice_Name")
  private String practiceName;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "PROVIDER_PRAC_ID", referencedColumnName = "Practice_ID", updatable = false)
  private List<ProviderInfo> providerInfoList;

  @OneToOne
  @JoinColumn(name = "Practice_ID")
  private PracticeLocations practiceLocations;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "Practice_ID", updatable = false)
  private List<PatientReferralInfo> patientReferralInfoList;

  /* Mapping for combo box start */
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "Practice_ID", insertable = false)
  private List<ChInfoPracticeMapping> chInfoPracticeMappingList;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "Practice_ID", insertable = false)
  private List<PracticeSpecialtyMapping> practiceSpecialtyMappingList;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "Practice_ID", updatable = false)
  private List<RefferalCurrentStatus> currentStatuList ;
  /* End */

  /**
   * @return the practiceName
   */
  public String getPracticeName() {
    return practiceName;
  }

  /**
   * @param practiceName
   *          the practiceName to set
   */
  public void setPracticeName(String practiceName) {
    this.practiceName = practiceName;
  }

  /**
   * @return the providerInfoList
   */
  @JsonIgnore
  public List<ProviderInfo> getProviderInfoList() {
    return providerInfoList;
  }

  /**
   * @param providerInfoList
   *          the providerInfoList to set
   */
  public void setProviderInfoList(List<ProviderInfo> providerInfoList) {
    this.providerInfoList = providerInfoList;
  }

  @JsonIgnore
  public PracticeLocations getPracticeLocations() {
    return practiceLocations;
  }

  public void setPracticeLocations(PracticeLocations practiceLocations) {
    this.practiceLocations = practiceLocations;
  }

  public Integer getPracticeId() {
    return practiceId;
  }

  public void setPracticeId(Integer practiceId) {
    this.practiceId = practiceId;
  }
  @JsonIgnore
  public List<PatientReferralInfo> getPatientReferralInfoList() {
    return patientReferralInfoList;
  }

  public void setPatientReferralInfoList(List<PatientReferralInfo> patientReferralInfoList) {
    this.patientReferralInfoList = patientReferralInfoList;
  }

  @JsonIgnore
  public List<ChInfoPracticeMapping> getChInfoPracticeMappingList() {
    return chInfoPracticeMappingList;
  }

  public void setChInfoPracticeMappingList(List<ChInfoPracticeMapping> chInfoPracticeMappingList) {
    this.chInfoPracticeMappingList = chInfoPracticeMappingList;
  }
  @JsonIgnore
  public List<PracticeSpecialtyMapping> getPracticeSpecialtyMappingList() {
    return practiceSpecialtyMappingList;
  }

  public void setPracticeSpecialtyMappingList(List<PracticeSpecialtyMapping> practiceSpecialtyMappingList) {
    this.practiceSpecialtyMappingList = practiceSpecialtyMappingList;
  }
  @JsonIgnore
  public List<RefferalCurrentStatus> getCurrentStatuList() {
    return currentStatuList;
  }

  public void setCurrentStatuList(List<RefferalCurrentStatus> currentStatuList) {
    this.currentStatuList = currentStatuList;
  }

}
