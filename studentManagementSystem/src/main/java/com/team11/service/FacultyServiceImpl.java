package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team11.dao.FacultyRepository;
import com.team11.entity.Faculty;

@Service
public class FacultyServiceImpl implements FacultyService{
	private FacultyRepository facultyRepository;
	
	@Autowired
	public void setFacultyRepository(FacultyRepository departmentRepository) {
		this.facultyRepository = departmentRepository;
	}
	@Override
	@Transactional
	public List<Faculty> getFaculties() {
		// TODO Auto-generated method stub
		return facultyRepository.findAll();
	}

	@Override
	@Transactional
	public void saveFaculty(Faculty faculty) {
		// TODO Auto-generated method stub
		facultyRepository.save(faculty);
	}

	@Override
	@Transactional
	public Faculty getFaculty(int theId) {
		// TODO Auto-generated method stub
		return facultyRepository.getOne(theId);
	}

	@Override
	@Transactional
	public void deleteFaculty(int theId) {
		// TODO Auto-generated method stub
		facultyRepository.deleteById(theId);
	}

}
