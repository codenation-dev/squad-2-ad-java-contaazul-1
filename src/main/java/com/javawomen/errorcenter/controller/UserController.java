package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	// ------------------ GET ALL mudar para
	// Optional<User>--------------------------------

	// http://localhost:8080/users ok: traz uma lista de users cadastrados
	@GetMapping
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return UserDto.converter(users);
	}

	// ------------------ GET --------------------------------

	// http://localhost:8080/users/email ok: traz o user cadastrado no email
	// @GetMapping("/{email}") //(value = "/{email}")
	// public UserDto getUserByEmail(@PathVariable(value = "email") String email) {
	// Optional<User> user = userRepository.findByEmail(email);
	// return UserDto.converterToUser(user);
	// }

	// ------------------ POST --------------------------------

	@PostMapping
	@Transactional // ok: cadastra o user no bco
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) { // o
																														// post
																														// é
																														// RequestBody:
																														// spring
																														// pega
																														// no
																														// corpo
																														// e
																														// nao
																														// na
																														// url
																														// eihn!
		User user = form.converter(); // form: dado do cliente
		userRepository.save(user);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

	// ------------------ put --------------------------------

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateUserForm form) {
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
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
