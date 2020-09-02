package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfers;

public interface TransfersDAO {

	public List<Transfers> getAllTransfers(int userId);
	public Transfers getTransferById(int transactionId);
	
}
