package com.javawomen.errorcenter.service;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.repository.LevelRepository;

@Service
public class LevelService{// implements ServiceInterface<Level>{

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
	
	

}
