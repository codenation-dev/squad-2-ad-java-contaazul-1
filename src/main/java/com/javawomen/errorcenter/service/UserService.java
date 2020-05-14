package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import com.javawomen.errorcenter.config.validation.UserDataInvalid;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.config.validation.UserDataInvalid;
import com.javawomen.errorcenter.controller.form.UpdateUserForm;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.Role;

//import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;

/**
 * @author Karina
 *
 */

@Service
public class UserService {// implements ServiceInterface<User>{

	@Autowired
	UserRepository userRepository;

	public Page<User> findAll(Pageable paginacao) {
		return userRepository.findAll(paginacao);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public User save(User object) {
		return userRepository.save(object);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public User getOne(Long id) {
		return userRepository.getOne(id);
	}
	
	

	// implementar:

	// Page<UserDto> UserDto.converter(Page<User> users);

	// UserDto UserDto.converterToUser(Optional<User> user);
	
	
	
	

	// ---------------  UPDATE-USERFORM  -------------------------
	
	//usado para mudar a senha, email, nome do user
	public User update(Optional<User> userOptional, UserForm userForm) {
		
		//Optional<User> userOptional = findById(id);
		//if(!userOptional.isPresent())throw new ResourceNotFoundException("Usuário não encontrado.");
		User user = userOptional.get();
		
		String email = userForm.getEmail();		
		String pass =  userForm.getPassword();
		
		//validar senha e email
		new DataValidation(email, pass);
		
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
		return user;
	}
	
	//usado para mudar o perfil do user 
	public User updateRole(Optional<User> userOptional, Optional<Role> roleOptional) {		
		//Optional<User> userOptional = findById(id);
		//if(!userOptional.isPresent())throw new ResourceNotFoundException("Usuário não encontrado.");
		User user = userOptional.get();		
		user.setRoles(roleOptional.get());
		return user;
	}
	
	// ----------------------  USERFORM - NEW USER -------------------------

	// User Userform.converter();
	public User converter(RoleService roleService, UserForm userForm) {
	//public User converter(RoleService roleService) { 
		Optional<Role> roleOptional = roleService.findByName("ROLE_USER");
		if(!roleOptional.isPresent())throw new ResourceNotFoundException("Role não encontrado.");
		return new User(userForm.getName(), 
				new BCryptPasswordEncoder().encode(userForm.getPassword()),
				userForm.getEmail(), roleOptional.get());
	}
		
	// ------------------- INICIO VALIDAR EMAIL E SENHA ---------------------
	public static class DataValidation{
		
		private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		private static final Pattern emailpattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		private static final Pattern passwordpattern = Pattern.compile(PASSWORD_PATTERN);

		public DataValidation(String email, String senha) {
			validar(email, senha);
		}

		public void validar(String email, String senha) {
			if(!validarEmail(email))throw new UserDataInvalid("E-mail inválido."
					+ " O e-mail deve possuir no mínimo 3 caracteres antes do @.");
			if(!validarSenha(senha))throw new UserDataInvalid ("Senha inválida. "
					+ " A senha deve possuir no mínimo 8 caracteres, entre letras e números.");
		}
		
		// Método de validação do email
		public static boolean validarEmail(String email) {
			Matcher matcher = emailpattern.matcher(email);
			return matcher.matches();
		}

		// Método de validação da senha
		public static boolean validarSenha(String senha) {
			Matcher matcher = passwordpattern.matcher(senha);
			return matcher.matches();
		}
		
	}
	// ---------- FIM VALIDAR EMAIL E SENHA ---------------

}
