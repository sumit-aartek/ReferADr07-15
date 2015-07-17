package com.referadr.practice.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name = "RAD_BOARD_CERTIFICATIONS")

public class RadBoardCertifications  implements Serializable {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "BOARD_CERT_ID")
	  private Integer boardCertId;
	  
	  
	 
	  @Column(name = "BOARD_CERT_DESC")
	  private String boardCertDesc;
	  
	  @LazyCollection(LazyCollectionOption.FALSE)
	  @OneToMany
	  @JoinColumn(name = "PROVIDER_ID",updatable=false ,insertable=true)
	  private List<RadProvBoardCertMapping>redProvBoardCertMappingList ;
	   
	  
	public Integer getBoardCertId() {
		return boardCertId;
	}

	public void setBoardCertId(Integer boardCertId) {
		this.boardCertId = boardCertId;
	}

	public String getBoardCertDesc() {
		return boardCertDesc;
	}

	public void setBoardCertDesc(String boardCertDesc) {
		this.boardCertDesc = boardCertDesc;
	}

	public List<RadProvBoardCertMapping> getRedProvBoardCertMappingList() {
		return redProvBoardCertMappingList;
	}

	public void setRedProvBoardCertMappingList(
			List<RadProvBoardCertMapping> redProvBoardCertMappingList) {
		this.redProvBoardCertMappingList = redProvBoardCertMappingList;
	}

	

	/*public List<RadBoardCertifications> getRadBoardCertification() {
		return radBoardCertification;
	}

	public void setRadBoardCertification(
			List<RadBoardCertifications> radBoardCertification) {
		this.radBoardCertification = radBoardCertification;
	}
*/
	
	  

}
