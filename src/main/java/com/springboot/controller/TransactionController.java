package com.springboot.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.AccountManager;
import com.springboot.entities.Transaction;
import com.springboot.entities.UserManager;
import com.springboot.service.AccountManagerService;
import com.springboot.service.TransactionService;
import com.springboot.service.UserManagerService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountManagerService accountManagerService;
	
	@Autowired
	private UserManagerService userManagerService;
	
	@RequestMapping("/transactions")
	public String getAllTransactions(ModelMap map){
		List<Transaction> transactions = transactionService.getAllTransactions();
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		map.addAttribute("bankAccountList",bankAccountList);
		
		return "transactions";
	}
	
	@RequestMapping(params = "withdraw", value="/transactions", method=RequestMethod.POST)
	public String withdrawTransaction(HttpServletRequest request, ModelMap map) {
		
		String accountNumber = request.getParameter("option");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		AccountManager account = accountManagerService.getAccountByAccountNumber(accountNumber);
		
//		if(account == null) {
//			map.addAttribute("alert","onload=\"alert('Invalid account number!')\"");
//			
//			List<Transaction> transactions = transactionService.getAllTransactions();
//			map.addAttribute("Transactions", transactions);
//			
//			/* OPTION */
//			List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
//			map.addAttribute("bankAccountList",bankAccountList);
//			
//			return "transactions";
//		}
		
		//Withdrawal sequence
		if(account.getBalance() < amount){
			map.addAttribute("alert","onload=\"alert('Your balance is not enough for this transaction!')\"");
			
			List<Transaction> transactions = transactionService.getAllTransactions();
			map.addAttribute("Transactions", transactions);
			
			/* OPTION */
			List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
			
			/* TRANSIENT */
			for(AccountManager bankAccount:bankAccountList){
				bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
			}
			
			map.addAttribute("bankAccountList",bankAccountList);
			
			return "transactions";
		}else {
		   int previousBalance = account.getBalance();
		   int newBalance = account.getBalance()-amount;
		   account.setBalance(newBalance);
		   
		   //Update account information
		   accountManagerService.updateAccount(account);

		   transactionService.withdrawTransaction(accountNumber, amount, account, previousBalance, newBalance);
		}
		
		map.addAttribute("alert","onload=\"alert('Withdrawal successful!')\"");
		
		List<Transaction> transactions = transactionService.getAllTransactions();
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		map.addAttribute("bankAccountList",bankAccountList);
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		return "transactions";
		
	}
	
	@RequestMapping(params = "deposit", value="/transactions", method=RequestMethod.POST)
	public String depositTransaction(HttpServletRequest request, ModelMap map) {
		
		String accountNumber = request.getParameter("option");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		AccountManager account = accountManagerService.getAccountByAccountNumber(accountNumber);
		
//		if(account == null) {
//			map.addAttribute("alert","onload=\"alert('Invalid account number!')\"");
//			
//			List<Transaction> transactions = transactionService.getAllTransactions();
//			map.addAttribute("Transactions", transactions);
//			
//			/* OPTION */
//			List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
//			map.addAttribute("bankAccountList",bankAccountList);
//			
//			return "transactions";
//		}
			
		//Deposit sequence
		   int previousBalance = account.getBalance();
		   int newBalance = account.getBalance()+amount;
		   account.setBalance(newBalance);
			   
		   //Update account information
		   accountManagerService.updateAccount(account);
		
		transactionService.depositTransaction(accountNumber, amount, account, previousBalance, newBalance);
		
		map.addAttribute("alert","onload=\"alert('Deposit successful!')\"");
		
		List<Transaction> transactions = transactionService.getAllTransactions();
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		map.addAttribute("bankAccountList",bankAccountList);
		
		return "transactions";
	}
	
	@RequestMapping(params = "searchByAccountNumber", value="/transactions", method=RequestMethod.POST)
	public String searchByAccountNumber(HttpServletRequest request, ModelMap map) {
		
		String accountNumber = request.getParameter("accountNumber");
		
		List<Transaction> transactions = transactionService.searchByAccountNumber(accountNumber);
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		map.addAttribute("bankAccountList",bankAccountList);
		
		return "/transactions";
	}
	
}