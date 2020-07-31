package com.mifinity.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MifinityException extends Exception{

	private static final long serialVersionUID = 1L;

	public MifinityException(String message){
    	super(message);
    }
}
