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

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "rad_ch_specality_mapping")
public class ChInfoSpecialtyMapping extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CH_SPL_MAP_ID")
  private Integer chSplMapId;

  @ManyToOne
  @JoinColumn(name = "CH_ID")
  private CHInfo chInfo;

  @ManyToOne
  @JoinColumn(name = "CH_PRACSPL_ID", referencedColumnName = "PRAC_SPL_ID")
  private PracticeSpecialty practiceSpecialty;

  @Column(name = "CH_SPL_STATUS")
  private Character chSplStatus;

  public Integer getChSplMapId() {
    return chSplMapId;
  }

  public void setChSplMapId(Integer chSplMapId) {
    this.chSplMapId = chSplMapId;
  }
  @JsonIgnore
  public CHInfo getChInfo() {
    return chInfo;
  }

  public void setChInfo(CHInfo chInfo) {
    this.chInfo = chInfo;
  }

  public PracticeSpecialty getPracticeSpecialty() {
    return practiceSpecialty;
  }

  public void setPracticeSpecialty(PracticeSpecialty practiceSpecialty) {
    this.practiceSpecialty = practiceSpecialty;
  }

  public Character getChSplStatus() {
    return chSplStatus;
  }

  public void setChSplStatus(Character chSplStatus) {
    this.chSplStatus = chSplStatus;
  }

}
