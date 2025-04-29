package com.automaticLife.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automaticLife.controller.dto.PessoaDTO;
import com.automaticLife.repository.PessoaRepository;
import com.automaticLife.repository.entity.Pessoa;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repo;
	
	public Boolean validar(PessoaDTO pessoa) {
		
		//Melhorar as validações depois
		
		if(pessoa == null) {
			return false;
		}else {
			if(pessoa.getNome() == null || 
			   pessoa.getDataNascimento() == null || 
			   pessoa.getTelefone() == null) {
				return false;
			}else {
				return true;
			}
		}
	}
	
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
   
   public void inserir(PessoaDTO pessoa) {
	   Pessoa novaPessoa = new Pessoa(pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento());
	   repo.save(novaPessoa);
   }
   
   public void editar(Pessoa pessoaOriginal, PessoaDTO pessoa) {
	   Pessoa editPessoa = new Pessoa(pessoaOriginal.getId(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getDataNascimento());
	   repo.save(editPessoa);
   }

}