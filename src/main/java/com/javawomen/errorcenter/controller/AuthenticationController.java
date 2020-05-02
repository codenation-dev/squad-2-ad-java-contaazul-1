package com.javawomen.errorcenter.controller;

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

import com.javawomen.errorcenter.config.security.TokenService;
import com.javawomen.errorcenter.controller.dto.TokenDto;
import com.javawomen.errorcenter.controller.form.LoginForm;


@RestController
@RequestMapping("/auth")  //endereço de autenticação que o cliente deve configurar
public class AuthenticationController {
	//qnd no formulario do cliente o user clicar em logar, aqui é onde ele será autenticado
	
	@Autowired
	private AuthenticationManager authManager;//essa classe é do spring mas não faz injecao de dependencias automaticamente, temos que fz manualmente na classe security configuration
	
	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm loginForm){
		//tirar
		System.out.println("class AuthenticationController: " + loginForm.getEmail());
		System.out.println("class AuthenticationController: " + loginForm.getPassCode());
		
		UsernamePasswordAuthenticationToken loginData = loginForm.convert();
		
		try {
			//essa linha vai para o authentication service, passa pelo repository
			Authentication authentication = authManager.authenticate(loginData);//email e senha q vem do obj a cima				
			
			//antes de devolver o OK,devo gerar o TOKEN
			String token = tokenService.generateToken(authentication);
			
			System.out.println("TOKEN GERADO pelo json=webtoken pelo jjwt:  " + token);
			//return ResponseEntity.ok().build();
			//retornar o token para o cliente
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();//para nao devolver erro 500 e sim badrequest
		}
		
		

		
	}
}
