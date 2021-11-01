package br.com.jackcompany.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jackcompany.forum.config.security.TokenService;
import br.com.jackcompany.forum.controller.dto.LoginFormDTO;
import br.com.jackcompany.forum.controller.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> authentication(@RequestBody @Valid LoginFormDTO form){
		UsernamePasswordAuthenticationToken loginData = form.parseToAuthentication();
		try {
			Authentication authenticate = authenticationManager.authenticate(loginData);
			String token = tokenService.generateToken(authenticate);
			return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
		}catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
