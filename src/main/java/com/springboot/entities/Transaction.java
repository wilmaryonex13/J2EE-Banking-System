package com.springboot.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "bankingdb", name="history")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String accountNumber;
	private String date;
	private String action;
	private int before_amount;
	private int amount;
	private int after_amount;
//	private String firstName;
//	private String lastName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getBefore_amount() {
		return before_amount;
	}
	public void setBefore_amount(int before_amount) {
		this.before_amount = before_amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAfter_amount() {
		return after_amount;
	}
	public void setAfter_amount(int after_amount) {
		this.after_amount = after_amount;
	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
	
}
