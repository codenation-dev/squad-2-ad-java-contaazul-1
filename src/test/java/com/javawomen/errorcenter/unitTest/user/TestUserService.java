package com.javawomen.errorcenter.unitTest.user;

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

import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;
import com.javawomen.errorcenter.service.UserService;


@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	@InjectMocks
	UserMock mock = new UserMock();
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
		
	//1 - public Page<User> findAll(Pageable paginacao) 
	@Test
	public void getAllUsers() { 
		mock.createForm();
		Pageable pageable = PageRequest.of(0, 10);
		List<User> lista = mock.listaUsers();
		Page<User> userPage = new PageImpl<>(lista, pageable, 0);
		when(userRepository.findAll(pageable)).thenReturn(userPage);
		Page<User> userPageble = userService.findAll(pageable);		
		Assert.assertEquals(userPage.getTotalPages(), userPageble.getTotalPages());
		Assert.assertEquals(userPage.getNumberOfElements(), userPageble.getNumberOfElements());
		Assert.assertEquals(lista.size(), userPageble.getNumberOfElements());
	}
		
	
	//2. 	public Optional<User> findById(Long id)
	@Test
	public void findUserById() {
		mock.createForm();
		int id = 1;
		Long idL = 1L;	
		User user = mock.createUser(id);
		when(userRepository.findById(idL)).thenReturn(Optional.of(user));
		when(userService.findById(idL)).thenReturn(Optional.of(user));
		Optional<User> optional = userService.findById(idL);
		Assert.assertSame(optional.get(), user);
		Assert.assertEquals(optional.get().getId(), user.getId());
	}
	
	//2.1 id invalido
	@Test(expected=NoSuchElementException.class)
	public void findUserByIdInvalido() {
		mock.createForm();
		final int id = 1;
		final Long idL = 1L;
		final Long idL2 = 2L;//este id nao consta na lista
		User user = mock.createUser(id);
		when(userRepository.findById(idL)).thenReturn(Optional.of(user));
		when(userService.findById(idL)).thenReturn(Optional.of(user));
		Optional<User> optional = userService.findById(idL2);
		Assert.assertEquals(optional.get().getId(), user.getId());
	}
	
	// 3. public Log save(User object)
	@Test
	public void registerUser() {
		mock.createForm();
		User user = mock.createUser(1);
		when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		User user2 = userRepository.save(user);
		Assert.assertSame(user,user2);
	}
	
	// 4. public void deleteById(Long id)
	@Test
	public void deleteById() {
		mock.createForm();
		//User user = mock.createUser(0);
		userRepository.deleteById(0L);
		verify(userRepository).deleteById(0L);
	}
	
	// 5. public User getOne(Long id) 
	@Test
	public void getOneId() {
		mock.createForm();
		int id = 1;
		Long idL = 1L;	
		User user = mock.createUser(id);
		when(userRepository.getOne(idL)).thenReturn(user);
		when(userService.getOne(idL)).thenReturn(user);
		User user2 = userService.getOne(idL);
		Assert.assertSame(user2, user);
		Assert.assertEquals(user2.getId(), user.getId());
	}
	
	// 5.1 id invalido 
	@Test(expected=NullPointerException.class)
	public void getOneIdInvalido() {
		mock.createForm();
		int id = 1;
		Long idL = 1L;	
		Long idL2 = 2L;//este id nao consta na lista
		User user = mock.createUser(id);
		when(userRepository.getOne(idL)).thenReturn(user);
		when(userService.getOne(idL)).thenReturn(user);
		User user2 = userService.getOne(idL2);
		Assert.assertEquals(user2.getId(), user.getId());
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// 6 - 	public Optional<User> findByEmail(String email)
	@Test
	public void findByEmail() {
		mock.createForm();
		String name = "pedro.henrique@gmail.com";
		User user = mock.getEmail(name);
		when(userRepository.findByEmail(name)).thenReturn(Optional.of(user));
		when(userService.findByEmail(name)).thenReturn(Optional.of(user));
		Optional<User> listaImpl = userService.findByEmail(name);	
		Assert.assertEquals(listaImpl.get().getEmail(), "pedro.henrique@gmail.com");

	}
	
	// 7. 	public User update(Optional<User> userOptional, UserForm userForm)
	@Test
	public void updateUser() {
		mock.createForm();
		List<UserForm> lista = mock.getListaForm();
		String name = lista.get(0).getName();
		User user = mock.updateUser();
		when(userService.findById(0L)).thenReturn(Optional.of(user));		
		Optional<User> listaImpl = userService.findById(0L);	
		Assert.assertNotEquals(name, listaImpl.get().getName());
	}

}
