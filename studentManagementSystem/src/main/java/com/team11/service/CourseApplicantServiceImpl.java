package com.team11.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.team11.dao.CourseApplicantRepository;
import com.team11.entity.CourseApplicant;

@Service
public class CourseApplicantServiceImpl implements CourseApplicantService{
	
	private CourseApplicantRepository courseApplicantRepository;
	
	@Autowired
	public void setCourseApplicantService(CourseApplicantRepository courseApplicantRepository) {
		this.courseApplicantRepository = courseApplicantRepository;
	}
	
	@Override
	@Transactional
	public List<CourseApplicant> getCourseApplicants() {
		// TODO Auto-generated method stub
		return courseApplicantRepository.findAll();
	}

	@Override
	@Transactional
	public void saveCourseApplicant(CourseApplicant courseApplicant) {
		// TODO Auto-generated method stub
		courseApplicantRepository.save(courseApplicant);
	}

	@Override
	@Transactional
	public CourseApplicant getCourseApplicant(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void deleteCourseApplicant(int id) {
		// TODO Auto-generated method stub
		courseApplicantRepository.deleteById(id);
	}

	@Override
	@Transactional
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(String studentId) {
		// TODO Auto-generated method stub
		return courseApplicantRepository.findCourseApplicantsByStudentID(studentId);
	}


	@Override
	@Transactional
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentIDAndStatus(String studentId, int status) {
		// TODO Auto-generated method stub
		return courseApplicantRepository.findCourseApplicantsByStudentIDAndStatus(studentId, status);
	}


	@Override
	@Transactional
	public ArrayList<CourseApplicant> findCourseApplicantsByStatus(int status) {
		// TODO Auto-generated method stub
		return courseApplicantRepository.findCourseApplicantsByStatus(status);
	}


	@Override
	@Modifying
	@Transactional
	public void approvePendingApplicant(int id) {
		// TODO Auto-generated method stub
		courseApplicantRepository.approvePendingApplicant(id);
		
	}
	
	
	@Override
	@Modifying
	@Transactional
	public List<CourseApplicant> getActiveCourses(String studentId){
		return courseApplicantRepository.getActiveCourses(studentId);
	}
	



	


}
