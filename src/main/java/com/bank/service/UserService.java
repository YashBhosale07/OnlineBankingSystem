package com.bank.service;

import com.bank.model.SavingsAccount;
import com.bank.model.User;

public interface UserService {

	User saveUser(User user,String accountType);
	boolean userExist(String email);
	SavingsAccount findAccountNumberByUser(User user);
	
}
