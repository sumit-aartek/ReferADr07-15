package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rad_provider_speciality")

public class RedProviderSpeciality  extends AbstractEntity implements Serializable {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_SPL_ID")
	  private Integer provSplId;
	  
	  @Column(name = "PROV_SPL_DESC")
	  private String provSplDesc;
	  
	  @Column(name = "PROV_SPL_STATUS")
	  private String provSplStatus;

	
	public Integer getProvSplId() {
		return provSplId;
	}

	public void setProvSplId(Integer provSplId) {
		this.provSplId = provSplId;
	}

	public String getProvSplDesc() {
		return provSplDesc;
	}

	public void setProvSplDesc(String provSplDesc) {
		this.provSplDesc = provSplDesc;
	}

	public String getProvSplStatus() {
		return provSplStatus;
	}

	public void setProvSplStatus(String provSplStatus) {
		this.provSplStatus = provSplStatus;
	}
	  
}
