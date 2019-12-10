package com.team11.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.team11.entity.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	
	public void saveCourse(Course course);
	
	public Course getCourse(int id);
	
	public void deleteCourse(int id);
	
	public List<Course> getAvailableCourses();

	public Page<Course> getPaginated(PageRequest pageable);
}
