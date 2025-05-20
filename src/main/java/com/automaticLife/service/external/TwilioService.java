package com.automaticLife.service.external;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.automaticLife.exception.TwilioServiceException;
import com.automaticLife.repository.entity.People;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {

	@Autowired
	ChatGPTService chatGPTService;

	@Value("${TWILIO_ACCOUNT}")
	private String ACCOUNT_SID;

	@Value("${TWILIO_AUTH_TOKEN}")
	private String AUTH_TOKEN;

	@Value("${TWILIO_NUMBER}")
	private String FROM_NUMERO;

	public void sendWhatsAppMessage(List<People> peoples) throws ParseException {

		for (People people : peoples) {
			try {

				String solicitation = "Criar uma mensagem bonita de parabéns no Whatsapp sem " + " pular linha para "
						+ people.getName() + " enviado por Rogério";

				String response = chatGPTService.chatGPT(solicitation);

				send(people.getPhoneNumber(), response);

			} catch (Exception e) {
				throw new TwilioServiceException(e);
			}
		}
	}

	public void send(String phoneNumber, String message) throws ParseException, IOException {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message.creator(new PhoneNumber("whatsapp:" + phoneNumber), new PhoneNumber("whatsapp:" + FROM_NUMERO), message)
				.create();
	}

}