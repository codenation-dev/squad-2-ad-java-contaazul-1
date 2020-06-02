package com.javawomen.errorcenter.unitTest.token;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.javawomen.errorcenter.config.security.TokenService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(TokenService.class)
public class TestToken {
		
		@Autowired
	    private MockMvc mvc;
				
		@Before
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }
	
	@Test
    public void shouldNotAllowAccess() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(status().isForbidden());
    }
	
	@Test
	public void shouldGenerateAuthToken() throws Exception {
		String body = "{\"email\":\"admin@email.com\",\"senha\":\"admin123\"}";
        String token = TokenService.createToken("admin@email.com");
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(body)
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}
   	
}
