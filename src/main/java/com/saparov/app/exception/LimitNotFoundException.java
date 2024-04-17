package com.saparov.app.exception;

public class LimitNotFoundException extends RuntimeException {
	public LimitNotFoundException(String message) {
		super(message);
	}
}
