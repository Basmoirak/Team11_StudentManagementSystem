package com.team11.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.team11.entity.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	
	public Course saveCourse(Course course);
	
	public Course getCourse(int id);
	
	public void deleteCourse(int id);
	
	public List<Course> getAvailableCourses();
	
	public List<Course> getCourseByFacultyID(String id);

	public List<Course> getActiveCourses(String id);
	
	public Page<Course> searchAndPaginate(String search, Pageable pageable);
}
