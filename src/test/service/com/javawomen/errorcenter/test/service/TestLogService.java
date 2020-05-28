package service.com.javawomen.errorcenter.test.service;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;
import com.javawomen.errorcenter.service.LogService;


@RunWith(MockitoJUnitRunner.class)
public class TestLogService {

	// criando o mock
	@Mock
	LogRepository logRepositoryMock;// DataService dataServiceMock;
	@Mock
	LogDto dto;
	@Mock
	LogForm form;
	@Mock
	Level level;
	@Mock
	Environment env;

	// Injetar mock como dependências no BusinessService
	@InjectMocks
	LogService logServiceImpl;// BusinessService businessImpl;
	@InjectMocks
	LogMock mock = new LogMock();// eventsMocks

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findByIdLogs() {
		//cria lista de entrada de dados
		mock.createForm();
		int id = 1;
		Long idL =  2L;
		//gera um logo com o index da lista
		Log log = mock.createLog(id);

		when(logRepositoryMock.findById(idL)).thenReturn(Optional.of(log));
		when(logServiceImpl.findById(idL)).thenReturn(Optional.of(log));
		Optional<Log> optional = logServiceImpl.findById(idL);
		
		// Asserts that two objects refer to the same object.
		Assert.assertSame(optional.get(), log);
	}
	
	@Test
	public void findAllLogs() {
		mock.createForm();
		List<Log> logs = mock.listaLog();
		when(logRepositoryMock.findAll()).thenReturn(logs);
		when(logServiceImpl.findAll()).thenReturn(logs);
		List<Log> listaImpl = logServiceImpl.findAll();
		Assert.assertEquals(listaImpl.size(),logs.size());
	}

	@Test
	public void findByEnvironment() {
		mock.createForm();
		String name = "producao";
		List<Log> lista = mock.listaEnvironment(name);
		Mockito.lenient().when(logRepositoryMock.findByEnvironmentName(name)).thenReturn((lista));
		when(logServiceImpl.findByEnvironment(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByEnvironment(name);
		Assert.assertSame(listaImpl.get(1), lista.get(1));
		Assert.assertEquals(listaImpl.size(), lista.size());
	}
	
	@Test
	public void findByLevel() {
		mock.createForm();
		String name = "error";
		List<Log> lista = mock.listaLevel(name);
		Mockito.lenient().when(logRepositoryMock.findByLevelName(name)).thenReturn((lista));
		when(logServiceImpl.findByLevel(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByLevel(name);
		Assert.assertSame(listaImpl.get(1), lista.get(1));
		Assert.assertEquals(listaImpl.size(), lista.size());
	}
	
	@Test
	public void findByOrigem() {
		mock.createForm();
		String name = "127.0.0.1";
		List<Log> lista = mock.listaOrigem(name);
		when(logRepositoryMock.findByOrigin(name)).thenReturn((lista));
		when(logServiceImpl.findByOrigin(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByOrigin(name);
		Assert.assertSame(listaImpl.get(1), lista.get(1));
		Assert.assertEquals(listaImpl.size(), lista.size());
	}
	
	@Test
	public void findByDescription() {
		mock.createForm();
		String name = "description do log";
		List<Log> lista = mock.listaDescription(name);
		when(logRepositoryMock.findByDescription(name)).thenReturn((lista));
		when(logServiceImpl.findByDescription(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByDescription(name);
		Assert.assertSame(listaImpl.get(1), lista.get(1));
		Assert.assertEquals(listaImpl.size(), lista.size());
	}
	
	@Test
	public void saveLog() {
		mock.createForm();
		LogForm form = new LogForm();
		form.setNameLevel("error");
		form.setNameEnvironment("desenvolvimento");
		form.setOrigin("127.0.0.1");
		form.setDescription("salvar log test");
		form.setDetails("details do log");
		
		mock.salvar(form);
		
		String name = "salvar log test";
		List<Log> lista = mock.listaDescription(name);		
		when(logRepositoryMock.findByDescription(name)).thenReturn((lista));
		when(logServiceImpl.findByDescription(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByDescription(name);
		Assert.assertSame(listaImpl.get(0), lista.get(0));
		Assert.assertEquals(listaImpl.size(), 1);
	}

}
