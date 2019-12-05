package com.team11.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Faculty {
	
	//Fields
	@Id
	private int faculty;
	private String firstName;
	private String lastName;
	private String middleName;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "department_id")
	private Department departmentID;
	
	//Constructor
	public Faculty() {}

	public Faculty(int faculty, String firstName, String lastName, String middleName, Department departmentID) {
		this.faculty = faculty;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.departmentID = departmentID;
	}

	//Getters & Setters
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

	public Department getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Department departmentID) {
		this.departmentID = departmentID;
	}

	@Override
	public String toString() {
		return "Faculty [faculty=" + faculty + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", departmentID=" + departmentID + "]";
	}
	
}

