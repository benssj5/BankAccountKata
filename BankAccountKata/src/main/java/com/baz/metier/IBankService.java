package com.baz.metier;

import com.baz.entities.Account;
import com.baz.entities.Operation;

import java.util.List;

public interface IBankService {

	public Account consultAccount(String codeAccount);
	public void deposit(String codeAccount, double amount);
	public void withdrawal(String codeAccount, double amount);
	public void transfertToOtherAccount(String codeAccount1, String codeAccount2, double amount);
	public List<Operation> listOperation(String codeAccount);
	public void deleteOperationsByAccount(String codeAccount);
}