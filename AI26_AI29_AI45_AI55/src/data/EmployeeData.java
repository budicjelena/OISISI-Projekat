package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Adress;
import model.Employee;
import model.Software;
import model.WorkPlace;

public class EmployeeData {
	
	
	private ArrayList<Employee> employees = new ArrayList<>(); 
	
	//initial data
	
	public EmployeeData(){
		loadDummyData();
	}
	
	private void loadDummyData() {
		Adress a1 = new Adress("street a", "1", "City A");
		Adress a2 = new Adress("street b", "2", "City B");
		Adress a3 = new Adress("street c", "3A", "City C");
		
		Software s1 = new Software();
		Software s2 = new Software();
		Software s3 = new Software();
		s1.setName("s1");
		s2.setName("s2");
		s3.setName("s4");
		
		ArrayList<Software> softwares1 = new ArrayList<>();
		ArrayList<Software> softwares2 = new ArrayList<>();
		ArrayList<Software> softwares3 = new ArrayList<>();
		
		softwares1.add(s3);
		
		softwares2.add(s1);
		softwares2.add(s2);
		
		softwares3.add(s1);
		softwares3.add(s3);
		
		Employee e1 =new Employee("a","a",1, LocalDate.parse("02-08-2000",DateTimeFormatter.ofPattern("dd-MM-yyyy")),"a",a1, WorkPlace.ANIMATOR,softwares1);
		Employee e2 =new Employee("b","b",1, LocalDate.parse("11-11-1998",DateTimeFormatter.ofPattern("dd-MM-yyyy")),"b",a2, WorkPlace.ILLUSTRATOR,softwares2);
		Employee e3 =new Employee("c","c",1, LocalDate.parse("12-03-1999",DateTimeFormatter.ofPattern("dd-MM-yyyy")),"c",a3, WorkPlace.RIGGER,softwares3);
		
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public void removeEmployeeFromList(int index) {
		// TODO Auto-generated method stub
		this.employees.remove(index);
	}
	

}