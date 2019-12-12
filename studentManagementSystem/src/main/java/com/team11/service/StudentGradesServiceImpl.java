package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
			studentGrade.setSemester(student.getSemester().getSemester());
			studentGrade.setLevel(student.getLevel().getId());
			studentGrade.setGrade(null);
			
			studentGradesRepository.save(studentGrade);
		}
		
		
	}

	@Override
	@Transactional
	public ArrayList<StudentGrades> findStudentGradesByStudentID(String studentId) {
		// TODO Auto-generated method stub
		return studentGradesRepository.findStudentGradesByStudentID(studentId);
	}

	@Override
	@Transactional
	public float getTotalUnits(String studentId) {
		// TODO Auto-generated method stub
		return studentGradesRepository.getTotalUnits(studentId);
	}

	@Override
	//convert from letter grade to number grade
	public double convertGrade(String letter) {
		double numGrade = 0;
		if (letter.equalsIgnoreCase("A+") || letter.equalsIgnoreCase("A")) {
			numGrade = 5;
		}
		else if(letter.equalsIgnoreCase("A-")) {
			numGrade = 4.5;
		}
		else if(letter.equalsIgnoreCase("B+")) {
			numGrade = 4;
		}
		else if(letter.equalsIgnoreCase("B")) {
			numGrade = 3.5;
		}
		else if(letter.equalsIgnoreCase("B-")) {
			numGrade = 3;
		}
		else if(letter.equalsIgnoreCase("C+")) {
			numGrade = 2.5;
		}
		else if(letter.equalsIgnoreCase("C")) {
			numGrade = 2;
		}
		else if(letter.equalsIgnoreCase("D+")) {
			numGrade = 1.5;
		}
		else if(letter.equalsIgnoreCase("D")) {
			numGrade = 1;
		}
		else if(letter.equalsIgnoreCase("F")) {
			numGrade = 0;
		}
		return numGrade;
	}

	@Override
	@Transactional
	public List<StudentGrades> getStudentGradesByCourseID(int courseId) {
		return studentGradesRepository.getStudentGradesByCourseID(courseId);
	}

	@Override
	@Transactional
	public StudentGrades getStudentGradesById(int id) {
		return studentGradesRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	@Modifying
	public void updateGrade(String grade, int id) {
		studentGradesRepository.updateGrade(grade, id);
	}
}
