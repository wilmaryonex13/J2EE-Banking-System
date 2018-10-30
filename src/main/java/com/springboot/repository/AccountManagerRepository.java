package com.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entities.AccountManager;

@Repository
public interface AccountManagerRepository extends CrudRepository<AccountManager, Integer>{
	public List<AccountManager> findByAccountNumberContaining(String accountNumber);
	public List<AccountManager> findByUserId(int userId);
	public AccountManager findByAccountNumber(String accountNumber);
	public List<AccountManager> findAllByOrderByAccountNumberAsc();
}
