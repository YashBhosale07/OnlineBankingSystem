package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.FundTransfer;

public interface FundTransferDAO extends JpaRepository<FundTransfer, Long> {
	
	
	

}
