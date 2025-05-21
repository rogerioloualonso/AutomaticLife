package com.automaticLife.service;

import java.time.LocalDateTime;
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

	public List<People> searchBirthdaysFromDay(LocalDateTime today) {
		return repo.searchBirthdays(Integer.toString(today.getMonth().getValue()),
				Integer.toString(today.getDayOfMonth()));
	}

	public Optional<People> searchById(int id) {
		return repo.findById(id);
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public void insert(PeopleDTO pessoa) {
		repo.save(new People(pessoa.getName(), pessoa.getPhoneNumer(), pessoa.getBirthday()));
	}

	public void edit(int id, PeopleDTO pessoa) {
		repo.save(new People(id, pessoa.getName(), pessoa.getPhoneNumer(), pessoa.getBirthday()));
	}

}