package com.saparov.app.util;

import com.saparov.app.exception.ExchangeRateNotFoundException;
import com.saparov.app.exception.LimitNotFoundException;
import com.saparov.app.exception.TransactionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<AppError> catchTransactionNotFoundException(TransactionNotFoundException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<AppError> catchLimitNotFoundException(LimitNotFoundException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<AppError> catchExchangeRateNotFoundException(ExchangeRateNotFoundException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
}
