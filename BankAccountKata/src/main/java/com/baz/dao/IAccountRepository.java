package com.baz.dao;

import com.baz.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, String>{

}
