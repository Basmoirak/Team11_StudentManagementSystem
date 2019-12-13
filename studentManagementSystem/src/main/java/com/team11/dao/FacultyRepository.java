package com.team11.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Query("select f FROM Faculty f WHERE "
			+ "concat(f.firstName, ' ', f.lastName) like %?1% or f.department.name like %?1% order by f.faculty")
	public Page<Faculty> searchAndPaginate(String search, Pageable pageable);
}
