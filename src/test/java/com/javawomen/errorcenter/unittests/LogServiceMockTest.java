package com.javawomen.errorcenter.unittests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;
import com.javawomen.errorcenter.service.LogService;

//O JUnit Runner, que faz com que toda a mágica de inicialização com @Mock 
//e @InjectMocks ocorra antes dos testes serem executados.
@RunWith(MockitoJUnitRunner.class)
public class LogServiceMockTest {

	// criando o mock
	@Mock
	LogRepository logRepositoryMock;// DataService dataServiceMock;
	@Mock
	LogDto dto;
	@Mock
	LogForm form;
	@Mock
	Level level;;
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
		// cria lista de entrada de dados
		mock.createForm();
		int id = 1;
		Long idL = 2L;
		// gera um logo com o index da lista
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
		Assert.assertEquals(listaImpl.size(), logs.size());
	}

	@Test
	public void findByEnvironmentName() {
		mock.createForm();
		Pageable pageable = PageRequest.of(0, 10);
		String name = "producao";
		List<Log> lista = mock.listaEnvironment(name);
		Page<Log> logPage = new PageImpl<>(lista, pageable, 0);
		when(logRepositoryMock.findByEnvironmentName(name, pageable)).thenReturn(logPage);
		Page<Log> logPageble = logRepositoryMock.findByEnvironmentName(name, pageable);
		Assert.assertEquals(logPage.getTotalPages(), logPageble.getTotalPages());
		Assert.assertEquals(logPage.getNumberOfElements(), logPageble.getNumberOfElements());
		Assert.assertEquals(lista.size(), logPageble.getNumberOfElements());
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

	@Test
	public void countByAttribute() {
		mock.createForm();
		Long counter = 0L;
		Long id = 2L;
		List<Log> logs = mock.listaLog();
		Log log = logs.get(2);
		counter = (long) mock.count(log);
		when(logRepositoryMock.findById(id)).thenReturn(Optional.of(log));
		when(logRepositoryMock.countByAllAttribute(log.getLevel().getName(), log.getEnvironment().getName(),
				log.getOrigin(), log.getDescription())).thenReturn(2l);
		when(logServiceImpl.countByAttribute(id)).thenReturn(1L);
		Long y = logServiceImpl.countByAttribute(id);
		assertTrue(counter == y);
	}
}
