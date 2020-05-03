package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.repository.LevelRepository;

@Service
public class LevelService implements ServiceInterface<Level>{

	@Autowired
	private LevelRepository levelRepository;
	
	@Override
	public Page<Level> findAll(Pageable paginacao) {
		return levelRepository.findAll(paginacao);
	}

	@Override
	public Optional<Level> findById(Long id) {
		return levelRepository.findById(id);
	}

	@Override
	public Level save(Level object) {
		return levelRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		levelRepository.deleteById(id);		
	}

}
