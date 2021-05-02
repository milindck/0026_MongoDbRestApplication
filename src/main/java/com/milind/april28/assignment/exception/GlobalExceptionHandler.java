package com.milind.april28.assignment.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	private static final String THROWABLE = "UNKNOWN_ERROR";
	private static final String BAD_REQUEST = "BAD_REQUEST";
	private static final String DATA_EXISTS = "DATA_EXISTS";

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(
			RecordNotFoundException ex, WebRequest request
	)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RecordFoundException.class)
	public final ResponseEntity<ErrorResponse> handleRecordFoundException(
			RecordFoundException ex, WebRequest request
	)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse(DATA_EXISTS, details);
		return new ResponseEntity<>(error, HttpStatus.FOUND);
	}

	@ExceptionHandler(Throwable.class)
	public final ResponseEntity<ErrorResponse> handleRecordNotFoundException(
			Throwable ex, WebRequest request
	)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ex.printStackTrace();
		ErrorResponse error = new ErrorResponse(THROWABLE, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
	)
	{
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(x -> x.getField() + ": " + x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);

	}
}
