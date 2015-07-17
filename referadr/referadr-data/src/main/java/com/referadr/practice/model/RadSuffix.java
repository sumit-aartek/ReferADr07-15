package com.referadr.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RAD_SUFFIX")
public class RadSuffix {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUFFIX_ID")
	private Integer suffixId;
	
	 @Column(name = "SUFFIX_VALUE")
	 private String suffixValue;
	 
	 @Column(name = "SUFFIX_DESC")
	 private String suffixDesc;

	public Integer getSuffixId() {
		return suffixId;
	}

	public void setSuffixId(Integer suffixId) {
		this.suffixId = suffixId;
	}

	public String getSuffixValue() {
		return suffixValue;
	}

	public void setSuffixValue(String suffixValue) {
		this.suffixValue = suffixValue;
	}

	public String getSuffixDesc() {
		return suffixDesc;
	}

	public void setSuffixDesc(String suffixDesc) {
		this.suffixDesc = suffixDesc;
	}
	 
	

}
