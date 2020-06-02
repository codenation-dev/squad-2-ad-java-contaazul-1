package com.javawomen.errorcenter.unitTest.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.model.User;

@Component
public class UserMock {
	
	List<UserForm> listaForm = new ArrayList<>();
	List<User> lista = new ArrayList<>();
	//UserForm form = new UserForm();
	
	public UserMock() {
	}
	
	public void createForm() {
		UserForm form = new UserForm();
		form.setName("Leticia Buss");
		form.setEmail("leticia.souzabuss@gmail.com");
		form.setPassword("Lele2008");
		listaForm.add(form);

		UserForm form1 = new UserForm();
		form1.setName("Alice Borges");
		form1.setEmail("alice.borges@gmail.com");
		form1.setPassword("Alice8547");
		listaForm.add(form1);

		UserForm form2 = new UserForm();
		form2.setName("Maria da Silva");
		form2.setEmail("maria.silva@gmail.com");
		form2.setPassword("Maria8547");
		listaForm.add(form2);

		UserForm form3 = new UserForm();
		form3.setName("João de Souza");
		form3.setEmail("joao.souza@gmail.com");
		form3.setPassword("Joao8547");
		listaForm.add(form3);

		UserForm form4 = new UserForm();
		form4.setName("Pedro Henrique");
		form4.setEmail("pedro.henrique@gmail.com");
		form4.setPassword("Pedro8547");
		listaForm.add(form4);
	}
	
	public List<User> listaUsers() {
		for (int j = 0; j < listaForm.size(); j++) {
			User user = createUser(j);
			lista.add(user);
		}
		return lista;
	}
	
	public User createUser(int id) {
		UserForm form = getListaForm().get(id);
		User user = new User();
		user.setCreatedAt(LocalDateTime.now());
		user.setId((long) id);
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		return user;
	}
	
	public User getEmail(String name) {
		User user;
		for (int i = 0; i < listaForm.size(); i++) {
			if (listaForm.get(i).getEmail().contentEquals(name)) {
				user = createUser(i);
				return user;
			}
		}
		return null;
	}

	public List<UserForm> getListaForm() {
		return this.listaForm;
	}
	
	public User updateUser() {
		UserForm form = listaForm.get(0);
		form.setName("Maria Henrique");
		form.setEmail("maria.henrique@gmail.com");
		form.setPassword("Maria8547");
		listaForm.add(0, form);
		return createUser(0);
	}
}