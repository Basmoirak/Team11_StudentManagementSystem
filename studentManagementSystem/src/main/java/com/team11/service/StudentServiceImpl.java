package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team11.dao.StudentRepository;
import com.team11.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	//Inject StudentRepository
	private StudentRepository studentRepository;
	
	//Setter Injection
	@Autowired
	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
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
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	//Retrieve one student
	@Override
	@Transactional
	public Student getStudent(int theId) {
		return studentRepository.getOne(theId);
	}
	
	//Delete student
	@Override
	@Transactional
	public void deleteStudent(int theId) {
		studentRepository.deleteById(theId);
	}

}
