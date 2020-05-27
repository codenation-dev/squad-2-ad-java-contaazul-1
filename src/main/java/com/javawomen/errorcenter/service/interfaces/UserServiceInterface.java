package com.javawomen.errorcenter.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javawomen.errorcenter.controller.dto.ResetPasswordDTO;
import com.javawomen.errorcenter.controller.dto.UserDto;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.service.RoleService;

public interface UserServiceInterface {
	
	Page<User> findAll(Pageable paginacao);

	Optional<User> findById(Long id);

	User save(User user);

	void deleteById(Long id);

	User getOne(Long id);

	Optional<User> findByEmail(String email);

	List<Role> findAllRolesByUser(Long id);

	User updateRole(Optional<User> userOptional, Optional<Role> roleOptional);
	
	User update(Optional<User> userOptional, UserForm userForm);

	User updatePassword(ResetPasswordDTO form);

	User converter(RoleService roleService, UserForm userForm);

	UserDto converterToUser(User user);

	Page<UserDto> converter(Page<User> users);

	UserDto converterToUser(Optional<User> userOptional);

}
