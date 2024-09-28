package com.bank.service;

import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.User;

public interface UserService {

	User saveUser(User user,String accountType);
	boolean userExist(String email);
	SavingsAccount findSavingsAccountNumberByUser(User user);
	CurrentAccount findCurrentAccountNumberByUser(User user);
	Boolean validateUser(Long accountNumber,String password);
	
}
