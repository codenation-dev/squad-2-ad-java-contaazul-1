package com.javawomen.errorcenter.testUnit.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.RoleForm;
import com.javawomen.errorcenter.model.Role;

@Component
public class RoleMock {

	List<RoleForm> listForm = new ArrayList<RoleForm>();
		List<Role> Roles = new ArrayList<>();
		
		public RoleMock() {
		}

		public List<RoleForm> getListaForm() {
			return listForm;
		}
		
		public List<Role> getRoles() {
			return Roles;
		}
		
			public void createForm() {				
				RoleForm form1 = new RoleForm();				
				form1.setRoleName("ROLE_ADMIN");
				listForm.add(form1);
								
				RoleForm form2 = new RoleForm();
				form2.setRoleName("ROLE_USER");
				listForm.add(form2);
				
				RoleForm form3 = new RoleForm();
				form3.setRoleName("ROLE_SYSTEM");
				listForm.add(form3);
			}
			
			public void create() {
				Role obj = new Role();
				obj.setId(0L);
				obj.setRoleName("ROLE_ADMIN");
				Roles.add(obj);
				
				Role obj1 = new Role();
				obj1.setId(1L);
				obj1.setRoleName("ROLE_USER");
				Roles.add(obj1);
				
				Role obj2 = new Role();
				obj2.setId(2L);
				obj2.setRoleName("ROLE_SYSTEM");
				Roles.add(obj2);
			}

			public Role getRole(int id) {
				return Roles.get(id);
			}
			
			public List<Role> listaRole(String name) {
				List<Role> lista = new ArrayList<>();
				for (int j = 0; j < Roles.size(); j++) {
					if(Roles.get(j).getRoleName().contentEquals(name)) {
						lista.add(Roles.get(j));
					}
				}
				return lista;
			}	
	}