package com.automaticLife.controller.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static Logger logger = LogManager.getLogger(CongratulationsControllerImpl.class);

	@Autowired
	PeopleService peopleService;

	@Autowired
	TwilioService twilioService;

	@PostMapping("/congratulations")
	public ResponseEntity<Void> sendCongratulations() {

		logger.info("Sending congratulations started.");

		List<People> birthdays = peopleService.searchBirthdaysFromDay(LocalDateTime.now());
		logger.info("Number of peoples: {}.", birthdays.size());

		if (birthdays.isEmpty()) {
			logger.info("Sending congratulations finished, people not found.");
			return ResponseEntity.status(204).build();
		} else {
			try {
				twilioService.sendWhatsAppMessage(birthdays);
				logger.info("Sending congratulations finished with success!");
				return ResponseEntity.status(200).build();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}

}