package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.Loan;

public interface LoanDAO extends JpaRepository<Loan, Integer> {

}
