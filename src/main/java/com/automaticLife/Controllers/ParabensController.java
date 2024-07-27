package com.automaticLife.Controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String desejarParabens() throws ParseException, IOException {
		
		List<Pessoa> aniversariantes = pessoaService.buscarAniversariantesDoDia();
		
		twilioService.EnviarMensagens(aniversariantes);
		
		return "Parabenizações enviadas!";
	}

}