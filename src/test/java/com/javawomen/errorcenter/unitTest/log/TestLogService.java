package com.javawomen.errorcenter.unitTest.log;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
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

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;
import com.javawomen.errorcenter.service.LogService;


@RunWith(MockitoJUnitRunner.class)
public class TestLogService {

	// criando o mock
	@Mock
	LogRepository logRepositoryMock;


	// Injetar mock como dependências 
	@InjectMocks
	LogService logServiceImpl;
	
	@InjectMocks
	LogMock mock = new LogMock();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	// 1 - public List<Log> findAll()
	@Test
	public void findAllLogs() {
		mock.createForm();
		List<Log> logs = mock.listaLog();
		when(logRepositoryMock.findAll()).thenReturn(logs);
		List<Log> listaImpl = logServiceImpl.findAll();
		Assert.assertEquals(listaImpl.size(), logs.size());
	}

	//	2 - public Page<Log> findAll(Pageable paginacao)
	@Test
	public void findAllLogsPage() {
		mock.createForm();
		Pageable pageable = PageRequest.of(0, 10);
		List<Log> logs = mock.listaLog();		
		Page<Log> logPage = new PageImpl<>(logs, pageable, 0);
		when(logRepositoryMock.findAll(pageable)).thenReturn(logPage);
		Page<Log> logPageble = logServiceImpl.findAll(pageable);
		Assert.assertEquals(logPage.getTotalPages(), logPageble.getTotalPages());
		Assert.assertEquals(logPage.getNumberOfElements(), logPageble.getNumberOfElements());
		Assert.assertEquals(logs.size(),logPageble.getNumberOfElements());
	}
	
	//	3 - public Page<Log> findByEnvironment(String nameEnvironment, Pageable paginacao)
	@Test
	public void findByPageEnvironmentName() {
		mock.createForm();
		Pageable pageable = PageRequest.of(0, 10);
		String name = "PRODUCAO";
		List<Log> lista = mock.listaEnvironment(name);
		Page<Log> logPage = new PageImpl<>(lista, pageable, 0);
		when(logRepositoryMock.findByEnvironmentName(name, pageable)).thenReturn(logPage);
		Page<Log> logPageble = logRepositoryMock.findByEnvironmentName(name, pageable);
		Assert.assertEquals(logPage.getTotalPages(), logPageble.getTotalPages());
		Assert.assertEquals(logPage.getNumberOfElements(), logPageble.getNumberOfElements());
		Assert.assertEquals(lista.size(), logPageble.getNumberOfElements());
	}
	
	//	3.1 - ambiente invalido
	@Test//(expected=DataInvalid.class)
	public void findByPageEnvironmentNameInvalido() {
		mock.createForm();
		Pageable pageable = PageRequest.of(0, 10);
		String name = "INVALIDO";
		List<Log> lista = mock.listaEnvironment(name);
		Page<Log> logPage = new PageImpl<>(lista, pageable, 0);
		when(logRepositoryMock.findByEnvironmentName(name, pageable)).thenReturn(logPage);
		Page<Log> logPageble = logRepositoryMock.findByEnvironmentName(name, pageable);
		//Assert.assertEquals(logPage.getTotalPages(), logPageble.getTotalPages());
		Assert.assertEquals(logPage.getNumberOfElements(), 0);
		Assert.assertEquals(logPageble.getNumberOfElements(), 0);
		Assert.assertEquals(lista.size(), logPageble.getNumberOfElements());
	}

	// 	4 - public Optional<Log> findById(Long id) {
	@Test
	public void findByIdLogs() {
		// cria lista de entrada de dados
		mock.createForm();
		int id = 1;
		Long idL = 1L;
		Log log = mock.createLog(id);
		when(logRepositoryMock.findById(idL)).thenReturn(Optional.of(log));
		when(logServiceImpl.findById(idL)).thenReturn(Optional.of(log));
		Optional<Log> optional = logServiceImpl.findById(idL);
		Assert.assertSame(optional.get(), log);
		Assert.assertEquals(optional.get().getId(), log.getId());
	}

	// 	4.1 Id inexistente
	@Test(expected=NoSuchElementException.class)
	public void findByIdLogsInvalido() {
		mock.createForm();
		final int id = 1;
		final Long idL = 1L;
		final Long idL2 = 2L;//este id nao consta na lista
		Log log = mock.createLog(id); 
		when(logRepositoryMock.findById(idL)).thenReturn(Optional.of(log));
		when(logServiceImpl.findById(idL)).thenReturn(Optional.of(log));
		Optional<Log> optional = logServiceImpl.findById(idL2);
		Assert.assertEquals(optional.get().getId(), log.getId());
	}

