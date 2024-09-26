package com.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.dao.SavingAccountsDAO;
import com.bank.model.SavingsAccount;
import com.bank.model.User;
import com.bank.service.UserService;

@Controller
@RequestMapping("/User")
public class UserRegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String email, @RequestParam String accountType,Map<String,Object>map) {
		if(userService.userExist(email)) {
			map.put("error", "User already exits with the email");
			return "register";
		}
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAccountType(accountType);

		userService.saveUser(user, user.getAccountType());
		SavingsAccount s=userService.findAccountNumberByUser(user);
		map.put("user", user);
		map.put("accountNumber",s.getAccountNumber());
		return "UserDetails";

	}
}
