package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.model.Environment;

public class EnvironmentForm {
	
	@NotNull(message = "{nameEnvironment.not.null}")
	@NotEmpty(message = "{nameEnvironment.not.empty}")
	@NotBlank(message = "{nameEnvironment.not.blank}")
	@Length(min = 3, max = 25)
    private String name;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Environment converter() {
		return new Environment(name);
	}
}
