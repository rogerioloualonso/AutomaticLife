package com.automaticLife.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automaticLife.controller.dto.PeopleDTO;
import com.automaticLife.repository.PeopleRepository;
import com.automaticLife.repository.entity.People;

@Service
public class PeopleService {

	@Autowired
	private PeopleRepository repo;

	public Boolean validate(PeopleDTO pessoa) {

		if (pessoa == null) {
			return false;
		} else {
			if (pessoa.getName() == null || pessoa.getBirthday() == null || pessoa.getPhoneNumer() == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	public List<People> searchBirthdays() {
		return repo.searchBirthdays("05", "06");
	}

	public Optional<People> searchById(int id) {
		return repo.findById(id);
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public void insert(PeopleDTO pessoa) {
		People novaPessoa = new People(pessoa.getName(), pessoa.getPhoneNumer(), pessoa.getBirthday());
		repo.save(novaPessoa);
	}

	public void edit(People pessoaOriginal, PeopleDTO pessoa) {
		People editPessoa = new People(pessoaOriginal.getId(), pessoa.getName(), pessoa.getPhoneNumer(),
				pessoa.getBirthday());
		repo.save(editPessoa);
	}

}