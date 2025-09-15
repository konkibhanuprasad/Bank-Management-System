package com.banking.service;

import java.util.List;

import com.banking.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accounDto);
	
	AccountDto getAccountById(long id);
	
	AccountDto deposite(long id, double amount);
	
	AccountDto withdraw(long id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(long id);
	
	void transfer(long fromId, long toId, double amount);
}
