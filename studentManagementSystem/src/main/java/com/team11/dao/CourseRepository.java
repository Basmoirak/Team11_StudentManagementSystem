package com.team11.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.Course;
import com.team11.entity.CourseApplicant;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
//	getting available courses by filtering with start date
	@Modifying
	@Transactional
	@Query("SELECT c FROM Course c  WHERE startDate > curdate() - 2")
	public List<Course> getAvailableCourses();
	
	
}
