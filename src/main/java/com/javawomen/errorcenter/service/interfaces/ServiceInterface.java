package com.javawomen.errorcenter.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T, R, U> {
	
	List<T> findAll();

	Optional<T> findById(Long id);

	T save(T object);

	void deleteById(Long id);

	Optional<T> findByName(String string);
	
	R converterToDto(T object);
	
	List<R> converter(List<T> objects);
	
	T converter(U object);

}
