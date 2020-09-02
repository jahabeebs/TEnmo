package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import com.techelevator.tenmo.model.Account;

public interface AccountDAO {
	BigDecimal getBalance(int userId);
	BigDecimal addToBalance(BigDecimal amountToAdd);
	BigDecimal subtractFromBalance(BigDecimal amountToSubtract);
	Account findUserById(int userId);
	
}
