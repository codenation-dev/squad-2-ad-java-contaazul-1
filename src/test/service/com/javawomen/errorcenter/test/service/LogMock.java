package service.com.javawomen.errorcenter.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.LogForm;

@Component
public class LogMock {
	
	//new form
	List<LogForm> listaForm = new ArrayList<LogForm>();
	LogForm  form = new LogForm();
	
	public LogMock() {
		createForm();
	}

	public List<LogForm> getListaForm() {
		
		return listaForm;
	}

	//cria 20 form
	public void createForm() {
		for (int i = 0; i < 5; i++) {
			form.setNameLevel("error");
			form.setNameEnvironment("development");
			form.setOrigin("127.0.0.1");
			form.setDescription("description do log");
			form.setDetails("details do log");
			listaForm.add(form);
			
			form.setNameLevel("debug");
			form.setNameEnvironment("development");
			form.setOrigin("127.0.0.1");
			form.setDescription("description do log");
			form.setDetails("details do log");
			listaForm.add(form);
			
			form.setNameLevel("warn");
			form.setNameEnvironment("development");
			form.setOrigin("127.0.0.1");
			form.setDescription("description do log");
			form.setDetails("details do log");
			listaForm.add(form);
			
			form.setNameLevel("error");
			form.setNameEnvironment("development");
			form.setOrigin("127.0.0.1");
			form.setDescription("description do log");
			form.setDetails("details do log");
			listaForm.add(form);
		}
	}

}
