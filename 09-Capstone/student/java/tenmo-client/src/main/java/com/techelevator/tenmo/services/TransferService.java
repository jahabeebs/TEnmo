package com.techelevator.tenmo.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.User;

public class TransferService {

	private String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();
	private AuthenticatedUser currentUser;
	
	public TransferService(String url, AuthenticatedUser currentUser) {
		this.currentUser = currentUser;
		BASE_URL = url;
	}
	
	public List<Transfers> transfersList() {
		List<Transfers> output = restTemplate.exchange(BASE_URL + "account/transfers/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), List.class).getBody();
		return output;
	}
	

	private HttpEntity<Transfers> makeTransferEntity(Transfers transfer) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(currentUser.getToken());
	    HttpEntity<Transfers> entity = new HttpEntity<>(transfer, headers);
	    return entity;
	  }

	  private HttpEntity makeAuthEntity() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(currentUser.getToken());
	    HttpEntity entity = new HttpEntity<>(headers);
	    return entity;
	  }
}
