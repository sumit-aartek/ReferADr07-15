package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rad_practice_locations")
public class PracticeLocations extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PRACTICE_LOC_ID")
  private Integer precticeLocationId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "LOC_ID")
  private RadLocation radLocation;

  @OneToOne
  @JoinColumn(name = "Practice_ID",updatable=false)
  private PracticeInfo practiceInfo;

  
  public Integer getPrecticeLocationId() {
    return precticeLocationId;
  }

  /**
   * @param precticeLocationId
   *          the precticeLocationId to set
   */
  public void setPrecticeLocationId(Integer precticeLocationId) {
    this.precticeLocationId = precticeLocationId;
  }

  public PracticeInfo getPracticeInfo() {
    return practiceInfo;
  }

  public void setPracticeInfo(PracticeInfo practiceInfo) {
    this.practiceInfo = practiceInfo;
  }

  public RadLocation getRadLocation() {
    return radLocation;
  }

  public void setRadLocation(RadLocation radLocation) {
    this.radLocation = radLocation;
  }

}
