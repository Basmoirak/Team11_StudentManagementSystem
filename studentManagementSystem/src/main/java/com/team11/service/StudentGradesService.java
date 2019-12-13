package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.team11.entity.CourseApplicant;
import com.team11.entity.StudentGrades;

public interface StudentGradesService {
	
	public List<StudentGrades> getStudentGrades();
	
	public void saveStudentGrades(StudentGrades course);
	
	public StudentGrades getStudentGradesById(int id);
	
	public void deleteStudentGrades(int id);
	
	public void createNewStudentGrades(CourseApplicant ca);
	
	public ArrayList<StudentGrades> findStudentGradesByStudentID(String studentId);
	
	public float getTotalUnits(String studentId);
	
	//convert from letter grade to number grade
	public double convertGrade(String letter);

	public List<StudentGrades> getStudentGradesByCourseID(int courseId);
	
	public void updateGrade(String grade, int id);
	
	public Page<StudentGrades> searchAndPaginate(String search, Pageable pageable, int courseId);
}
