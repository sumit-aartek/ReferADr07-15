package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "referpatient")
public class ReferPatient implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "REFERPATIENT_ID")
	  private Integer referPatientId;

	  @Column(name = "DATE")
	  private String date;
	  
	  @Column(name = "ADDRESS1")
	  private String address1;

	  public String getReferingPhysician() {
		return referingPhysician;
	}

	public void setReferingPhysician(String referingPhysician) {
		this.referingPhysician = referingPhysician;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "REFERING_PHYSICIAN")
	  private String referingPhysician;
	  
	  @Column(name = "ADDRESS2")
	  private String address2;

	  @Column(name = "CITY")
	  private String city;
	  
	  @Column(name = "STATE")
	  private String state;
	  
	  @Column(name = "ZIP")
	  private String zip;
	  
	  @Column(name = "PHONE")
	  private String phone;
	  
	  @Column(name = "FAX")
	  private String fax;
	  
	  
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Integer getReferPatientId() {
		return referPatientId;
	}

	public void setReferPatientId(Integer referPatientId) {
		this.referPatientId = referPatientId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
