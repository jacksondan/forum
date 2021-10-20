package br.com.jackcompany.forum.config.validation.dto;

public class JsonErrorDTO {
	
	private String field;
	private String message;
	
	public JsonErrorDTO(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
	
	
	
}
