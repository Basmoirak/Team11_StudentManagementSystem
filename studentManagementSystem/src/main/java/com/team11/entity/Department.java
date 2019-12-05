package com.team11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
	
	@Id
	private int Id;
	private String firstName;
	
	//Constructors
	public Department() {}

	//Getters & Setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "Department [Id=" + Id + ", firstName=" + firstName + "]";
	}
	
}
