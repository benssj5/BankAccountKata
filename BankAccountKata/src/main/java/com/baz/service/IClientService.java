package com.baz.service;

import com.baz.entities.Client;

public interface IClientService {

	public Client create(Client c);
	public Client connectLogin(Client c);
	public Client selectById(Long id);
	public boolean delete(Long id);
	public Client findByEmail(Client c);

	
}