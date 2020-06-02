 
package com.javawomen.errorcenter.config.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
//responsável por capturar e tratar erros
@RestControllerAdvice 
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override 
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> errors = getErrors(ex);
		ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
		return new ResponseEntity<>(errorResponse, status);
	}

	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
			List<ObjectError> errors) {
		return new ErrorResponse("Requisição possui campos inválidos", status.value(), status.getReasonPhrase(),
				ex.getBindingResult().getObjectName(), errors);
	}

	private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new ObjectError(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
				.collect(Collectors.toList());
	}

	//404 - not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
		ResourceNotFoundDetails resourceDetails = new ResourceNotFoundDetails( 
				HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), ex.getMessage());
		return new ResponseEntity<>(resourceDetails, HttpStatus.NOT_FOUND);
	}
	
	//400 - Bad Request
	@ExceptionHandler(DataInvalid.class)
	public ResponseEntity<?> handleUserDataInvalid(DataInvalid ex) {
		ResourceNotFoundDetails resourceDetails = new ResourceNotFoundDetails( 
				HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage());
		return new ResponseEntity<>(resourceDetails, HttpStatus.BAD_REQUEST);
	}
	
	//500 - Error Server
	@ExceptionHandler(ErrorInternal.class)
	public ResponseEntity<?> handleErrorServer(DataInvalid ex) {
		ResourceNotFoundDetails resourceDetails = new ResourceNotFoundDetails( 
				HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage());
		return new ResponseEntity<>(resourceDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
