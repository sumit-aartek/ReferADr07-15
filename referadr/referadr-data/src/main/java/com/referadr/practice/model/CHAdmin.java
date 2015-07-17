package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CH_Admin")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)
public class CHAdmin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CH_Admin_ID")
	private int chAdminId;
	
	@Column(name="CH_ID")
	private int chId;
	
	@Column(name="CH_Admin_Role_First_Name")
	private String firstName;
	
	@Column(name="CH_Admin_Role_Last_Name")
	private String lastName;
	
	@Column(name="CH_Admin_Role_PWD")
	private String password;
	
	@Column(name="CH_Admin_Role_Contact")
	private String contact;
	
	@Column(name="CH_Admin_Role_Email")
	private String emaiId;
	
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
		System.out.println("firstName--->"+firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		System.out.println("lastName--->"+lastName);
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("password--->"+password);
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		System.out.println("contact--->"+contact);
		this.contact = contact;
	}

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		System.out.println("emaiId--->"+emaiId);
		this.emaiId = emaiId;
	}

}
