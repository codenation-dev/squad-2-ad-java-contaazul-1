package service.com.javawomen.errorcenter.test.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;

@Component
public class LogMock {

	// new form
	List<LogForm> listaForm = new ArrayList<LogForm>();

	public LogMock() {

	}

	public List<LogForm> getListaForm() {
		return listaForm;
	}

	// cria 20 form
	public void createForm() {
		LogForm form = new LogForm();
		// for (int i = 0; i < 5; i++) {
		form.setNameLevel("error");
		form.setNameEnvironment("desenvolvimento");
		form.setOrigin("127.0.0.1");
		form.setDescription("deion do log");
		form.setDetails("details do log");
		listaForm.add(form);
		

		form.setNameLevel("debug");
		form.setNameEnvironment("desenvolvimento");
		form.setOrigin("127.0.0.1");
		form.setDescription("deion do log");
		form.setDetails("details do log");
		listaForm.add(form);

		form.setNameLevel("warn");
		form.setNameEnvironment("homologacao");
		form.setOrigin("127.0.0.1");
		form.setDescription("deion do log");
		form.setDetails("details do log");
		listaForm.add(form);

		form.setNameLevel("error");
		form.setNameEnvironment("producao");
		form.setOrigin("127.0.0.1");
		form.setDescription("deion do log");
		form.setDetails("details do log");
		listaForm.add(form);

		// }
	}

	public Level getLevel(String name) {

		int id = 0;
		Level level = new Level();

		if (name.equals("error"))
			id = 1;
		if (name.equals("debug"))
			id = 2;
		if (name.equals("warn"))
			id = 3;

		level.setId((long) id);
		level.setName(name);

		return level;
	}

	public Environment getEnvironment(String name) {
		int id = 0;
		Environment environment = new Environment();

		if (name.equals("desenvolvimento"))
			id = 1;
		if (name.equals("homologacao"))
			id = 2;
		if (name.equals("producao"))
			id = 3;

		environment.setId((long) id);
		environment.setName(name);

		return environment;
	}

	public Log createLog(int id) {

		Log log = new Log();
		Long ids = (long) id;

		LogForm form = listaForm.get(id);

		log.setCreatedAt(LocalDateTime.now());
		log.setId((long) id);
		log.setLevel(getLevel(form.getNameLevel()));
		log.setEnvironment(getEnvironment(form.getNameEnvironment()));
		log.setDescription(form.getDescription());
		log.setDetails(form.getDetails());
		log.setOrigin(form.getOrigin());

		return log;
	}

	public List<Log> listaLog() {
		// createForm();
		// ==20
		List<Log> lista = new ArrayList<>();
		for (int j = 0; j < listaForm.size(); j++) {
			Log log = createLog(j);
			lista.add(log);
		}
		return lista;
	}

	public List<Log> listaEnvironment(String name) {
		List<Log> lista = new ArrayList<>();
		for (int j = 0; j < listaForm.size(); j++) {
			if (listaForm.get(j).getNameEnvironment().contentEquals(name)) {
				Log log = createLog(j);
				lista.add(log);
			}
		}
		return lista;
	}

	public List<Log> listaLevel(String name) {
		List<Log> lista = new ArrayList<>();
		for (int j = 0; j < listaForm.size(); j++) {
			if(listaForm.get(j).getNameLevel().contentEquals(name)) {
				Log log = createLog(j);
				lista.add(log);
			}
		}
		return lista;
	}

	public List<Log> listaOrigem(String name) {
		List<Log> lista = new ArrayList<>();
		for (int j = 0; j < listaForm.size(); j++) {
			if(listaForm.get(j).getOrigin().contentEquals(name)) {
				Log log = createLog(j);
				lista.add(log);
			}
		}
		return lista;
	}
	
	public List<Log> listaDescription(String name) {
		List<Log> lista = new ArrayList<>();
		for (int j = 0; j < listaForm.size(); j++) {
			if(listaForm.get(j).getDescription().contentEquals(name)) {
				Log log = createLog(j);
				lista.add(log);
			}
		}
		return lista;
	}

	public void salvar(LogForm form) {
		listaForm.add(form);
	}
}
