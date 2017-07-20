package com.baz.metier;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baz.dao.IClientRepository;
import com.baz.dao.IAccountRepository;
import com.baz.dao.IOperationRepository;
import com.baz.entities.Client;
import com.baz.entities.Account;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	private IClientRepository clientDao;

	@Autowired
	private IAccountRepository accountDao;

	@Autowired
	private IOperationRepository operationDao;

	private static Logger logger = Logger.getLogger(ClientServiceImpl.class);

	@Override
	/**
	 * @param client,
	 * Permit to create a client
	 * @return Client if all is OK, null if KO
	 */
	public Client create(Client client) {
		Client c = null;
		if (findByEmail(client) != null) {
			logger.error("This mail already exist");

		} else {
			Account account;
			client.setAccount(null);
			c = clientDao.save(client);
			String codeAccountAutoMatic = Long.valueOf(new Date().getTime()).toString();
			account = new Account(codeAccountAutoMatic, new Date(), 1000, 200, c);
			account = accountDao.save(account);
			c.setAccount(account);
			logger.debug("Inscription OK");
		}
		return c;
	}

	@Override
	public Client selectById(Long id) {

		Client c = clientDao.findOne(id);
		return c;
	}

	@Override
	/**
	 * Delete a client by his Id
	 * @param id,
	 * @return true if all is OK, false if KO
	 */
	public boolean delete(Long id) {
		Client client = clientDao.findOne(id);
		operationDao.deleteOperationByAccount(client.getAccount().getCodeAccount());
		accountDao.delete(client.getAccount());
		try {
			clientDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		} 
	}


	@Override
	/**
	 * use email and password to connect a client
	 * @param client,
	 * @return client or null if doesn't exist
	 */
	public Client connectLogin(Client c) {
		Client c2 = null;
		List<Client> listCli = null;
		try {
			listCli = clientDao.connectClientLogin(c.getEmail(), c.getPassword());
			if (listCli != null && listCli.size() > 0) {
				c2 = listCli.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException("We don't find this Account");
		}
		return c2;
	}

	/**
	 * search client by email
	 * @param client,
	 * @return Client, null if doesn't exist
	 */
	public Client findByEmail(Client client) {
		Client c2 = null;
		List<Client> listCli = null;
		try {
			listCli = clientDao.returnClientByEmail(client.getEmail());
			if (listCli != null && listCli.size() > 0) {
				c2 = listCli.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException("We don't find this Account");
		}
		return c2;
	}

}
