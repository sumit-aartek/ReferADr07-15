package com.referadr.practice.util;

/**
 * This class has the common code that can be used through out the application.
 */
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.referadr.practice.model.Document;
import com.referadr.practice.model.FileMeta;

public class CommonUtils {

	private static JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		CommonUtils.jdbcTemplateObject = new  JdbcTemplate(dataSource);
	}
	public static JdbcTemplate getJdbcTemplate()
	{
		return CommonUtils.jdbcTemplateObject;
			}
	/**
	 * Get the PatientId based on ReferralId
	 * @author Dileep
	 * @Date 03/03/2015
	 * @param refId
	 * @return
	 */
	public static int getPatientIdByReferralId(int refId) {
		int patId = 0;
		try {
			String fetchPatientIdSQL="select PATIENT_ID from rad_patient_referral_info where PATIENT_REFERRAL_ID = ?";
			Object[] params=new Object[]{refId};
			patId =  jdbcTemplateObject.queryForInt(fetchPatientIdSQL, params);
			
		} catch (Exception ex) {
			System.out.println("Exception while getting patientId: "+ex.getMessage());
		}
		return patId;
	}
	
	/**
	 * Get the Documents List based on RefProviderActionId
	 * @author Dileep
	 * @Date 03/03/2015
	 * @param refProvActionId
	 * @return
	 */
	
	
	public static ArrayList<Document> getDocumentListByRefProvActionId(int insinfoid) {
		ArrayList<Document> docList = new ArrayList<Document>();
		try {
			
			
				String fetchDocListSQL="select DOCOMENT_ID, DOCUMENT_NAME, DOCUMENT_PATH from attach_document where PAT_INS_INFO_ID = ?";
			
				
		
			docList =  (ArrayList<Document>) jdbcTemplateObject.query(fetchDocListSQL,docMapper, insinfoid);
		} catch (Exception ex) {
			System.out.println("Exception while getting document list: "+ex.getMessage());
		}
	
		return docList;
	}
	
	
	
	
	
	public static ArrayList<Document> getDocumentListByRefProvActionId(int refProvActionId,boolean flag) {
		ArrayList<Document> docList = new ArrayList<Document>();
		try {
			String fetchDocListSQL="";
			if(flag){
			 fetchDocListSQL="select DOCOMENT_ID, DOCUMENT_NAME, DOCUMENT_PATH from attach_document where REF_PROV_ACTION_ID = ?";
			}else{
				 fetchDocListSQL="select DOCOMENT_ID, DOCUMENT_NAME, DOCUMENT_PATH from attach_document where REF_CH_ACTION_ID = ?";
			}
			docList =  (ArrayList<Document>) jdbcTemplateObject.query(fetchDocListSQL,docMapper, refProvActionId);
		} catch (Exception ex) {
			System.out.println("Exception while getting document list: "+ex.getMessage());
		}
		return docList;
	}
	public static ArrayList<Document> getDocumentListByProviderId(int providerId) {
		ArrayList<Document> docList = new ArrayList<Document>();
		try {
			String fetchDocListSQL="select DOCOMENT_ID, DOCUMENT_NAME, DOCUMENT_PATH from attach_document where PROVIDER_ID = ?";
			docList =  (ArrayList<Document>) jdbcTemplateObject.query(fetchDocListSQL,docMapper, providerId);
		} catch (Exception ex) {
			System.out.println("Exception while getting document list: "+ex.getMessage());
		}
		return docList;
	}
	
	/**
	 * Get the documents list based on RefCHActionId
	 * @author Dileep
	 * @Date 03/03/2015
	 * @param refChActionId
	 * @return
	 */
	public static ArrayList<Document> getDocumentListByRefCHActionId(int refChActionId) {
		ArrayList<Document> docList = new ArrayList<Document>();
		try {
			String fetchDocListSQL="select DOCOMENT_ID, DOCUMENT_NAME, DOCUMENT_PATH from attach_document where REF_CH_ACTION_ID = ?";
			docList =  (ArrayList<Document>) jdbcTemplateObject.query(fetchDocListSQL,docMapper, refChActionId);
		} catch (Exception ex) {
			System.out.println("Exception while getting document list: "+ex.getMessage());
		}
		return docList;
	}
	
	//DocMapper
	private static ParameterizedRowMapper<Document> docMapper = new ParameterizedRowMapper<Document>() {
		public Document mapRow(ResultSet rs, int arg1)
				throws SQLException {
			
			Document document = new Document();
			document.setDocId(rs.getInt("DOCOMENT_ID"));
			document.setDocName(rs.getString("DOCUMENT_NAME"));
			document.setDocPath(rs.getString("DOCUMENT_PATH"));
			return document;
		}
	};
	
	/**
	 * Get the latest Id based on ReferralId.
	 * @author Dileep
	 * @Date 03/03/2015
	 * @param refId
	 * @return
	 */
	public static int getLatestRefProvActionIdByRefId(int refId) {
		
		int refProvActnId = 0;
		try {
			String fetchDocListSQL="select REF_PROV_ACTION_ID from rad_referral_provider_action " +
									"where REFERRAL_ID = ? " +
									"order by REF_PROV_ACTION_ID desc limit 1 ";
			Object[] params=new Object[]{refId};
			refProvActnId =  jdbcTemplateObject.queryForInt(fetchDocListSQL, params);
			
		} catch (Exception ex) {
			System.out.println("Exception while getting patientId: "+ex.getMessage());
		}
		return refProvActnId;
	}
	
	
	
