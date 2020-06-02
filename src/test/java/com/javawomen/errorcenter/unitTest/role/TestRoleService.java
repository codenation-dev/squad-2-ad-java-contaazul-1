package com.javawomen.errorcenter.unitTest.role;

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

import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.repository.RoleRepository;
import com.javawomen.errorcenter.service.RoleService;

@RunWith(MockitoJUnitRunner.class)
public class TestRoleService {

	@Mock
	RoleRepository repositoryMock;

	@InjectMocks
	RoleService serviceImpl;

	@InjectMocks
	RoleMock mock = new RoleMock();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	// 1 - public List<Role> findAll()
	@Test
	public void findAllRole() {
		mock.create();
		List<Role> Roles = mock.getRoles();
		when(repositoryMock.findAll()).thenReturn(Roles);
		List<Role> listaImpl = serviceImpl.findAll();
		Assert.assertEquals(listaImpl.size(), Roles.size());
		Assert.assertNotNull(listaImpl);
	}

	// 2 - public Optional<Role> findById(Long id)
	@Test
	public void findById() {
		// cria lista de entrada de dados
		mock.create();
		Long id = 1L;
		int ids = 1;
		Role Role = mock.getRole(ids);
		when(repositoryMock.findById(id)).thenReturn(Optional.of(Role));
		when(serviceImpl.findById(id)).thenReturn(Optional.of(Role));
		Optional<Role> optional = serviceImpl.findById(id);
		Assert.assertSame(optional.get(), Role);
		Assert.assertEquals(optional.get().getId(), Role.getId());
	}

	// 2.1 - Id Invalido
	@Test(expected = NoSuchElementException.class)
	public void findByIdInvalido() {
		// cria lista de entrada de dados
		mock.create();
		Long id = 1L;
		Long id2 = 2L;
		int ids = 1;
		Role Role = mock.getRole(ids);
		when(repositoryMock.findById(id)).thenReturn(Optional.of(Role));
		when(serviceImpl.findById(id)).thenReturn(Optional.of(Role));
		Optional<Role> optional = serviceImpl.findById(id2);
		Assert.assertSame(optional.get(), Role);
		Assert.assertEquals(optional.get().getId(), Role.getId());
	}

	// 3 -public Role save(Role object)
	@Test
	public void save() {
		mock.create();
		Role rol = mock.getRole(0);
		when(repositoryMock.save(Mockito.any(Role.class))).thenReturn(rol);
		Role rol2 = serviceImpl.save(rol);
		Assert.assertSame(rol, rol2);
		verify(repositoryMock).save(rol);
	}

	// 4. public void deleteById(Long id)
	@Test
	public void delete() {
		mock.create();
		repositoryMock.deleteById(0L);
		verify(repositoryMock).deleteById(0L);
	}


	
}
