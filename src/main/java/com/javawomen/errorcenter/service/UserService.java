package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;


/**
 * @author Karina
 *
 */

@Service
public class UserService implements ServiceInterface<User>{
	
	UserRepository userRepository;

	@Override
	public Page<User> findAll(Pageable paginacao) {
		return userRepository.findAll(paginacao);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User object) {
		return userRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	//implementar:
	
	//Page<UserDto> UserDto.converter(Page<User> users);
	
	//UserDto UserDto.converterToUser(Optional<User> user);
	
	//User Userform.converter();
	
	//User UpdateUserform.update(Long id, UserRepository userRepository);



}
