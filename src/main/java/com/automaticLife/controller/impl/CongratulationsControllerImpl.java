package com.automaticLife.controller.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automaticLife.controller.CongratulationsController;
import com.automaticLife.repository.entity.People;
import com.automaticLife.service.PeopleService;
import com.automaticLife.service.external.TwilioService;

@RestController
public class CongratulationsControllerImpl implements CongratulationsController {

	@Autowired
	PeopleService peopleService;

	@Autowired
	TwilioService twilioService;

	@PostMapping("/congratulations")
	public ResponseEntity<Void> sendCongratulations() {

		List<People> birthdays = peopleService.searchBirthdaysFromDay(LocalDateTime.now());

		if (birthdays.isEmpty()) {
			return ResponseEntity.status(204).build();
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