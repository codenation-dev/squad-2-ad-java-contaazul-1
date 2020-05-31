package com.javawomen.errorcenter.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javawomen.errorcenter.config.security.TokenService;
import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.ResetPasswordDTO;
import com.javawomen.errorcenter.controller.dto.TokenDto;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.controller.form.LoginForm;
import com.javawomen.errorcenter.model.ResetToken;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.service.ResetTokenService;
import com.javawomen.errorcenter.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Autentica usuário e gera Token JWT
 */
@Api(tags = "1. Autenticação de Usuário - ")
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResetTokenService resetTokenService;

	//---------------------------- LOGIN ----------------------------
	@ApiOperation(value = "Login")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm login){
		UsernamePasswordAuthenticationToken loginData = login.convert();
		try {
			Authentication authentication = authManager.authenticate(loginData);					
			//gerar o token
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	//----------------- GERA TOKEN PARA ALTERAR SENHA ---------------
	//@GetMapping(path="/forgotPassword")
	@ApiOperation(value = "Resgata um token para alterar a senha de usuário")	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TokenDto> forgotPassword(@RequestParam String email) {
			Optional<User> user = userService.findByEmail(email);
			if (!user.isPresent())throw new ResourceNotFoundException("E-mail não encontrado. Verifique se você digitou corretamente.");
			String token = tokenService.generateResetToken(user);
			ResetToken resetToken = new ResetToken();
			resetToken.setToken(token);
			resetToken.setEmail(email);
			resetTokenService.save(resetToken);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		}
		
	//----------------------- ALTERAR SENHA -------------------------
	//@PutMapping(path="/resetPassword")
	@ApiOperation(value = "Altera a senha de usuário com uso de token")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> resetPassword(@RequestBody ResetPasswordDTO resetPassword) {
		User user = userService.updatePassword(resetPassword);		
		return ResponseEntity.ok(new UserDto(user));
	}	
	
}





