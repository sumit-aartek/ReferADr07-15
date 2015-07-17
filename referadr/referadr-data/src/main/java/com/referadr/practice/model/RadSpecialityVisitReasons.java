package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RAD_SPECIALITY_VISIT_REASONS")

public class RadSpecialityVisitReasons  extends AbstractEntity implements Serializable{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "SPL_VISIT_RSN_ID")
	  private Integer splVisitRsnId;
	 
	  @Column(name = "VISIT_REASON")
	  private String visitReason;
	  

	  @Column(name = "PROV_SPL_ID")
	  private Integer provSplId;


	public Integer getSplVisitRsnId() {
		return splVisitRsnId;
	}


	public void setSplVisitRsnId(Integer splVisitRsnId) {
		this.splVisitRsnId = splVisitRsnId;
	}


	public String getVisitReason() {
		return visitReason;
	}


	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}


	public Integer getProvSplId() {
		return provSplId;
	}


	public void setProvSplId(Integer provSplId) {
		this.provSplId = provSplId;
	}


	
	  

}
