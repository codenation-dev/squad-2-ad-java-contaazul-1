package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;


/**
 * @author Karina
 *
 */

@Service
public class UserService {
	
	UserRepository userrepository;

	public Iterable<User> findAll() {
		return userrepository.findAll();
	}
	
	public Optional<User> findById(Long id){
		return userrepository.findById(id);
	}

	public User save(User u) {
		return userrepository.save(u);
	}

}
