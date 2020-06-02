package com.javawomen.errorcenter.unitTest.level;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.javawomen.errorcenter.controller.form.LevelForm;
import com.javawomen.errorcenter.model.Level;

@Component
public class LevelMock {
	
	List<LevelForm> listForm = new ArrayList<LevelForm>();
	List<Level> levels = new ArrayList<>();
	
	public LevelMock() {
	}

	public List<LevelForm> getListaForm() {
		return listForm;
	}
	
	public List<Level> getLevels() {
		return levels;
	}
	
		public void createForm() {
			LevelForm form1 = new LevelForm();
			form1.setName("ERROR");
			listForm.add(form1);
			
			LevelForm form2 = new LevelForm();
			form2.setName("DEBUG");
			listForm.add(form2);
			
			LevelForm form3 = new LevelForm();
			form3.setName("WARN");
			listForm.add(form3);
		}
		
		public void create() {
			Level obj = new Level();
			obj.setId(0L);
			obj.setName("ERROR");
			levels.add(obj);
			
			Level obj1 = new Level();
			obj1.setId(1L);
			obj1.setName("DEBUG");
			levels.add(obj1);
			
			Level obj2 = new Level();
			obj2.setId(2L);
			obj2.setName("WARN");
			levels.add(obj2);
		}

		public Level getLevel(int id) {
			return levels.get(id);
		}
		
		public List<Level> listaLevel(String name) {
			List<Level> lista = new ArrayList<>();
			for (int j = 0; j < levels.size(); j++) {
				if(levels.get(j).getName().contentEquals(name)) {
					lista.add(levels.get(j));
				}
			}
			return lista;
		}	
}
