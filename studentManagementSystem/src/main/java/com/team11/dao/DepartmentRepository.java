package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team11.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
}
