package com.team11.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
	@Id
	private String courseCode;
	private String courseName;
	private int courseUnit;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "department_id")
	private Department departmentID;
	
	@OneToMany(mappedBy = "course")
	private List<StudentGrades> studentGrades;
	
	//Constructors
	public Course() {}

	public Course(String courseCode, String courseName, int courseUnit, Department departmentID) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseUnit = courseUnit;
		this.departmentID = departmentID;
	}

	//Getters & Setters
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseUnit() {
		return courseUnit;
	}

	public void setCourseUnit(int courseUnit) {
		this.courseUnit = courseUnit;
	}

	public Department getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Department departmentID) {
		this.departmentID = departmentID;
	}

	public List<StudentGrades> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGrades> studentGrades) {
		this.studentGrades = studentGrades;
	}

	@Override
	public String toString() {
		return "Course [courseCode=" + courseCode + ", courseName=" + courseName + ", courseUnit=" + courseUnit
				+ ", departmentID=" + departmentID + "]";
	}
	
}
