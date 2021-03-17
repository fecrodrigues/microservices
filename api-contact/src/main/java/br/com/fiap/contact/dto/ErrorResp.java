package br.com.fiap.contact.dto;

public class ErrorResp {

	private String type;
	private String message;
	
	public ErrorResp () {}
	
	public ErrorResp(String type, String message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
