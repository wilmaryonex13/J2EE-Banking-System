package com.springboot.controller;

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
public class UserManagerController {

	@Autowired
	private UserManagerService userManagerService;
	
	@Autowired
	private AccountManagerService accountManagerService;
	
	@RequestMapping(value="/userManager", method = RequestMethod.GET)
	public String userAccountList(Model map) {
		
		/* LIST */
		List<UserManager> userAccountList = userManagerService.getUserAccountList();
		
		map.addAttribute("userAccountList",userAccountList);
		
		return "userManager";
	}
	
	@RequestMapping(params="buttonSearch", value="/userManager", method = RequestMethod.POST)
	public String userAccountSearch(HttpServletRequest request, Model map) {
		
		String inputSearch = request.getParameter("inputSearch");
		
		/* LIST SEARCH */
		String inputSearch1 = inputSearch;
		if(inputSearch.contains(" ")) {
			int spaceIndex = inputSearch.lastIndexOf(" ");
			inputSearch1 = inputSearch1.substring(spaceIndex+1);
			System.out.println(inputSearch1);
		}
		List<UserManager> userAccountList = userManagerService.getUserAccountListSearch(inputSearch, inputSearch1);
		
		map.addAttribute("userAccountList",userAccountList);
		
		return "userManager";
	}
	
	@RequestMapping(params="buttonAdd", value="/userManager", method = RequestMethod.POST)
	public String userAccountAdd(HttpServletRequest request, Model map) {
		
		/* ADD */
		UserManager temp = new UserManager();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String emailAddress = request.getParameter("emailAddress");
		temp.setFirstName(firstName);
		temp.setLastName(lastName);
		temp.setAge(age);
		temp.setEmailAddress(emailAddress);
		userManagerService.addAccount(temp);
		
		/* LIST */
		List<UserManager> userAccountList = userManagerService.getUserAccountList();
		
		map.addAttribute("userAccountList",userAccountList);
		
		map.addAttribute("alert","onload=\"alert('Add successful!')\"");
		
		return "userManager";
	}
	
	@RequestMapping(params="buttonDelete", value="/userManager", method = RequestMethod.POST)
	public String userAccountDelete(HttpServletRequest request, Model map) {
		
		/* DELETE */
		String[] ids = request.getParameterValues("checkbox");
		if(ids != null) {
			boolean isZeroBalance = true;
			loop: for(String item:ids) {
				int userId = Integer.parseInt(item);
				List<AccountManager> bankAccountList = accountManagerService.getBankAccountByUserId(userId);
				for(AccountManager bankAccount:bankAccountList) {
					if(bankAccount.getBalance()!=0) {
						isZeroBalance = false;
						break loop;
					}
				}
			}
			if(isZeroBalance == true) {
				for(String item:ids) {
					int userId = Integer.parseInt(item);
					userManagerService.deleteAccount(userId);
					List<AccountManager> bankAccountList = accountManagerService.getBankAccountByUserId(userId);
					for(AccountManager bankAccount:bankAccountList) {
						accountManagerService.deleteAccount(bankAccount.getId());
					}
				}
			}
			else {
				map.addAttribute("alert","onload=\"alert('Unable to delete!\\nAvailable account balance is not zero.')\"");
				
				/* LIST */
				List<UserManager> userAccountList = userManagerService.getUserAccountList();
				
				map.addAttribute("userAccountList",userAccountList);
				
				return "userManager";
			}
		}
		else {
			map.addAttribute("alert","onload=\"alert('Unable to delete!\\nNo selection.')\"");
			
			/* LIST */
			List<UserManager> userAccountList = userManagerService.getUserAccountList();
			
			map.addAttribute("userAccountList",userAccountList);
			
			return "userManager";
		}
		
		/* LIST */
		List<UserManager> userAccountList = userManagerService.getUserAccountList();
		
		map.addAttribute("userAccountList",userAccountList);
		
		map.addAttribute("alert","onload=\"alert('Delete successful!')\"");
		
		return "userManager";
	}
}
