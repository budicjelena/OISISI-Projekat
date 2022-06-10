package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Employee {

	private String firstName;
	private String lastName;
	private int jmbg;
	private LocalDate dateOfBirth;
	private String email;
	private Adress adress;
	private WorkPlace workplace;
	private ArrayList<Software> softwares = new ArrayList<>();
	

	public Employee() {
		
	}


	public Employee(String firstName, String lastName, int jmbg, LocalDate dateOfBirth, String email, Adress adress,
			WorkPlace workplace, ArrayList<Software> softwares) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.jmbg = jmbg;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.adress = adress;
		this.workplace = workplace;
		this.softwares = softwares;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getJmbg() {
		return jmbg;
	}


	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Adress getAdress() {
		return adress;
	}


	public void setAdress(Adress adress) {
		this.adress = adress;
	}


	public WorkPlace getWorkplace() {
		return workplace;
	}


	public void setWorkplace(WorkPlace workplace) {
		this.workplace = workplace;
	}


	public ArrayList<Software> getSoftwares() {
		return softwares;
	}


	public void setSoftwares(ArrayList<Software> softwares) {
		this.softwares = softwares;
	}
	
	
	

}
