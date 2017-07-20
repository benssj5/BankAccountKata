package com.baz.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("W")
public class Withdrawal extends Operation{

	public Withdrawal(Date dateOperation, double amount, Account account) {
		super(dateOperation, amount, account);
		
	}

	public Withdrawal() {
		super();
		
	}

	
	
}