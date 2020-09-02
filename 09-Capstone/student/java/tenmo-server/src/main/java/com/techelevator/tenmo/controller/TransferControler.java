package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.JDBCTransfersDAO;
import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.security.jwt.TokenProvider;

@PreAuthorize("isAuthorized()")
@RestController
public class TransferControler {

	private JDBCTransfersDAO transfersDAO;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
	public TransferControler(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
		this.transfersDAO = new JDBCTransfersDAO();
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
	}
	
	@RequestMapping(path = "/account/transfers/{id}", method = RequestMethod.GET)
	private List<Transfers> getAllMyTransfers(@PathVariable int id) {
		List<Transfers> output = transfersDAO.getAllTransfers(id);
		return output;
	}
	
	@RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
	public Transfers getSelectedTransfer(@PathVariable int id) {
		Transfers transfer = transfersDAO.getTransferById(id);
		return transfer;
	}
	
	@RequestMapping(path = "/transfers", method = RequestMethod.POST)
	public void sendTransferRequest(@RequestBody Transfers transfer) {
		transfersDAO.sendTransfer(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
	}
	
}
