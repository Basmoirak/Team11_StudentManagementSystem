package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team11.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	
}
