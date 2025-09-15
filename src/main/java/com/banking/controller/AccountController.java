package com.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dto.AccountDto;
import com.banking.dto.TransferDto;
import com.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		
		this.accountService = accountService;
	}
	
	//Rest Api
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//get account by id
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//deposite api
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposite(@PathVariable long id, 
											@RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto accountDto = accountService.deposite(id, amount);
		return ResponseEntity.ok(accountDto);
		
	}
	
	//withdraw api
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable long id, 
												@RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	//all accounts Api
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
		
	}
	
	//delete account using Api
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Successfully deleted");
	}
	
	@PutMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody TransferDto transferDto){
		accountService.transfer(transferDto.getFromId(),
				transferDto.getToId(), 
				transferDto.getAmount());
		return ResponseEntity.ok("Amount Transafer success");
		
	}
	
}
