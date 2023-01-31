package com.patient.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.patient.constants.ApplicationConstants;

/**
 * 
 * @author BK106402 An interceptor for handling the Controller exception
 *         handling
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(PatientIdNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handlePatientNotFoundException(PatientIdNotFoundException ex,
			WebRequest request) {
		String details = ex.getLocalizedMessage();
		ErrorResponse error = new ErrorResponse(ApplicationConstants.RECORD_NOT_FOUND, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyInputException.class)
	public final ResponseEntity<String> handleEmptyInputException(EmptyInputException ex) {
		return new ResponseEntity<String>("Input field is empty please check the request", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
		return new ResponseEntity<String>("No Value is present in DB,Please check your request", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
		String details = ex.getLocalizedMessage();
		ErrorResponse error = new ErrorResponse(ApplicationConstants.SERVER_ERROR, details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}