package model;

import java.util.ArrayList;

public class Software {
	
	private String name;
	private ArrayList<Brush> brushes =  new ArrayList<>();
	private FileFormat fileFormat;
	private Render render;
	private ArrayList<String> animationTools =  new ArrayList<>(); // maybe change String to Object (make AnimationTool class)
	
	public Software() {
		
	}

	public Software(String name, ArrayList<Brush> brushes, FileFormat fileFormat, Render render,
			ArrayList<String> animationTools) {
		super();
		this.name = name;
		this.brushes = brushes;
		this.fileFormat = fileFormat;
		this.render = render;
		this.animationTools = animationTools;
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

	public ArrayList<String> getAnimationTools() {
		return animationTools;
	}

	public void setAnimationTools(ArrayList<String> animationTools) {
		this.animationTools = animationTools;
	}
	
	
}