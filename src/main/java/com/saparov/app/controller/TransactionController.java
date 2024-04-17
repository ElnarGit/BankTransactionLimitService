package com.saparov.app.controller;

import com.saparov.app.model.Transaction;
import com.saparov.app.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Transaction getTransactionById(@PathVariable("id") Long id){
		return transactionService.findTransactionById(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Transaction> getAllTransactions(){
		return transactionService.findAllTransactions();
	}
	
	@GetMapping("/limit-exceeded")
	@ResponseStatus(HttpStatus.OK)
	public List<Transaction> getTransactionsByLimitExceededTrue(){
		return transactionService.findByTransactionsLimitExceededTrue();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction createTransaction(@Valid @RequestBody Transaction transaction){
		return transactionService.createTransaction(transaction);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Transaction updateTransaction(@PathVariable("id") Long id,
										 @Valid @RequestBody Transaction updatedTransaction){
		
		return transactionService.updateTransaction(id, updatedTransaction);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteTransactionById(@PathVariable("id") Long id){
		transactionService.deleteTransactionById(id);
		return "Transaction deleted";
	}
}
