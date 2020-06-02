package com.javawomen.errorcenter.unitTest.env;

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

import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.repository.EnvironmentRepository;
import com.javawomen.errorcenter.service.EnvironmentService;

@RunWith(MockitoJUnitRunner.class)
public class TestEnvService {

	@Mock
	EnvironmentRepository repositoryMock;

	@InjectMocks
	EnvironmentService serviceImpl;

	@InjectMocks
	EnvMock mock = new EnvMock();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	// 1 - public List<Environment> findAll()
	@Test
	public void findAllEnvironment() {
		mock.create();
		List<Environment> Environments = mock.getEnvironments();
		when(repositoryMock.findAll()).thenReturn(Environments);
		List<Environment> listaImpl = serviceImpl.findAll();
		Assert.assertEquals(listaImpl.size(), Environments.size());
		Assert.assertNotNull(listaImpl);
	}

	// 2 - public Optional<Environment> findById(Long id)
	@Test
	public void findById() {
		// cria lista de entrada de dados
		mock.create();
		Long id = 1L;
		int ids = 1;
		Environment Environment = mock.getEnvironment(ids);
		when(repositoryMock.findById(id)).thenReturn(Optional.of(Environment));
		when(serviceImpl.findById(id)).thenReturn(Optional.of(Environment));
		Optional<Environment> optional = serviceImpl.findById(id);
		Assert.assertSame(optional.get(), Environment);
		Assert.assertEquals(optional.get().getId(), Environment.getId());
	}

	// 2.1 - Id Invalido
	@Test(expected = NoSuchElementException.class)
	public void findByIdInvalido() {
		// cria lista de entrada de dados
		mock.create();
		Long id = 1L;
		Long id2 = 2L;
		int ids = 1;
		Environment Environment = mock.getEnvironment(ids);
		when(repositoryMock.findById(id)).thenReturn(Optional.of(Environment));
		when(serviceImpl.findById(id)).thenReturn(Optional.of(Environment));
		Optional<Environment> optional = serviceImpl.findById(id2);
		Assert.assertSame(optional.get(), Environment);
		Assert.assertEquals(optional.get().getId(), Environment.getId());
	}

	// 3 -public Environment save(Environment object)
	@Test
	public void save() {
		mock.create();
		Environment env = mock.getEnvironment(0);
		when(repositoryMock.save(Mockito.any(Environment.class))).thenReturn(env);
		Environment env2 = serviceImpl.save(env);
		Assert.assertSame(env, env2);
		verify(repositoryMock).save(env);
	}

	// 4. public void deleteById(Long id)
	@Test
	public void delete() {
		mock.create();
		repositoryMock.deleteById(0L);
		verify(repositoryMock).deleteById(0L);
	}


	
}
