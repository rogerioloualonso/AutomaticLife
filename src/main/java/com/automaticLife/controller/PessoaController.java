package com.automaticLife.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.automaticLife.controller.dto.PeopleDTO;
import com.automaticLife.repository.entity.People;
import com.automaticLife.service.PeopleService;

@Controller
@RequestMapping("/people")
public class PessoaController {

	@Autowired
	PeopleService peopleService;

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody PeopleDTO people) {

		if (peopleService.validate(people)) {
			peopleService.insert(people);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).body("Dados enviados incorretamente");
		}
	}

	@PutMapping
	public ResponseEntity<String> edit(@RequestParam int id, @RequestBody PeopleDTO people) {

		if (peopleService.validate(people)) {
			Optional<People> entity = peopleService.searchById(id);
			if (entity.isPresent()) {
				peopleService.edit(entity.get(), people);
				return ResponseEntity.status(200).build();
			} else {
				return ResponseEntity.status(400).body("Dados enviados incorretamente");
			}
		} else {
			return ResponseEntity.status(400).body("Dados enviados incorretamente");
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestParam int id) {

		Optional<People> entity = peopleService.searchById(id);

		if (!entity.isEmpty()) {
			peopleService.delete(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(404).build();
		}
	}
}
