package com.efooddeliveryapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.efooddeliveryapi.domain.exception.EntidadeEmUsoException;
import com.efooddeliveryapi.domain.exception.EstadoNaoEncontradaException;
import com.efooddeliveryapi.domain.model.Cozinha;
import com.efooddeliveryapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {



	private static final String MSG_COZINHA_EM_USO = "cozinha de código %d não pode ser removida, pois esta em uso!";
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
		
	public Cozinha salvar(Cozinha cozinha) {
		
		return cozinhaRepository.save(cozinha);		
		
	}
	
	public void excluirCozinha(Long cozinhaId) {
		
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(cozinhaId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
		
	}
	
	
	
	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow( () -> new EstadoNaoEncontradaException(cozinhaId));
		
	}
	
	
	
}
