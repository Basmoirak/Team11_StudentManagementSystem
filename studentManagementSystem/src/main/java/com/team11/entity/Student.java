package com.team11.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "student")
public class Student {
	
	// Fields
	@Id
	private String Id;
	
	@NotBlank(message = "first name is required")
	private String firstName;
	@NotBlank(message = "last name is required")
	private String lastName;
	@NotBlank
	private String gender;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	@NotBlank(message = "Degree is required")
	private String degree;
	@NotBlank(message = "Address is required")
	private String address;
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp="^(8|9)[0-9]{7}$", message = "Singaporean Mobile Numbers only")
	private String mobile;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "level_id")
	private Level level;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "semester_id")
	private Semester semester;
	
	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private tblGPA cgpa;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "status_id")
	private Status status;
	
	@OneToMany(mappedBy = "student")
	private List<StudentGrades> studentGrades;
	
	//course applicant  one to many
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private List<CourseApplicant> courseApplicants;
	
	public List<CourseApplicant> getCourseApplicants() {
		return courseApplicants;
	}

	public void setCourseApplicants(List<CourseApplicant> courseApplicants) {
		this.courseApplicants = courseApplicants;
	}
	
	// Constructors
	public Student() {}
	
	public Student(String firstName, String lastName, String gender, Date birthDate,
			String degree, String address, String mobile, Level level, Semester semester, tblGPA cgpa, Status status) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.degree = degree;
		this.address = address;
		this.mobile = mobile;
		this.level = level;
		this.semester = semester;
		this.cgpa = cgpa;
		this.status = status;
	}

	// Getters and Setters
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public tblGPA getCgpa() {
		return cgpa;
	}

	public void setCgpa(tblGPA cgpa) {
		this.cgpa = cgpa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<StudentGrades> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGrades> studentGrades) {
		this.studentGrades = studentGrades;
	}
	
	@Override
	public String toString() {
		return "Student [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", birthDate=" + birthDate + ", degree=" + degree + ", address=" + address + ", mobile=" + mobile
				+ ", level=" + level + ", semester=" + semester + ", cgpa=" + cgpa + ", status=" + status
				+ ", studentGrades=" + studentGrades + "]";
	}
	
}

