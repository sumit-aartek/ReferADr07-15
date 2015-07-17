package com.referadr.practice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.multipart.MultipartFile;

import com.referadr.practice.model.FileMeta;

public class ReferadrUtils {

	public static String sha1(String input){
		StringBuffer sb = new StringBuffer();
		try{
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
		}
		catch(NoSuchAlgorithmException e) {
			System.out.println("Converting to SHA has failed");
		}
         
        return sb.toString();
    }
	public static String sendMail(String from,String to,String subject,String body)
	{
	//	to = "patidarsandeep991@gmail.com";
		final String username = "referadr@gmail.com";//change accordingly
	      final String password = "Sum!t123";//change accordingly

	      // Assuming you are sending email through gmail
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(username));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(subject);
	         // Now set the actual message
	         message.setText(body);
	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }

		return "Success";
	}
	
	  /**
	   * This Method will add uploaded files to List.
	   * @author Dileep
	   * @param files
	   * @return
	   */
	  public static ArrayList<FileMeta> getFiles(MultipartFile[] files,  ArrayList<FileMeta> fileList, String keyName) {
		  FileMeta fileMeta = new FileMeta();
		  
		  try {
			  if (files != null && files.length > 0) {

				  for (MultipartFile file : files) {
					  if (file.getSize() > 0) {
						  fileMeta = new FileMeta();
						  fileMeta.setFile(file);
						  fileMeta.setFileKey(keyName);
						  String fileName = DateUtil.getDateFormat(DateUtil.getCurrentDate(),IConstant.DATE_FORMAT_FILE);
						  fileName += "_"+file.getOriginalFilename();
						  fileMeta.setFileName(fileName);
						  fileMeta.setFilePath(keyName+"/"+fileName);
						  fileList.add(fileMeta);
					  }
				  }
			  }
		  } catch (Exception ex) {
			  // Replace this with Log.error in future
			  System.out.println(ex.getMessage());
		  }
		  return fileList;
	  }
	
	public static void main(String args[])
	{
		System.out.println(sha1("123abc"));
	}
	
	////////////////////
    private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
	
	
	public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
	
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}
	/////////////////
}
