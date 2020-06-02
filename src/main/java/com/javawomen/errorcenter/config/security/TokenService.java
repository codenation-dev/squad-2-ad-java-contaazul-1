package com.javawomen.errorcenter.config.security;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TokenService {

	@Value("${errorcenter.jwt.expiration}")
	private String expiration;

	@Value("${errorcenter.jwt.secret}")
	private String secret; 

	public String generateToken(Authentication authentication) {
		
		// retorna user logado
		User userLoggedIn = (User) authentication.getPrincipal(); 
		Date dateActual = new Date();
		Date dateExpiration = new Date(dateActual.getTime() + Long.parseLong(expiration));

		// colocar a api do JJWT para gerar o token
		return Jwts.builder().setIssuer("API Central de Erros").setSubject(userLoggedIn.getId().toString())
				.setIssuedAt(dateActual)
				.setExpiration(dateExpiration) 
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

	}

	// metodo que valida o token recebido na classe AuthenticationByTokenFilter
	public boolean isTokenValido(String token) {
		// parser, descriptografa e verifica se estah ok
		// parseClaimsJws, devolve o token e informações de dentro do token , como o id
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getIdUser(String token) {
		// recuperar os dados do token
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	
	public String generateResetToken(Optional<User> user) {
		Date dateActual = new Date();
		Date dateExpiration = new Date(dateActual.getTime() + Long.parseLong(expiration));
		return Jwts.builder().setIssuer("API Central de Erros").setSubject(user.get().toString())
				.setIssuedAt(dateActual)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	
	public boolean isTokenExpired(String token) {

		String[] splitToken = token.split("\\.");
		String base64EncodedBody = splitToken[1];
		Base64 base64Url = new Base64();

		String body = new String(base64Url.decode(base64EncodedBody));
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = null;
		try {
			actualObj = mapper.readTree(body);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String exp = actualObj.get("exp").asText();
		long expLong = Long.parseLong(exp) * 43200000;
		long currentTime = System.currentTimeMillis();
		return expLong <= currentTime;
	}
	
	//realizado para teste
	public static String createToken(String name) {
		Date dateActual = new Date();
		Date dateExpiration = new Date(dateActual.getTime() + Long.parseLong("43200000"));
		return Jwts.builder().setIssuer("API Central de Erros").setSubject(name)
				.setIssuedAt(dateActual)
				.setExpiration(dateExpiration)
				.signWith(SignatureAlgorithm.HS256, "segredo")
				.compact();
	}

}
