package com.referadr.practice.model;

public class PateintInfoVO  implements Comparable<PateintInfoVO>{
	
	private String proNotes;
	private String pateintFirstName;
	private String pateintLastName;
	private String practiceName;
	private int refId;
	private String creationDate;
	private int patientId;
	private int providerId;
	private int currentStatusId;
	private int provRefActionId;
	public String getPateintLastName() {
		return pateintLastName;
	}
	public void setPateintLastName(String pateintLastName) {
		this.pateintLastName = pateintLastName;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getProvRefActionId() {
		return provRefActionId;
	}
	public void setProvRefActionId(int provRefActionId) {
		this.provRefActionId = provRefActionId;
	}
	public int getCurrentStatusId() {
		return currentStatusId;
	}
	public void setCurrentStatusId(int currentStatusId) {
		this.currentStatusId = currentStatusId;
	}
	public String getProNotes() {
		return proNotes;
	}
	public void setProNotes(String proNotes) {
		this.proNotes = proNotes;
	}
	public String getPateintFirstName() {
		return pateintFirstName;
	}
	public void setPateintFirstName(String pateintFirstName) {
		this.pateintFirstName = pateintFirstName;
	}
	public String getPracticeName() {
		return practiceName;
	}
	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	public int getRefId() {
		return refId;
	}
	public void setRefId(int refId) {
		this.refId = refId;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	 
public int compareTo(PateintInfoVO pateintInfoVO) {
        
        return pateintInfoVO.creationDate.compareTo(creationDate);
    }

}
