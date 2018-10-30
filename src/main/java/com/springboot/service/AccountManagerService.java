package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.AccountManager;
import com.springboot.repository.AccountManagerRepository;

@Service
public class AccountManagerService {
	
	@Autowired
	private AccountManagerRepository accountManagerRepository;
	
	public List<AccountManager> getBankAccountList(){
		List<AccountManager> bankAccountList = (List<AccountManager>) accountManagerRepository.findAll();
		return bankAccountList;
	}
	
	public List<AccountManager> getBankAccountListOrderByAccountNumber(){
		List<AccountManager> bankAccountList = (List<AccountManager>) accountManagerRepository.findAllByOrderByAccountNumberAsc();
		return bankAccountList;
	}
	
	public List<AccountManager> getBankAccountListSearch(String accountNumber) {
		List<AccountManager> bankAccountList = accountManagerRepository.findByAccountNumberContaining(accountNumber);
		return bankAccountList;
	}
	
	public AccountManager getBankAccountById(int id) {
		AccountManager bankAccount = accountManagerRepository.findOne(id);
		return bankAccount;
	}
	
	public List<AccountManager> getBankAccountByUserId(int userId) {
		List<AccountManager> bankAccountList = accountManagerRepository.findByUserId(userId);
		return bankAccountList;
	}
	
	public List<AccountManager> getAccountsByAccountNumber(String accountNumber) {
		List<AccountManager> bankAccount = accountManagerRepository.findByAccountNumberContaining(accountNumber);
		return bankAccount;
	}
	
	public AccountManager getAccountByAccountNumber(String accountNumber) {
		return accountManagerRepository.findByAccountNumber(accountNumber);
	}
	
	public void addAccount(AccountManager temp) {
		accountManagerRepository.save(temp);
	}
	
	public void deleteAccount(int id) {
		accountManagerRepository.delete(id);
	}
	
	public void updateAccount(AccountManager account) {
		accountManagerRepository.save(account);
	}
	
	

}
