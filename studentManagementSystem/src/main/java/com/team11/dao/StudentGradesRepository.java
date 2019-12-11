package com.team11.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.StudentGrades;

@Repository
public interface StudentGradesRepository extends JpaRepository<StudentGrades, Integer>{
	
	public ArrayList<StudentGrades> findStudentGradesByStudentID(String studentId);

	@Query("select sum(c.courseUnit) from StudentGrades g join g.course c where g.studentID = :studentId")
	public float getTotalUnits(String studentId);	
	
	@Query("select sg from StudentGrades sg where sg.courseID = :courseId")
	public List<StudentGrades> getStudentGradesByCourseID(int courseId);
}