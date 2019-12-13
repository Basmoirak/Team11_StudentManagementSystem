package com.team11.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.team11.entity.Level;
import com.team11.entity.Semester;
import com.team11.entity.Student;

public interface StudentService {
	
	public List<Student> getStudents();
	
	public void saveStudent(Student student);
	
	public Student getStudent(String theId);
	
	public void deleteStudent(String theId);
	
	public List<Level> getLevels();
	
	public List<Semester> getSemesters();
	
	public Page<Student> searchAndPaginate(String search, Pageable pageable);
}
