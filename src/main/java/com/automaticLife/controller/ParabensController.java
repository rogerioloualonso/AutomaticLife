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
import com.automaticLife.service.external.TwilioService;

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

}