package com.team11.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.team11.entity.Faculty;

public interface FacultyService {
public List<Faculty> getFaculties();
	
	public Faculty saveFaculty(Faculty faculty);
	
	public Faculty getFaculty(String theId);
	
	public void deleteFaculty(String theId);
	
	public List<Faculty> getFacultiesByDepartmentId(int departmentID);
	
	public Page<Faculty> searchAndPaginate(String search, Pageable pageable);
}
