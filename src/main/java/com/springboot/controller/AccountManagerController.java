package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.entities.AccountManager;
import com.springboot.entities.UserManager;
import com.springboot.service.AccountManagerService;
import com.springboot.service.UserManagerService;

@Controller
public class AccountManagerController {

	@Autowired
	private AccountManagerService accountManagerService;
	
	@Autowired
	private UserManagerService userManagerService;
	
	@RequestMapping(value="/accountManager", method = RequestMethod.GET)
	public String bankAccountList(Model map) {
		
		/* LIST */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		/* OPTION */
		List<UserManager> userAccountList = userManagerService.getUserAccountListOrderByNameAsc();
		
		map.addAttribute("bankAccountList",bankAccountList);
		map.addAttribute("userAccountList",userAccountList);
		
		return "accountManager";
	}
	
	@RequestMapping(params="buttonSearch", value="/accountManager", method = RequestMethod.POST)
	public String bankAccountSearch(HttpServletRequest request, Model map) {
		
		String inputSearch = request.getParameter("inputSearch");
		String inputSearch1 = request.getParameter("inputSearch1");
		
		/* LIST SEARCH */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountListSearch(inputSearch);
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		/* LIST NAME SEARCH */
		String inputSearch2 = inputSearch1;
		if(inputSearch1.contains(" ")) {
			int spaceIndex = inputSearch1.lastIndexOf(" ");
			inputSearch2 = inputSearch2.substring(spaceIndex+1);
			System.out.println(inputSearch2);
		}
		if(inputSearch1 != "") {
			List<AccountManager> tempBankAccountList = new ArrayList<AccountManager>();
			for(AccountManager bankAccount:bankAccountList) {
				if(bankAccount.getUser().getFirstName().toLowerCase().contains(inputSearch1.toLowerCase()) == true ||
				    bankAccount.getUser().getLastName().toLowerCase().contains(inputSearch2.toLowerCase()) == true) {
					tempBankAccountList.add(bankAccount);
				}
			}
			bankAccountList = tempBankAccountList;
		}

		/* OPTION */
		List<UserManager> userAccountList = userManagerService.getUserAccountListOrderByNameAsc();
		
		map.addAttribute("bankAccountList",bankAccountList);
		map.addAttribute("userAccountList",userAccountList);
		
		return "accountManager";
	}
	
	@RequestMapping(params="buttonAdd", value="/accountManager", method = RequestMethod.POST)
	public String userAccountAdd(HttpServletRequest request, Model map) {
		
		/* ADD */
		AccountManager temp = new AccountManager();
		int userId = Integer.parseInt(request.getParameter("option"));
		String accountNumber = request.getParameter("accountNumber");
		int balance = Integer.parseInt(request.getParameter("balance"));
		temp.setUserId(userId);
		temp.setAccountNumber(accountNumber);
		temp.setBalance(balance);
		accountManagerService.addAccount(temp);
		
		/* LIST */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		/* OPTION */
		List<UserManager> userAccountList = userManagerService.getUserAccountListOrderByNameAsc();
		
		map.addAttribute("bankAccountList",bankAccountList);
		map.addAttribute("userAccountList",userAccountList);
		
		map.addAttribute("alert","onload=\"alert('Add successful!')\"");
		
		return "accountManager";
	}
	
	@RequestMapping(params="buttonDelete", value="/accountManager", method = RequestMethod.POST)
	public String userAccountDelete(HttpServletRequest request, Model map) {
		
		/* DELETE */
		String[] ids = request.getParameterValues("checkbox");
		if(ids != null) {
			boolean isZeroBalance = true;
			for(String item:ids) {
				int id = Integer.parseInt(item);
				if(accountManagerService.getBankAccountById(id).getBalance()!=0) {
					isZeroBalance = false;
					break;
				}
			}
			if(isZeroBalance == true) {
				for(String item:ids) {
					int id = Integer.parseInt(item);
					accountManagerService.deleteAccount(id);
				}
			}
			else {
				map.addAttribute("alert","onload=\"alert('Delete Error!\\nAvailable Balance Is Not Zero.')\"");
				
				/* LIST */
				List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
				
				/* TRANSIENT */
				for(AccountManager bankAccount:bankAccountList){
					bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
				}
				
				/* OPTION */
				List<UserManager> userAccountList = userManagerService.getUserAccountListOrderByNameAsc();
				
				map.addAttribute("bankAccountList",bankAccountList);
				map.addAttribute("userAccountList",userAccountList);
					
				return "accountManager";
			}
		}
		else {
			map.addAttribute("alert","onload=\"alert('Delete Error!\\nNo Selection.')\"");
			
			/* LIST */
			List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
			
			/* TRANSIENT */
			for(AccountManager bankAccount:bankAccountList){
				bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
			}
			
			/* OPTION */
			List<UserManager> userAccountList = userManagerService.getUserAccountListOrderByNameAsc();
			
			map.addAttribute("bankAccountList",bankAccountList);
			map.addAttribute("userAccountList",userAccountList);
				
			return "accountManager";
		}
		
		/* LIST */
		List<AccountManager> bankAccountList = accountManagerService.getBankAccountList();
		
		/* TRANSIENT */
		for(AccountManager bankAccount:bankAccountList){
			bankAccount.setUser(userManagerService.getUserNameById(bankAccount.getUserId()));
		}
		
		/* OPTION */
		List<UserManager> userAccountList = userManagerService.getUserAccountListOrderByNameAsc();
		
		map.addAttribute("bankAccountList",bankAccountList);
		map.addAttribute("userAccountList",userAccountList);
		
		map.addAttribute("alert","onload=\"alert('Delete successful!')\"");
			
		return "accountManager";
	}
}
