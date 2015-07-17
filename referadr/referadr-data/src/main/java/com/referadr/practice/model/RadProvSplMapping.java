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
@Table(name = "rad_prov_spl_mapping")

public class RadProvSplMapping  extends AbstractEntity implements Serializable {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_SPL_MAP_ID")
	  private Integer provSplMapId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	 // @Column(name = "PROV_SPL_ID")
/*	  @ManyToOne
	  @JoinColumn(name = "PROV_SPL_ID", referencedColumnName = "PROV_SPL_ID")
	  private String provSplId;
*/	 
	  @Column(name = "PROV_SPL_STATUS")
	  private String provSplStatus;
	
	 
	public Integer getProvSplMapId() {
		return provSplMapId;
	}

	public void setProvSplMapId(Integer provSplMapId) {
		this.provSplMapId = provSplMapId;
	}

	public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}



	public String getProvSplStatus() {
		return provSplStatus;
	}

	public void setProvSplStatus(String provSplStatus) {
		this.provSplStatus = provSplStatus;
	}


}
