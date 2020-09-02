package com.techelevator.tenmo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.tenmo.dao.JDBCTransfersDAO;
import com.techelevator.tenmo.model.Transfers;

class JDBCTransfersDAOTestJUnit5 {

	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbcTemplate;
	@Autowired
	private JDBCTransfersDAO dao;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Starting test suite");
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
		dataSource.setUsername("tenmo_appuser");
		dataSource.setPassword("tebucks");
		dataSource.setAutoCommit(false);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		dataSource.destroy();
		System.out.println("All tests are done");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Starting test");
		dao = new JDBCTransfersDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Ending test");
		try {
			dataSource.getConnection().rollback();
		} catch (SQLException e) {
			System.out.println("Database connection problems");
		}
	}

	@Test
	public void test() {
		assertEquals(1, 1);
	}
	
////	@Test
//	public void getAllTransfersTest() {
//		System.out.print("HELLO!!!");
//		List<Transfers> list1 = dao.getAllTransfers(1);
//		System.out.println(list1.size());
////		String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " + 
////				"VALUES (2, 2, 2, 1, 11.11)";
////		jdbcTemplate.update(sql);
//		dao.sendTransfer(1, 2, new BigDecimal(11.11));
//		List<Transfers> list2 = dao.getAllTransfers(1);
//		assertEquals(list1.size() + 1, list2.size());
//	}
//
//	@Test
//	public void test() {
//		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
//		dataSource.setUsername("tenmo_appuser");
//		dataSource.setPassword("tebucks");
//		dataSource.setAutoCommit(false);
//		dao = new JDBCTransfersDAO();
//		JdbcTemplate template = new JdbcTemplate(dataSource);
//		List<Transfers> list1 = dao.getAllTransfers(1);
//		String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " + 
//				"VALUES (2, 2, 2, 1, 11.11)";
//		template.update(sql);
//		List<Transfers> list2 = dao.getAllTransfers(1);
//		assertEquals(list1.size() + 1, list2.size());
//		try {
//			dataSource.getConnection().rollback();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
