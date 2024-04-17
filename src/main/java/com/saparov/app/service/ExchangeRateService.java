package com.saparov.app.service;

import com.saparov.app.exception.ExchangeRateNotFoundException;
import com.saparov.app.model.ExchangeRate;
import com.saparov.app.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExchangeRateService {
	private final ExchangeRateRepository exchangeRateRepository;
	
	public ExchangeRate findExchangeRateById(Long id){
		return exchangeRateRepository.findById(id)
				.orElseThrow(() -> new ExchangeRateNotFoundException("ExchangeRate not found with id: " + id));
	}
	
	public List<ExchangeRate> findAllExchangeRates(){
		return exchangeRateRepository.findAll();
	}
	
	@Transactional
	public ExchangeRate createExchangeRate(ExchangeRate exchangeRate){
		return exchangeRateRepository.save(exchangeRate);
	}
	
	@Transactional
	public ExchangeRate updateExchangeRate(Long id, ExchangeRate updateExchangeRate){
		ExchangeRate exchangeRate = exchangeRateRepository.findById(id)
				.orElseThrow(() -> new ExchangeRateNotFoundException("ExchangeRate not found with id: " + id));
		
		exchangeRate.setCurrencyPair(updateExchangeRate.getCurrencyPair());
		exchangeRate.setExchangeRate(updateExchangeRate.getExchangeRate());
		exchangeRate.setDate(updateExchangeRate.getDate());
		
		return exchangeRateRepository.save(exchangeRate);
	}
	
	public void deleteExchangeRateById(Long id){
		exchangeRateRepository.findById(id)
				.orElseThrow(() -> new ExchangeRateNotFoundException("ExchangeRate not found with id: " + id));
		
		exchangeRateRepository.deleteById(id);
	}
	
	public Optional<ExchangeRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date){
		return exchangeRateRepository.findByCurrencyPairAndDate(currencyPair, date);
	}
	
	public ExchangeRate findLastExchangeRateByCurrencyPair(String currencyPair){
		return exchangeRateRepository.findLastExchangeRateByCurrencyPair(currencyPair);
	}
}
