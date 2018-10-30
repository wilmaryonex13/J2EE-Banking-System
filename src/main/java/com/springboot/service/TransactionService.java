package com.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.AccountManager;
import com.springboot.entities.Transaction;
import com.springboot.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> getAllTransactions(){
		List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
		return transactions;
	} 
	
	public void withdrawTransaction(String accountNumber, int amount, AccountManager account, int previousBalance, int newBalance) {
		   
		   Transaction newTransaction = new Transaction();
		   newTransaction.setAccount_number(account.getAccountNumber());
		   newTransaction.setDate(LocalDateTime.now().toString());
		   newTransaction.setAction("W");
		   newTransaction.setAmount(amount);
		   newTransaction.setBefore_amount(previousBalance);
		   newTransaction.setAfter_amount(newBalance);
		   transactionRepository.save(newTransaction);
	}
		
	public void depositTransaction(String accountNumber, int amount, AccountManager account, int previousBalance, int newBalance) {
			   
		   Transaction newTransaction = new Transaction();
		   newTransaction.setAccount_number(account.getAccountNumber());
		   newTransaction.setDate(LocalDateTime.now().toString());
		   newTransaction.setAction("D");
		   newTransaction.setAmount(amount);
		   newTransaction.setBefore_amount(previousBalance);
		   newTransaction.setAfter_amount(newBalance);
		   transactionRepository.save(newTransaction);	
	}
	
	public List<Transaction> searchByAccountNumber(String accountNumber) {
		
		List<Transaction> transactions = transactionRepository.findByAccountNumberContaining(accountNumber);
		return transactions;
	}
	
	
}
