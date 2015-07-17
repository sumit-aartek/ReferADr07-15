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
@Table(name = "rad_practice_spl_mapping")
public class PracticeSpecialtyMapping extends AbstractEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "PRAC_SPL_MAP_ID")
  private Integer pracSplMapId;

  @ManyToOne
  @JoinColumn(name = "PRACTICE_ID")
  private PracticeInfo practiceInfo;

  @ManyToOne
  @JoinColumn(name = "PRACTICE_SPL_ID", referencedColumnName = "PRAC_SPL_ID")
  private PracticeSpecialty practiceSpecialty;

  @Column(name = "PRACTICE_SPL_STATUS")
  private Character practiceSplStatus;

  public Integer getPracSplMapId() {
    return pracSplMapId;
  }

  public void setPracSplMapId(Integer pracSplMapId) {
    this.pracSplMapId = pracSplMapId;
  }

  public PracticeInfo getPracticeInfo() {
    return practiceInfo;
  }

  public void setPracticeInfo(PracticeInfo practiceInfo) {
    this.practiceInfo = practiceInfo;
  }
 @JsonIgnore
  public PracticeSpecialty getPracticeSpecialty() {
    return practiceSpecialty;
  }

  public void setPracticeSpecialty(PracticeSpecialty practiceSpecialty) {
    this.practiceSpecialty = practiceSpecialty;
  }

  public Character getPracticeSplStatus() {
    return practiceSplStatus;
  }

  public void setPracticeSplStatus(Character practiceSplStatus) {
    this.practiceSplStatus = practiceSplStatus;
  }

}
