package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team11.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	
}
