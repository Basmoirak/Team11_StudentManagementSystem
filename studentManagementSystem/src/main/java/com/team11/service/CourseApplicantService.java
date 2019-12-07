package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.team11.entity.CourseApplicant;

public interface CourseApplicantService {
	
	public List<CourseApplicant> getCourses();
	
	public void saveCourseApplicant(CourseApplicant courseApplicant);
	
	public CourseApplicant getCourseApplicant(int id);
	
	public void deleteCourseApplicant(int id);

	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(int studentId);
	
}