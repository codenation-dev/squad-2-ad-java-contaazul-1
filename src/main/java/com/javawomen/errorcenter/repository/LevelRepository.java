package com.javawomen.errorcenter.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long>{

	
	Optional<Level> findByName(String name);
	//Level findByName(String name);
}
