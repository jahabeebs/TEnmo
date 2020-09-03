package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
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

	public BigDecimal getBalance() {
		Account account = new Account();
		try {
			account = restTemplate.exchange(BASE_URL + "/balance", HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
		} catch (RestClientException e) {
			System.out.println("Error getting balance");
		}
		return account.getBalance();
	}
	
	public User[] getUsers() {
		User[] user = null;
		try {
			user = restTemplate.exchange(BASE_URL + "/listusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
		} catch (RestClientResponseException e) {
			System.out.println("Error gettng users");
		}
		return user;
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
