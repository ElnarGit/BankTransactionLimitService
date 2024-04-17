package com.saparov.app.controller;

import com.saparov.app.model.ExchangeRate;
import com.saparov.app.service.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exchangeRates")
@RequiredArgsConstructor
public class ExchangeRateController {
	private final ExchangeRateService exchangeRateService;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ExchangeRate getExchangeRateById(@PathVariable("id") Long id){
		return exchangeRateService.findExchangeRateById(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ExchangeRate> getAllExchangeRates(){
		return exchangeRateService.findAllExchangeRates();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ExchangeRate createExchangeRate(@Valid @RequestBody ExchangeRate exchangeRate){
		return exchangeRateService.createExchangeRate(exchangeRate);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ExchangeRate updateExchangeRate(@PathVariable("id") Long id ,
										   @Valid @RequestBody ExchangeRate updatedExchangeRate){
		return exchangeRateService.updateExchangeRate(id, updatedExchangeRate);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteExchangeRateById(@PathVariable("id") Long id){
		exchangeRateService.deleteExchangeRateById(id);
		
		return "ExchangeRate deleted";
	}
}
