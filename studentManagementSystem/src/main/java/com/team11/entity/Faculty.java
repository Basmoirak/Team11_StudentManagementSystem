package com.team11.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Faculty {
	
	//Fields
	@Id
	private String faculty;
	@Size(min = 3, max = 10)
	private String firstName;
	@Size(min = 3, max = 10)
	private String lastName;
	@Column(name = "department_id")
	private int departmentID;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "department_id", insertable = false, updatable = false, nullable = false)
	private Department department;
	
	@OneToMany(mappedBy = "faculty")
	private List<Course> courses;
	
	//Constructor
	public Faculty() {
		
	}

	public Faculty(String facultyID, String firstName, String lastName, int departmentID) {
		this.faculty = facultyID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentID = departmentID;
	}

	
	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
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
		return "Faculty [faculty=" + faculty + ", firstName=" + firstName + ", lastName=" + lastName + ", departmentID=" + departmentID + "]";
	}
	
}

