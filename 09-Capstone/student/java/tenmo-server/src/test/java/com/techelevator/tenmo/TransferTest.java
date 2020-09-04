package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.techelevator.tenmo.model.Transfers;

public class TransferTest {
	Transfers transfer = new Transfers();
	
	@Test
	public void transferId_returns_5() {
		transfer.setTransferId(5);
		assertEquals(transfer.getTransferId(), 5);
	}
	
	@Test
	public void transferTypeId_returns_5() {
		transfer.setTransferTypeId(5);
		assertEquals(transfer.getTransferTypeId(), 5);
	}
	
	@Test
	public void transferStatusId_returns_5() {
		transfer.setTransferStatusId(5);
		assertEquals(transfer.getTransferStatusId(), 5);
	}
	
	@Test
	public void accountFrom_returns_5() {
		transfer.setAccountFrom(5);
		assertEquals(transfer.getAccountFrom(), 5);
	}
	
	@Test
	public void accountTo_returns_5() {
		transfer.setAccountTo(5);
		assertEquals(transfer.getAccountTo(), 5);
	}
	
	@Test
	public void account_returns_5() {
		transfer.setAmount(BigDecimal.valueOf(5));
		assertEquals(transfer.getAmount(), BigDecimal.valueOf(5));
	}
	
	@Test
	public void transferType_returns_Hello() {
		transfer.setTransferType("Hello");
		assertEquals(transfer.getTransferType(), "Hello");
	}
	
	@Test
	public void transferStatus_returns_Hello() {
		transfer.setTransferStatus("Hello");
		assertEquals(transfer.getTransferStatus(), "Hello");
	}
	
	@Test
	public void userFrom_returns_Hello() {
		transfer.setUserFrom("Hello");
		assertEquals(transfer.getUserFrom(), "Hello");
	}
	
	@Test
	public void userTo_returns_Hello() {
		transfer.setUserTo("Hello");
		assertEquals(transfer.getUserTo(), "Hello");
	}
	
}
