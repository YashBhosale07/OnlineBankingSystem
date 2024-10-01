package com.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.model.CurrentAccount;
import com.bank.model.FundTransfer;
import com.bank.model.Loan;
import com.bank.model.SavingsAccount;
import com.bank.service.UserService;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;


@Controller
@RequestMapping("/User")
@Getter
@Setter
public class UserloginController {
	
	private Long accountNumberVara;
	
	@Autowired
	UserService service;
	
	@GetMapping("/login")
	public String alreadyUser() {
		return "login";
	}
	@PostMapping("/login")
	public String access(@RequestParam Long accountNumber,@RequestParam String password,Map<String,Object>map ) {
		accountNumberVara=accountNumber;
		System.out.println(accountNumberVara);
		if(service.validateUser(accountNumber, password)) {
			return "homePage";
		}
		map.put("error", "Incorrect Password or AccountNumber");
		
		return "login";
	}
	
	@GetMapping("/loan")
	public String loanApplication() {		
		return "loan";
	}
	@PostMapping("/loan")
	public String applyForLoan(@RequestParam Long phoneNumber,@RequestParam(required = false)String email,@RequestParam String loanType) {	
		Loan l=new Loan();
		CurrentAccount c=new CurrentAccount();
		SavingsAccount s=new SavingsAccount();
		System.out.println(accountNumberVara);
		if(service.findSavingsAccountByAccountNumber(accountNumberVara).isPresent()) {
			s=service.findSavingsAccountByAccountNumber(accountNumberVara).get();
			l.setTypeOfAccount(s.getUser().getAccountType());
			if(email.isEmpty()) {
				l.setEmail(s.getUser().getEmail());
			}else {
				l.setEmail(email);
			}
			l.setUser(s.getUser());
		}else {
			c=service.findCurrentAccountByAccountNumber(accountNumberVara).get();
			l.setTypeOfAccount(c.getUser().getAccountType());
			if(email.isEmpty()) {
				l.setEmail(c.getUser().getEmail());
			}else {
				l.setEmail(email);
			}
			l.setUser(c.getUser());
		}
		l.setLoanType(loanType);
		l.setAccountNumber(accountNumberVara);
		l.setPhoneNumber(phoneNumber);
		service.savedLoanDetails(l);
		return "home";
	}
	
	@GetMapping("/fundTransfer")
	public String transferFund() {
		return "fundTransfer";
	}
	@Transactional
	@PostMapping("/fundTransfer")
	public String transferFundAmount(@RequestParam Long fromAccount,@RequestParam Long toAccount,
	@RequestParam Double amountTransfer) {
		FundTransfer f=new FundTransfer();
		f.setAmountTransfer(amountTransfer);
		f.setFromAccount(fromAccount);
		f.setToAccount(toAccount);
		if(service.findSavingsAccountByAccountNumber(toAccount).isPresent()) {
			Double senderFund=service.findFundByAccountNumber(fromAccount);
			Double reciverFund=service.findFundByAccountNumber(toAccount);
			if(senderFund>=amountTransfer) {
				Double fundSender=senderFund-amountTransfer;
				Double fundReciver=reciverFund+amountTransfer;
				service.UpdateFund(fromAccount, fundSender);
				service.UpdateFund(toAccount, fundReciver);
			}
		}else if(service.findCurrentAccountByAccountNumber(toAccount).isPresent()) {
			Double senderFund=service.findFundByAccountNumber(fromAccount);
			Double reciverFund=service.findFundByAccountNumber(toAccount);
			if(senderFund>=amountTransfer) {
				Double fundSender=senderFund-amountTransfer;
				Double fundReciver=reciverFund+amountTransfer;
				service.UpdateFund(fromAccount, fundSender);
				service.UpdateFund(toAccount, fundReciver);
			}
		}
		return "home";
	}
}
