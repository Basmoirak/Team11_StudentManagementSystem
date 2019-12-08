package com.team11.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	@Size(min = 3, max = 20)
	private String courseCode;
	@Size(min = 3, max = 10)
	private String courseName;
	
	@NotNull
	@Range(min=1, max=200,message = "course unit must be between 1 to 200")
	private int courseUnit;
	
	
	@Column(name = "department_id")
	private int departmentID;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "department_id", insertable = false, updatable = false, nullable = false)
	private Department department;
	
	@OneToMany(mappedBy = "course")
	private List<StudentGrades> studentGrades;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private List<CourseApplicant> courseApplicants;
	
	public List<CourseApplicant> getCourseApplicants() {
		return courseApplicants;
	}

	public void setCourseApplicants(List<CourseApplicant> courseApplicants) {
		this.courseApplicants = courseApplicants;
	}

	//Constructors
	public Course() {}

	public Course(String courseCode, String courseName, int courseUnit, int departmentID) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseUnit = courseUnit;
		this.departmentID = departmentID;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

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

	public List<StudentGrades> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGrades> studentGrades) {
		this.studentGrades = studentGrades;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", courseUnit=" + courseUnit + ", departmentID=" + departmentID + ", department=" + department
				+ ", studentGrades=" + studentGrades + "]";
	}

	
	
}
