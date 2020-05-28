package service.com.javawomen.errorcenter.test.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.javawomen.errorcenter.repository.LogRepository;
import com.javawomen.errorcenter.service.LogService;


@RunWith(MockitoJUnitRunner.class)
public class TestLogService {
	@Mock
	private LogRepository logRepository;
	
	@InjectMocks
	private LogService logService;
}
