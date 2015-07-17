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
@Table(name = "rad_provider_hospital")

public class RadProviderHospital extends AbstractEntity implements Serializable {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_HOSP_ID")
	  private Integer provHospId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	  @Column(name = "PROVIDER_HOSP_NAME")
	  private String providerHospName;

		
	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	public Integer getProvHospId() {
		return provHospId;
	}

	public void setProvHospId(Integer provHospId) {
		this.provHospId = provHospId;
	}

	public String getProviderHospName() {
		return providerHospName;
	}

	public void setProviderHospName(String providerHospName) {
		this.providerHospName = providerHospName;
	}
	
}
