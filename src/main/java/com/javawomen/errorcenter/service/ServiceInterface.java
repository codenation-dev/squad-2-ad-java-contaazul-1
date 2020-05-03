package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceInterface<T> {

	Page<T>findAll(Pageable paginacao);
	
	Optional<T>findById(Long id);
	
	T save(T object);
	
	void deleteById(Long id);
}
