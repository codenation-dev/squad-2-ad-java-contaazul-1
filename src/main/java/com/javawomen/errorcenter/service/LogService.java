package com.javawomen.errorcenter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;

/**
 * @author Letícia
 *
 */

@Service
public class LogService {

	LogRepository logRepository;

	public Log save(Log log) {
		return logRepository.save(log);
	}

	public Iterable<Log> findAll() {
		return logRepository.findAll();
	}

	public Optional<Log> findById(Long longId) {
		return logRepository.findById(longId);
	}
}
