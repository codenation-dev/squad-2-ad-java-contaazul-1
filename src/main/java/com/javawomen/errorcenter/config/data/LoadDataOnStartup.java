package com.javawomen.errorcenter.config.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.service.RoleService;
import com.javawomen.errorcenter.service.UserService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class LoadDataOnStartup{
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	boolean loaded = false;
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadData(){
		System.out.println("Application is sucecssfully Started");
		if (loaded)
		      return;
		long roles = roleService.count();
		if(!(roles > 0)){
		 
		//seta os roles
		Role roleAdmin = roleService.converter("ROLE_ADMIN");
		Role roleUser = roleService.converter("ROLE_USER");
		Role roleSystem = roleService.converter("ROLE_SYSTEM");
		
		//persiste role no banco
		roleService.save(roleAdmin);
		roleService.save(roleUser);
		roleService.save(roleSystem);	
		
		//cria um role admin
		User userDefault = new User("admin", 
				(new BCryptPasswordEncoder().encode("admin123")), 
				"admin@email.com", roleAdmin);				
		
		//persiste o user default no banco
		userService.save(userDefault);
		}
	    loaded = true;		
	}
}
