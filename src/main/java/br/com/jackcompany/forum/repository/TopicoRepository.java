package br.com.jackcompany.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jackcompany.forum.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{
	
	List<Topico> findByCurso_Nome(String nomeCurso);
}
