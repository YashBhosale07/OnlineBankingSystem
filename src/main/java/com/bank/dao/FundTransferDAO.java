package com.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.FundTransfer;

public interface FundTransferDAO extends JpaRepository<FundTransfer, Long> {
	List<FundTransfer>findByFromAccount(Long fromAccount);	
	

}
