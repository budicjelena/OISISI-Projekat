package data;

import java.util.ArrayList;

import model.Brush;
import model.Render;
import model.Software;
import model.enums.FileFormat;

public class SoftwareData {

	private ArrayList<Software> softwares = new ArrayList<>();

	public SoftwareData(ArrayList<Render> renders, ArrayList<Brush> brushes) {
		loadDummyData(renders, brushes);
	}

	private void loadDummyData(ArrayList<Render> renders, ArrayList<Brush> brushes) {
		Render r1 = renders.get(0);
		Render r2 = renders.get(1);
		Render r3 = renders.get(2);

		Brush b1 = brushes.get(0);
		Brush b2 = brushes.get(1);
		Brush b3 = brushes.get(2);

		ArrayList<Brush> brushes1 = new ArrayList<>();
		ArrayList<Brush> brushes2 = new ArrayList<>();

		brushes1.add(b1);

		brushes2.add(b2);
		brushes2.add(b3);

		Software s1 = new Software("3DMAX", brushes1, FileFormat.PDF, r1);
		Software s2 = new Software("PHOTOSHOP", brushes2, FileFormat.JPEG, r2);
		Software s3 = new Software("TEST", brushes2, FileFormat.JPEG, r3);

		softwares.add(s1);
		softwares.add(s2);
		softwares.add(s3);
	}

	public ArrayList<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(ArrayList<Software> softwares) {
		this.softwares = softwares;
	}

	public void removeSoftwareFromList(int index) {
		this.softwares.remove(index);
	}

}