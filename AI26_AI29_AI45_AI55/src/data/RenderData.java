package data;

import java.util.ArrayList;

import model.Render;
import model.Camera;
import model.Material;

public class RenderData {

	private ArrayList<Render> renders = new ArrayList<>();

	public RenderData() {
		loadDummyData();
	}

	private void loadDummyData() {
		ArrayList<Material> m1 = new ArrayList<>();
		ArrayList<Material> m2 = new ArrayList<>();
		m1.add(Material.MAT1);
		m1.add(Material.MAT2);
		m2.add(Material.MAT2);
		m2.add(Material.MAT3);
		
		ArrayList<model.Object> o1 = new ArrayList<>();
		ArrayList<model.Object> o2 = new ArrayList<>();
		o1.add(model.Object.OBJ_1);
		o1.add(model.Object.OBJ_2);
		o2.add(model.Object.OBJ_3);

		ArrayList<Camera> c1 = new ArrayList<>();
		ArrayList<Camera> c2 = new ArrayList<>();
		
		c1.add(Camera.HD);
		c1.add(Camera.ROUNDED);
		
		c2.add(Camera.HD);
		c2.add(Camera.ULTRA_HD);
		c2.add(Camera.WIDE);
		
		Render r1= new Render("r1", m1, c1, o1);
		Render r2= new Render("r2", m2, c2, o2);
		Render r3= new Render("r3", m1, c2, o2);
		
		renders.add(r1);
		renders.add(r2);
		renders.add(r3);
	}

	public ArrayList<Render> getRenders() {
		return renders;
	}

	public void setRenders(ArrayList<Render> renders) {
		this.renders = renders;
	}
	
	public void removeRenderFromList(int  index) {
		// TODO Auto-generated method stub
		this.renders.remove(index);
	}
}