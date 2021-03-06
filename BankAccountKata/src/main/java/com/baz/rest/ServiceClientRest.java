package com.baz.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baz.entities.Client;
import com.baz.service.IClientService;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping(value = "/services")
public class ServiceClientRest {

	@Autowired
	private IClientService clientService;

	private static Logger logger = Logger.getLogger(ServiceClientRest.class);

	/**
	 * Return Client by id
	 * 
	 * @param id
	 * @return ResponseEntity<Client>
	 */
	@RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> selectById(@PathVariable("id") Long id) {

		Client client = clientService.selectById(id);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	/**
	 * Creation Client
	 * 
	 * @param client
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		Client cl = clientService.create(client);
		if(cl != null) {
			return new ResponseEntity<Client>(cl, HttpStatus.OK);
		} else {
			return new ResponseEntity<Client>(cl, HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/clientsConnect", method = RequestMethod.POST)
	public ResponseEntity<Client> connectClient(@RequestBody Client client) {
		Client cl=null;
		try {
			cl = clientService.connectLogin(client);
		} catch (Exception e) {
			return new ResponseEntity<Client>(cl, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Client>(cl, HttpStatus.OK);
	}

	/**
	 * Delete Client
	 * 
	 * @param client
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteClient(@PathVariable Long id) {
		try {
			clientService.delete(id);
		}
		catch (Exception e) {
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
