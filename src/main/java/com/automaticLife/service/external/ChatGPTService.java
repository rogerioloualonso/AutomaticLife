package com.automaticLife.service.external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.automaticLife.exception.ChatGPTServiceException;

@Service
public class ChatGPTService {

	@Value("${CHATGPT_APIKEY}")
	private String apiKey;

	@Value("${CHATGPT_URL}")
	private String url;

	@Value("${CHATGPT_MODEL}")
	private String model;

	public String chatGPT(String prompt) {

		try {
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + apiKey);
			connection.setRequestProperty("Content-Type", "application/json");

			String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt
					+ "\"}]}";
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(body);
			writer.flush();
			writer.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			StringBuffer response = new StringBuffer();

			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			br.close();

			return extractMessageFromResponse(response.toString());

		} catch (Exception e) {
			throw new ChatGPTServiceException(e);
		}
	}

	public static String extractMessageFromResponse(String response) {

		int start = response.indexOf("content") + 11;
		int end = response.indexOf("\"", start);

		return response.substring(start, end);
	}
}