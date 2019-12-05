package com.team11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {
	
	//Fields
	@Id
	private int status;
	private String label;
	
	//Constructor
	public Status() {}

	public Status(int status, String label) {
		this.status = status;
		this.label = label;
	}

	//Getters & Setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
