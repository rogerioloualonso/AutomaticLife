package com.automaticLife.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.automaticLife.controller.dto.PessoaDTO;
import com.automaticLife.repository.entity.Pessoa;
import com.automaticLife.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<String> excluirPessoa(@RequestBody PessoaDTO pessoa){
		
		if(pessoaService.validar(pessoa)) {
			pessoaService.inserir(pessoa);
			return ResponseEntity.status(200).build();
		}else {
			return ResponseEntity.status(400).body("Dados enviados incorretamente");
		}
	}
	
	@PutMapping
	public ResponseEntity<String> excluirPessoa(@RequestParam int id, @RequestBody PessoaDTO pessoa){
		
		if(pessoaService.validar(pessoa)){
			Optional<Pessoa> pessoaOriginal = pessoaService.buscarPorId(id);
			if(pessoaOriginal.isPresent()) {
				pessoaService.editar(pessoaOriginal.get(), pessoa);
				return ResponseEntity.status(200).build();
			}else {
				return ResponseEntity.status(400).body("Dados enviados incorretamente");
			}
		}else {
			return ResponseEntity.status(400).body("Dados enviados incorretamente");
		}
	}
	
	@DeleteMapping
	public ResponseEntity<Void> excluirPessoa(@RequestParam int id){
		
		Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
		
		if(!pessoa.isEmpty()) {
			pessoaService.excluir(id);
			return ResponseEntity.status(200).build();
		}
		else {
			return ResponseEntity.status(404).build();
		}
	}
}
