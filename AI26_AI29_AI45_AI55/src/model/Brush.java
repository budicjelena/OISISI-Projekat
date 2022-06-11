package model;

public class Brush {
	
	private String name;
	private String purpose;
	private Color color;
	
    public Brush() {
		
	}
    
    public Brush(String name, String purpose, Color color) {
		super();
		this.name = name;
		this.purpose = purpose;
		this.color = color;
	}
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getPurpose() {
		return purpose;
	}
    
    public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
    
    public Color getColor() {
		return color;
	}
    
    public void setColor(Color color) {
		this.color = color;
	}

}
