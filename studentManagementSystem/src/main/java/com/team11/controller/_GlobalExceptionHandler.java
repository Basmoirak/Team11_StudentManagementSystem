package com.team11.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class _GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public String handleGenericException(Exception e) {
		
		//Log generic exception
		System.out.println("Exception Occured:" + e);
		
		return "exception/generic-exception";
	}
	
}
