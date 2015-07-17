package com.referadr.practice.model;

/**
 * This Class holds the properties for the Documents.
 */
import java.io.Serializable;


public class Document implements Serializable {

	private static final long serialVersionUID = 1L;
	private int docId;
	private String docName;
	private String docPath;

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

}
