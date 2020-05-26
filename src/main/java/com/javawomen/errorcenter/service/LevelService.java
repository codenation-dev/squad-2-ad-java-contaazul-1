package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.javawomen.errorcenter.controller.dto.LevelDto;
import com.javawomen.errorcenter.controller.form.LevelForm;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.repository.LevelRepository;

public interface LevelService {
	public List<Level> findAll();

	public Optional<Level> findById(Long id);

	public Level save(Level object);
		
	public void deleteById(Long id);

	public Optional<Level> findByName(String nameLevel);
		
	//--------------- métodos que devolvem um dto ------------
	public LevelDto converterToLevel(Optional<Level> levelOptional);
		
	public LevelDto converterToLevel(Level level);
		
	public List<LevelDto> converter(List<Level> levels);
		
	//------------------- métodos que devolvem um FORM --------------------
	public Level converter(LevelForm form);
}
