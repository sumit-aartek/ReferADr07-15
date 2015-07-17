package com.referadr.practice.model;

import java.util.ArrayList;

public class ReferralProviderActionVO implements Comparable<ReferralProviderActionVO> {
	
	private int refProActionId;
	private String refReason;
	private String refDigCode;
	private String refNotes;
	private String providerName;
	private String practiceName;
	private String creationDate;
	private boolean flag;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	ArrayList<Document> docList;
	public ArrayList<Document> getDocList() {
		return docList;
	}
	public void setDocList(ArrayList<Document> docList) {
		this.docList = docList;
	}
	public String getRefReason() {
		return refReason;
	}
	public void setRefReason(String refReason) {
		this.refReason = refReason;
	}
	public String getRefDigCode() {
		return refDigCode;
	}
	public void setRefDigCode(String refDigCode) {
		this.refDigCode = refDigCode;
	}
	public String getRefNotes() {
		return refNotes;
	}
	public void setRefNotes(String refNotes) {
		this.refNotes = refNotes;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getRefProActionId() {
		return refProActionId;
	}
	public void setRefProActionId(int refProActionId) {
		this.refProActionId = refProActionId;
	}
	
public int compareTo(ReferralProviderActionVO referralProviderActionVO) {
		
		return referralProviderActionVO.creationDate.compareTo(creationDate);
	}
	
}
