package br.com.jackcompany.forum.controller.dto;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jackcompany.forum.model.Curso;
import br.com.jackcompany.forum.model.Topico;
import br.com.jackcompany.forum.repository.CursoRepository;

public class TopicoFormDTO {
	@NotNull @NotEmpty 
	private String titulo;
	@NotNull @NotEmpty @Size(min = 10)
	private String mensagem;
	@NotNull @NotEmpty 
	private String nomeCurso;
	
	
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
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico parseToTopico(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(this.nomeCurso);
		return new Topico(this.titulo,this.mensagem,curso);
	}
	
	
}
