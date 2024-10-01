package com.bank.service;

import java.util.Optional;

import com.bank.model.CurrentAccount;
import com.bank.model.FundTransfer;
import com.bank.model.Loan;
import com.bank.model.SavingsAccount;
import com.bank.model.User;

public interface UserService {

	User saveUser(User user,String accountType);
	boolean userExist(String email);
	SavingsAccount findSavingsAccountNumberByUser(User user);
	CurrentAccount findCurrentAccountNumberByUser(User user);
	Boolean validateUser(Long accountNumber,String password);
	Optional<SavingsAccount> findSavingsAccountByAccountNumber(Long accountNumber);
	Optional<CurrentAccount> findCurrentAccountByAccountNumber(Long accountNumber);
	boolean savedLoanDetails(Loan l);
	Double findFundByAccountNumber(Long accountNumber);
	FundTransfer saveHistory(FundTransfer funds);
	int UpdateFund(Long accountNumber,Double fund);
	
}
