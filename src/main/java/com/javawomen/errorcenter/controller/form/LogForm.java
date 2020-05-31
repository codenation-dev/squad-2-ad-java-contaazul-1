package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LogForm {

	@NotBlank(message = "{nameLevel.not.blank}")
	@Size(min = 3, max = 100)
	private String nameLevel;

	@NotBlank(message = "{nameEnvironment.not.blank}")
	@Size(min = 3, max = 100)
	private String nameEnvironment;

	@NotBlank(message = "{origin.not.blank}")
	@Size(min = 3, max = 100)
	private String origin;

	@NotBlank(message = "{description.not.blank}")
	@Size(min = 10, max = 100)
	private String description;
	
	@NotBlank(message = "{details.not.blank}")
	@Size(min = 10, max = 255)
    private String details;

	
	public LogForm() {
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	public String getNameEnvironment() {
		return nameEnvironment;
	}

	public void setNameEnvironment(String nameEnvironment) {
		this.nameEnvironment = nameEnvironment;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}

}
