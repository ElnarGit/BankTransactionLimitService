package com.saparov.app.service;

import com.saparov.app.exception.TransactionNotFoundException;
import com.saparov.app.model.ExchangeRate;
import com.saparov.app.model.Transaction;
import com.saparov.app.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionService {
	private final TransactionRepository transactionRepository;
	private final ExchangeRateService exchangeRateService;
	
	public Transaction findTransactionById(Long id){
		return transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
	}
	
	public List<Transaction> findAllTransactions(){
		return transactionRepository.findAll();
	}
	
	public List<Transaction> findByTransactionsLimitExceededTrue(){
		return transactionRepository.findByLimitExceededTrue();
	}
	
	@Transactional
	public Transaction createTransaction(Transaction transaction){
		return transactionRepository.save(transaction);
	}
	
	@Transactional
	public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
		Transaction transaction = transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
		
		transaction.setAccountFrom(updatedTransaction.getAccountFrom());
		transaction.setAccountTo(updatedTransaction.getAccountTo());
		transaction.setCurrencyShortName(updatedTransaction.getCurrencyShortName());
		transaction.setSum(updatedTransaction.getSum());
		transaction.setExpenseCategory(updatedTransaction.getExpenseCategory());
		transaction.setDatetime(updatedTransaction.getDatetime());
		transaction.setLimitExceeded(updatedTransaction.isLimitExceeded());
		
		return transactionRepository.save(transaction);
		
	}
	
	@Transactional
	public void deleteTransactionById(Long id){
		transactionRepository.findById(id)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
		
		transactionRepository.deleteById(id);
	}
	
	public BigDecimal calculateUsdAmount(Transaction transaction){
		Optional<ExchangeRate> exchangeRateOptional = exchangeRateService.findByCurrencyPairAndDate(
				transaction.getCurrencyShortName(), transaction.getDatetime().toLocalDate());
		
		ExchangeRate exchangeRate = exchangeRateOptional
				.orElse(exchangeRateService
						.findLastExchangeRateByCurrencyPair(transaction.getCurrencyShortName()));
				
		
		return transaction.getSum().multiply(exchangeRate.getExchangeRate());
	}
	
	
}
