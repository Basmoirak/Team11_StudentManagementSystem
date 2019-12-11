package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team11.dao.StudentGradesRepository;
import com.team11.dao.StudentRepository;
import com.team11.entity.CourseApplicant;
import com.team11.entity.Student;
import com.team11.entity.StudentGrades;

@Service
public class StudentGradesServiceImpl implements StudentGradesService {
	
	// Inject studentGrades repository
	private StudentGradesRepository studentGradesRepository;
	@Autowired
	public void setCourseRepository(StudentGradesRepository studentGradesRepository) {
		this.studentGradesRepository = studentGradesRepository;
	}
	
	// Inject student repository
	private StudentRepository studentRepository;
	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Override
	@Transactional
	public List<StudentGrades> getStudentGrades() {
		return studentGradesRepository.findAll();
	}

	@Override
	@Transactional
	public void saveStudentGrades(StudentGrades studentGrade) {
		studentGradesRepository.save(studentGrade);
	}

	@Override
	@Transactional
	public StudentGrades getStudentGrades(int id) {
		return studentGradesRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteStudentGrades(int id) {
		studentGradesRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void createNewStudentGrades(CourseApplicant ca) {
		Student student = studentRepository.findById(ca.getStudentID()).orElse(null);
		
		if(student != null) {
			StudentGrades studentGrade = new StudentGrades();		
			
			studentGrade.setCourseID(ca.getCourseID());
			studentGrade.setStudentID(ca.getStudentID());
			studentGrade.setSemester(student.getSemesterID());
			studentGrade.setLevel(student.getLevelID());
			studentGrade.setGrade(null);
			
			studentGradesRepository.save(studentGrade);
		}
		
		
	}
}
