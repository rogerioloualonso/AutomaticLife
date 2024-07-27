package com.automaticLife.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.automaticLife.Classes.Pessoa;

@Service
public class PessoaService {
	
   public List<Pessoa> buscarAniversariantesDoDia() {
	   
	   List<Pessoa> aniversariantes = new ArrayList<>();
	   
	   SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	   Date dataFormatada = new Date();
	   
	   try {
		   dataFormatada = formato.parse("27/07/2002");
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   
	   Pessoa mozinho = new Pessoa ("Meu", "+5521986199779", dataFormatada);
	   aniversariantes.add(mozinho);
	   
	   return aniversariantes;
   }

}