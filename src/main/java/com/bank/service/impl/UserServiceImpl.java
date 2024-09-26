package com.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.CurrentAccountDAO;
import com.bank.dao.SavingAccountsDAO;
import com.bank.dao.UserDAO;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.User;
import com.bank.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CurrentAccountDAO currentAccountDAO;
	
	@Autowired 
	private SavingAccountsDAO savingAccountsDAO;
	
	//method to check whether the userExist or not
	@Override
	public boolean userExist(String email) {
		if(userDAO.findByEmail(email).isPresent()) {
			return true;
		}
		return false;
	}
	
	//method for account number generation
	public int createAccountNumber() {
		return (int)(Math.random()*1000);
	}
	
	@Override
	public User saveUser(User user,String accountType) {
		Integer accountNumber=createAccountNumber();
		userDAO.save(user);
		if(accountType.equalsIgnoreCase("savings")) {
			SavingsAccount savingsAccount=new SavingsAccount();
			savingsAccount.setAccountNumber(accountNumber);
			savingsAccount.setUser(user);
			savingAccountsDAO.save(savingsAccount);
			
		}else if(accountType.equalsIgnoreCase("current")) {
			CurrentAccount currentAccount=new CurrentAccount();
			currentAccount.setAccountNumber(accountNumber);
			currentAccount.setUser(user);
			currentAccountDAO.save(currentAccount);
		}
		return user;
	}

	@Override
	public SavingsAccount findAccountNumberByUser(User user) {
		SavingsAccount accounts=savingAccountsDAO.findByUser(user).get();
		return accounts;
	}

	
	
	

}
