package com.javawomen.errorcenter.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
@Service
public class TokenService {

	//esses values estao no .properties
	@Value("${errorcenter.jwt.expiration}")
	private String expiration;
	
	@Value("${errorcenter.jwt.secret}")
	private String secret; //injeta a criptografia da senha

	public String generateToken(Authentication authentication) {
		
		User userLoggedIn = (User)authentication.getPrincipal(); //pega o user que está logado
		Date dateActual = new Date();
		Date dateExpiration = new Date(dateActual.getTime()+ Long.parseLong(expiration));// data mais 1 dia em milisegundos
		
		// colocar a api do JJWT para gerar o token		
		return Jwts.builder()
				.setIssuer("API Central de Erros")
				.setSubject(userLoggedIn.getId().toString())//o user dono do token
				.setIssuedAt(dateActual) //qual a data de geração deste token
				.setExpiration(dateExpiration) //data de expiracao do token 1 dia de milisegundos, por exemplo, napratica colocar apenas 30 minutos, ver no properties, estah em segundos
				.signWith(SignatureAlgorithm.HS256, secret) //algoritimo, senha ; // HS256 = hmac e char56
				.compact();
	
	}
	//metodo que valida o token recebido na classe ...filter
	//devolve se token estah ou nao valido
	public boolean isTokenValido(String token) {
		//parser, decriptografa e verifica se estah ok
		//key == secret, chave q encript e desencript
		//parseClaimsJws == devolve o token e informações de dentro do token , como o ID
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}		
		
	}
	public Long getIdUser(String token) {
		//recuperar os dados do token: use o parser:
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();//devolve o corpo, o obj do token em si
		return Long.parseLong(claims.getSubject());//pega o id do user, eu setei o subject no securityconfig
	}

}
