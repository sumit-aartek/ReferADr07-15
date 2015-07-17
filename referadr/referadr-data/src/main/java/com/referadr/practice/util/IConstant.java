package com.referadr.practice.util;

public interface IConstant {
	static final String docImagePath="E:/images";
	//Amazon S3 Bucket Folder Names
	static final String FILE_PAT_INS = "PATIENT_INSURANCE";
	static final String FILE_PAT_REF= "PATIENT_REFERRAL";
	static final String FILE_REF_CH_ACTION= "PATIENT_REFERRAL_CH";
	static final String FILE_REF_PROV_ACTION= "PATIENT_REFERRAL_PROVIDER";
	static final String FILE_PROVIDER_INFO="PRACTICE_INFO";
	static final String SITE_URL="https://s3.amazonaws.com/referadr/";
	//static final String PASSWORD_RECOVERY_URL="http://localhost:8080/referadr-web/recoverPassword.do";
	static final String PASSWORD_RECOVERY_URL="http://referadrqa-env.elasticbeanstalk.com/recoverPassword.do";
	//RAD_CODES related to DOCUMENT ATTACHMENTS
	static final String DOC_ATTACH_TYPE= "DOC_ATTACH_TYPE";
	static final String DOC_ATTACH_PATINS= "PATIENT_INS";
	static final String DOC_ATTACH_REF= "REFERRAL";
	static final String DOC_ATTACH_PAT= "PATIENT";
	static final String DOC_ATTACH_CH_ACTION= "REF_CH_ATTACHMENTS";
	static final String DOC_ATTACH_PROV_ACTION= "REF_PROV_ATTACHMENTS";
	static final String DOC_ATTACH_PROVIDER= "PROVIDER";
	
	static final String DATE_FORMAT_FILE= "yyyyMMddHHmmss";
	
	//its for QA
	static final String FROM_MAIL= " freekwent@mkonnekt.com";
	static final String TO_MAIL= " freekwent@mkonnekt.com";
	
	//Its for production
//	static final String FROM_MAIL= "referadr@mkonnekt.com";
//	static final String TO_MAIL= "referadr@mkonnekt.com";
	

	

}
