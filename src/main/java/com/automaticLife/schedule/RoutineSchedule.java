package com.automaticLife.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.automaticLife.controller.CongratulationsController;

@Component
public class RoutineSchedule {

	private static final String TIME_ZONE = "America/Sao_Paulo";

	@Autowired
	CongratulationsController CongratulationsController;

	@Scheduled(cron = "0 0 9", zone = TIME_ZONE)
	public void routine() {
		CongratulationsController.sendCongratulations();
	}

}
