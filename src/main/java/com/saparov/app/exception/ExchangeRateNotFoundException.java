package com.saparov.app.exception;

public class ExchangeRateNotFoundException extends RuntimeException {
	
	public ExchangeRateNotFoundException(String message) {
		super(message);
	}
}
