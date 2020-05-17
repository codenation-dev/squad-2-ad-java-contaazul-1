package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.model.Level;

public class LevelForm {
	
	@NotNull(message = "{nameLevel.not.null}")
	@NotEmpty(message = "{nameLevel.not.empty}")
	@NotBlank(message = "{nameLevel.not.blank}")
	@Length(min = 3, max = 15)
    private String name;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//public Level converter() {
		//return new Level(name);
	//}
}
