package com.team11.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StudentGrades {
	
	//Fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	//Insertable Foreign Key Values
	@Column(name = "student_id")
	private String studentID;
	
	@Column(name = "course_id")
	private int courseID;
	
	@ManyToOne
	@JoinColumn(name = "student_id", insertable = false, updatable = false, nullable = false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false, nullable = false)
	private Course course;
	
	private String grade;
	private int semester;
	private int level;
	
	//Constructors
	public StudentGrades() {}
	

	
	@Override
	public String toString() {
		return "StudentGrades [Id=" + Id + ", studentID=" + studentID + ", courseID=" + courseID + ", student="
				+ student + ", course=" + course + ", grade=" + grade + ", semester=" + semester + ", level=" + level
				+ "]";
	}
	
	
	//Getters & Setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
	
}



