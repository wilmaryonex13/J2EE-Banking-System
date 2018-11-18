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
	
	@Autowired
	private AccountManagerService accountManagerService;

	public List<Transaction> getAllTransactions(){
		List<Transaction> transactions = (List<Transaction>) transactionRepository.findAllByOrderByDateDesc();
		return transactions;
	} 
	
	public boolean withdrawTransaction(int amount, AccountManager account) {
		
		int previousBalance = 0;
		int newBalance = 0;
		
		//Withdrawal sequence
		if(account.getBalance() < amount){
			return false;
		}
		else{
		   previousBalance = account.getBalance();
		   newBalance = previousBalance-amount;
		   account.setBalance(newBalance);
		   
		   //Update account information
		   accountManagerService.updateAccount(account);
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAccountNumber(account.getAccountNumber());
		newTransaction.setDate(LocalDateTime.now().toString());
		newTransaction.setAction("W");
		newTransaction.setAmount(amount);
		newTransaction.setBefore_amount(previousBalance);
		newTransaction.setAfter_amount(newBalance);
		newTransaction.setFirstName(account.getUser().getFirstName());
		newTransaction.setLastName(account.getUser().getLastName());		   
		transactionRepository.save(newTransaction);
		
		return true;
	}
		
	public void depositTransaction(int amount, AccountManager account) {
		
		int previousBalance = 0;
		int newBalance = 0;
		
		previousBalance = account.getBalance();
		newBalance = previousBalance + amount;
		account.setBalance(newBalance);
		
		//Update account information
		accountManagerService.updateAccount(account);
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAccountNumber(account.getAccountNumber());
		newTransaction.setDate(LocalDateTime.now().toString());
		newTransaction.setAction("D");
		newTransaction.setAmount(amount);
		newTransaction.setBefore_amount(previousBalance);
		newTransaction.setAfter_amount(newBalance);
		newTransaction.setFirstName(account.getUser().getFirstName());
		newTransaction.setLastName(account.getUser().getLastName());
		transactionRepository.save(newTransaction);	
	
	}
	
	public List<Transaction> searchByAccountNumber(String accountNumber) {
		
		List<Transaction> transactions = transactionRepository.findByAccountNumberContainingOrderByDateDesc(accountNumber);
		return transactions;
	}
	
	public List<Transaction> searchByFirstNameOrLastName(String firstName, String lastName) {
		
		List<Transaction> transactions = transactionRepository.findByFirstNameContainingOrLastNameContainingOrderByDateDesc(firstName, lastName);
		return transactions;
	}
	
	public List<Transaction> searchByAccountNumberAndFirstNameOrLastName(String accountNumber, String firstName, String lastName) {
		
		List<Transaction> transactions = transactionRepository.findByAccountNumberContainingAndFirstNameContainingOrLastNameContainingOrderByDateDesc(accountNumber,firstName, lastName);
		return transactions;
	}
	
	
}
