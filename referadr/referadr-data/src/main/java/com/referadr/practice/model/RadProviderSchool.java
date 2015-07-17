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
@Table(name = "rad_provider_school")

public class RadProviderSchool extends AbstractEntity implements Serializable {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_SCHL_ID")
	  private Integer provSchlId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	  @Column(name = "PROV_SCHOOL")
	  private String provSchool;

	public Integer getProvSchlId() {
		return provSchlId;
	}

	public void setProvSchlId(Integer provSchlId) {
		this.provSchlId = provSchlId;
	}

	
	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	public String getProvSchool() {
		return provSchool;
	}

	public void setProvSchool(String provSchool) {
		this.provSchool = provSchool;
	}
	  
	
}
