package com.javawomen.errorcenter.unitTest.level;

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

import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.repository.LevelRepository;
import com.javawomen.errorcenter.service.LevelService;

@RunWith(MockitoJUnitRunner.class)
public class TestLevelService {

	@Mock
	LevelRepository repositoryMock;

	@InjectMocks
	LevelService serviceImpl;

	@InjectMocks
	LevelMock mock = new LevelMock();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	// 1 - public List<Level> findAll()
	@Test
	public void findAllLevel() {
		mock.create();
		List<Level> levels = mock.getLevels();
		when(repositoryMock.findAll()).thenReturn(levels);
		List<Level> listaImpl = serviceImpl.findAll();
		Assert.assertEquals(listaImpl.size(), levels.size());
		Assert.assertNotNull(listaImpl);
	}

	// 2 - public Optional<Level> findById(Long id)
	@Test
	public void findById() {
		// cria lista de entrada de dados
		mock.create();
		Long id = 1L;
		int ids = 1;
		Level level = mock.getLevel(ids);
		when(repositoryMock.findById(id)).thenReturn(Optional.of(level));
		when(serviceImpl.findById(id)).thenReturn(Optional.of(level));
		Optional<Level> optional = serviceImpl.findById(id);
		Assert.assertSame(optional.get(), level);
		Assert.assertEquals(optional.get().getId(), level.getId());
	}

	// 2.1 - Id Invalido
	@Test(expected = NoSuchElementException.class)
	public void findByIdInvalido() {
		// cria lista de entrada de dados
		mock.create();
		Long id = 1L;
		Long id2 = 2L;
		int ids = 1;
		Level level = mock.getLevel(ids);
		when(repositoryMock.findById(id)).thenReturn(Optional.of(level));
		when(serviceImpl.findById(id)).thenReturn(Optional.of(level));
		Optional<Level> optional = serviceImpl.findById(id2);
		Assert.assertSame(optional.get(), level);
		Assert.assertEquals(optional.get().getId(), level.getId());
	}

	// 3 -public Level save(Level object)
	@Test
	public void save() {
		mock.create();
		Level lev = mock.getLevel(0);
		when(repositoryMock.save(Mockito.any(Level.class))).thenReturn(lev);
		Level lev2 = serviceImpl.save(lev);
		Assert.assertSame(lev, lev2);
		verify(repositoryMock).save(lev);
	}

	// 4. public void deleteById(Long id)
	@Test
	public void delete() {
		mock.create();
		//Level lev = mock.getLevel(0);
		repositoryMock.deleteById(0L);
		verify(repositoryMock).deleteById(0L);
	}


}
