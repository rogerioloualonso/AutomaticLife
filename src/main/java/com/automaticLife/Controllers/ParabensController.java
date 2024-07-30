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
	public ResponseEntity<Void> desejarParabens() throws ParseException, IOException {
		
		List<Pessoa> aniversariantes = pessoaService.buscarAniversariantesDoDia();
		
		twilioService.EnviarMensagens(aniversariantes);
		
		return ResponseEntity.status(200).build();
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