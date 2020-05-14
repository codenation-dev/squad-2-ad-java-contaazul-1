package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.repository.EnvironmentRepository;

@Service
public class EnvironmentService{// implements ServiceInterface<Environment>{

	@Autowired
	private EnvironmentRepository environmentRepository;

	public List<Environment> findAll() {
		return environmentRepository.findAll();
	}

	public Optional<Environment> findById(Long id) {
		return environmentRepository.findById(id);
	}

	public Environment save(Environment object) {
		return environmentRepository.save(object);
	}

	public void deleteById(Long id) {
		environmentRepository.deleteById(id);		
	}

	public Optional<Environment> findByName(String nameEnvironment) {
		return environmentRepository.findByName(nameEnvironment);
	}
	


}
