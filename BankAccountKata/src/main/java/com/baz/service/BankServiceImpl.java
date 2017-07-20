package com.baz.service;

import java.util.Date;
import java.util.List;

import com.baz.dao.IAccountRepository;
import com.baz.dao.IOperationRepository;
import com.baz.entities.Account;
import com.baz.entities.Operation;
import com.baz.entities.Withdrawal;
import com.baz.entities.DepositTransfer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankServiceImpl implements IBankService{
	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private IOperationRepository operationRepository;

	private static Logger logger = Logger.getLogger(BankServiceImpl.class);
	
	@Override
	public Account consultAccount(String codeAccount) {
		Account cp = accountRepository.findOne(codeAccount);
		if(cp == null) {
			logger.error("We don't find this Account : " + codeAccount); 
			throw new RuntimeException("We don't find this Account");
		}
		return cp;
	}

	@Override
	public void deposit(String codeAccount, double amount) {
		Account cp = consultAccount(codeAccount);
		DepositTransfer v = new DepositTransfer(new Date(), amount, cp);
		operationRepository.save(v);
		cp.setAmount(cp.getAmount() + amount);
		accountRepository.save(cp);
		
	}

	@Override
	public void withdrawal(String codeAccount, double amount) {
		Account cp = consultAccount(codeAccount);
		double facilitesCaisse = cp.getOverdraft();
		if(cp.getAmount() + facilitesCaisse < amount) {
			logger.error("Not enough money in your account : " + amount);
			throw new RuntimeException("Not enough money in your account !!!");
		}
		Withdrawal r = new Withdrawal(new Date(), amount, cp);
		operationRepository.save(r);
		cp.setAmount(cp.getAmount() - amount);
		accountRepository.save(cp);
		
	}

	@Override
	public void transfertToOtherAccount(String codeAccount1, String codeAccount2, double amount) {
		if(codeAccount1.equals(codeAccount2)){
			logger.error("Impossible to send a transfert on the same account");
			throw new RuntimeException("Impossible to send a transfert on the same account");
		}
		withdrawal(codeAccount1,amount);
		deposit(codeAccount2, amount);
		
	}

	@Override
	public List<Operation> listOperation(String codeAccount) {
		
		return operationRepository.listOperation(codeAccount);
	}

	@Override
	public void deleteOperationsByAccount(String codeAccount) {
	
		operationRepository.deleteOperationByAccount(codeAccount);
	}

}