package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.techelevator.tenmo.model.Account;

public class AccountTest {
	Account account = new Account();
	
	@Test
	public void accountId_equal_to_5() {
		account.setAccountId(5);
		assertEquals(account.getAccountId(), 5);
	}
	
	@Test
	public void userId_equal_to_5() {
		account.setUserId(5);
		assertEquals(account.getUserId(), 5);
	}
	
	@Test
	public void accountBalance_equal_to_5() {
		account.setBalance(BigDecimal.valueOf(5));
		assertEquals(account.getBalance(), BigDecimal.valueOf(5));
	}
}
