package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.repository.EnvironmentRepository;
import com.javawomen.errorcenter.service.ServiceInterface;

@Service
public class EnvironmentService implements ServiceInterface<Environment>{

	@Autowired
	private EnvironmentRepository environmentRepository;

	@Override
	public Page<Environment> findAll(Pageable paginacao) {
		return environmentRepository.findAll(paginacao);
	}

	@Override
	public Optional<Environment> findById(Long id) {
		return environmentRepository.findById(id);
	}

	@Override
	public Environment save(Environment object) {
		return environmentRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		environmentRepository.deleteById(id);		
	}
	


}
