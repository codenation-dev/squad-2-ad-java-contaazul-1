package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.config.validation.UserDataInvalid;
import com.javawomen.errorcenter.controller.dto.RoleDto;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.service.UserServiceImpl.DataValidation;

public interface UserService {

	public Page<User> findAll(Pageable paginacao);

	public Optional<User> findById(Long id);

	public User save(User object);

	public void deleteById(Long id);

	public User getOne(Long id);
	
	public Optional<User> findByEmail(String email);

	public Map<Long, List<RoleDto>> findAllRolesByUser();
	
	public User update(Optional<User> userOptional, UserForm userForm);

	public User updateRole(Optional<User> userOptional, Optional<Role> roleOptional);

	public User converter(RoleServiceImpl roleService, UserForm userForm);
	
	public UserDto converterToUser(User user);
	
	public Page<UserDto> converter(Page<User> users);
	
	public UserDto converterToUser(Optional<User> userOptional);
	
//	public static class DataValidation;


}