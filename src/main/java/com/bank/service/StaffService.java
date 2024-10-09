package com.bank.service;

import java.util.List;

import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.Staff;

public interface StaffService {
	
	Staff findByUsernameAndPassword(String Username,String Password);
	List<SavingsAccount>viewAllSavingsAccounts();
	List<CurrentAccount>viewAllCurrentAccounts();

}
