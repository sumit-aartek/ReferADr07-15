package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RAD_LANGUAGE")
public class RadLanguage  implements Serializable {
	
	  private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "LANGUAGE_ID")
	  private Integer languageId;
	  
	  @Column(name = "LANGUAGE_DESC")
	  private String languageDesc;

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getLanguageDesc() {
		return languageDesc;
	}

	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

	
}
