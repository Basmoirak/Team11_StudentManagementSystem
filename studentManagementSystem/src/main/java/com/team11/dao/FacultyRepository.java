package com.team11.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team11.entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String>{
	
	@Query("SELECT f FROM Faculty f WHERE f.departmentID = :departmentID")
	List<Faculty> getFacultiesByDepartmentId(
			@Param("departmentID") Integer departmentID);
}
