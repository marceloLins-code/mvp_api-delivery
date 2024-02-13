package com.efooddeliveryapi.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.efooddeliveryapi.domain.exception.EntidadeEmUsoException;
import com.efooddeliveryapi.domain.exception.EntidadeNaoEncontradaException;
import com.efooddeliveryapi.domain.exception.NegocioException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(
			EntidadeEmUsoException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.CONFLICT, request);
	}
		
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, org.springframework.http.HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        body = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(ex.getMessage()).build();
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
