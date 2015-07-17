package com.referadr.practice.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "rad_provider_info")
public class ProviderInfo extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Provider_ID")
	private Integer providerId;
	
/*	@Column(name = "PROVIDER_CD_PRVROLE_ID")
	private Integer providerCdPrvroleId;*/
	
	@Column(name = "Provider_First_Name")
	private String providerFirstName;

	/*public Integer getProviderCdPrvroleId() {
		return providerCdPrvroleId;
	}

	public void setProviderCdPrvroleId(Integer providerCdPrvroleId) {
		this.providerCdPrvroleId = providerCdPrvroleId;
	}*/

	@Column(name = "Provider_Last_Name")
	private String providerLastName;

	@Column(name = "Provider_PWD")
	private String providerPwd;

	@Column(name = "PROVIDER_PHONE")
	private String providerPhone;

	@Column(name = "Provider_Email")
	private String providerEmail;

	@Column(name = "Provider_Pic_Link")
	private String providerPicLink;

	@Column(name = "Provider_NPI_Num")
	private Integer providerNpiNum;

	@Column(name = "Provider_Notes")
	private String providerNotes;

	@Column(name = "PROVIDER_PATIENT_CAT")
	private String practicePatientCat;

	@Column(name = "PROVIDER_GENDER")
	private String providerGender;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROV_LANG_MAP_ID", referencedColumnName = "Provider_ID",updatable=false)
	private List<RadProvLanguageMapping> radProvLanguageMapping;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROV_PUB_ID", referencedColumnName = "Provider_ID", updatable = false)
	private List<RadProviderPublication> radProviderPublications;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROV_MEM_ID", referencedColumnName = "Provider_ID", updatable = false)
	private List<RadProviderMembership> radProviderMemberships;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "REF_PROVIDER_ID", referencedColumnName = "Provider_ID", updatable = false)
	private List<Referral_Provider_Action> referral_Provider_ActionList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Provider_ID", updatable = false,nullable=true)
	private List<PatientReferralInfo> patientReferralInfoList;
	
	@ManyToOne
	@JoinColumn(name = "PROVIDER_PRAC_ID", referencedColumnName = "Practice_ID" ,nullable=true )
	private PracticeInfo practiceInfo;

	@OneToOne
	@JoinColumn(name = "PROVIDER_CD_PRVROLE_ID", referencedColumnName = "CODE_ID")
	private RadCodes radCodes;
	
	
	/*@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "PROVIDER_ID")
	private List<RadProvBoardCertMapping> redProvBoardCertMappingList;*/
	@Transient
	private String boardCertifications;
	@Transient
	private String radProviderSuffixes;
	@Transient
	private String radProvSpleatiy;
	@Transient
	private String radProvEduction;
	@Transient
	private String radProvHospital;
	@Transient
	private String radprovLanguage;
	@Transient
	private String radprovMember;
	@Transient
	private Integer providerCdPrvroleId;
	

	public Integer getProviderCdPrvroleId() {
		return providerCdPrvroleId;
	}

	public void setProviderCdPrvroleId(Integer providerCdPrvroleId) {
		this.providerCdPrvroleId = providerCdPrvroleId;
	}

	@Transient
	private String radprovPublication;
	public String getRadprovMember() {
		return radprovMember;
	}

	public void setRadprovMember(String radprovMember) {
		this.radprovMember = radprovMember;
	}

	public String getRadprovPublication() {
		return radprovPublication;
	}

	public void setRadprovPublication(String radprovPublication) {
		this.radprovPublication = radprovPublication;
	}

	@Transient
	private String radVisitReasons;
	
	@Transient
	private String imgPath;

public List<RadProviderMembership> getRadProviderMemberships() {
	return radProviderMemberships;
}

public void setRadProviderMemberships(
		List<RadProviderMembership> radProviderMemberships) {
	this.radProviderMemberships = radProviderMemberships;
}

public List<RadProviderPublication> getRadProviderPublications() {
	return radProviderPublications;
}

public void setRadProviderPublications(
		List<RadProviderPublication> radProviderPublications) {
	this.radProviderPublications = radProviderPublications;
}

public List<RadProvLanguageMapping> getRadProvLanguageMapping() {
	return radProvLanguageMapping;
}

