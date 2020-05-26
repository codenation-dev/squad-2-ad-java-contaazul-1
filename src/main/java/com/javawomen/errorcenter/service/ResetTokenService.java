package com.javawomen.errorcenter.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.javawomen.errorcenter.model.ResetToken;
import com.javawomen.errorcenter.repository.ResetTokenRepository;

@Service
public class ResetTokenService {
	
	@Autowired
	ResetTokenRepository resetTokenRepository;

	public Page<ResetToken> findAll(Pageable paginacao) {
		return resetTokenRepository.findAll(paginacao);
	}

	public Optional<ResetToken> findById(Long id) {
		return resetTokenRepository.findById(id);
	}

	public ResetToken save(ResetToken object) {
		return resetTokenRepository.save(object);
	}

	public void deleteById(Long id) {
		resetTokenRepository.deleteById(id);
	}

	public ResetToken getOne(Long id) {
		return resetTokenRepository.getOne(id);
	}

	public Optional<ResetToken> findByEmail(String email) {
		return resetTokenRepository.findByEmail(email);
	}


}
