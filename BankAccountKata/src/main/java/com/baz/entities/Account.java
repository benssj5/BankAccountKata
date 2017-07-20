package com.baz.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Account implements Serializable{

	@Id
	private String codeAccount;
	private Date dateCreation;
	private double amount;
	private double overdraft;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CODE_CLI")
	private Client client;
	
	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Operation> operations;

	public Account(String codeAccount, Date dateCreation, double amount, double overdraft, Client client) {
		super();
		this.codeAccount = codeAccount;
		this.dateCreation = dateCreation;
		this.amount = amount;
		this.overdraft = overdraft;
		this.client = client;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCodeAccount() {
		return codeAccount;
	}

	public void setCodeAccount(String codeAccount) {
		this.codeAccount = codeAccount;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}



	
	
}
