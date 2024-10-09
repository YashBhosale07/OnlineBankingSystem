package com.bank.service.impl;

import java.util.List;
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
	    // Directly return the result from the DAO, which should already be an Optional
	    return savingAccountsDAO.findByAccountNumber(accountNumber);  // This returns Optional<SavingsAccount>
	}

	@Override
	public Optional<CurrentAccount> findCurrentAccountByAccountNumber(Long accountNumber) {
	    // Directly return the result from the DAO, which should already be an Optional
	    return currentAccountDAO.findByAccountNumber(accountNumber);  // This returns Optional<CurrentAccount>
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
	public Double findFundByAccountNumber(Long accountNumber) {
	    // Check for the fund in the savings account first
	    Double fund = savingAccountsDAO.findFundByAccountNumber(accountNumber);
	    
	    // If fund is not found or null, check the current account
	    if (fund == null || fund <= 0) {
	        fund = currentAccountDAO.findFundByAccountNumber(accountNumber);
	    }
	    
	    // Return the fund amount (could still be null if not found in both accounts)
	    return fund;
	}


	@Override
	public int UpdateFund(Long accountNumber, Double fund) {
		if(savingAccountsDAO.findByAccountNumber(accountNumber).isPresent()) {
			return savingAccountsDAO.updateFund(accountNumber, fund);
			 
		}
		return currentAccountDAO.updateFund(accountNumber, fund);
	}

	@Override
	public List<FundTransfer> findByFromAccount(Long fromAccount) {
		return fundTransferDAO.findByFromAccount(fromAccount) ;
	}

}
