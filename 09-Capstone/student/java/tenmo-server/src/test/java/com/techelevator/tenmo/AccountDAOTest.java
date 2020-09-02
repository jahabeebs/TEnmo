package com.techelevator.tenmo;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.JDBCAccountDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

public class AccountDAOTest {
	
	private static SingleConnectionDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private AccountDAO accountDAO;
	private User user;
	private Account account;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		accountDAO = new JDBCAccountDAO(jdbcTemplate);
		user = new User();
		addUserToUserTable(user);
		account = new Account();
		//addUserToAccountTable();
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	public void addUserToUserTable(User user) {
		user.setUsername("Jacob");
		user.setPassword("Jacob");
		SqlRowSet result = null;
		try {
		jdbcTemplate.queryForRowSet("INSERT INTO users (username, password_hash) VALUES (?, ?)", user.getUsername(), user.getPassword());
		result = jdbcTemplate.queryForRowSet("SELECT user_id FROM users WHERE username = ? AND password_hash = ?)", user.getUsername(), user.getPassword());
		user.setId(result.getLong("account_id"));
		} catch (DataAccessException e) {
			System.out.println("Error in accessing test data");
		}
	}
	
	public void addUserToAccountTable(User user) {
		user.setUsername("Jacob");
		user.setPassword("Jacob");
		SqlRowSet result = null;
		try {
		jdbcTemplate.queryForRowSet("INSERT INTO users (username, password_hash) VALUES (?, ?)", user.getUsername(), user.getPassword());
		result = jdbcTemplate.queryForRowSet("SELECT user_id FROM users WHERE username = ? AND password_hash = ?)", user.getUsername(), user.getPassword());
		user.setId(result.getLong("account_id"));
		} catch (DataAccessException e) {
			System.out.println("Error in accessing test data");
		}
	}
	
	@Test
	public void account_balance_equal_to_5( ) {
		
	}
	
	
}
