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
@Table(name = "rad_provider_publication")

public class RadProviderPublication  extends AbstractEntity implements Serializable  {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_PUB_ID")
	  private Integer provPubId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	  @Column(name = "PROV_PUBLICATION")
	  private String provPublication;

	public Integer getProvPubId() {
		return provPubId;
	}

	public void setProvPubId(Integer provPubId) {
		this.provPubId = provPubId;
	}

	
	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	public String getProvPublication() {
		return provPublication;
	}

	public void setProvPublication(String provPublication) {
		this.provPublication = provPublication;
	}
	  
	  

}
