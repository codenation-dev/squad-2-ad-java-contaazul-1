package service.com.javawomen.errorcenter.test.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.UpdateUserForm;
import com.javawomen.errorcenter.controller.form.UserForm;
import com.javawomen.errorcenter.model.User;

@Component
public class UserMock {
	List<UserForm> listaForm = new ArrayList<>();
	List<UpdateUserForm> updateForm = new ArrayList<>();
	List<User> lista = new ArrayList<>();
	
	UserForm form = new UserForm();
	UpdateUserForm formUpdate = new UpdateUserForm();

	public void createForm() {
		form.setName("Leticia Buss");
		form.setEmail("leticia.souzabuss@gmail.com");
		form.setPassword("Lele2008");
		listaForm.add(form);

		form.setName("Alice Borges");
		form.setEmail("alice.borges@gmail.com");
		form.setPassword("Alice8547");
		listaForm.add(form);

		form.setName("Maria da Silva");
		form.setEmail("maria.silva@gmail.com");
		form.setPassword("Maria8547");
		listaForm.add(form);

		form.setName("João de Souza");
		form.setEmail("joao.souza@gmail.com");
		form.setPassword("Joao8547");
		listaForm.add(form);

		form.setName("Pedro Henrique");
		form.setEmail("pedro.henrique@gmail.com");
		form.setPassword("Pedro8547");
		listaForm.add(form);
	}
	
	public void updateUserForm() {
		formUpdate.setName("Maria Henrique");
		formUpdate.setEmail("maria.henrique@gmail.com");
		formUpdate.setPassword("Maria8547");
		updateForm.add(formUpdate);
	}
	
	public UserMock() {
		createForm();
		updateUserForm();
	}

	public List<UserForm> getListaForm() {
		return listaForm;
	}
	
	public List<UpdateUserForm> getUpdateForm() {
		return updateForm;
	}
	
	public List<User> getLista() {
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
	
	public User updateUser() {
		User user = listaUsers().get(1);
		List<UpdateUserForm> updateForm = getUpdateForm();
		user.setName(updateForm.get(0).getName());
		user.setEmail(updateForm.get(0).getEmail());
		user.setPassword(updateForm.get(0).getPassword());
		
		lista.set(0, user);
		return user;
	}
	
	public List<User> listaUsers() {
		
		for (int j = 0; j < listaForm.size(); j++) {
			User user = createUser(j);
			lista.add(user);
		}
		
		return lista;
	}
}