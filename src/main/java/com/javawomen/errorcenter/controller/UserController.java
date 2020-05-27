package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.service.RoleService;
import com.javawomen.errorcenter.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Usuário
 */
@Api(tags = "5. Usuário da API - ")
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")//libera os dominios de acesar a api: http://dominio.com.br
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	
	// -------------------------- GET ALL ---------------------------
	// http://localhost:8080/users
	@ApiOperation(value = "Retorna uma lista de usuários existentes")
	@GetMapping
	@Cacheable("listOfUser") // @Cacheable do spring
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", 
					value = "Pagina à ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", 
					value = "Quantidade de registros", defaultValue = "10"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", 
					paramType = "query", value = "Ordenação dos registros") })
	public Page<UserDto> getAllUsers(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) 
			@ApiIgnore Pageable paginacao) {
		Page<User> users = userService.findAll(paginacao);
		return userService.converter(users);

	}

	// ------------------------ GET BY ID ---------------------------
	// http://localhost:8080/users/{id}
	@ApiOperation(value = "Retorna um usuário cadastrado")
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("Usuário não encontrado.");
		return userService.converterToUser(userOptional);
	}

	// ---------------------- GET ROLE BY USER ----------------------
	// http://localhost:8080/users/role?nameRole=ROLE_ADMIN -> Long == User id
	@ApiOperation(value = "Retorna as permissões de perfil do usuário")
	@GetMapping("/role/{id}")
	public ResponseEntity<List<Role>> getRoleByUser(@PathVariable Long id) {// @RequestParam ou @PathVariable
		return ResponseEntity.ok(userService.findAllRolesByUser(id));
	}

	// --------------------------- POST -----------------------------
	// o post é RequestBody: spring pega no corpo e nao na url
	// http://localhost:8080/users/#@RequestBody
	@ApiOperation(value = "Cria um novo usuário")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserForm form, 
			UriComponentsBuilder uriBuilder) {
		User user = userService.converter(roleService, form);
		userService.save(user);
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

	// --------------------------- PUT ------------------------------
	// http://localhost:8080/users/{id}
	@ApiOperation(value = "Atualiza os dados do usuário")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, 
			@RequestBody @Valid UserForm form) {
		Optional<User> userOptional = userService.findById(id);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("Usuário não encontrado.");
		User user = userService.update(userOptional, form);
		return ResponseEntity.ok(new UserDto(user));
	}

	// -------------------------- DELETE ----------------------------
	// http://localhost:8080/users/{id}
	@ApiOperation(value = "Exclui um usuário")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("Usuário não encontrado.");
		userService.deleteById(id);
		return ResponseEntity.ok(userService.converterToUser(userOptional));
	}

	// --------------------- UPDATE ROLE USER -----------------------
	// metodo para o admin setar/mudar a role para admin, user,
	// http://localhost:8080/users/{id}/role/{id}
	@ApiOperation(value = "Concede permissões ao perfil do usuário")
	@PatchMapping("/{id}/role/{roleId}")
	@Transactional
	public ResponseEntity<UserDto> updateUserRole(@PathVariable Long id, 
			@PathVariable Long roleId) {																										
		Optional<User> userOptional = userService.findById(id);
		Optional<Role> roleOptional = roleService.findById(roleId);
		if (!roleOptional.isPresent())
			throw new ResourceNotFoundException("Perfil não encontrado.");
		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("Usuário não encontrado.");
		User user = userOptional.get();
		user.setRoles(roleOptional.get());
		return ResponseEntity.ok(new UserDto(user));
	}
}
