package com.automaticLife.util;

import com.automaticLife.repository.entity.People;

public class Prompt {

	public static String createPromptToCongratulations(People people) {

		String solicitation = "Create a beautiful message to WhatsApp without skip line to " + people.getName() + ".";

		return solicitation;
	}

}
