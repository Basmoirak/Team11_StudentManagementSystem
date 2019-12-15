package com.team11.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.Student;
import com.team11.entity.tblGPA;

@Repository
public interface tblGPARepository extends JpaRepository<tblGPA, Integer>{

	//update gpa
	@Query("update tblGPA g set g.gpaCalculated = :gpaCalculated  where g.student.Id = :studentId")
	@Modifying
	public void updateGPA(String studentId, double gpaCalculated);

	@Query("select g from tblGPA g where g.id = :studentId")
	public tblGPA findtblGPAsByStudentId(String studentId);
}
