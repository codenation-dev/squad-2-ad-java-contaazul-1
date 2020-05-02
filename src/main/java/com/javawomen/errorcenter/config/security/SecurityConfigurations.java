package com.javawomen.errorcenter.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.javawomen.errorcenter.repository.UserRepository;


//classe java e não tem haver com spring
//spring bloqueia acesso total na api, só libera o que eu mandar liberar aki dentro
//habilitar o modulo de seguranca da app com spring:
@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Bean //metodo devolve authentication mananger
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//configurações de autenticação //userDetailsService == Authentication // passwordEncoder == BCryptPasswordEncoder ==algoritimo de hash para senha
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
	
	//configurações de Autorização da urls do projeto //colocaraki o endpoint publico
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/logs").permitAll()                  //métodos de config liberacao de urls
		.antMatchers(HttpMethod.GET, "/logs/*").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.POST, "/users").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll() //após testar retire isso antes de ir a producao
		.anyRequest().authenticated() //evita q uma url que nao foi configurada seja publica
		.and().csrf().disable() //desabilita o ataque csrf
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//politica de criacao de sessão, diz para o spring q nao é para criar sessao pois a comunicacao será statelles
		.and().addFilterBefore(new AuthenticationByTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class)
		;
		//.and().formLogin();//sem isso fico sem a controller de autentic tb;gera form, sessao nao statelless de autenticação do spring - spring tem o form q autenticacao e o controller q o recebe esse formulario
		//do modo statelles...  o form de login quem fornece é o cliente
	}
	
	
	
	
	//não precisa pois não tenho o front
	//configurações de recursos estáticos(js, css, imagens)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");

	}
	
	
	
	//-----------    DEMONSTRACAO DE CRIPTOGRAFIA DA SENHA     -------------------------
	//apenas mostrar com funciona a criptografia da senha que vai para o bco que se coloca no data.sql 
	//public static void main(String[] args) {
	//	System.out.println(new BCryptPasswordEncoder().encode("123"));
	//}
	
}