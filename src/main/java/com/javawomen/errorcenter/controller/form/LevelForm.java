package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LevelForm {
	
	@NotBlank(message = "{nameLevel.not.blank}")
	@Size(min = 3, max = 100)
    private String name;
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
