package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import com.team11.entity.CourseApplicant;

public interface CourseApplicantService {
	
	public List<CourseApplicant> getCourseApplicants();
	
	public void saveCourseApplicant(CourseApplicant courseApplicant);
	
	public CourseApplicant getCourseApplicant(int id);
	
	public void deleteCourseApplicant(int id);

	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(String studentId);
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentIDAndStatus(String studentId, int status);
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStatus(int status);
	
	public void approvePendingApplicant(int id);

	public List<CourseApplicant> getActiveCourses(String studentId);


	
}
