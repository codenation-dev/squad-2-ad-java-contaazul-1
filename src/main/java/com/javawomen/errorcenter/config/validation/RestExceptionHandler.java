
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

//troquei o nome, https://medium.com/@msealvial/validando-requisi%C3%A7%C3%B5es-e-tratando-exce%C3%A7%C3%B5es-no-spring-boot-1750ddb1e1cc
//MethodArgumentNotValidException  para @Valida com falha

@RestControllerAdvice // responsável por capturar e tratar erros; @ tornar visivel ao spring
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override // @ExceptionHandler(handleMethodArgumentNotValid.class)
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

	//para code 500 - internal server, e 404 - not found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
		ResourceNotFoundDetails resourceDetails = new ResourceNotFoundDetails("Resource not found.", 
				HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getClass().getName());
		return new ResponseEntity<>(resourceDetails, HttpStatus.NOT_FOUND);

	}
	
}
