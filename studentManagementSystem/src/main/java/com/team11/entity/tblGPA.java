package com.team11.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_gpa")
public class tblGPA {
	
	@Id
	private String id;
	
	private double gpaCalculated;
	
	
	
	@OneToOne
	@JoinColumn
	@MapsId
	private Student student;
	
	public tblGPA() {}

	@Override
	public String toString() {
		return "tblGPA [id=" + id + ", gpaCalculated=" + gpaCalculated + ", student=" + student + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getGpaCalculated() {
		return gpaCalculated;
	}

	public void setGpaCalculated(double gpaCalculated) {
		this.gpaCalculated = gpaCalculated;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	};
	
	
	
	
}