	//5 - public List<Log> findByOrigin(String nameOrigin) {
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
	
	//5.1 - Origem inexistente
	@Test(expected=ResourceNotFoundException.class)
	public void findByOrigemInvalido() {
		mock.createForm();
		String name = "127.0.0.2";//este id nao consta na lista
		List<Log> lista = mock.listaOrigem(name);
		when(logRepositoryMock.findByOrigin(name)).thenReturn((lista));
		when(logServiceImpl.findByOrigin(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByOrigin(name);
		Assert.assertSame(listaImpl.get(1), lista.get(1));
		Assert.assertEquals(listaImpl.size(), lista.size());
	}

	//6 - public List<Log> findByDescription(String description)
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
	
	//6.1 - Description inexistente
	@Test(expected=ResourceNotFoundException.class)
	public void findByDescriptionInvalido() {
		mock.createForm();
		String name = "nao existe";
		List<Log> lista = mock.listaDescription(name);
		when(logRepositoryMock.findByDescription(name)).thenReturn((lista));
		when(logServiceImpl.findByDescription(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByDescription(name);
		Assert.assertSame(listaImpl.get(1), lista.get(1));
		Assert.assertEquals(listaImpl.size(), lista.size());
	}
	
	// 7 - public List<Log> findByLevel(String level)
	@Test
	public void findByLevel() {
		mock.createForm();
		String name = "ERROR";
		List<Log> lista = mock.listaLevel(name);
		when(logRepositoryMock.findByLevelName(name)).thenReturn(lista);
		when(logServiceImpl.findByLevel(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByLevel(name);
		Assert.assertEquals(lista.size(), 2);
		Assert.assertEquals(listaImpl.size(),2);
		Assert.assertSame(listaImpl.get(0), lista.get(0));
	}
	
	// 7.1 - Level inexistente
	@Test(expected=ResourceNotFoundException.class)
	public void findByLevelInvalido() {
		mock.createForm();
		String name = "INVALIDO";
		List<Log> lista = mock.listaLevel(name);
		when(logRepositoryMock.findByLevelName(name)).thenReturn(lista);
		when(logServiceImpl.findByLevel(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByLevel(name);
		Assert.assertEquals(lista.size(), 2);
		Assert.assertEquals(listaImpl.size(),2);
		Assert.assertSame(listaImpl.get(0), lista.get(0));
	}
	
	// 8 - 	public List<Log> findByEnvironment(String nameEnvironment) {
	@Test
	public void findByEnvironment() {
		mock.createForm();
		String name = "DESENVOLVIMENTO";
		List<Log> lista = mock.listaEnvironment(name);
		when(logRepositoryMock.findByEnvironmentName(name)).thenReturn(lista);
		when(logServiceImpl.findByEnvironment(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByEnvironment(name);
		Assert.assertEquals(lista.size(), 1);
		Assert.assertEquals(listaImpl.size(),1);
		Assert.assertSame(listaImpl.get(0), lista.get(0));
	}
	
	// 8.1 - Environment inexistente
	@Test(expected=ResourceNotFoundException.class)
	public void findByEnvironmentInavlido() {
		mock.createForm();
		String name = "INVALIDO";
		List<Log> lista = mock.listaEnvironment(name);
		when(logRepositoryMock.findByEnvironmentName(name)).thenReturn(lista);
		when(logServiceImpl.findByEnvironment(name)).thenReturn((lista));
		List<Log> listaImpl = logServiceImpl.findByEnvironment(name);
		Assert.assertEquals(lista.size(), 1);
		Assert.assertEquals(listaImpl.size(),1);
		Assert.assertSame(listaImpl.get(0), lista.get(0));
	}
	
	// 9. public Log save(Log object)
	@Test
	public void saveLog() {
		mock.createForm();
		LogForm form = new LogForm();
		form.setNameLevel("ERROR");
		form.setNameEnvironment("DESENVOLVIMENTO");
		form.setOrigin("127.0.0.1");
		form.setDescription("salvar log test");
		form.setDetails("details do log");

		int id = mock.salvar(form);
		Log log = mock.createLog(id);
		
		when(logRepositoryMock.save(Mockito.any(Log.class))).thenReturn(log);		        
		Log log2 = logServiceImpl.save(log);
		Assert.assertSame(log, log2);
	}
	
	// 10. public void deleteById(Long id)
	@Test
	public void deleteLog() {
		mock.createForm();
		Log log = mock.createLog(0);
		logRepositoryMock.deleteById(0L);
		verify(logRepositoryMock).deleteById(0L);
	}

	// 11. public Long countByAttribute(Long id)
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
		Long count = logServiceImpl.countByAttribute(id);
		assertTrue(counter == count);
	}
	
	
}
