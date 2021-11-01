package br.com.jackcompany.forum.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginFormDTO {
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty
	private String senha;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsernamePasswordAuthenticationToken parseToAuthentication() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
	
	
	
	
}
