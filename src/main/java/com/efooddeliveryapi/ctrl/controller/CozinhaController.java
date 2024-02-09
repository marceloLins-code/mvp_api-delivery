package com.efooddeliveryapi.ctrl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import com.efooddeliveryapi.domain.exception.EntidadeEmUsoException;
import com.efooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import com.efooddeliveryapi.domain.model.Cozinha;
import com.efooddeliveryapi.domain.model.Restaurante;
import com.efooddeliveryapi.domain.repository.CozinhaRepository;
import com.efooddeliveryapi.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	CozinhaRepository cozinhaRepository;

	@Autowired
	CozinhaService cozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		return cozinhaService.buscarOuFalhar(cozinhaId);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
	Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);

			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
	
			return cozinhaService.salvar(cozinhaAtual);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cozinhaId}")
	public void remover(@PathVariable Long cozinhaId) {
		cozinhaService.excluirCozinha(cozinhaId);

	}
			
}
