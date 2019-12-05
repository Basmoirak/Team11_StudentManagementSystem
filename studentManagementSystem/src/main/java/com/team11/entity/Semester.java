package com.team11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semester")
public class Semester {
	
	//Fields
	@Id
	private int semester;
	private String label;
	
	//Constructor
	public Semester() {}
	
	public Semester(int semester, String label) {
		this.semester = semester;
		this.label = label;
	}

	//Getters & Setters
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "Semester [semester=" + semester + ", label=" + label + "]";
	}
	
}
