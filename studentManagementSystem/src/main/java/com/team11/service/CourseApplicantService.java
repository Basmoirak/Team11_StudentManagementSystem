package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	
	public CourseApplicant findByIdAndStatus(int id);
	
	public List<CourseApplicant> getActiveCourses(String studentId);

	public Page<CourseApplicant> searchAndPaginate(String search, Pageable pageable);
	
	public List<String> findStudentEmailByCourseId(int courseId);
	
}
