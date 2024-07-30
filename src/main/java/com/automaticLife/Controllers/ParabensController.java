package com.automaticLife.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.automaticLife.Classes.Pessoa;
import com.automaticLife.Services.PessoaService;
import com.automaticLife.Services.TwilioService;

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

}