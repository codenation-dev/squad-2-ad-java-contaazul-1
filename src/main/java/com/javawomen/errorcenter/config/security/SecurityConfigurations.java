package com.javawomen.errorcenter.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
	@Bean 
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//configurações de autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	 
	//configurações de Autorização da urls do projeto
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/auth").permitAll()
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/roles").hasAnyRole("ADMIN", "USER")
		.antMatchers("/roles/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/levels").hasAnyRole("ADMIN", "USER")
		.antMatchers("/levels/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/environments").hasAnyRole("ADMIN", "USER")
		.antMatchers("/environments/**").hasAnyRole("ADMIN", "USER")
		
		.antMatchers(HttpMethod.POST, "/logs").hasAnyRole("SYSTEM", "ADMIN")//.save
		.antMatchers(HttpMethod.POST, "/logs/**").hasAnyRole("ADMIN", "USER")//.arquivar
		.antMatchers(HttpMethod.DELETE, "/logs/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/logs").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.GET, "/logs/**").hasAnyRole("ADMIN", "USER")
		
		.antMatchers(HttpMethod.POST, "/users").permitAll()
		.antMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.PATCH, "/users/role/**").hasRole("ADMIN")
	
		//.antMatchers(HttpMethod.GET, "/actuator/**").permitAll() //usar nos teste, não na producao
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//politica de criacao de sessão, então spring q nao é para criar sessao pois a comunicacao será statelles
		.and().addFilterBefore(new AuthenticationByTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class)
		;

	}

            
     
	
	// configurações de recursos estáticos(js, css, imagens)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", 
        		"/swagger-resources/**", "/swagger-ui.html",
        		"/h2/**", "/h2/*", "/h2", "/users/register");
	}
	
}
