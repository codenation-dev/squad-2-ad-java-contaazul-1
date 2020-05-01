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


//o filtro foi instanciado na classe Security\cpnfigurations e por isso o spring nao pode fz injecao autowired
//nao tem o bean de gerenciamento do spring, ou seja, é uma classe java
public class AuthenticationByTokenFilter extends OncePerRequestFilter{

	//nao tem com injetar esses obj, receba no contrutor da classe SecurityConfigurations
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
		// verifica se estah ok
		System.out.println("Token :  " + token);
		// se estiver ok autentica no spring
		boolean valido = tokenService.isTokenValido(token);
		//testando se valido token true==logado ou/e false==nao logado 
		//postman: delete: http://localhost:8080/logs/2
		//headers: authorization e Bearer numero token(pega ele passando um post antes aparece no body: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgQ2VudHJhbCBkZSBFcnJvcyIsInN1YiI6IjQiLCJpYXQiOjE1ODc2MDE3NzMsImV4cCI6MTU4NzY4ODE3M30.9JSau2v10doPRZZG5O0O1jB0hKhqK6m1-sXNUPmv6Bc)
		System.out.println(valido);
		//não existe o conceito de user logado, nao tenho o token do user que solicitou
		//eh stantless, nao tem sessao
		//tem q autenticar novamnente, mas como o valido dah true faco isso
		
		if(valido) {
			authenticClient(token);
		}		
		
		//fiz o que tinha q rodar segue o fluxo da requisicao
		filterChain.doFilter(request, response);
		
	}

	private void authenticClient(String token) {
		// nesse momento já tenho o token, o user e senha já foram autenticados
		
		//recuperar id do user no token
		Long idUser = tokenService.getIdUser(token);
		
		//pegar user completo, carregar o user do banco de dados, use o repository
		User user = userRepository.findById(idUser).get();
		
		//classe user implementa a classe usesrDetails que tem o getAuthorities
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// classe do spring para forçar autenticação para este request: spring já estah tudo ok, considere user is autenticade 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//AuthenticationManager: só é usada na lógica de autenticação User/senha para gerar o token
		//SecurityContextHolder: o user já foi autenticado antes de entrar aki
	}

	private String takeToken(HttpServletRequest request) {
		// precisa de um cabeçalho chamado authorization
		String token = request.getHeader("Authorization");
		//verificar se o cabeçalho estah correto
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) { //!!!deixar espaço após Bearer: ("Bearer ")
			return null;
		}
		//somente o token, sem o "Bearer "...
		return token.substring(7, token.length()); // 7, pois Bearer + o espaço  == 7, ele pega a partir do espço ateh o final da string
	}

}
