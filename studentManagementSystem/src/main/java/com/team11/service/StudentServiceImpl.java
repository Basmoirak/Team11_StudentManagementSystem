package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team11.dao.LevelRepository;
import com.team11.dao.SemesterRepository;
import com.team11.dao.StudentRepository;
import com.team11.entity.Level;
import com.team11.entity.Semester;
import com.team11.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	//Inject Repositories
	private StudentRepository studentRepository;
	private LevelRepository levelRepository;
	private SemesterRepository semesterRepository;
	
	//Setter Injection
	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	@Autowired
	public void setLevelRepository(LevelRepository levelRepository) {
		this.levelRepository = levelRepository;
	}
	@Autowired
	public void setSemesterRepository(SemesterRepository semesterRepository) {
		this.semesterRepository = semesterRepository;
	}
	
	//Get full list of students
	@Override
	@Transactional
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	//Save or Update student
	@Override
	@Transactional
	public Student saveStudent(Student student) {
		studentRepository.save(student);
		return student;
	}

	//Retrieve one student
	@Override
	@Transactional
	public Student getStudent(String theId) {
		return studentRepository.getOne(theId);
	}
	
	//Delete student
	@Override
	@Transactional
	public void deleteStudent(String theId) {
		studentRepository.deleteById(theId);
	}
	
	//Retrieve all student levels
	@Override
	@Transactional
	public List<Level> getLevels() {
		return levelRepository.findAll();
	}
	
	//Retrieve all student semesters
	@Override
	@Transactional
	public List<Semester> getSemesters() {
		return semesterRepository.findAll();
	}
	
	
	@Override
	@Transactional
	public Page<Student> searchAndPaginate(String search, Pageable pageable) {
		return studentRepository.searchAndPaginate(search, pageable);
	}

}
