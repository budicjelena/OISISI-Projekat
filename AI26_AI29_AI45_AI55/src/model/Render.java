package model;

import java.util.ArrayList;

public class Render {
	
	private String name;
	private ArrayList<Material> materials = new ArrayList<>();
	private ArrayList<Camera> cameras = new ArrayList<>();
	private ArrayList<Object> objects = new ArrayList<>();
	
	public Render() {
		
	}
	
	public Render(String name, ArrayList<Material> materials, ArrayList<Camera> cameras, ArrayList<Object> objects) {
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
	public ArrayList<Material> getMaterials(){
		return materials;
	}
	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}
	public ArrayList<Camera> getCameras(){
		return cameras;
	}
	public void setCameras(ArrayList<Camera> cameras) {
		this.cameras = cameras;
	}
	public ArrayList<Object> getObjects(){
		return objects;
	}
	public void setObjects(ArrayList<Object> objects) {
		this.objects = objects;
	}

}
