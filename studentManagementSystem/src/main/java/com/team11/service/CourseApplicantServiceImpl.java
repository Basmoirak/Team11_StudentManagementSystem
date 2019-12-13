package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return courseApplicantRepository.findAll();
	}

	@Override
	@Transactional
	public void saveCourseApplicant(CourseApplicant courseApplicant) {
		courseApplicantRepository.save(courseApplicant);
	}

	@Override
	@Transactional
	public CourseApplicant getCourseApplicant(int id) {
		return courseApplicantRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteCourseApplicant(int id) {
		courseApplicantRepository.deleteById(id);
	}

	@Override
	@Transactional
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentID(String studentId) {
		return courseApplicantRepository.findCourseApplicantsByStudentID(studentId);
	}

	@Override
	@Transactional
	public ArrayList<CourseApplicant> findCourseApplicantsByStudentIDAndStatus(String studentId, int status) {
		return courseApplicantRepository.findCourseApplicantsByStudentIDAndStatus(studentId, status);
	}

	@Override
	@Transactional
	public ArrayList<CourseApplicant> findCourseApplicantsByStatus(int status) {
		return courseApplicantRepository.findCourseApplicantsByStatus(status);
	}

	@Override
	@Modifying
	@Transactional
	public void approvePendingApplicant(int id) {
		courseApplicantRepository.approvePendingApplicant(id);
	}
	
	@Override
	@Modifying
	@Transactional
	public List<CourseApplicant> getActiveCourses(String studentId){
		return courseApplicantRepository.getActiveCourses(studentId);
	}

	@Override
	@Modifying
	@Transactional
	public CourseApplicant findByIdAndStatus(int id) {
		return courseApplicantRepository.findByIdAndStatus(id);
	}

	@Override
	@Transactional
	public Page<CourseApplicant> searchAndPaginate(String search, Pageable pageable) {
		return courseApplicantRepository.searchAndPaginate(search, pageable);
	}


}
