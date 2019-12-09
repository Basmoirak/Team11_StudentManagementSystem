package com.team11.service;

import java.util.List;

import com.team11.entity.Level;
import com.team11.entity.Semester;
import com.team11.entity.Status;
import com.team11.entity.Student;

public interface StudentService {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(String theId);
	
	public void deleteStudent(String theId);
	
	public List<Level> getLevels();
	
	public List<Semester> getSemesters();
	
	public List<Status> getStatuses();
}
