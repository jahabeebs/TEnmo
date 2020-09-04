package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.techelevator.tenmo.model.RegisterUserDTO;

public class RegisteredUserDTOTest {
	RegisterUserDTO user = new RegisterUserDTO();
	
	@Test
	public void getUsername_equals_Hello() {
		user.setUsername("Hello");
		assertEquals(user.getUsername(), "Hello");
	}
	
	@Test
	public void getPassword_equals_Hello() {
		user.setPassword("Hello");
		assertEquals(user.getPassword(), "Hello");
	}
}