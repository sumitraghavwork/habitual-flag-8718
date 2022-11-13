package com.bookmybus.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FeedbackException.class)
	public ResponseEntity<MyErrorDetails> myFeedbackExceptionHandler(FeedbackException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BusException.class)
	public ResponseEntity<MyErrorDetails> myBusExceptionHandler(BusException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> myLoginExceptionHandler(LoginException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> myAdminExceptionHandler(AdminException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> myUserExceptionHandler(UserException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> myNoHandlerFoundExceptionHandler(NoHandlerFoundException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}		

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), "Validation Error",
				me.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<MyErrorDetails> reservationExceptionHandler(ReservationException e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest web) {

		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), web.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
}
