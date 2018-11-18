package com.springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entities.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
	public List<Transaction> findAllByOrderByDateDesc();
	public List<Transaction> findByAccountNumberContainingOrderByDateDesc(String accountNumber);
	public List<Transaction> findByFirstNameContainingOrLastNameContainingOrderByDateDesc(String firstName, String lastName);
	public List<Transaction> findByAccountNumberContainingAndFirstNameContainingOrLastNameContainingOrderByDateDesc(
			String accountNumber, String firstName, String lastName);
}
