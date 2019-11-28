package main.com.nationwide.service;

import main.com.nationwide.persistence.domain.Account;
import main.com.nationwide.persistence.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Boolean checkUsername(String username) {
		return !(accountRepository.findByUsername(username).isPresent());
	}
	
	public Account createAccount(Account account) {
		if(checkUsername(account.getUsername())) {
			return accountRepository.saveAndFlush(account);			
		} else {
			throw new RuntimeException("Username already exists");
		}
	}
	
	public Boolean authenticate(Account account) {
		Account savedAccount = accountRepository.findByUsername(account.getUsername()).orElseThrow(() -> new RuntimeException("Account not found"));
		if(savedAccount.getPassword().equals(account.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteAccount(Account account) {
		if(authenticate(account)) {
			accountRepository.delete(account);
		} else {
			throw new RuntimeException("Incorrect username or password");
		}
	}
	
}
