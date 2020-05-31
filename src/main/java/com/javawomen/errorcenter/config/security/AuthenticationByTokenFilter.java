package com.javawomen.errorcenter.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;
 

public class AuthenticationByTokenFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UserRepository userRepository;
	
	public AuthenticationByTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// pega o token do cabeçalho
		String token = takeToken(request);
		System.out.println("Token:  " + token);
		// verifica se o token está válido
		boolean valido = tokenService.isTokenValido(token);		
		System.out.println(valido);
		// se token ainda válido autoriza o request
		if(valido) {
			authenticClient(token);
		}		
		// requisicao
		filterChain.doFilter(request, response);
	}

	private void authenticClient(String token) {		
		Long idUser = tokenService.getIdUser(token);
		User user = userRepository.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		// classe do spring para autenticação do request 
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String takeToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		// verifica se o cabeçalho estah correto
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		// retorna token sem o "Bearer "; Bearer + o espaço  == 7
		return token.substring(7, token.length());
	}

}
