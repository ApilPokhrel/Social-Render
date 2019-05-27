package com.example.render.exceptionhandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.render.entity.exception.ExceptionResponse;


@ControllerAdvice
public class HandleAllException extends ResponseEntityExceptionHandler{

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ExceptionResponse> handleAllException(Exception ex, HttpServletResponse res, WebRequest wreq){
//		ExceptionResponse eres  = new ExceptionResponse(ex.getMessage(), wreq.getDescription(false), String.valueOf(res.getStatus()),"Dont try this way");
//		return new ResponseEntity<ExceptionResponse>(eres,new HttpHeaders(),HttpStatus.BAD_REQUEST);
//	}
	
//	@ExceptionHandler({AccessDeniedException.class})
//	public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletResponse res, WebRequest wreq) throws 
//	IOException, ServletException{
//		ExceptionResponse eres  = new ExceptionResponse(ex.getMessage(), wreq.getDescription(false), String.valueOf(res.getStatus()),"Dont try this way");
//		return new ResponseEntity<ExceptionResponse>(eres,new HttpHeaders(),HttpStatus.FORBIDDEN);
//		
//	}
}
