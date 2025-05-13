package com.automaticLife.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.automaticLife.repository.entity.People;
import com.automaticLife.service.PeopleService;
import com.automaticLife.service.external.TwilioService;

@Controller
public class CongratulationsController {

	@Autowired
	PeopleService peopleService;

	@Autowired
	TwilioService twilioService;

	@PostMapping("/congratulations")
	public ResponseEntity<String> desejarParabens() {

		List<People> birthdays = peopleService.searchBirthdaysFromDay(LocalDateTime.now());

		if (birthdays.isEmpty()) {
			return ResponseEntity.status(204).body("Sem aniversariantes neste dia.");
		} else {
			try {
				twilioService.sendWhatsAppMessage(birthdays);
				return ResponseEntity.status(200).build();
			} catch (ParseException e) {
				return ResponseEntity.status(500).build();
			}
		}
	}

}