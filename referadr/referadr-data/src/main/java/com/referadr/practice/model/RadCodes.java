package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rad_codes")
public class RadCodes extends AbstractEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODE_ID")
	private Integer codeId;
	
	 @Column(name = "CODE_TYPE")
	 private String codeType;
	 
	 @Column(name = "CODE_VALUE")
	 private String codeValue;
	 
	 @Column(name = "CODE_DESC")
	 private String codeDesc;

	 @Column(name = "CODE_ACTIVE")
	 private String codeActive;
	 
	 @Column(name = "CODE_SORT")
	 private String codeSort;

	 @Column(name = "CODE_DB_VALUE")
	 private String codeDbValue;

	public Integer getCodeId() {
		return codeId;
	}

	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodeActive() {
		return codeActive;
	}

	public void setCodeActive(String codeActive) {
		this.codeActive = codeActive;
	}

	public String getCodeSort() {
		return codeSort;
	}

	public void setCodeSort(String codeSort) {
		this.codeSort = codeSort;
	}

	public String getCodeDbValue() {
		return codeDbValue;
	}

	public void setCodeDbValue(String codeDbValue) {
		this.codeDbValue = codeDbValue;
	}

}
