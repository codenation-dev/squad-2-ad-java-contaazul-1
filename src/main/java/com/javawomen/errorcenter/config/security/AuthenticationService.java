package com.javawomen.errorcenter.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;
 

//o controller do user fica no spring, eu nao preciso fazer
//esse é service userdao
@Service //gerenciada por spring
public class AuthenticationService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	//qnd entrar no form de login, o spring entra no Service e chama o método abaixo
	//userName == Email do user, a senha fica por conta do spring checar em memoria se estah correta, eu nao tenho acesso a ela
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//consultar bc atraves do email
		Optional<User> user = userRepository.findByEmail(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("Usuário " + username + " não foi encontrado.");
	}

}
