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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_location")
public class RadLocation extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  public RadLocation()
  {
	  
  }
  
  public RadLocation(int locId)
  {
	  this.locId=locId;
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "LOC_ID")
  private Integer locId;

  @Column(name = "LOC_WEBSITE")
  private String locWebsite;

  @Column(name = "LOC_FAX")
  private String locFax;

  @Column(name = "LOC_PHONE")
  private String locPhone;

  @Column(name = "LOC_ZIP")
  private String locZip;

  @Column(name = "LOC_CITY")
  private String locCity;

  @Column(name = "LOC_ADDRESS2")
  private String locAddress2;

  @Column(name = "LOC_ADDRESS1")
  private String locAddress1;

  @Column(name = "Practice_State")
  private String locState;

  @ManyToOne
  @JoinColumn(name = "LOC_STATE_ID",referencedColumnName="STATE_ID")
  private RedState redState;

  @OneToOne
  @JoinColumn(name = "LOC_ID")
  private PracticeLocations practiceLocations;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
  @JoinColumn(name = "CH_LOC_ID", referencedColumnName="LOC_ID",updatable=false)
  private List<CHInfo> chInfoList;
  
  @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
  @JoinColumn(name = "LOC_ID")
  private PatientInfo patientInfo  ;

  public Integer getLocId() {
    return locId;
  }

  public void setLocId(Integer locId) {
    this.locId = locId;
  }
  
  public RedState getRedState() {
    return redState;
  }

  public void setRedState(RedState redState) {
    this.redState = redState;
  }
  @JsonIgnore
  public PracticeLocations getPracticeLocations() {
    return practiceLocations;
  }

  public void setPracticeLocations(PracticeLocations practiceLocations) {
    this.practiceLocations = practiceLocations;
  }

  public String getLocWebsite() {
    return locWebsite;
  }

  public void setLocWebsite(String locWebsite) {
    this.locWebsite = locWebsite;
  }

  public String getLocFax() {
    return locFax;
  }

  public void setLocFax(String locFax) {
    this.locFax = locFax;
  }

  public String getLocPhone() {
    return locPhone;
  }

  public void setLocPhone(String locPhone) {
    this.locPhone = locPhone;
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

  public String getLocState() {
    return locState;
  }

  public void setLocState(String locState) {
    this.locState = locState;
  }
  @JsonIgnore
  public List<CHInfo> getChInfoList() {
    return chInfoList;
  }

  public void setChInfoList(List<CHInfo> chInfoList) {
    this.chInfoList = chInfoList;
  }
  @JsonIgnore
  public PatientInfo getPatientInfo() {
    return patientInfo;
  }

  public void setPatientInfo(PatientInfo patientInfo) {
    this.patientInfo = patientInfo;
  }

}
