package com.referadr.ch.model;

import java.util.List;

import com.referadr.practice.model.PracticeInfo;

public class CHPSPBean {
	
	private List<String> sp;
	private List<Integer> spId;
	private int practiceID;
	private String name;
	private String emailId;
	private int categoryId;
	private String category;
	
	public int getPracticeID() {
		return practiceID;
	}
	public void setPracticeID(int practiceID) {
		this.practiceID = practiceID;
	}
	public List<Integer> getSpId() 
	
	{
		return spId;
	}
	public void setSpId(List<Integer> spId) {
		this.spId = spId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public List<String> getSp() {
		return sp;
	}
	public void setSp(List<String> sp) {
		this.sp = sp;
	}
	

}
