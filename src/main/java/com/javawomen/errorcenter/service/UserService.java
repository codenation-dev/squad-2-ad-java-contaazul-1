package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.config.security.TokenService;
import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.config.validation.UserDataInvalid;
import com.javawomen.errorcenter.controller.dto.ResetPasswordDTO;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.ResetToken;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.ResetTokenRepository;
import com.javawomen.errorcenter.repository.UserRepository;
import com.javawomen.errorcenter.service.interfaces.UserServiceInterface;


@Service
public class UserService  implements UserServiceInterface{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	ResetTokenRepository resetTokenRepository;

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

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<Role> findAllRolesByUser(Long id) {
		return userRepository.findRolesByUser(id);
	}

	public User updateRole(Optional<User> userOptional, Optional<Role> roleOptional) {
		User user = userOptional.get();
		user.setRoles(roleOptional.get());
		return user;
	}
	// --------------------- UPDATE-USERFORM -------------------------

	// usado para mudar a senha, email, nome do user
	public User update(Optional<User> userOptional, UserForm userForm) {
		User user = userOptional.get();
		String email = userForm.getEmail();
		String password = userForm.getPassword();
		
		// validar senha e email
		new DataValidation(email, password);
		
		user.setName(userForm.getName());
		user.setEmail(email);
		//user.setEmail(userForm.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		return user;
	}

	// ---------------------  RESET-PASSWORD  -------------------------
	public User updatePassword(ResetPasswordDTO form) {

		Optional<User> userOptional = findByEmail(form.getEmail());
		if (!userOptional.isPresent())throw new ResourceNotFoundException("Email não encontrado");
		
		Optional<ResetToken> resetTokenOptional = resetTokenRepository.findByToken(form.getToken());
		//Verifica se o token digitado existe no banco de dados.
		if(!resetTokenOptional.isPresent())throw new ResourceNotFoundException("Token digitado não encontrado.");
		
		if(!form.getPassword().equals(form.getConfirmPassword()))
			throw new ResourceNotFoundException("A senha e a confirmação de senha não são iguais. Digite novamente.");
		
		//Verifica se o token foi digitado incorretamente.
		if(resetTokenRepository.findByToken(form.getToken()) == null ||
				!form.getToken().equals(resetTokenOptional.get().getToken()))
			throw new ResourceNotFoundException("Token não encontrado.");
		
		//Verifica se o token digitado está expirado
		if(tokenService.isTokenExpired(form.getToken()))
			throw new ResourceNotFoundException("Token digitado expirou. Informe um novo token.");		

		User user = userOptional.get();

		// validar senha e email
		new DataValidation(form.getEmail(), form.getPassword());

		user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		
		deleteById(user.getId());
		save(user);

		return user;
	}
	

	// -------------------- USERFORM - NEW USER ---------------------

	public User converter(RoleService roleService, UserForm userForm) {
		Optional<Role> roleOptional = roleService.findByName("ROLE_USER");
		if (!roleOptional.isPresent())throw new ResourceNotFoundException("Role não encontrado.");
		return new User(userForm.getName(), new BCryptPasswordEncoder().encode(userForm.getPassword()),
				userForm.getEmail(), roleOptional.get());
	}

	// -------------------------- USERDTO  --------------------------

	// retorna um Usuário sem a senha
	public UserDto converterToUser(User user) {
		return new UserDto(user);
	}

	// retorna uma lista de Usuários sem a senha
	public Page<UserDto> converter(Page<User> users) {
		return users.map(UserDto::new);
	}

	public UserDto converterToUser(Optional<User> userOptional) {
		return converterToUser(userOptional.get());
	}

	// ------------------- INICIO VALIDAR EMAIL E SENHA ---------------------
	public static class DataValidation {

		private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		private static final Pattern emailpattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		private static final Pattern passwordpattern = Pattern.compile(PASSWORD_PATTERN);

		public DataValidation(String email, String senha) {
			validar(email, senha);
		}

		public void validar(String email, String senha) {
			if (!validarEmail(email))
				throw new UserDataInvalid("E-mail inválido. O e-mail deve possuir no mínimo 3 caracteres antes do @,"
						+ " sem espaço, sem acentuação e sem caracteres especiais.");
			if (!validarSenha(senha))
				throw new UserDataInvalid(
						"Senha inválida. " + " A senha deve possuir no mínimo 8 caracteres, entre letras e números.");
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
