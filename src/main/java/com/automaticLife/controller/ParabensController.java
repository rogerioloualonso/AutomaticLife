package com.automaticLife.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.automaticLife.controller.dto.PessoaDTO;
import com.automaticLife.repository.entity.Pessoa;
import com.automaticLife.service.PessoaService;
import com.automaticLife.service.TwilioService;

@Controller
public class ParabensController {
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	TwilioService twilioService;

	@PostMapping("/parabenizar")
	@ResponseBody 
	public ResponseEntity<String> desejarParabens(){
		
		List<Pessoa> aniversariantes = pessoaService.buscarAniversariantesDoDia();
		
		if(aniversariantes.isEmpty()) {
			return ResponseEntity.status(200).body("Sem aniversariantes neste dia.");
		}else {
			try {
				
				twilioService.EnviarMensagens(aniversariantes);
				return ResponseEntity.status(200).build();
				
			} catch (ParseException e) {
				return ResponseEntity.status(500).build();
			}
		}
	}
	
	@PostMapping("/excluir")
	@ResponseBody
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
	
	@PostMapping("/inserir")
	@ResponseBody
	public ResponseEntity<String> excluirPessoa(@RequestBody PessoaDTO pessoa){
		
		if(pessoaService.validar(pessoa)) {
			pessoaService.inserir(pessoa);
			return ResponseEntity.status(200).build();
		}else {
			return ResponseEntity.status(400).body("Dados enviados incorretamente");
		}
	}
	
	@PostMapping("/editar")
	@ResponseBody
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

}