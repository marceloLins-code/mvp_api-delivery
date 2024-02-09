package com.efooddeliveryapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.efooddeliveryapi.domain.exception.EntidadeEmUsoException;
import com.efooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import com.efooddeliveryapi.domain.model.Cozinha;
import com.efooddeliveryapi.domain.model.Restaurante;
import com.efooddeliveryapi.domain.repository.CozinhaRepository;
import com.efooddeliveryapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {	
		Long cozinhaId =restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com codigo %d", cozinhaId)));
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);

	}

	public void excluirRestaurante(Long restauranteId) {

		try {
			restauranteRepository.deleteById(restauranteId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Restaurante de codigo %d não pode ser encontrada.");
		}

		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Restaurante de codigo %d não pode ser removida, pois esta em uso.");
		}

	}
}
	
	