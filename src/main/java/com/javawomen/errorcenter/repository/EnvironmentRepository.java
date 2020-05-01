package com.javawomen.errorcenter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.Environment;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long>{

	Environment findByName(String name);

}