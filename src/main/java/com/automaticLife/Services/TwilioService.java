package com.automaticLife.Services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automaticLife.Classes.Pessoa;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {
	
	@Autowired
	ChatGPTService chatGPTService;
	
	public static final String ACCOUNT_SID = "SID Aqui!";
	public static final String AUTH_TOKEN = "Auth Tojen aqui!";
	public static final String FROM_NUMERO = "Número aqui!";
	
   public void EnviarMensagens(List<Pessoa> pessoas) throws ParseException {
	   
	   for(Pessoa pessoa : pessoas) {
		   try {
			   
			String solicitacao = "Criar uma mensagem bonita de parabéns para " + pessoa.getNome()
			 + " enviado por Rogério";
			String mensagem = chatGPTService.chatGPT(solicitacao);
			
			Enviar(pessoa.getTelefone(), mensagem);
			
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
   }
   
   public void Enviar(String toNumero, String mensagem) throws ParseException, IOException {
	  
	   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       Message message = Message.creator(
               new PhoneNumber("whatsapp:" + toNumero),
               new PhoneNumber("whatsapp:" + FROM_NUMERO),
               mensagem)
           .create();
   }

}