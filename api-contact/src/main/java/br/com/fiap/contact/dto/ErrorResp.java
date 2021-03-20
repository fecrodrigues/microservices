package br.com.fiap.contact.dto;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder(value = {"type","errorCode","message"})
public class ErrorResp {

	private String type;
	private Integer errorCode;
	private String message;
	
	public ErrorResp () {}
	
	public ErrorResp(String type, Integer errorCode, String message) {
		this.type = type;
		this.errorCode = errorCode; 
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
