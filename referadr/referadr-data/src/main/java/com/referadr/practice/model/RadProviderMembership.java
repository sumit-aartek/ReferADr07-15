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
@Table(name = "rad_provider_membership")

public class RadProviderMembership  extends AbstractEntity implements Serializable {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_MEM_ID")
	  private Integer provMemId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	  @Column(name = "PROV_MEMBERSHIP")
	  private String provMembership;

	public Integer getProvMemId() {
		return provMemId;
	}

	public void setProvMemId(Integer provMemId) {
		this.provMemId = provMemId;
	}

	
	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	public String getProvMembership() {
		return provMembership;
	}

	public void setProvMembership(String provMembership) {
		this.provMembership = provMembership;
	}
	  
	  

}
