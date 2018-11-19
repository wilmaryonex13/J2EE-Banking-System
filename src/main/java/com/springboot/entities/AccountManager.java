package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (schema="bankingdb",name="bank_account")
public class AccountManager implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private transient UserManager user;
	
	@Id
	int id;
	int userId;
	String accountNumber;
	int balance;
	int pinNumber;
	
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public UserManager getUser() {
		return user;
	}
	public void setUser(UserManager user) {
		this.user = user;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
}
