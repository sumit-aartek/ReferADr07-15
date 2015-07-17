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
@Table(name = "rad_ch_practice_mapping")
public class ChInfoPracticeMapping extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CH_PRAC_MAP_ID")
  private Integer chPracMapId;

  @ManyToOne
  @JoinColumn(name = "CH_ID")
  private CHInfo chInfo;

  @ManyToOne
  @JoinColumn(name = "PRACTICE_ID")
  private PracticeInfo practiceInfo;

  @Column(name = "CH_PRAC_STATUS")
  private Character chPracStatus;

  public Integer getChPracMapId() {
    return chPracMapId;
  }

  public void setChPracMapId(Integer chPracMapId) {
    this.chPracMapId = chPracMapId;
  }

  public CHInfo getChInfo() {
    return chInfo;
  }

  public void setChInfo(CHInfo chInfo) {
    this.chInfo = chInfo;
  }

  public PracticeInfo getPracticeInfo() {
    return practiceInfo;
  }

  public void setPracticeInfo(PracticeInfo practiceInfo) {
    this.practiceInfo = practiceInfo;
  }

  public Character getChPracStatus() {
    return chPracStatus;
  }

  public void setChPracStatus(Character chPracStatus) {
    this.chPracStatus = chPracStatus;
  }

}
