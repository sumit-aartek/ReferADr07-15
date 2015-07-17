package com.referadr.practice.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_ch_info")
public class CHInfo extends AbstractEntity implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CH_ID")
  private int id;
  @Column(name = "CN_NAME")
  private String name;

  @Column(name = "CH_DEC")
  private String description;

  @Column(name = "CH_ANC_PROVIDE")
  private Integer chAncProvide;

  @ManyToOne
  @JoinColumn(name = "CH_LOC_ID", referencedColumnName = "LOC_ID")
  private RadLocation radLocation;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
  @JoinColumn(name = "CH_ID", insertable = false)
  private List<PatientReferralInfo> patientReferralInfoList;
  
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
  @JoinColumn(name = "CH_ID", insertable = false)
  private List<RefferalCurrentStatus> refferalCurrentStatuList ;

  /*
   * @LazyCollection(LazyCollectionOption.FALSE)
   * 
   * @OneToMany(cascade = CascadeType.ALL)
   * 
   * @JoinColumn(name = "CH_ID", updatable = false) private
   * List<PracticeSpecialty> practiceSpecialtieList ;
   */

  /* Mapping for combo box start */
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CH_ID", insertable = false)
  private List<ChInfoPracticeMapping> chInfoPracticeMappingList;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "CH_ID", insertable = false)
  private List<ChInfoSpecialtyMapping> chInfoSpecialtyMappingList;

  /* End */

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the patientReferralInfoList
   */
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

  public RadLocation getRadLocation() {
    return radLocation;
  }

  public void setRadLocation(RadLocation radLocation) {
    this.radLocation = radLocation;
  }

  public Integer getChAncProvide() {
    return chAncProvide;
  }

  public void setChAncProvide(Integer chAncProvide) {
    this.chAncProvide = chAncProvide;
  }

  /*
   * @JsonIgnore public List<PracticeSpecialty> getPracticeSpecialtieList() {
   * return practiceSpecialtieList; }
   * 
   * public void setPracticeSpecialtieList(List<PracticeSpecialty>
   * practiceSpecialtieList) { this.practiceSpecialtieList =
   * practiceSpecialtieList; }
   */

  public List<ChInfoPracticeMapping> getChInfoPracticeMappingList() {
    return chInfoPracticeMappingList;
  }

  public void setChInfoPracticeMappingList(List<ChInfoPracticeMapping> chInfoPracticeMappingList) {
    this.chInfoPracticeMappingList = chInfoPracticeMappingList;
  }

  public List<ChInfoSpecialtyMapping> getChInfoSpecialtyMappingList() {
    return chInfoSpecialtyMappingList;
  }

  public void setChInfoSpecialtyMappingList(List<ChInfoSpecialtyMapping> chInfoSpecialtyMappingList) {
    this.chInfoSpecialtyMappingList = chInfoSpecialtyMappingList;
  }
@JsonIgnore
public List<RefferalCurrentStatus> getRefferalCurrentStatuList() {
	return refferalCurrentStatuList;
}

public void setRefferalCurrentStatuList(
		List<RefferalCurrentStatus> refferalCurrentStatuList) {
	this.refferalCurrentStatuList = refferalCurrentStatuList;
}

}
