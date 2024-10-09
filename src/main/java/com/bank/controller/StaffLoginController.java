package com.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.model.Staff;
import com.bank.service.StaffService;

@Controller
@RequestMapping("/Staff")
public class StaffLoginController {
	
	@Autowired
	private StaffService service;
	
	@GetMapping("/loginStaff")
	public String loginLoadPage() {
		return "loginStaff";
	}
	
	@PostMapping("/loginStaff")
	public String loginPage(@RequestParam String username,@RequestParam String password) {
		Staff s=service.findByUsernameAndPassword(username, password);
		if(s!=null) {
			return "StaffHomePage";
		}else {
			return "ErrorPage";
		}
		
	}
	
	@GetMapping("/savings")
	public String viewAllSavingsAccount(Map<String,Object>model) {
		model.put("savingsAccount", service.viewAllSavingsAccounts());
		return "viewAllSavingsAccount";
	}
	
	@GetMapping("/StaffHomePage")
	public String staffHomePage() {
		return "StaffHomePage";
	}
	
	@GetMapping("/current")
	public String viewAllCurrentAccount(Map<String,Object>model) {
		model.put("currentAccount", service.viewAllCurrentAccounts());
		return "viewAllCurrentAccount";
	}

}
