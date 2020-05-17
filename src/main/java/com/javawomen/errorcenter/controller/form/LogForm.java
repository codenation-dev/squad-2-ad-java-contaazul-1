package com.javawomen.errorcenter.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.service.EnvironmentService;
import com.javawomen.errorcenter.service.LevelService;

public class LogForm {

	@NotNull(message = "{nameLevel.not.null}")
	@NotEmpty(message = "{nameLevel.not.empty}")
	@NotBlank(message = "{nameLevel.not.blank}")
	@Length(min = 3, max = 15)
	private String nameLevel;

	@NotNull(message = "{nameEnvironment.not.null}")
	@NotEmpty(message = "{nameEnvironment.not.empty}")
	@NotBlank(message = "{nameEnvironment.not.blank}")
	@Length(min = 3, max = 25)
	private String nameEnvironment;

	@NotNull(message = "{origin.not.null}")
	@NotEmpty(message = "{origin.not.empty}")
	@NotBlank(message = "{origin.not.blank}")
	@Length(min = 5, max = 50)
	private String origin;

	@NotNull(message = "{description.not.null}")
	@NotEmpty(message = "{description.not.empty}")
	@NotBlank(message = "{description.not.blank}")
	@Length(min = 10, max = 100)
	private String description;
	

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

	//public Log converter(LevelRepository levelRepository, EnvironmentRepository environmentRepository) {

	//	Optional<Level> levelOptional = levelRepository.findByName(nameLevel);
	//	Optional<Environment> environmentOptional = environmentRepository.findByName(nameEnvironment);

	//	if(!levelOptional.isPresent())throw new ResourceNotFoundException("Level não encontrado.");
	//	if(!environmentOptional.isPresent())throw new ResourceNotFoundException("Ambiente não encontrado.");

		// retornar um optional para tratar o nullpointexception
		//if(levelOptional.isPresent() && environmentOptional.isPresent())
	//	return new Log(levelOptional.get(), environmentOptional.get(), origin, description);

	//}

	
	//PASSADO PARA O SERVICE, TESTAR e apagar esse
	/*public Log converter(LevelService levelService, EnvironmentService environmentService) {
		Optional<Level> levelOptional = levelService.findByName(nameLevel);
		Optional<Environment> environmentOptional = environmentService.findByName(nameEnvironment);

		if(!levelOptional.isPresent())throw new ResourceNotFoundException("Level não encontrado.");
		if(!environmentOptional.isPresent())throw new ResourceNotFoundException("Ambiente não encontrado.");

		return new Log(levelOptional.get(), environmentOptional.get(), origin, description);

	}*/


}
