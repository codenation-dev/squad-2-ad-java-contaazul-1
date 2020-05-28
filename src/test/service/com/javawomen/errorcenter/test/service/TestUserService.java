package service.com.javawomen.errorcenter.test.service;

import static org.junit.Assert.assertEquals;

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
		Assert.assertSame(mock.getListaForm().get(3).getName(), user.getName());
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
