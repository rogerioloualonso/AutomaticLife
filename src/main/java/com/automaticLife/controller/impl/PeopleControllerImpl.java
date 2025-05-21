package com.automaticLife.controller.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.automaticLife.controller.PeopleController;
import com.automaticLife.dto.PeopleDTO;
import com.automaticLife.exception.ObjectNotFoundException;
import com.automaticLife.repository.entity.People;
import com.automaticLife.service.PeopleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/people")
public class PeopleControllerImpl implements PeopleController {

	private static Logger logger = LogManager.getLogger(PeopleControllerImpl.class);

	@Autowired
	PeopleService peopleService;

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PeopleDTO people) {
		logger.info("[People] People insert started.");
		peopleService.insert(people);
		logger.info("[People] Insert People finished with success!");
		return ResponseEntity.status(200).build();
	}

	@PutMapping
	public ResponseEntity<Void> edit(@RequestParam int id, @Valid @RequestBody PeopleDTO people) {

		logger.info("[People] Edit people started.");
		Optional<People> entity = peopleService.searchById(id);

		if (entity.isPresent()) {
			peopleService.edit(id, people);
			logger.info("[People] Edit people finished with success!");
			return ResponseEntity.status(200).build();
		} else {
			logger.error("[People] People not found.");
			throw new ObjectNotFoundException("People");
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestParam int id) {
		logger.info("[People] Delete people started.");
		Optional<People> entity = peopleService.searchById(id);

		if (!entity.isEmpty()) {
			peopleService.delete(id);
			logger.info("[People] Delete people finished with success!");
			return ResponseEntity.status(200).build();
		} else {
			logger.error("People not found.");
			throw new ObjectNotFoundException("People");
		}
	}
}
