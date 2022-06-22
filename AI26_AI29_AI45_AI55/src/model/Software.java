package model;

import java.util.ArrayList;

import model.FileFormat;

public class Software {
	
	private String name;
	
	private FileFormat fileFormat;
	private Render render;
	private ArrayList<Brush> brushes =  new ArrayList<>();
	
	public Software() {
		
	}

	public Software(String name, ArrayList<Brush> brushes, FileFormat fileFormat, Render render) {
		
		super();
		this.name = name;
		this.brushes = brushes;
		this.fileFormat = fileFormat;
		this.render = render;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Brush> getBrushes() {
		return brushes;
	}

	public void setBrushes(ArrayList<Brush> brushes) {
		this.brushes = brushes;
	}

	public FileFormat getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(FileFormat fileFormat) {
		this.fileFormat = fileFormat;
	}

	public Render getRender() {
		return render;
	}

	public void setRender(Render render) {
		this.render = render;
	}

	@Override
	
	public String toString() {
		return "Software [name = " + name +"]";
	}
	
	
}
