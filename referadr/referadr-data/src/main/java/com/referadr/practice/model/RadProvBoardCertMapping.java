package com.referadr.practice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "rad_prov_board_cert_mapping")
public class RadProvBoardCertMapping extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROV_BRDCERT_MAP_ID")
	private Integer provBrdcertMapId;

	/*@ManyToOne
	@JoinColumn(name = "PROVIDER_ID",updatable = false)
	private ProviderInfo provider;*/

	@ManyToOne
	@JoinColumn(name = "BOARD_CERT_ID", updatable = false)
	private RadBoardCertifications radBoardCertifications;

	/*public ProviderInfo getProvider() {
		return provider;
	}

	public void setProvider(ProviderInfo provider) {
		this.provider = provider;
	}*/

	public Integer getProvBrdcertMapId() {
		return provBrdcertMapId;
	}

	public void setProvBrdcertMapId(Integer provBrdcertMapId) {
		this.provBrdcertMapId = provBrdcertMapId;
	}

	

	public RadBoardCertifications getRadBoardCertifications() {
		return radBoardCertifications;
	}

	public void setRadBoardCertifications(
			RadBoardCertifications radBoardCertifications) {
		this.radBoardCertifications = radBoardCertifications;
	}

	

	

}
