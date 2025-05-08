package com.automaticLife.service.external;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.automaticLife.repository.entity.Pessoa;
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

	public void EnviarMensagens(List<Pessoa> pessoas) throws ParseException {

		for (Pessoa pessoa : pessoas) {
			try {

				String solicitacao = "Criar uma mensagem bonita de parabéns no Whatsapp sem " + " pular linha para "
						+ pessoa.getNome() + " enviado por Rogério";
				String mensagem = chatGPTService.chatGPT(solicitacao);

				Enviar(pessoa.getTelefone(), mensagem);
				Enviar(pessoa.getTelefone(), "Ass, Rogério Alonso");

			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void Enviar(String toNumero, String mensagem) throws ParseException, IOException {

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message
				.creator(new PhoneNumber("whatsapp:" + toNumero), new PhoneNumber("whatsapp:" + FROM_NUMERO), mensagem)
				.create();
	}

}