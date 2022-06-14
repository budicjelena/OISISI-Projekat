package data;

import java.util.ArrayList;

import model.Software;

public class SoftwareData {

	private ArrayList<Software> softwares = new ArrayList<>();
	
	public SoftwareData() {
		loadDummyData();
	}

	private void loadDummyData() {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(ArrayList<Software> softwares) {
		this.softwares = softwares;
	}
	
	
}
