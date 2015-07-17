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
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_insurance_info")
public class InsuranceInfo extends AbstractEntity implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "INSURANCE_ID")
  private Integer insuranceId;

  @Column(name = "INSURANCE_COMPANY")
  private String insuranceCompany;

  @Column(name = "INSURANCE_PLAN")
  private String insurancePlan;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "PAT_INS_ID" ,referencedColumnName="INSURANCE_ID")
  private List<PatientInsuranceInfo> patientInsuranceInfoList;

  /**
   * @return the patientInsuranceInfoList
   */
  @JsonIgnore
  public List<PatientInsuranceInfo> getPatientInsuranceInfoList() {
    return patientInsuranceInfoList;
  }

  /**
   * @param patientInsuranceInfoList
   *          the patientInsuranceInfoList to set
   */
  public void setPatientInsuranceInfoList(List<PatientInsuranceInfo> patientInsuranceInfoList) {
    this.patientInsuranceInfoList = patientInsuranceInfoList;
  }

  public String getInsuranceCompany() {
	return insuranceCompany;
}

public void setInsuranceCompany(String insuranceCompany) {
	this.insuranceCompany = insuranceCompany;
}

public String getInsurancePlan() {
	return insurancePlan;
}

public void setInsurancePlan(String insurancePlan) {
	this.insurancePlan = insurancePlan;
}

public Integer getInsuranceId() {
    return insuranceId;
  }

  public void setInsuranceId(Integer insuranceId) {
    this.insuranceId = insuranceId;
  }


}
