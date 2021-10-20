package br.com.jackcompany.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticationService autenticationService;
	
	//Configurações de autenticação (Login e Senha)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuração de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	//Configuração para recursos staticos, como páginas do próprio sistema, porém, no caso de API, não é utilizado.
	@Override
	public void configure(WebSecurity web) throws Exception {
		
	}
}

/*	Para criar form de autenticação com login e senha
  
 

//Configurações de autenticação (Login e Senha)
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
}

//Configuração de autorização
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	.antMatchers(HttpMethod.GET, "/topicos").permitAll()
	.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
	.anyRequest().authenticated()
	.and().formLogin();
	
}
*/
