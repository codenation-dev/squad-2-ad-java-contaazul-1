package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javawomen.errorcenter.controller.dto.EnvironmentDto;
import com.javawomen.errorcenter.controller.form.EnvironmentForm;
import com.javawomen.errorcenter.model.Environment;

public interface EnvironmentService {
	public List<Environment> findAll();

	public Optional<Environment> findById(Long id);

	public Environment save(Environment object);

	public void deleteById(Long id);

	public Optional<Environment> findByName(String nameEnvironment);
	
	//--------------- métodos que devolvem um dto ------------
	
	public EnvironmentDto converterToEnvironment(Environment environment);
	
	public List<EnvironmentDto> converter(List<Environment> environments);

	public EnvironmentDto converterToEnvironment(Optional<Environment> environmentOptional);

	//------------------- métodos que devolvem um FORM --------------------
	   
	public Environment converter(EnvironmentForm form);
}
