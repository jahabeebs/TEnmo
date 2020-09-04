package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import com.techelevator.tenmo.model.TransferTypes;

public class TransferTypesTest {
	TransferTypes transfer = new TransferTypes();
	
	public void transferTypeId_equals_5() {
		transfer.setTransferTypeId(5);
		assertEquals(transfer.getTransferTypeId(), 5);
	}
	
	public void transferTypeDesc_equals_Hello() {
		transfer.setTransferTypeDesc("Hello");
		assertEquals(transfer.getTransferTypeDesc(), "Hello");
	}
}
