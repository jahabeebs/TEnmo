package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.techelevator.tenmo.model.TransferStatuses;

public class TransferStatusesTest {
	TransferStatuses transfer = new TransferStatuses();
		
		@Test
		public void getTransferStatusId_equals_5() {
			transfer.setTransferStatusId(5);
			assertEquals(transfer.getTransferStatusId(), 5);
		}
		
		@Test
		public void getTransferStatusDesc_equals_Hello() {
			transfer.setTransferStatusDesc("Hello");
			assertEquals(transfer.getTransferStatusDesc(), "Hello");
		}
}