public static int getLatestRefCHActionIdByRefId(int refId) {
		
		int refCHActnId = 0;
		try {
			String fetchDocListSQL="select REF_CH_ACTION_ID from rad_referral_ch_action " +
									"where REFERRAL_ID = ? " +
									"order by REF_CH_ACTION_ID desc limit 1 ";
			Object[] params=new Object[]{refId};
			refCHActnId =  jdbcTemplateObject.queryForInt(fetchDocListSQL, params);
			
		} catch (Exception ex) {
			System.out.println("Exception while getting patientId: "+ex.getMessage());
		}
		return refCHActnId;
	}
	
	/**
	 * This method will upload the documents to the Amazon S3 Server.
	 * Also inserts record to the database with fileName and filePath.
	 * @author Dileep
	 * @param fileList
	 * @param patientId
	 * @param referralId
	 * @param refProvActnId
	 * @param isProviderActn
	 */




public static void uploadAttachments(ArrayList<FileMeta> fileList, int patientId, int referralId, int refProvOrCHActnId, boolean isProviderActn,int insinfoid) {
	BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJTMFOQQTO6NVQVUA", "nZip7jHdQgBWQIajn+Bwi6zvDMGR9nkPik+xcbK0");
	AmazonS3 s3client = new AmazonS3Client(awsCreds);
	try {
		System.out.println("In Utils");
		System.out.println("In Utils insinfoid="+insinfoid);
		String insertDocAttacSQL = "INSERT INTO attach_document (DOCUMENT_NAME,DOCUMENT_PATH, PATIENT_ID, REFERRAL_ID, REF_CH_ACTION_ID, REF_PROV_ACTION_ID, DOCTYPE_CD_ID, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,PAT_INS_INFO_ID) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

		int[] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER, Types.VARCHAR, Types.DATE,Types.VARCHAR, Types.DATE,Types.INTEGER};
		Object[] params = null;
		int docCdTypeId = RADCodeUtils.getCodeId(IConstant.DOC_ATTACH_TYPE, IConstant.DOC_ATTACH_PROV_ACTION);
		
		if (fileList != null && fileList.size() > 0) {
			for (FileMeta fileMeta : fileList) {
				File newFile =new File(fileMeta.getFileName());
				System.out.println("Size: "+fileMeta.getFile().getSize());
				fileMeta.getFile().transferTo(newFile);
				System.out.println("File Converted..!!"+fileMeta.getFileKey());
				//If this is Provider Action
			
					params=new Object[] {fileMeta.getFileName(),fileMeta.getFilePath(),patientId,referralId,null,null,docCdTypeId,"SYSTEM",DateUtil.getCurrentDate(),"SYSTEM",DateUtil.getCurrentDate(),insinfoid};	
				
				
				int docId = jdbcTemplateObject.update(insertDocAttacSQL,params,types);
				System.out.println("docId: "+docId);
				if (docId > 0) {
					
					s3client.putObject(new PutObjectRequest(
							"referadr", fileMeta.getFilePath(), newFile));
				}
			}
		}
	} catch (AmazonServiceException ase) {
		System.out.println("File Not Uploaded Service Exception: "+ase.getErrorMessage());
	} catch (AmazonClientException ace) {
		System.out.println("File Not Uploaded Client Exception: "+ace.getMessage());
	} catch (Exception ex) {
		System.out.println("File Not Uploaded: "+ex.getMessage());
	}
}




	public static void uploadAttachments(ArrayList<FileMeta> fileList, int patientId, int referralId, int refProvOrCHActnId, boolean isProviderActn) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJTMFOQQTO6NVQVUA", "nZip7jHdQgBWQIajn+Bwi6zvDMGR9nkPik+xcbK0");
		AmazonS3 s3client = new AmazonS3Client(awsCreds);
		try {
			System.out.println("In Utils");
			String insertDocAttacSQL = "INSERT INTO attach_document (DOCUMENT_NAME,DOCUMENT_PATH, PATIENT_ID, REFERRAL_ID, REF_CH_ACTION_ID, REF_PROV_ACTION_ID, DOCTYPE_CD_ID, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

			int[] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER, Types.VARCHAR, Types.DATE,Types.VARCHAR, Types.DATE};
			Object[] params = null;
			int docCdTypeId = RADCodeUtils.getCodeId(IConstant.DOC_ATTACH_TYPE, IConstant.DOC_ATTACH_PROV_ACTION);
			
			if (fileList != null && fileList.size() > 0) {
				for (FileMeta fileMeta : fileList) {
					File newFile =new File(fileMeta.getFileName());
					System.out.println("Size: "+fileMeta.getFile().getSize());
					fileMeta.getFile().transferTo(newFile);
					System.out.println("File Converted..!!"+fileMeta.getFileKey());
					//If this is Provider Action
					if (isProviderActn) {
						params=new Object[] {fileMeta.getFileName(),fileMeta.getFilePath(),patientId,referralId,null,refProvOrCHActnId,docCdTypeId,"SYSTEM",DateUtil.getCurrentDate(),"SYSTEM",DateUtil.getCurrentDate()};	
					} else {
						//If this is CH Action, please pass chProviderActionId.
						params=new Object[] {fileMeta.getFileName(),fileMeta.getFilePath(),patientId,referralId,refProvOrCHActnId,null,docCdTypeId,"SYSTEM",DateUtil.getCurrentDate(),"SYSTEM",DateUtil.getCurrentDate()};
					}
					
					int docId = jdbcTemplateObject.update(insertDocAttacSQL,params,types);
					System.out.println("docId: "+docId);
					if (docId > 0) {
						
						s3client.putObject(new PutObjectRequest(
								"referadr", fileMeta.getFilePath(), newFile));
					}
				}
			}
		} catch (AmazonServiceException ase) {
			System.out.println("File Not Uploaded Service Exception: "+ase.getErrorMessage());
		} catch (AmazonClientException ace) {
			System.out.println("File Not Uploaded Client Exception: "+ace.getMessage());
		} catch (Exception ex) {
			System.out.println("File Not Uploaded: "+ex.getMessage());
		}
	}
	public static void uploadAttachments(ArrayList<FileMeta> fileList, int providerID, boolean isProviderActn) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJTMFOQQTO6NVQVUA", "nZip7jHdQgBWQIajn+Bwi6zvDMGR9nkPik+xcbK0");
		AmazonS3 s3client = new AmazonS3Client(awsCreds);
		ArrayList<Document> docList = new ArrayList<Document>();
		try {
			System.out.println("In Utils");
			String fetchDocListSQL="select DOCOMENT_ID, DOCUMENT_NAME, DOCUMENT_PATH from attach_document where PROVIDER_ID=?";
			docList =  (ArrayList<Document>) jdbcTemplateObject.query(fetchDocListSQL,docMapper, providerID);
			String insertDocAttacSQL=null;
			if(docList.size()==0){
				System.out.println("inside if for provider image");
			 insertDocAttacSQL = "INSERT INTO attach_document (DOCUMENT_NAME,DOCUMENT_PATH, DOCTYPE_CD_ID, CREATED_BY, CREATION_DATE, UPDATED_BY, UPDATED_DATE,PROVIDER_ID) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			}
			else
			{
				System.out.println("inside else for provider image");
				insertDocAttacSQL="UPDATE attach_document set DOCUMENT_NAME=?,DOCUMENT_PATH=?, DOCTYPE_CD_ID=?, CREATED_BY=?, CREATION_DATE=?, UPDATED_BY=?, UPDATED_DATE=? where PROVIDER_ID=?";
			}
                   
			int[] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.INTEGER, Types.VARCHAR, Types.DATE,Types.VARCHAR, Types.DATE,Types.INTEGER};
			Object[] params = null;
			int docCdTypeId = RADCodeUtils.getCodeId(IConstant.DOC_ATTACH_TYPE, IConstant.DOC_ATTACH_PROVIDER);
			
			if (fileList != null && fileList.size() > 0) {
				for (FileMeta fileMeta : fileList) {
					File newFile =new File(fileMeta.getFileName());
					System.out.println("Size: "+fileMeta.getFile().getSize());
					fileMeta.getFile().transferTo(newFile);
					System.out.println("File Converted..!!"+fileMeta.getFileKey());
					//If this is Provider Action
					if (isProviderActn) {
						params=new Object[] {fileMeta.getFileName(),fileMeta.getFilePath(),docCdTypeId,"SYSTEM",DateUtil.getCurrentDate(),"SYSTEM",DateUtil.getCurrentDate(),providerID};	
					} else {
						//If this is CH Action, please pass chProviderActionId.
						params=new Object[] {fileMeta.getFileName(),fileMeta.getFilePath(),docCdTypeId,"SYSTEM",DateUtil.getCurrentDate(),"SYSTEM",DateUtil.getCurrentDate(),providerID};
					}
					
					int docId = jdbcTemplateObject.update(insertDocAttacSQL,params,types);
					System.out.println("docId: "+docId);
					if (docId > 0) {
						
						s3client.putObject(new PutObjectRequest(
								"referadr", fileMeta.getFilePath(), newFile));
					}
				}
			}
		} catch (AmazonServiceException ase) {
			System.out.println("File Not Uploaded Service Exception: "+ase.getErrorMessage());
		} catch (AmazonClientException ace) {
			System.out.println("File Not Uploaded Client Exception: "+ace.getMessage());
		} catch (Exception ex) {
			System.out.println("File Not Uploaded: "+ex.getMessage());
		}
	}
}
