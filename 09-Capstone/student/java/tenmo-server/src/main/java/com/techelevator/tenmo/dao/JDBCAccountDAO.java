package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Account;

@Service 
public class JDBCAccountDAO implements AccountDAO {
	private JdbcTemplate jdbcTemplate;
	private Account account = new Account();
	
	public JDBCAccountDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public JDBCAccountDAO(JdbcTemplate jdbcTemplate, Account account) {
		this.account = account;
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public BigDecimal getBalance(int userId) {
		String sqlString = "SELECT balance FROM accounts WHERE user_id = ?";
		SqlRowSet results = null;
		BigDecimal balance = null;
	try {
		results = jdbcTemplate.queryForRowSet(sqlString, userId);
		if (results.next()) {
		balance = results.getBigDecimal("balance");
		}
	} catch (DataAccessException e) {
		System.out.println("Error accessing data");
	}
	System.out.println("Your account balance is: " + account.getBalance());
	return balance;
	}

	@Override
	public BigDecimal addToBalance(BigDecimal amountToAdd) {
		String sqlString = "UPDATE accounts SET balance = ? WHERE user_id = ?";
		try {
			jdbcTemplate.update(sqlString, account.getBalance().add(amountToAdd), account.getUserId());
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
			jdbcTemplate.update(sqlString, account.getBalance().subtract(amountToSubtract), account.getUserId());
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
