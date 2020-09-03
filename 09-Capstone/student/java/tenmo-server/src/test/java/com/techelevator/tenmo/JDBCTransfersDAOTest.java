package com.techelevator.tenmo;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.tenmo.dao.JDBCTransfersDAO;
import com.techelevator.tenmo.model.Transfers;

public class JDBCTransfersDAOTest {

//	private static SingleConnectionDataSource dataSource;
//	private JdbcTemplate jdbcTemplate;
//	private JDBCTransfersDAO dao;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		System.out.println("Starting test suite");
//		dataSource = new SingleConnectionDataSource();
//		dataSource.setUrl("Http://localhost:8080");
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("postgres1");
//		dataSource.setAutoCommit(false);
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		dataSource.destroy();
//		System.out.println("All tests are done");
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		System.out.println("Starting test");
//		jdbcTemplate = new JdbcTemplate(dataSource);
//		dao = new JDBCTransfersDAO();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		System.out.println("Ending test");
//		try {
//			dataSource.getConnection().rollback();
//		} catch (SQLException e) {
//			System.out.println("Database connection problems");
//		}
//	}
//
//	@Test
//	public void getAllTransfersTest() {
//		List<Transfers> list1 = dao.getAllTransfers(1);
//		String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " + 
//				"VALUES (2, 2, 2, 1, 11.11)";
//		jdbcTemplate.update(sql);
//		List<Transfers> list2 = dao.getAllTransfers(1);
//		assertEquals(list1.size() + 1, list2.size());
//	}

}
