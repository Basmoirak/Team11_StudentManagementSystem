package com.team11.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Faculty {
	
	//Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int faculty;
	
	@Size(min = 3, max = 10)
	private String firstName;
	
	@Size(min = 3, max = 10)
	private String lastName;
	private String middleName;
	@Column(name = "department_id")
	private int departmentID;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "department_id", insertable = false, updatable = false, nullable = false)
	private Department department;
	
	//Constructor
	public Faculty() {
	
	}

	public Faculty(String firstName, String lastName, String middleName, int departmentID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.departmentID = departmentID;
	}

	
	public int getFaculty() {
		return faculty;
	}

	public void setFaculty(int faculty) {
		this.faculty = faculty;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Faculty [faculty=" + faculty + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", departmentID=" + departmentID + "]";
	}
	
}

