package com.automaticLife.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.automaticLife.controller.impl.CongratulationsControllerImpl;

@Component
public class RoutineSchedule {

	private static Logger logger = LogManager.getLogger(RoutineSchedule.class);

	private static final String TIME_ZONE = "America/Sao_Paulo";

	@Autowired
	CongratulationsControllerImpl CongratulationsController;

	@Scheduled(cron = "0 0 9 * * *", zone = TIME_ZONE)
	public void routine() {
		logger.info("[Schedule-routine] Routine started!");
		CongratulationsController.sendCongratulations();
		logger.info("[Schedule-routine] Routine finished!");
	}

}
