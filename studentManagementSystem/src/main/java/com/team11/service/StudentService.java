package com.team11.service;

import java.util.List;

import com.team11.entity.Student;

public interface StudentService {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(int theId);
	
	public void deleteStudent(int theId);
}
