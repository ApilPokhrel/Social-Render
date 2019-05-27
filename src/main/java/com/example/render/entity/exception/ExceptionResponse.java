package com.example.render.entity.exception;


public class ExceptionResponse {
private String message;
private String details;
private String code;
private String solution;


public ExceptionResponse() {
	super();
}
public ExceptionResponse(String message, String details, String code, String solution) {
	super();
	this.message = message;
	this.details = details;
	this.code = code;
	this.solution = solution;
}
/**
 * @return the message
 */
public String getMessage() {
	return message;
}
/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}
/**
 * @return the details
 */
public String getDetails() {
	return details;
}
/**
 * @param details the details to set
 */
public void setDetails(String details) {
	this.details = details;
}
/**
 * @return the code
 */
public String getCode() {
	return code;
}
/**
 * @param code the code to set
 */
public void setCode(String code) {
	this.code = code;
}
/**
 * @return the solution
 */
public String getSolution() {
	return solution;
}
/**
 * @param solution the solution to set
 */
public void setSolution(String solution) {
	this.solution = solution;
}
}
