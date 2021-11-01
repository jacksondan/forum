package br.com.jackcompany.forum.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jackcompany.forum.controller.dto.TopicoDTO;
import br.com.jackcompany.forum.controller.dto.TopicoFormDTO;
import br.com.jackcompany.forum.controller.dto.TopicoUpdateFormDTO;
import br.com.jackcompany.forum.model.Topico;
import br.com.jackcompany.forum.model.Usuario;
import br.com.jackcompany.forum.repository.CursoRepository;
import br.com.jackcompany.forum.repository.TopicoRepository;

@RestController
@RequestMapping("topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	@Cacheable(value = "listaTopicos")
	public Page<TopicoDTO> listaTopicos(@PageableDefault(page = 0, size = 2) Pageable pageable){
		
		Page<Topico> topicos = topicoRepository.findAll(pageable);
		return TopicoDTO.converte(topicos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDTO> buscaTopicoPorID(@PathVariable Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);
		if(topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Transactional
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public TopicoDTO cadastraTopico(@RequestBody @Valid TopicoFormDTO form) {
		Topico topico = form.parseToTopico(cursoRepository);
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		topico.setAutor(usuario);
		return new TopicoDTO(topicoRepository.save(topico));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public ResponseEntity<TopicoDTO> atualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoUpdateFormDTO updateForm) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			Topico topico = updateForm.parseTopico(optional.get());
			return ResponseEntity.ok(new TopicoDTO(topico));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaTopicos", allEntries = true)
	public ResponseEntity<?> removerTopico(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	/*@PostMapping //De acordo com os instrutores, esta é a melhor forma de se fazer um post com retorno.
	public ResponseEntity<TopicoDTO> cadastraTopico(@Valid @RequestBody TopicoFormDTO form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.parseTopico(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}*/
	
	
}
