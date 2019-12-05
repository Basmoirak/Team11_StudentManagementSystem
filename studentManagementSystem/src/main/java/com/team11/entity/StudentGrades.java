package com.team11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StudentGrades {
	
	//Fields
	@Id
	private int Id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	private String grade;
	private int semester;
	private int level;
	
	//Constructors
	public StudentGrades() {}
	
	//Getters & Setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	@Override
	public String toString() {
		return "StudentGrades [Id=" + Id + ", student=" + student + ", course=" + course + ", grade=" + grade
				+ ", semester=" + semester + ", level=" + level + "]";
	}
	
}



