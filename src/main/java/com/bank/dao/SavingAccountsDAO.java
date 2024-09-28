package com.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.SavingsAccount;
import com.bank.model.User;

@Repository
public interface SavingAccountsDAO extends JpaRepository<SavingsAccount, Integer> {
	Optional<SavingsAccount> findByUser(User user);

	Optional<SavingsAccount> findByAccountNumberAndUser_Password(Long accountNumber, String password);
}
