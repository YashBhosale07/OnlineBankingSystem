package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.CurrentAccountDAO;
import com.bank.dao.SavingAccountsDAO;
import com.bank.dao.StaffDAO;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.Staff;
import com.bank.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDAO dao;
	@Autowired
	private SavingAccountsDAO savingsAccountDao;
	@Autowired
	private CurrentAccountDAO currentAccountDAO;
	
	@Override
	public Staff findByUsernameAndPassword(String Username, String Password) {		
		return dao.findByUsernameAndPassword(Username, Password);
	}

	@Override
	public List<SavingsAccount> viewAllSavingsAccounts() {
		// TODO Auto-generated method stub
		return savingsAccountDao.findAll();
	}

	@Override
	public List<CurrentAccount> viewAllCurrentAccounts() {
		// TODO Auto-generated method stub
		return currentAccountDAO.findAll();
	}

}
