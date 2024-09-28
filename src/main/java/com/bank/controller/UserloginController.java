package com.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.service.UserService;


@Controller
@RequestMapping("/User")
public class UserloginController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/login")
	public String alreadyUser() {
		return "login";
	}
	@PostMapping("/login")
	public String access(@RequestParam Long accountNumber,@RequestParam String password,Map<String,Object>map ) {
		if(service.validateUser(accountNumber, password)) {
			return "homePage";
		}
		map.put("error", "Incorrect Password or AccountNumber");
		
		return "login";
	}
}
