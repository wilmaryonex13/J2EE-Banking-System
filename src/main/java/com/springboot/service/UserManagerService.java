package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entities.UserManager;
import com.springboot.repository.UserManagerRepository;

@Service
public class UserManagerService {
	
	@Autowired
	private UserManagerRepository userManagerRepository;
	
	public UserManager getUserNameById(int id){
		UserManager userAccount = userManagerRepository.findOne(id);
		return userAccount;
	}
	
	public List<UserManager> getUserAccountList(){
		List<UserManager> userAccountList = (List<UserManager>) userManagerRepository.findAll();
		return userAccountList;
	}
	
	public List<UserManager> getUserAccountListOrderByNameAsc(){
		List<UserManager> userAccountList = (List<UserManager>) userManagerRepository.findAllByOrderByFirstNameAsc();
		return userAccountList;
	}
	
	public List<UserManager> getUserAccountListSearch(String firstName, String lastName){
		List<UserManager> userAccountList = (List<UserManager>) userManagerRepository.findAllByFirstNameContainingOrLastNameContaining(firstName, lastName);
		return userAccountList;
	}
	
	public void addAccount(UserManager temp) {
		userManagerRepository.save(temp);
	}
	
	public void deleteAccount(int id) {
		userManagerRepository.delete(id);
	}

}
