package com.referadr.ch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class CHAdmin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int chAdminId;
	private int chId;
	private String firstName;
	private String lastName;
	private String password;
	private String contact;
	private String emaiId;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updatedDate;

	public CHAdmin()
	{
	
	}
	
	public CHAdmin(int chId)
	{
		this.chId=chId;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getChId() {
		return chId;
	}

	public void setChId(int chId) {
		System.out.println("chId-->"+chId);
		this.chId = chId;
	}



	public int getChAdminId() {
		return chAdminId;
	}

	public void setChAdminId(int chAdminId) {
		this.chAdminId = chAdminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		
		this.contact = contact;
	}

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		
		this.emaiId = emaiId;
	}

}
