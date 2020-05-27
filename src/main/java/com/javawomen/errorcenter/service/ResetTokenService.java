package com.javawomen.errorcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.ResetToken;
import com.javawomen.errorcenter.repository.ResetTokenRepository;

@Service
public class ResetTokenService {
	
	@Autowired
	ResetTokenRepository resetTokenRepository;
	
	public ResetToken save(ResetToken object) {
		return resetTokenRepository.save(object);
	}

}
