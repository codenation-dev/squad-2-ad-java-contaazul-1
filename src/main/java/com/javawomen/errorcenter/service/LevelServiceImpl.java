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

@Service
public class LevelServiceImpl implements LevelService{

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
	
	//--------------- métodos que devolvem um dto ------------
	public LevelDto converterToLevel(Optional<Level> levelOptional) {
		return converterToLevel(levelOptional.get());
	}
	
	public LevelDto converterToLevel(Level level) {			
		return new LevelDto(level);
	}
	
	public List<LevelDto> converter(List<Level> levels) {
		return levels.stream().map(LevelDto::new).collect(Collectors.toList());
	}
	
	//------------------- métodos que devolvem um FORM --------------------
	public Level converter(LevelForm form) {
		return new Level(form.getName());
	}

}
