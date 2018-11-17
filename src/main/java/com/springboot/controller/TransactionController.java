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
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountListOrderByAccountNumber();
		
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
		
		//TRANSIENT
		account.setUser(userManagerService.getUserNameById(account.getUserId()));
		
		boolean result = transactionService.withdrawTransaction(amount, account);
		
		if(result == true) {
			map.addAttribute("alert","onload=\"alert('Withdrawal successful!')\"");
		}else {
			map.addAttribute("alert","onload=\"alert('Your balance is not enough for this transaction!')\"");
		}
		
		List<Transaction> transactions = transactionService.getAllTransactions();
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountListOrderByAccountNumber();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		map.addAttribute("bankAccountList",bankAccountList);
		
		return "transactions";
		
	}
	
	@RequestMapping(params = "deposit", value="/transactions", method=RequestMethod.POST)
	public String depositTransaction(HttpServletRequest request, ModelMap map) {
		
		String accountNumber = request.getParameter("option");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		AccountManager account = accountManagerService.getAccountByAccountNumber(accountNumber);
		
		/* TRANSIENT */
		account.setUser(userManagerService.getUserNameById(account.getUserId()));
		
		transactionService.depositTransaction(amount, account);
			   
		//Update account information
		accountManagerService.updateAccount(account);
		
		map.addAttribute("alert","onload=\"alert('Deposit successful!')\"");
		
		List<Transaction> transactions = transactionService.getAllTransactions();
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountListOrderByAccountNumber();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		map.addAttribute("bankAccountList",bankAccountList);
		
		return "transactions";
	}
	
	@RequestMapping(params = "buttonSearch", value="/transactions", method=RequestMethod.POST)
	public String searchByAccountNumber(HttpServletRequest request, ModelMap map) {
		
		String inputSearch = request.getParameter("inputSearch");
		String inputSearch1 = request.getParameter("inputSearch1");
		
		List<Transaction> transactions = null;
		
		if(!inputSearch.equals("") && !inputSearch1.equals("")) {
			transactions = transactionService.searchByAccountNumberAndFirstNameOrLastName(inputSearch,inputSearch1,inputSearch1);
		}
		else if(!inputSearch.equals("") && inputSearch1.equals("")) {
			transactions = transactionService.searchByAccountNumber(inputSearch);
		}
		else if(inputSearch.equals("") && !inputSearch1.equals("")) {
			transactions = transactionService.searchByFirstNameOrLastName(inputSearch1,inputSearch1);
		}
		else {
			transactions = transactionService.getAllTransactions();
		}
		
		
		map.addAttribute("Transactions", transactions);
		
		/* OPTION */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountListOrderByAccountNumber();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		map.addAttribute("bankAccountList",bankAccountList);
		
		return "/transactions";
	}
	
}