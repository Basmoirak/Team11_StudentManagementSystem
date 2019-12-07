package com.team11.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.CourseApplicant;

@Repository
public interface CourseApplicantRepository extends JpaRepository<CourseApplicant, Integer>{
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(int studentId);
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentIDAndStatus(int studentId, int status);
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStatus(int status);
	
	@Modifying
	@Transactional
	@Query("update CourseApplicant ca set ca.status =1 where ca.id =:id")
	public void approvePendingApplicant(int id);
}
