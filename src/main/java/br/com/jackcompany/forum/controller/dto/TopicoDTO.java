package br.com.jackcompany.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.jackcompany.forum.model.Topico;

public class TopicoDTO {
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.dataAlteracao = topico.getDataAlteracao();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public Topico parseTopico() {
		Topico topico = new Topico(titulo, mensagem, null);
		return topico;
	}

	public static Page<TopicoDTO> converte(Page<Topico> topicos) {
		return topicos.map(TopicoDTO::new);
	}
	
	
}
