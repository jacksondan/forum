package br.com.jackcompany.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jackcompany.forum.controller.dto.UsuarioFormDTO;
import br.com.jackcompany.forum.model.Usuario;
import br.com.jackcompany.forum.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario cadastraUsuario(@RequestBody @Valid UsuarioFormDTO form) {
		String senha = new BCryptPasswordEncoder().encode(form.getSenha());
		form.setSenha(senha);
		return usuarioRepository.save(form.parseToUsuario());
	}
}
