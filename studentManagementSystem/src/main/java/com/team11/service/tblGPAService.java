package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import com.team11.dao.tblGPARepository;
import com.team11.entity.CourseApplicant;
import com.team11.entity.Student;
import com.team11.entity.tblGPA;

public interface tblGPAService {
	public List<tblGPA> getGPAs();
	
	public void saveGPA(tblGPA tblGPA);
	
	public void updateGPA(String studentId, double gpaCalculated);
	
	public void createNewGPA(String studentId);

	public tblGPA findtblGPAsByStudentId(String studentId);
}
