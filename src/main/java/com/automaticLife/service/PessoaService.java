package com.automaticLife.service;

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

		// Melhorar as validações depois

		if (pessoa == null) {
			return false;
		} else {
			if (pessoa.getNome() == null || pessoa.getDataNascimento() == null || pessoa.getTelefone() == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public List<Pessoa> buscarAniversariantesDoDia() {
		return repo.buscarAniversariantesDia("05", "06");
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
		Pessoa editPessoa = new Pessoa(pessoaOriginal.getId(), pessoa.getNome(), pessoa.getTelefone(),
				pessoa.getDataNascimento());
		repo.save(editPessoa);
	}

}