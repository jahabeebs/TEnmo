package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.techelevator.tenmo.model.Authority;

public class AuthorityTest {
	Authority authority = new Authority("Hello");
	
	@Test
	public void getName_equal_to_5() {
		authority.setName("Hello");
		assertEquals(authority.getName(), "Hello");
	}
}
