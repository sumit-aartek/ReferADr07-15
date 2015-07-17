package com.referadr.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CH_SP_Mapping")
public class ChSpMapping {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ch_sp_mapping_id")
	private int ch_sp_mapping_id;
	public int getCh_sp_mapping_id() {
		return ch_sp_mapping_id;
	}
	public void setCh_sp_mapping_id(int ch_sp_mapping_id) {
		this.ch_sp_mapping_id = ch_sp_mapping_id;
	}
	@Column(name="ch_id")
	private int ch_id;
	@Column(name="Pratice_Spl_ID")
	private int Pratice_Spl_ID;
	@Transient
	private String chSplStatus;
	public String getChSplStatus() {
		return chSplStatus;
	}
	public void setChSplStatus(String chSplStatus) {
		this.chSplStatus = chSplStatus;
	}
	public int getCh_id() {
		return ch_id;
	}
	public void setCh_id(int ch_id) {
		this.ch_id = ch_id;
	}
	public int getPratice_Spl_ID() {
		return Pratice_Spl_ID;
	}
	public void setPratice_Spl_ID(int pratice_Spl_ID) {
		Pratice_Spl_ID = pratice_Spl_ID;
	}
	

}