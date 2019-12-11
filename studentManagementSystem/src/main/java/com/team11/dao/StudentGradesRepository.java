package com.team11.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.StudentGrades;

@Repository
public interface StudentGradesRepository extends JpaRepository<StudentGrades, Integer>{
	
	@Query("select sg from StudentGrades sg JOIN sg.course c where c.facultyID = :id and sg.status = 1 and CURRENT_DATE >= c.startDate and CURRENT_DATE <= c.endDate order by c.startDate asc")
	public List<StudentGrades> getActiveCourses(String id);
}
