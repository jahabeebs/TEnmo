package com.techelevator.tenmo.controller;

import java.math.BigDecimal; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private UserDAO userDAO;

	
	public AccountController(AccountDAO accountDAO, UserDAO userDAO) {
		this.accountDAO = accountDAO;
		this.userDAO = userDAO;
	}
	
	@RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
	public BigDecimal getBalance(@PathVariable int id) {
		BigDecimal balance = accountDAO.getBalance(id);
		return balance;
	}
	
	@RequestMapping(path = "listusers", method = RequestMethod.GET)
	public List <User> listUsers() {
		List <User> users = userDAO.findAll();
		return users;
	}
	
}
