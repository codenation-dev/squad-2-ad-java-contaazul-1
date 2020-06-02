package com.javawomen.errorcenter.config.validation;

import java.time.LocalDateTime;

public class ResourceNotFoundDetails {

	private LocalDateTime timeStamp= LocalDateTime.now();
	private int status;
	private String error;
	private String message;
	
 
	
	public ResourceNotFoundDetails(int status, String error, String message) {
		this.status = status;
		this.error = error;
		this.message = message;
	}
	
	public int getStatus() {
		return status;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}
	
}
