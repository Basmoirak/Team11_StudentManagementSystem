package com.team11.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
//	getting available courses by filtering with start date
	
	@Modifying
	@Query("SELECT c FROM Course c  WHERE startDate > curdate() - 2")
	public List<Course> getAvailableCourses();
	
	
	@Query("SELECT c FROM Course c WHERE c.facultyID = :id")
	public List<Course> getCoursesByFacultyID(String id);
	
	//getting active courses
	@Query("SELECT c FROM Course c WHERE c.facultyID =:id and "
			+ "CURRENT_DATE >= c.startDate and CURRENT_DATE <= c.endDate order by c.startDate asc")
	public List<Course> getActiveCourses(String id);
	
	@Query("select c FROM Course c WHERE "
			+ "c.courseName like %?1% or c.department.name like %?1% or c.courseName like %?1% or "
			+ "concat(c.faculty.firstName, ' ', c.faculty.lastName) like %?1% order by c.courseId")
	public Page<Course> searchAndPaginate(String search, Pageable pageable);
}
