package com.referadr.Exception;

public class ReferadrException extends RuntimeException{

	private String exceptionMessage;
	
	public ReferadrException(String exceptionMessage)
	{
		this.exceptionMessage=exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
}
