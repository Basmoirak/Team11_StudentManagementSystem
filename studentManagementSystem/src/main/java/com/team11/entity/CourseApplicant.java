package com.team11.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CourseApplicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	//insertable foreign key values
	@Column(name = "course_id")
	private int courseID;
	@Column(name = "student_id")
	private int studentID;
	
	//foreign key from course
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "course_id", insertable = false, updatable = false, nullable = false)
	private Course course;
	
	//foreign key from student
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "student_id", insertable = false, updatable = false, nullable = false)
	private Student student;
	
	//status
	private int status;
	
	//date log
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date submittedDate;

	public CourseApplicant(int courseID, int studentID, Course course, Student student, int status,
			Date submittedDate) {
		super();
		this.courseID = courseID;
		this.studentID = studentID;
		this.course = course;
		this.student = student;
		this.status = status;
		this.submittedDate = submittedDate;
	}

	public CourseApplicant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	@Override
	public String toString() {
		return "CourseApplicant [Id=" + Id + ", courseID=" + courseID + ", studentID=" + studentID + ", course="
				+ course + ", student=" + student + ", status=" + status + ", submittedDate=" + submittedDate + "]";
	}
	
	
}
