package br.com.jackcompany.forum.controller.dto;

public class TokenDTO {

	private String token;
	private String type;

	public TokenDTO(String token, String type) {
		this.token = token;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}
	
	
	
}
