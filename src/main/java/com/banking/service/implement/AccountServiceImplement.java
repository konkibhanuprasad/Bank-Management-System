package com.banking.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banking.dto.AccountDto;
import com.banking.entity.Account;
import com.banking.mapping.AccountMapper;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;

@Service
public class AccountServiceImplement implements AccountService{

    private AccountRepository accountRepository;
    
	public AccountServiceImplement(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto); 
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto getAccountById(long id) {
		Account account =accountRepository
				.findById(id)
				.orElseThrow(() ->new RuntimeException("Account Does not Exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposite(long id, double amount) {
		Account account =accountRepository
				.findById(id)
				.orElseThrow(() ->new RuntimeException("Account Does not Exists"));
		
				double totalAmount = account.getBalance() + amount;
				account.setBalance(totalAmount);
				Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public AccountDto withdraw(long id, double amount) {
		Account account =accountRepository
				.findById(id)
				.orElseThrow(() ->new RuntimeException("Account Does not Exists"));
		
				if(account.getBalance() < amount) {
					throw new RuntimeException("Insufficient Amount");
				}
				
				double toatalAmount = account.getBalance() - amount;
				account.setBalance(toatalAmount);
				Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> allAccounts = accountRepository.findAll();
		return allAccounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}



	@Override
	public void deleteAccount(long id) {
		Account account =accountRepository
				.findById(id)
				.orElseThrow(() ->new RuntimeException("Account Does not Exists"));
		 accountRepository.delete(account);
	}



	@Override
	public void transfer(long fromId, long toId, double amount) {
		if(fromId == toId) {
			throw new RuntimeException("cannot transfer to the same account");
		}
		Account fromAccount =accountRepository
				.findById(fromId)
				.orElseThrow(() ->new RuntimeException("Senders Account Not found"));
		
		Account toAccount =accountRepository
				.findById(toId)
				.orElseThrow(() ->new RuntimeException("Receivers Account not found"));
		
				if(fromAccount.getBalance() < amount) {
					throw new RuntimeException("Insufficient Amount");
				}
				
				fromAccount.setBalance(fromAccount.getBalance() - amount);
				toAccount.setBalance(toAccount.getBalance() + amount);
				
				accountRepository.save(fromAccount);
				accountRepository.save(toAccount);
	}
	
}
