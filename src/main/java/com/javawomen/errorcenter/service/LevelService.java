package com.javawomen.errorcenter.service;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.controller.dto.LevelDto;
import com.javawomen.errorcenter.controller.form.LevelForm;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.repository.LevelRepository;
import com.javawomen.errorcenter.service.interfaces.ServiceInterface;

@Service
public class LevelService implements ServiceInterface<Level, LevelDto, LevelForm>{

	@Autowired
	private LevelRepository levelRepository;
	
	public List<Level> findAll() {
		return levelRepository.findAll();
	}

	public Optional<Level> findById(Long id) {
		return levelRepository.findById(id);
	}

	public Level save(Level object) {
		return levelRepository.save(object);
	}

	public void deleteById(Long id) {
		levelRepository.deleteById(id);		
	}

	public Optional<Level> findByName(String nameLevel) {
		return levelRepository.findByName(nameLevel);
	}
	
	//------------------- MÉTODO COM DTO  -------------------	
	
	public LevelDto converterToDto(Level level) {			
		return new LevelDto(level);
	}
	
	public List<LevelDto> converter(List<Level> levels) {
		return levels.stream().map(LevelDto::new).collect(Collectors.toList());
	}
	
	//------------------- MÉTODO COM FORM  ------------------
	
	public Level converter(LevelForm form) {
		return new Level(form.getName().toUpperCase());
	}
}
