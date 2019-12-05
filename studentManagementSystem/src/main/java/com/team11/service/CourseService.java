package com.team11.service;

import java.util.List;

import com.team11.entity.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	
	public void saveCourse(Course course);
	
	public Course getCourse(int id);
	
	public void deleteCourse(int id);
}
