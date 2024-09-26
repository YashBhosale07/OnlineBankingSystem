package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.CurrentAccount;
@Repository
public interface CurrentAccountDAO extends JpaRepository<CurrentAccount, Integer> {

}
