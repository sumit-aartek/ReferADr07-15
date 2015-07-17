package com.referadr.practice.model;

import java.util.List;

public class CHSPMappingBean {
	
	private int chId;
	private List<String> sp;
	private List<Integer> spId;
	private String serviceName;
	private String service;
	public int getChId() {
		return chId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public List<Integer> getSpId() {
		return spId;
	}
	public void setSpId(List<Integer> spId) {
		this.spId = spId;
	}
	public void setChId(int chId) {
		this.chId = chId;
	}
	public List<String> getSp() {
		return sp;
	}
	public void setSp(List<String> sp) {
		this.sp = sp;
	}

}
