package model;

public class Adress {
	
	private String street;
	private String number; // String not int because adress number can be 11A 11B ...
	private String city;
	
    public Adress() {
		
	}

	public Adress(String street, String number, String city) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return street + " " + number + " " + city ;
	}

}
