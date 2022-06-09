package model;

import java.util.ArrayList;

public class Render {
	
	private String name;
	private ArrayList<String> materials = new ArrayList<>();
	private ArrayList<String> cameras = new ArrayList<>();
	private ArrayList<String> objects = new ArrayList<>();
	
	public Render() {
		
	}
	
	public Render(String name, ArrayList<String> materials, ArrayList<String> cameras, ArrayList<String> objects) {
		super();
		this.name = name;
		this.materials = materials;
		this.cameras = cameras;
		this.objects = objects;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getMaterials(){
		return materials;
	}
	public void setMaterials(ArrayList<String> materials) {
		this.materials = materials;
	}
	public ArrayList<String> getCameras(){
		return cameras;
	}
	public void setCameras(ArrayList<String> cameras) {
		this.cameras = cameras;
	}
	public ArrayList<String> getObjects(){
		return objects;
	}
	public void setObjects(ArrayList<String> objects) {
		this.objects = objects;
	}

}
