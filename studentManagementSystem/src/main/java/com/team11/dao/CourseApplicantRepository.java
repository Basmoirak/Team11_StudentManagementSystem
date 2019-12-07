package com.team11.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team11.entity.CourseApplicant;

@Repository
public interface CourseApplicantRepository extends JpaRepository<CourseApplicant, Integer>{
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(int studentId);
}
