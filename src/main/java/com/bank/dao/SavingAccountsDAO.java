package com.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.User;

@Repository
public interface SavingAccountsDAO extends JpaRepository<SavingsAccount, Long> {
	Optional<SavingsAccount> findByUser(User user);
	Optional<SavingsAccount> findByAccountNumber(Long accountNumber);
	Optional<SavingsAccount> findByAccountNumberAndUser_Password(Long accountNumber, String password);
	@Query("SELECT a.fund FROM SavingsAccount a WHERE a.accountNumber = :accountNumber")
    Double findFundByAccountNumber(Long accountNumber);
	@Modifying
    @Query("UPDATE SavingsAccount a SET a.fund = :fund WHERE a.accountNumber = :accountNumber")
    int updateFund(Long accountNumber,Double fund);
	
}
