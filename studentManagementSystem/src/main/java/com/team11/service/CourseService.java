package com.team11.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.team11.entity.Course;
import com.team11.entity.Department;

public interface CourseService {
	
	public List<Course> getCourses();
	
	public void saveCourse(Course course);
	
	public Course getCourse(int id);
	
	public void deleteCourse(int id);
	
	public Page<Course> getPaginated(Pageable pageable);
}
