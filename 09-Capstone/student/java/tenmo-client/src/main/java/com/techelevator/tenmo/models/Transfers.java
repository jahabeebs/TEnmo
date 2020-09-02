package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfers {

	private int transferId;
	private int transferTyepId;
	private int transferStatusId;
	private int accountFrom;
	private int accountTo;
	private BigDecimal amount;
	
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getTransferTyepId() {
		return transferTyepId;
	}
	public void setTransferTyepId(int transferTyepId) {
		this.transferTyepId = transferTyepId;
	}
	public int getTransferStatusId() {
		return transferStatusId;
	}
	public void setTransferStatusId(int ransferStatusId) {
		this.transferStatusId = ransferStatusId;
	}
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
