package com.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Staff {

	@Id
	private Long id;
	private String username;
	private String password;
}
