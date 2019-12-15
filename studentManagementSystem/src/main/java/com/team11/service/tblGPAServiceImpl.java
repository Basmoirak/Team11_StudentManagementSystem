package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.team11.dao.StudentRepository;
import com.team11.dao.tblGPARepository;
import com.team11.entity.CourseApplicant;
import com.team11.entity.Student;
import com.team11.entity.tblGPA;

@Service
public class tblGPAServiceImpl implements tblGPAService{
	private tblGPARepository tblGPARepository;
	
	@Autowired
	public void setTblGPARepository(tblGPARepository tblGPARepository) {
		this.tblGPARepository = tblGPARepository;
	}
	// Inject student repository
	private StudentRepository studentRepository;
	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	@Override
	public List<tblGPA> getGPAs() {
		// TODO Auto-generated method stub
		return tblGPARepository.findAll();
	}
	
	
	@Override
	@Transactional
	public void saveGPA(tblGPA tblGPA) {
		// TODO Auto-generated method stub
		tblGPARepository.save(tblGPA);
	}

	@Override
	@Modifying
	@Transactional
	public void updateGPA(String studentId, double gpaCalculated) {
		// TODO Auto-generated method stub
		System.out.println(gpaCalculated);
		tblGPARepository.updateGPA(studentId, gpaCalculated);;
	}

	
	@Override
	@Transactional
	public void createNewGPA(String studentId) {
		
		Student student = studentRepository.getOne(studentId);
		
		if(tblGPARepository.findtblGPAsByStudentId(studentId) == null) {
			tblGPA gpa = new tblGPA();
			gpa.setGpaCalculated(0);
			gpa.setStudent(student);
			tblGPARepository.save(gpa);
		} else {
			System.err.println("Already exists");
		}
		
		
		
	}



	@Override
	@Transactional
	public tblGPA findtblGPAsByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return tblGPARepository.findtblGPAsByStudentId(studentId);
	}
	
	
}
