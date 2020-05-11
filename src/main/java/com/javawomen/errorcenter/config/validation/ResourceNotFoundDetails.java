package com.javawomen.errorcenter.config.validation;

public class ResourceNotFoundDetails {

	private String title;
	private int status;
	private String details;
	private String developmentMessage;

	
	public ResourceNotFoundDetails(String title, int status, String details, String developmentMessage) {
		this.title = title;
		this.status = status;
		this.details = details;
		this.developmentMessage = developmentMessage;
	}
	
	public String getTitle() {
		return title;
	}
	public int getStatus() {
		return status;
	}
	public String getDetails() {
		return details;
	}
	public String getDevelopmentMessage() {
		return developmentMessage;
	}
	
	
	
}
