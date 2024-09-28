package com.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.model.CurrentAccount;
import com.bank.model.User;

@Repository
public interface CurrentAccountDAO extends JpaRepository<CurrentAccount, Integer> {
	Optional<CurrentAccount> findByUser(User user);

	Optional<CurrentAccount> findByAccountNumberAndUser_Password(Long accountNumber, String password);

}
