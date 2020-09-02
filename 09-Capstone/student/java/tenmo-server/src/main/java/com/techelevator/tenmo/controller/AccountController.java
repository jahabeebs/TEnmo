package com.techelevator.tenmo.controller;

import java.math.BigDecimal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

	private AccountDAO accountDAO;
	
//	public AccountController(AccountDAO accountDAO) {
//		this.accountDAO = accountDAO;
//	}
	
//	@RequestMapping(path = "/balance", method = RequestMethod.GET)
//	public @ResponseBody getBalance() {
//		return balance;
//	}
}
