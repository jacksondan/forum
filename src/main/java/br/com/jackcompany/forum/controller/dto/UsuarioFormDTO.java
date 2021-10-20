package br.com.jackcompany.forum.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.jackcompany.forum.model.Usuario;

public class UsuarioFormDTO {
	@NotNull @NotEmpty 
	private String nome;
	@NotNull @NotEmpty @Email
	private String email;
	@NotNull @NotEmpty
	private String senha;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public Usuario parseToUsuario() {
		return new Usuario(this.nome, this.email, this.senha);
	}
	
}
