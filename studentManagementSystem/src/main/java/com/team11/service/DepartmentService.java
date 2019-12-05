package com.team11.service;

import java.util.List;

import com.team11.entity.Department;

public interface DepartmentService {
	public List<Department> getDepartments();
	
	public void saveDepartment(Department department);
	
	public Department getDepartment(int theId);
	
	public void deleteDepartment(int theId);
	
	
}