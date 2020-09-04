package com.techelevator.tenmo.controller;

import java.math.BigDecimal; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.JDBCAccountDAO;
import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private TransfersDAO transfersDAO;
	
	public AccountController(AccountDAO accountDAO, UserDAO userDAO, TransfersDAO transfersDAO) {
		this.accountDAO = accountDAO;
		this.userDAO = userDAO;
		this.transfersDAO = transfersDAO;
	}
	
	@RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
	public BigDecimal getBalance(@PathVariable int id) {
		System.out.println(accountDAO);
		BigDecimal balance = accountDAO.getBalance(id);
		return balance;
	}
	
	@RequestMapping(path = "/listusers", method = RequestMethod.GET)
	public List <User> listUsers() {
		List <User> users = userDAO.findAll();
		return users;
	}
	
//	@RequestMapping(path = "/sendmoney", method = RequestMethod.POST)
//	public void sendMoney(@RequestBody Transfers transfer) {
//		transfersDAO.sendTransfer(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
//	}
}
