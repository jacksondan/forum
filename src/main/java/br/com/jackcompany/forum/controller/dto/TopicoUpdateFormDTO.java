package br.com.jackcompany.forum.controller.dto;



import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jackcompany.forum.model.Topico;

public class TopicoUpdateFormDTO {
	@NotNull @NotEmpty 
	private String titulo;
	@NotNull @NotEmpty @Size(min = 10)
	private String mensagem;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico parseTopico(Topico topico) {
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		topico.setDataAlteracao(LocalDateTime.now());
		return topico;
	}
	
	
}
