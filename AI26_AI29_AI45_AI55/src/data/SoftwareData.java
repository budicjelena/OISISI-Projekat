package data;

import java.util.ArrayList;

import model.Brush;
import model.Color;
import model.FileFormat;
import model.Render;
import model.Software;

public class SoftwareData {

	private ArrayList<Software> softwares = new ArrayList<>();
	
	public SoftwareData() {
		loadDummyData();
	}

	private void loadDummyData() {
		// TODO Auto-generated method stub
		
		Render r1= new Render("r1", null, null, null);
		Render r2= new Render("r2", null, null, null);
		Render r3= new Render("r2", null, null, null);
		
		Brush b1 = new Brush("brush 1", "bbb 1", Color.BLUE);
		Brush b2 = new Brush("brush 2", "bbb 2", Color.RED);
		Brush b3 = new Brush("brush 3", "bbb 3", Color.GREEN);
		
		ArrayList<Brush> brushes1 = new ArrayList<>();
		ArrayList<Brush> brushes2 = new ArrayList<>();
		
		brushes1.add(b1);
		
		brushes2.add(b2);
		brushes2.add(b3);
		
		ArrayList<String> animationTools1 = new ArrayList<>();
		ArrayList<String> animationTools2 = new ArrayList<>();
		
		animationTools1.add("animation tool 1");
		animationTools1.add("animation tool 2");

		animationTools2.add("animation tool 3");
		animationTools2.add("animation tool 4");
		animationTools2.add("animation tool 5");

		Software s1 = new Software("3DMAX", brushes1, FileFormat.PDF, r1);
		Software s2 = new Software("PHOTOSHOP", brushes2, FileFormat.JPEG, r2);
		
		softwares.add(s1);
		softwares.add(s2);
	}

	public ArrayList<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(ArrayList<Software> softwares) {
		this.softwares = softwares;
	}

	public void removeSoftwareFromList(int  index) {
		// TODO Auto-generated method stub
		this.softwares.remove(index);
	}
	
	
}