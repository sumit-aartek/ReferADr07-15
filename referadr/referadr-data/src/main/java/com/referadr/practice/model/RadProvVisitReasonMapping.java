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
@Table(name = "rad_prov_visit_reason_mapping")

public class RadProvVisitReasonMapping extends AbstractEntity implements Serializable{

	  public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_VST_RSN_MAP_ID")
	  private Integer provVstRsnMapId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	  @Column(name = "SPL_VISIT_RSN_ID")
	  private Integer splVisitRsnId;

	public Integer getProvVstRsnMapId() {
		return provVstRsnMapId;
	}

	public void setProvVstRsnMapId(Integer provVstRsnMapId) {
		this.provVstRsnMapId = provVstRsnMapId;
	}

	public Integer getSplVisitRsnId() {
		return splVisitRsnId;
	}

	public void setSplVisitRsnId(Integer splVisitRsnId) {
		this.splVisitRsnId = splVisitRsnId;
	}

	
	  
	  
	
	 
}
