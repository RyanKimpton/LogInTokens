package main.com.nationwide.controller;

import main.com.nationwide.persistence.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.com.nationwide.service.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/{username}")
	public Boolean checkUsernameAvailable(@PathVariable String username) {
		return accountService.checkUsername(username);
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@PutMapping 
	public Boolean authenticate(@RequestBody Account account) {
		return accountService.authenticate(account);
	}
	
	@DeleteMapping
	public String deleteAccount(@RequestBody Account account) {
		accountService.deleteAccount(account);
		return "deleted";
	}
	
}
