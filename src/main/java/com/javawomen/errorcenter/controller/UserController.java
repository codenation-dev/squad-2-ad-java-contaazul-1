package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.service.RoleService;
import com.javawomen.errorcenter.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

import com.javawomen.errorcenter.controller.form.RoleForm;
import com.javawomen.errorcenter.controller.form.UpdateUserForm;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.dto.RoleDto;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.RoleRepository;
import com.javawomen.errorcenter.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	// ------------------------- GET ALL --------------------------------

	@GetMapping
	@Cacheable("listOfUser") // importei o do spring, verififcar se nao tem q ser o javax
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina à ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "10"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	public Page<UserDto> getAllUsers(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) @ApiIgnore Pageable paginacao) {
		Page<User> users = userService.findAll(paginacao);
		//return UserDto.converter(users);
		return userService.converter(users);

	}

	// ------------------ GET BY ID --------------------------------
	// http://localhost:8080/users/{id} nao testado: traz o user cadastrado no id
	@GetMapping("/{id}") // (value = "/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		//return UserDto.converterToUser(userOptional);
		return userService.converterToUser(userOptional);
	}

	// --------------------------- POST --------------------------------
	// o post é RequestBody: spring pega no corpo e nao na url eihn!
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = userService.converter(roleService, form); // form: dado do cliente
		userService.save(user);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

	// ------------------ PUT --------------------------------

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserForm form) {
		Optional<User> userOptional = userService.findById(id);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		User user = userService.update(userOptional, form);
		return ResponseEntity.ok(new UserDto(user));

		// return ResponseEntity.notFound().build();
	}

	// ------------------ DELETE --------------------------------
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
		// ao colocar essa linha e o return comentado retorna apenas o Response headers
		// sem o response body no swagger
		// public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		userService.deleteById(id);
		//return ResponseEntity.ok(UserDto.converterToUser(userOptional));
		return ResponseEntity.ok(userService.converterToUser(userOptional));
	}

	// ------------------ Alterar Role USer --------------------------------

	// metodo para o admin setar/mudar a role para admin, user,
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> updateUserRole(@PathVariable Long id, @RequestBody Long roleId) {
		Optional<User> userOptional = userService.findById(id);//
		Optional<Role> roleOptional = roleService.findById(roleId);//repository
		if (!roleOptional.isPresent())
			throw new ResourceNotFoundException("ID Role não encontrado.");//
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("ID do usuário não encontrado.");//
		User user = userOptional.get();
		user.setRoles(roleOptional.get());
		return ResponseEntity.ok(new UserDto(user));
	}
	
	// ------------------ GET ROLE BY ALL ----------------------------
	
	//http://localhost:8080/users/role?nameRole=ROLE_ADMIN -> Long == User id
	@GetMapping("/role") 
	public ResponseEntity<Map<Long, List<RoleDto>>> getListRoleByUser(@RequestParam(required = false) String nameRole) {		
		
		//Se nao der tempo nao implementamos esse.
		
		
		//if (nameRole == null) {
		//	return ResponseEntity.ok(userService.findAllRolesByUser());
		//} else {
			//return ResponseEntity.ok(userService.findByRoleByUser(nameRole));
		//}
		return null;
	}

}
