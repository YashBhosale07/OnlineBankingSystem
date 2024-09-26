package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/User")
public class UserloginController {

	@GetMapping("/login")
	public String alreadyUser() {
		return "login";
	}
}
