package data;

import java.util.ArrayList;

import model.Brush;
import model.enums.Color;

public class BrushData {
	
	private ArrayList<Brush> brushes = new ArrayList<>();
	
	public BrushData() {
		loadDummyData();
	}
	
	private void loadDummyData() {
		
		Brush b1 = new Brush("thin", "used for drawing lines", Color.RED);
		Brush b2 = new Brush("wide", "used for coloring", Color.RED);
		Brush b3 = new Brush("transparent", "used for shading", Color.YELLOW);
		Brush b4 = new Brush("ultra wide", "used for coloring background", Color.WHITE);
		Brush b5 = new Brush("rounded", "used for rounding objects", Color.GREEN);
		
		brushes.add(b1);
		brushes.add(b2);
		brushes.add(b3);
		brushes.add(b4);
		brushes.add(b5);
		
	}
	
	public ArrayList<Brush> getBrushes() {
		return brushes;
	}
	
	public void setBrushes(ArrayList<Brush> brushes) {
		this.brushes = brushes;
	}
	
	public void removeBrushFromList(int index) {
		//TODO Auto-generated method stub
		this.brushes.remove(index);
	}

}
