package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	
	public Transfers[] transfersList() {
		Transfers [] output = null;
		try {
			output = restTemplate.exchange(BASE_URL + "account/transfers/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Transfers[].class).getBody();		
			System.out.println("-------------------------------------------\r\n" + 
					"Transfers\r\n" + 
					"ID          From/To                 Amount\r\n" + 
					"-------------------------------------------\r\n"); 
			String fromOrTo = "";
			String name = "";
			for (Transfers i : output) {
				if (currentUser.getUser().getId() == i.getAccountFrom()) {
					fromOrTo = "From: ";
					name = i.getUserTo();
				} else {
					fromOrTo = "To: ";
					name = i.getUserFrom();
				}
				System.out.println(i.getTransferId() +"\t\t" + fromOrTo + name + "\t\t$" + i.getAmount());
			}
			System.out.print("-------------------------------------------\r\n" + 
					"Please enter transfer ID to view details (0 to cancel): ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (Integer.parseInt(input) != 0) {
				boolean foundTransferId = false;
				for (Transfers i : output) {
					if (Integer.parseInt(input) == i.getTransferId()) {
						restTemplate.exchange(BASE_URL + "transfers/" + i.getTransferId(), HttpMethod.GET, makeAuthEntity(), Transfers.class);
						foundTransferId = true;
						System.out.println("--------------------------------------------\r\n" + 
						"Transfer Details\r\n" +
						"--------------------------------------------\r\n" + 
						" Id: "+ i.getTransferId() + "\r\n" + 
						" From: " + i.getUserFrom() + "\r\n" +
						" To: " + i.getUserTo() + "\r\n" + 
						" Type: " + i.getTransferType() + "\r\n" + 
						" Status: " + i.getTransferStatus() + "\r\n" + 
						" Amount: $" + i.getAmount());
					}
				}
				if (!foundTransferId) {
					System.out.println("Not a valid transfer ID");
				} 
			}
		} catch (Exception e) {
			System.out.println("Something went wrong... Opps! We have all your money now!");
		}
		return output;
	}

	public BigDecimal getBalance() {
		Account account = new Account();
		try {
			account = restTemplate.exchange(BASE_URL + "balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
			System.out.println("Your current account balance is: $" + account.getBalance());
		} catch (RestClientException e) {
			System.out.println("Error getting balance");
		}
		return account.getBalance();
	}
	
	public User[] getUsers() {
		User[] user = null;
		try {
			user = restTemplate.exchange(BASE_URL + "listusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
			for (User i : user) {
				System.out.println(i);
			}
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
