package com.xtrip.common;

public class XResponse {
	private int errorCode;
	private String errorMessage;
	private Object data;
	
	public XResponse(){
		this.errorCode = 200;
		this.errorMessage = "Successful";
		this.data = "";
	}
	
	public XResponse(int errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = "";
	}
	
	public int getErrorCode(){
		return errorCode;
	}
	
	public void setErrorCode(int errorCode){
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public Object getData(){
		return data;
	}
	
	public void setData(Object data){
		this.data = data;
	}

}
