package com.team11.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.CourseApplicant;

@Repository
public interface CourseApplicantRepository extends JpaRepository<CourseApplicant, Integer>{
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(String studentId);
	
	@Query("select ca from CourseApplicant ca JOIN ca.course c where ca.studentID = :studentId and ca.status = :status order by c.startDate asc")
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentIDAndStatus(String studentId, int status);
	
	public ArrayList<CourseApplicant> findCourseApplicantsByStatus(int status);
	
	@Modifying
	@Query("update CourseApplicant ca set ca.status =1 where ca.id =:id")
	public void approvePendingApplicant(int id);
	
	@Query("select ca from CourseApplicant ca WHERE ca.status =1 and ca.id =:id")
	public CourseApplicant findByIdAndStatus(int id);
	
	//getting active courses
	//queried by login-ed student id, if today falls between startdate and enddate
	@Query("select ca from CourseApplicant ca JOIN ca.course c where ca.studentID = :studentId and ca.status = 1 and CURRENT_DATE >= c.startDate and CURRENT_DATE <= c.endDate order by c.startDate asc")
	public List<CourseApplicant> getActiveCourses(String studentId);
	
	@Query("select ca FROM CourseApplicant ca WHERE ca.status =0 "
			+ "and concat(ca.student.firstName, ' ', ca.student.lastName) like %?1%")
//			+ "or ca.course.courseName like %?1% or " 
//			+ "ca.course.department.name like %?1% order by ca.Id")
	public Page<CourseApplicant> searchAndPaginate(String search, Pageable pageable);
}
