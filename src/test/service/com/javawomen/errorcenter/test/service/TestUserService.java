package service.com.javawomen.errorcenter.test.service;

import static org.junit.Assert.assertEquals;
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

import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;
import com.javawomen.errorcenter.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService;
	
	UserMock mock = new UserMock();
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void registerUser() {
		User user = mock.createUser(1);
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
		userRepository.save(user);
		Assert.assertSame(mock.getListaForm().get(1).getName(), user.getName());
	}
	
	@Test
	public void authenticate() {
		
	}
	
	@Test
	public void getAllUsers() { 
		mock.createForm();
		Pageable pageable = PageRequest.of(0, 10);
		
		List<User> lista = mock.listaUsers();
		Page<User> userPage = new PageImpl<>(lista, pageable, 0);
		
		when(userRepository.findAll(pageable)).thenReturn(userPage);
		
		Page<User> userPageble = userRepository.findAll(pageable);
		
		Assert.assertEquals(userPage.getTotalPages(), userPageble.getTotalPages());
		Assert.assertEquals(userPage.getNumberOfElements(), userPageble.getNumberOfElements());
		Assert.assertEquals(lista.size(), userPageble.getNumberOfElements());
	}
	
	@Test
	public void updateUser() {
		mock.createForm();
		mock.updateUserForm();
		
		User user = mock.updateUser();
		List<User> lista = mock.getLista();
		
		Assert.assertEquals(lista.get(0).getName(), user.getName());
		Assert.assertEquals(lista.get(0).getEmail(), user.getEmail());
		Assert.assertEquals(lista.get(0).getPassword(), user.getPassword());
	}
	
	@Test
	public void deleteUser() {
		
	}
	
	@Test
	public void findUserById() {
		Long id = 5L;
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isPresent()) {
			assertEquals("aliceborges@outlook.com.br", userOptional.get().getEmail());
		}
	}
}
