package com.padma.parus.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AddOrderException extends RuntimeException{
	
	private String message;

	public AddOrderException( String message) {
		super(String.format("Error: [%s]", message));
		this.message = message;
	}
	
	@Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
