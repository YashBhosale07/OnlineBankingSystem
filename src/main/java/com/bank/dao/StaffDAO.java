package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Staff;

public interface StaffDAO extends JpaRepository<Staff, Long> {
	
	Staff findByUsernameAndPassword(String Username,String Password);

}
