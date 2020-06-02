package com.javawomen.errorcenter.unitTest.env;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.EnvironmentForm;
import com.javawomen.errorcenter.model.Environment;

@Component
public class EnvMock {
	
		List<EnvironmentForm> listForm = new ArrayList<EnvironmentForm>();
		List<Environment> Environments = new ArrayList<>();
		
		public EnvMock() {
		}

		public List<EnvironmentForm> getListaForm() {
			return listForm;
		}
		
		public List<Environment> getEnvironments() {
			return Environments;
		}
		
		// cria dados de entrada
			public void createForm() {
				EnvironmentForm form1 = new EnvironmentForm();
				form1.setName("PRODUCAO");
				listForm.add(form1);
				
				EnvironmentForm form2 = new EnvironmentForm();
				form2.setName("DESENVOLVIMENTO");
				listForm.add(form2);
				
				EnvironmentForm form3 = new EnvironmentForm();
				form3.setName("HOMOLOGACAO");
				listForm.add(form3);
			}
			
			public void create() {
				Environment obj = new Environment();
				obj.setId(0L);
				obj.setName("PRODUCAO");
				
				Environment obj1 = new Environment();
				obj1.setId(1L);
				obj1.setName("DESENVOLVIMENTO");
				Environments.add(obj1);
				
				Environment obj2 = new Environment();
				obj2.setId(2L);
				obj2.setName("HOMOLOGACAO");
				Environments.add(obj2);
			}

			public Environment getEnvironment(int id) {
				return Environments.get(id);
			}
			
			public List<Environment> listaEnvironment(String name) {
				List<Environment> lista = new ArrayList<>();
				for (int j = 0; j < Environments.size(); j++) {
					if(Environments.get(j).getName().contentEquals(name)) {
						lista.add(Environments.get(j));
					}
				}
				return lista;
			}	
	}
