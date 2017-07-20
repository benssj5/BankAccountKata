package com.baz.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baz.entities.Account;
import com.baz.entities.Operation;
import com.baz.entities.Withdrawal;
import com.baz.service.IBankService;
import com.baz.entities.DepositTransfer;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping(value = "/services")
public class ServiceBankRest {

	@Autowired
	private IBankService bankService;
	private static Logger logger = Logger.getLogger(ServiceBankRest.class);
	
	/**
	 * Return account by Id
	 * 
	 * @param codeCpte, l'id du compte
	 * @return ResponseEntity<Compte>
	 */
	@RequestMapping(value = "/compte/{codeAccount}", method = RequestMethod.GET)
	public ResponseEntity<Account> consulterAccount(@PathVariable("codeAccount")String codeAccount) {
		Account account=null;
		try {
			account = bankService.consultAccount(codeAccount);
		} catch (Exception e) {
			return new ResponseEntity<Account>(account, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	
	
	// http://localhost:8080/services/deposit
	@RequestMapping(value = "/deposit", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> deposit(@RequestBody DepositTransfer operation) {
		try {
			String codeAccount = operation.getAccount().getCodeAccount();
			double amount = operation.getAmount();
			bankService.deposit(codeAccount, amount);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	// http://localhost:8080/services/withdrawal
	@RequestMapping(value = "/withdrawal", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> withdrawal(@RequestBody Withdrawal operation) {
		try {
			String codeCpte = operation.getAccount().getCodeAccount();
			double amount = operation.getAmount();
			bankService.withdrawal(codeCpte, amount);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	/**
	 * implementer pour une futur version
	 * @param codeAccount1, id of account to send money
	 * @param codeAccount2, id of account to receive money
	 * @param montant
	 * @return true if all is OK
	 */
	// http://localhost:8080/services/transfer?codeAccount1=012345&codeAccount2=987654&amount=500
	@RequestMapping(value = "/transfer", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> transfer(@RequestParam String codeAccount1, String codeAccount2, double amount) {
		
		try {
			bankService.transfertToOtherAccount(codeAccount1, codeAccount2, amount);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/operations/{codeAccount}", method = RequestMethod.GET)
	public ResponseEntity<List<Operation>> listOperation(@PathVariable String codeAccount){
		
		List<Operation> operations=null;
		try {
			operations = bankService.listOperation(codeAccount);
		} catch (Exception e) {
			return new ResponseEntity<List<Operation>>(operations, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Operation>>(operations, HttpStatus.OK);
		
	}
	
	
	
}