public void setRadProvLanguageMapping(
		List<RadProvLanguageMapping> radProvLanguageMapping) {
	this.radProvLanguageMapping = radProvLanguageMapping;
}

	public String getPracticePatientCat() {
		return practicePatientCat;
	}

	public void setPracticePatientCat(String practicePatientCat) {
		this.practicePatientCat = practicePatientCat;
	}

	public RadCodes getRadCodes() {
		return radCodes;
	}

	public void setRadCodes(RadCodes radCodes) {
		this.radCodes = radCodes;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getProviderFirstName() {
		return providerFirstName;
	}

	public void setProviderFirstName(String providerFirstName) {
		this.providerFirstName = providerFirstName;
	}

	public String getProviderLastName() {
		return providerLastName;
	}

	public void setProviderLastName(String providerLastName) {
		this.providerLastName = providerLastName;
	}

	public String getProviderPwd() {
		return providerPwd;
	}

	public void setProviderPwd(String providerPwd) {
		this.providerPwd = providerPwd;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderPicLink() {
		return providerPicLink;
	}

	public void setProviderPicLink(String providerPicLink) {
		this.providerPicLink = providerPicLink;
	}

	public Integer getProviderNpiNum() {
		return providerNpiNum;
	}

	public void setProviderNpiNum(Integer providerNpiNum) {
		this.providerNpiNum = providerNpiNum;
	}

	public String getProviderNotes() {
		return providerNotes;
	}

	public void setProviderNotes(String providerNotes) {
		this.providerNotes = providerNotes;
	}

	/**
	 * @return the practiceInfo
	 */
	public PracticeInfo getPracticeInfo() {
		return practiceInfo;
	}

	/**
	 * @param practiceInfo
	 *            the practiceInfo to set
	 */
	public void setPracticeInfo(PracticeInfo practiceInfo) {
		this.practiceInfo = practiceInfo;
	}

	/**
	 * @return the referral_Provider_ActionList
	 */
	@JsonIgnore
	public List<Referral_Provider_Action> getReferral_Provider_ActionList() {
		return referral_Provider_ActionList;
	}

	/**
	 * @param referral_Provider_ActionList
	 *            the referral_Provider_ActionList to set
	 */
	public void setReferral_Provider_ActionList(
			List<Referral_Provider_Action> referral_Provider_ActionList) {
		this.referral_Provider_ActionList = referral_Provider_ActionList;
	}

	/**
	 * @return the patientReferralInfoList
	 */
	@JsonIgnore
	public List<PatientReferralInfo> getPatientReferralInfoList() {
		return patientReferralInfoList;
	}

	/**
	 * @param patientReferralInfoList
	 *            the patientReferralInfoList to set
	 */
	public void setPatientReferralInfoList(
			List<PatientReferralInfo> patientReferralInfoList) {
		this.patientReferralInfoList = patientReferralInfoList;
	}

	public String getProviderPhone() {
		return providerPhone;
	}

	public void setProviderPhone(String providerPhone) {
		this.providerPhone = providerPhone;
	}

	public String getProviderGender() {
		return providerGender;
	}

	public void setProviderGender(String providerGender) {
		this.providerGender = providerGender;
	}

	/*public List<RadProvBoardCertMapping> getRedProvBoardCertMappingList() {
		return redProvBoardCertMappingList;
	}

	public void setRedProvBoardCertMappingList(
			List<RadProvBoardCertMapping> redProvBoardCertMappingList) {
		this.redProvBoardCertMappingList = redProvBoardCertMappingList;
	}*/

	public String getBoardCertifications() {
		return boardCertifications;
	}

	public void setBoardCertifications(String boardCertifications) {
		this.boardCertifications = boardCertifications;
	}

	public String getRadProviderSuffixes() {
		return radProviderSuffixes;
	}

	public void setRadProviderSuffixes(String radProviderSuffixes) {
		this.radProviderSuffixes = radProviderSuffixes;
	}

	public String getRadProvSpleatiy() {
		return radProvSpleatiy;
	}

	public void setRadProvSpleatiy(String radProvSpleatiy) {
		this.radProvSpleatiy = radProvSpleatiy;
	}

	public String getRadProvEduction() {
		return radProvEduction;
	}

	public void setRadProvEduction(String radProvEduction) {
		this.radProvEduction = radProvEduction;
	}

	public String getRadProvHospital() {
		return radProvHospital;
	}

	public void setRadProvHospital(String radProvHospital) {
		this.radProvHospital = radProvHospital;
	}

	public String getRadprovLanguage() {
		return radprovLanguage;
	}

	public void setRadprovLanguage(String radprovLanguage) {
		this.radprovLanguage = radprovLanguage;
	}

	public String getRadVisitReasons() {
		return radVisitReasons;
	}

	public void setRadVisitReasons(String radVisitReasons) {
		this.radVisitReasons = radVisitReasons;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
}
