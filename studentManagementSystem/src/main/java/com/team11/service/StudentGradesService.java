package com.team11.service;

import java.util.List;

import com.team11.entity.CourseApplicant;
import com.team11.entity.StudentGrades;

public interface StudentGradesService {
	
	public List<StudentGrades> getStudentGrades();
	
	public void saveStudentGrades(StudentGrades course);
	
	public StudentGrades getStudentGrades(int id);
	
	public void deleteStudentGrades(int id);
	
	public void createNewStudentGrades(CourseApplicant ca);
}
