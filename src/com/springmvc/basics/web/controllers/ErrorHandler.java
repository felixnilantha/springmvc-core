package com.asta.spring.web.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseExceptions(DataAccessException ex){
		ex.printStackTrace();
		return "error";

	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleDatabaseExceptions(AccessDeniedException ex){
		
		return "denied";
	
	
	}

}
