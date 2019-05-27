package com.example.render.gateway;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.render.token.CheckAuthImpl;

import javax.servlet.http.HttpServletResponse;

@Service()
public class ApiGateway {

	@Autowired
	private CheckAuthImpl auth;
	
	public void sendCookie(HttpServletResponse res) throws URISyntaxException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("hijab",auth.getToken());
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		restTemplate.exchange("http://localhost:3000/api/gateway", HttpMethod.POST, entity, String.class);



	}
	
}
