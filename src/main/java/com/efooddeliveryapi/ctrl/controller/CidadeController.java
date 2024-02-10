package com.efooddeliveryapi.ctrl.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.efooddeliveryapi.domain.model.Cidade;
import com.efooddeliveryapi.domain.repository.CidadeRepository;
import com.efooddeliveryapi.domain.service.CidadeService;

@Controller
public class CidadeController {

	@Autowired
	CidadeService cidadeService;

	@Autowired
	CidadeRepository cidadeRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade criar(@RequestBody Cidade cidade) {
		return cidadeService.salvar(null);
	}

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		return cidadeService.buscarOuFalhar(cidadeId);
	}

	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		return cidadeService.salvar(cidadeAtual);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cozinhaId}")
	public void remover(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);

	}

}
