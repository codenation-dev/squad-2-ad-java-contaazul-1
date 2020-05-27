package service.com.javawomen.errorcenter.test.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;
import com.javawomen.errorcenter.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void findUserById() {
		Long id = 5L;
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isPresent()) {
			assertEquals("aliceborges@outlook.com.br", userOptional.get().getEmail());
		}
	}
}
