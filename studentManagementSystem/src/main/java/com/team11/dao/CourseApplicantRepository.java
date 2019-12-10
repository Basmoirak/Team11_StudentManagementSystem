package com.team11.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.CourseApplicant;

@Repository
public interface CourseApplicantRepository extends JpaRepository<CourseApplicant, Integer>{
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(String studentId);
	
	@Modifying
	@Transactional
	@Query("select ca from CourseApplicant ca JOIN ca.course c where ca.studentID = :studentId and ca.status = :status order by c.startDate asc")
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentIDAndStatus(String studentId, int status);
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStatus(int status);
	
	@Modifying
	@Transactional
	@Query("update CourseApplicant ca set ca.status =1 where ca.id =:id")
	public void approvePendingApplicant(int id);
	
	//getting active courses
	//queried by login-ed student id, if today falls between startdate and enddate
	@Modifying
	@Transactional
	@Query("select ca from CourseApplicant ca JOIN ca.course c where ca.studentID = :studentId and ca.status = 1 and CURRENT_DATE >= c.startDate and CURRENT_DATE <= c.endDate order by c.startDate asc")
	public List<CourseApplicant> getActiveCourses(String studentId);
	
}
