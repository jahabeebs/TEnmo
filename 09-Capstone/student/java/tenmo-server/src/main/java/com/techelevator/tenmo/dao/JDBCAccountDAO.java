package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.model.Account;

public class JDBCAccountDAO implements AccountDAO {
	private JdbcTemplate jdbcTemplate;
	private Account account;
	
	public JDBCAccountDAO(JdbcTemplate jdbcTemplate, Account account) {
		this.jdbcTemplate = jdbcTemplate;
		this.account = account;
		
	}
	
	@Override
	public BigDecimal getBalance(int userId) {
		String sqlString = "SELECT balance FROM accounts WHERE user_id = ?";
		SqlRowSet results = null;
		BigDecimal balance = null;
	try {
		results = jdbcTemplate.queryForRowSet(sqlString, userId);
		balance = results.getBigDecimal("balance");
	} catch (DataAccessException e) {
		System.out.println("Error accessing data");
	}
	return balance;
	}

	@Override
	public BigDecimal addToBalance(BigDecimal amountToAdd) {
		String sqlString = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		try {
			jdbcTemplate.update(sqlString, account.getBalance(), account.getUserId());
		} catch (DataAccessException e) {
			System.out.println("Error accessing data");
		}
		account.setBalance(account.getBalance().add(amountToAdd));
		System.out.println("Your new account balance is: " + account.getBalance());
		return account.getBalance();
	}

	@Override
	public BigDecimal subtractFromBalance(BigDecimal amountToSubtract) {
		String sqlString = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		try {
			jdbcTemplate.update(sqlString, account.getBalance(), account.getUserId());
		} catch (DataAccessException e) {
			System.out.println("Error accessing data");
		}
		account.setBalance(account.getBalance().subtract(amountToSubtract));
		System.out.println("Your new account balance is: " + account.getBalance());
		return account.getBalance();
	}

	@Override
	public Account findUserById(int userId) {
		String sqlString = "SELECT * FROM accounts WHERE user_id = ?";
		try {
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlString, userId);
			account = mapRowToAccount(result);
		} catch (DataAccessException e) {
			System.out.println("Error accessing data");
		}
		return account;
	}

	private Account mapRowToAccount(SqlRowSet result) {
		account.setBalance(result.getBigDecimal("balance"));
		account.setAccountId(result.getInt("account_id"));
		account.setUserId(result.getInt("user_id"));
		return account;
	}

}