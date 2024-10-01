package com.bank.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.CurrentAccountDAO;
import com.bank.dao.FundTransferDAO;
import com.bank.dao.LoanDAO;
import com.bank.dao.SavingAccountsDAO;
import com.bank.dao.UserDAO;
import com.bank.model.CurrentAccount;
import com.bank.model.FundTransfer;
import com.bank.model.Loan;
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
	
	@Autowired
	private LoanDAO loanDAO;
	
	@Autowired
	private FundTransferDAO fundTransferDAO;

	// method to check whether the userExist or not
	@Override
	public boolean userExist(String email) {
		if (userDAO.findByEmail(email).isPresent()) {
			return true;
		}
		return false;
	}

	// method for account number generation
	public long createAccountNumber() {
		return (long) (Math.random() * 9000000000L) + 1000000000L;
	}

	@Override
	public User saveUser(User user, String accountType) {
		Long accountNumber = createAccountNumber();
		userDAO.save(user);
		if (accountType.equalsIgnoreCase("savings")) {
			SavingsAccount savingsAccount = new SavingsAccount();
			savingsAccount.setAccountNumber(accountNumber);
			savingsAccount.setUser(user);
			savingAccountsDAO.save(savingsAccount);

		} else if (accountType.equalsIgnoreCase("current")) {
			CurrentAccount currentAccount = new CurrentAccount();
			currentAccount.setAccountNumber(accountNumber);
			currentAccount.setUser(user);
			currentAccountDAO.save(currentAccount);
		}
		return user;
	}

	@Override
	public SavingsAccount findSavingsAccountNumberByUser(User user) {
		SavingsAccount account = savingAccountsDAO.findByUser(user).get();
		return account;
	}

	@Override
	public CurrentAccount findCurrentAccountNumberByUser(User user) {
		CurrentAccount account = currentAccountDAO.findByUser(user).get();
		return account;
	}

	@Override
	public Boolean validateUser(Long accountNumber, String password) {
		if(savingAccountsDAO.findByAccountNumberAndUser_Password(accountNumber, password).isPresent()) {
			return true;
		}else if(currentAccountDAO.findByAccountNumberAndUser_Password(accountNumber, password).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public Optional<SavingsAccount> findSavingsAccountByAccountNumber(Long accountNumber) {
		if(savingAccountsDAO.findByAccountNumber(accountNumber).isPresent()) {
			return savingAccountsDAO.findByAccountNumber(accountNumber);
		}
		return null;
	}

	@Override
	public Optional<CurrentAccount> findCurrentAccountByAccountNumber(Long accountNumber) {
		if(currentAccountDAO.findByAccountNumber(accountNumber).isPresent()) {
			return currentAccountDAO.findByAccountNumber(accountNumber);
		}
		return null;
	}

	@Override
	public boolean savedLoanDetails(Loan l) {
		loanDAO.save(l);
		return true;
	}

	@Override
	public FundTransfer saveHistory(FundTransfer funds) {
		return fundTransferDAO.save(funds);
	}

	@Override
	public Long findFundByAccountNumber(Long accountNumber) {
		if(savingAccountsDAO.findByAccountNumber(accountNumber).isPresent()) {
			return savingAccountsDAO.findFundByAccountNumber(accountNumber);
		}
		return currentAccountDAO.findFundByAccountNumber(accountNumber);
	}

	@Override
	public int UpdateFund(Long accountNumber, Double fund) {
		if(savingAccountsDAO.findByAccountNumber(accountNumber).isPresent()) {
			return savingAccountsDAO.updateFund(accountNumber, fund);
			 
		}
		return currentAccountDAO.updateFund(accountNumber, fund);
	}

}
