package com.cg.aps.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Vishal Rana
 *
 */
@ControllerAdvice //Used to define @ExceptionHandler method that apply to all @RequestMapping methods for global handling.
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * @param exception
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(value = DatabaseException.class) //Used to handle the specific exceptions and sending the custom responses to the client.
	public ResponseEntity<Object> handleAdminRegistrationException(DatabaseException exception, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

	}

	/**
	 * @param exception
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(value = RecordNotFoundException.class)  //Used to handle the specific exceptions and sending the custom responses to the client.
	public ResponseEntity<Object> handleNotFoundException(RecordNotFoundException exception, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param exception
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(value = DuplicateRecordException.class)  //Used to handle the specific exceptions and sending the custom responses to the client.
	public ResponseEntity<Object> handleDuplicateRecordException(DuplicateRecordException exception,
			WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(406);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

	}
}