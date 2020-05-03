package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.repository.UserRepository;
import com.javawomen.errorcenter.controller.form.UpdateUserForm;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// ------------------ GET ALL --------------------------------

	// http://localhost:8080/users ok: traz uma lista de users cadastrados
	// @GetMapping
	// public List<UserDto> getAllUsers() {
	// List<User> users = userRepository.findAll();
	// return UserDto.converter(users);
	// }

	@GetMapping
	public Page<UserDto> getAllUsers(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		Page<User> users = userRepository.findAll(paginacao);
		return UserDto.converter(users);
	}
	
	
	// ------------------ GET BY ID --------------------------------
	// http://localhost:8080/users/{id} nao testado: traz o user cadastrado no id
	@GetMapping("/{id}") //(value = "/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		return UserDto.converterToUser(user);
		
	}

	// ------------------ GET BY EMAIL--------------------------------
	// método no repository usado por: AuthenticationService
	// http://localhost:8080/users/email ok: traz o user cadastrado no email
	// @GetMapping("/{email}") //(value = "/{email}")
	// public UserDto getUserByEmail(@PathVariable(value = "email") String email) {
	// Optional<User> user = userRepository.findByEmail(email);
	// return UserDto.converterToUser(user);
	// }

	// ------------------ POST --------------------------------
	// o post é RequestBody: spring pega no corpo e nao na url eihn!
	// testado ok: cadastra o user no bco
	//trocar o nome do metodo
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.converter(); // form: dado do cliente
		userRepository.save(user);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

	// ------------------ PUT --------------------------------

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserForm form) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			User user = form.update(id, userRepository);
			return ResponseEntity.ok(new UserDto(user));
		}
		
		return ResponseEntity.notFound().build();
	}

	// ------------------ DELETE --------------------------------
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
		//ao colocar essa linha e o return comentado retorna apenas o Response headers sem o response body no swagger
		//public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
		//return ResponseEntity.ok().build();
			return ResponseEntity.ok(UserDto.converterToUser(optionalUser));
		}

		return ResponseEntity.notFound().build();
	}

}
