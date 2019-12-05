package com.team11.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "level")
public class Level {
	
	//Fields
	@Id
	private int Id;
	private int level;
	public int getId() {
		return Id;
	}
	
	//Constructors
	public Level() {}
	
	public Level(int id, int level) {
		Id = id;
		this.level = level;
	}
	
	//Getter & Setters
	public void setId(int id) {
		Id = id;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Level [Id=" + Id + ", level=" + level + "]";
	}
}
