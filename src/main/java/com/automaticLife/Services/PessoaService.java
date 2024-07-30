package com.automaticLife.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automaticLife.Classes.Pessoa;
import com.automaticLife.Repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repo;
	
	public List<Pessoa> buscarAniversariantesDoDia() {
	   
	   List<Pessoa> pessoas = repo.findAll();
	   
	   List<Pessoa> aniversariantes = new ArrayList<>();
	   
	   for(Pessoa person : pessoas) {
		   if(isBirthday(person)) {
			   aniversariantes.add(person);
		   }
	   }
	   
	   return aniversariantes;
	}
   
   @SuppressWarnings("deprecation")
   public Boolean isBirthday(Pessoa pessoa) {
	   
	   Date dataAtual = new Date();
	   
	   if(dataAtual.getDay() == pessoa.getDataNascimento().getDay() && 
		  dataAtual.getMonth() == pessoa.getDataNascimento().getMonth()) {
		   return true;
	   }else {
		   return false;
	   }
   }
   
   public Optional<Pessoa> buscarPorId(int id) {
	   return repo.findById(id);
   }
   
   public void excluir(int id) {
	   repo.deleteById(id);
   }

}