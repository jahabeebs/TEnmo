package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.TransferNotFoundException;
import com.techelevator.tenmo.model.Transfers;

@Component
public class JDBCTransfersDAO implements TransfersDAO {

	private JdbcTemplate jdbcTemplate;
	private AccountDAO accountDAO;
	private DataSource ds;
	
	public JDBCTransfersDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Transfers> getAllTransfers(int userId) {
		List<Transfers> list = new ArrayList<>();
		String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo, a.user_id AS fromUserId FROM transfers t \r\n" + 
				"JOIN accounts a ON t.account_from = a.account_id\r\n" + 
				"JOIN accounts b ON t.account_to = b.account_id\r\n" + 
				"JOIN users u ON a.user_id = u.user_id\r\n" + 
				"JOIN users v ON b.user_id = v.user_id\r\n" + 
				"WHERE a.user_id = ? OR b.user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
		System.out.println("-------------------------------------------\r\n" + 
				"Transfers\r\n" + 
				"ID          From/To                 Amount\r\n" + 
				"-------------------------------------------\r\n"); 
		while (results.next() ) {
			Transfers transfer = mapRowToTransfer(results);
			list.add(transfer);
			String fromOrTo = "";
			String name = "";
			if (userId == results.getInt("fromUserId")) {
				fromOrTo = "From: ";
				name = results.getString("userFrom");
			} else {
				fromOrTo = "To: ";
				name = results.getString("userTo");
			}
			System.out.println(transfer.getTransferId() +"\t\t" + fromOrTo + name + "\t\t$" + transfer.getAmount());
		}
		System.out.print("-------------------------------------------\r\n" + 
		"Please enter transfer ID to view details (0 to cancel): ");
		return list;
	}

	@Override
	public Transfers getTransferById(int transactionId) {
		Transfers transfer = new Transfers();
		String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo, ts.transfer_status_desc, tt.transfer_type_desc FROM transfers t " + 
				"JOIN accounts a ON t.account_from = a.account_id " + 
				"JOIN accounts b ON t.account_to = b.account_id " + 
				"JOIN users u ON a.user_id = u.user_id " + 
				"JOIN users v ON b.user_id = v.user_id " + 
				"JOIN transfer_statuses ts ON t.transfer_status_id = ts.transfer_status_id " + 
				"JOIN transfer_types tt ON t.transfer_type_id = tt.transfer_type_id " + 
				"WHERE t.transfer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
		if (results.next()) {
			transfer = mapRowToTransfer(results);
			System.out.println("--------------------------------------------\r\n" + 
				"Transfer Details\r\n" + 
				"--------------------------------------------\r\n" + 
				" Id: " + transfer.getTransferId() + "\r\n" + 
				" From: " + results.getString("userFrom") + "\r\n" + 
				" To: " + results.getString("userTo") + "\r\n" + 
				" Type: " + results.getShort("transfer_type_desc") + "\r\n" + 
				" Status: " + results.getString("transfer_status_desc") + "\r\n" + 
				" Amount: $" + transfer.getAmount());
		} else {
			throw new TransferNotFoundException();
		}
		return transfer;
	}

	@Override
	public void sendTransfer(int userFrom, int userTo, BigDecimal amount) {
		Account from = accountDAO.findUserById(userFrom);
		Account to = accountDAO.findUserById(userTo);
		JDBCAccountDAO fromDAO = new JDBCAccountDAO(jdbcTemplate, from);
		fromDAO.subtractFromBalance(amount);
		JDBCAccountDAO toDAO = new JDBCAccountDAO(jdbcTemplate, to);
		toDAO.addToBalance(amount);
		String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " + 
				"VALUES (2, 2, ?, ?, ?)";
		jdbcTemplate.update(sql, from.getAccountId(), to.getAccountId(), amount);
	}
	
	private Transfers mapRowToTransfer(SqlRowSet results) {
		Transfers transfer = new Transfers();
		transfer.setTransferId(results.getInt("transfer_id"));
		transfer.setTransferTyepId(results.getInt("transfer_type_id"));
		transfer.setTransferStatusId(results.getInt("transfer_status_id"));
		transfer.setAccountFrom(results.getInt("account_From"));
		transfer.setAccountTo(results.getInt("account_to"));
		transfer.setAmount(results.getBigDecimal("amount"));
		return transfer;
	}

}
