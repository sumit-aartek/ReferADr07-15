package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "rad_prov_language_mapping")

public class RadProvLanguageMapping  extends AbstractEntity implements Serializable {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "PROV_LANG_MAP_ID")
	  private Integer provLangMapId;
	 
	  @ManyToOne                  //18-02-2015
	  @JoinColumn(name = "PROVIDER_ID")
	  private ProviderInfo provider;
	 
	 // @Column(name = "PROV_SPL_ID")
/*	  @ManyToOne
	  @JoinColumn(name = "PROV_SPL_ID", referencedColumnName = "PROV_SPL_ID")
	  private String provSplId;
*/	 @OneToOne
     @JoinColumn(name = "LANGUAGE_ID")
	  private RadLanguage radLanguage;
	
	 	public RadLanguage getRadLanguage() {
	return radLanguage;
}

public void setRadLanguage(RadLanguage radLanguage) {
	this.radLanguage = radLanguage;
}

		public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}

	public Integer getProvLangMapId() {
		return provLangMapId;
	}

	public void setProvLangMapId(Integer provLangMapId) {
		this.provLangMapId = provLangMapId;
	}


}
