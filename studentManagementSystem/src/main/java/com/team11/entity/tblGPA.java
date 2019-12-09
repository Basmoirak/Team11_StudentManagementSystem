package com.team11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_gpa")
public class tblGPA {
	
	//Fields
	@Id
	private String Id;
	private float gpaCalculated;
	
	@OneToOne
	@JoinColumn
	@MapsId
	private Student student;
	
	// Constructor
	public tblGPA() {}

	public tblGPA(float gpaCalculated) {
		this.gpaCalculated = gpaCalculated;
	}

	//Getters and Setters
	public float getGpaCalculated() {
		return gpaCalculated;
	}

	public void setGpaCalculated(float gpaCalculated) {
		this.gpaCalculated = gpaCalculated;
	}

	@Override
	public String toString() {
		return "tblGPA [Id=" + Id + ", gpaCalculated=" + gpaCalculated + ", student=" + student + "]";
	}
	
}
