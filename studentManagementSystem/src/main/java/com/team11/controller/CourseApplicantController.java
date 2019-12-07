package com.team11.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.dao.CourseApplicantRepository;
import com.team11.entity.CourseApplicant;
import com.team11.service.CourseApplicantService;
import com.team11.service.CourseService;

@Controller
public class CourseApplicantController {
	
	//injecting courseService
	private CourseService courseService;
	
	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	//injecting courseApplicantService
	private CourseApplicantService courseApplicantService;
	
	@Autowired
	public void setCourseApplicantService(CourseApplicantService courseApplicantService) {
		this.courseApplicantService = courseApplicantService;
	}
	
	@GetMapping("/courses")
	public String availableCourses(Model model) {
		//returning all courses
		model.addAttribute("courses", courseService.getCourses());
		
		//returning applied courses
		model.addAttribute("appliedCourses", courseApplicantService.findCourseApplicantsByStudentID(1));
		
		return "available-courses";
	}
	
	@GetMapping("/courses/apply/{id}")
	public String applyCourse(@PathVariable("id") int id,CourseApplicant courseApplicant, RedirectAttributes redirectAttributes) {
		
		String error = "You have already booked the course.";
		//default is true
		boolean flag = true;
		
		//checking student has already booked a course or not
		//assume that default student id is 1 for testing purposes
		for(CourseApplicant ca : courseApplicantService.findCourseApplicantsByStudentID(1)) {
			
			if(ca.getCourseID() == id) {
				flag = false;
			}
			
		}
		
		//if false send error to front end
		if(flag == false) {
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/courses";
		} else {
			//setting student_id
			courseApplicant.setStudentID(1);
			courseApplicant.setCourseID(id);
			courseApplicant.setStatus(0);
			
			Date submittedDate = new Date();
			courseApplicant.setSubmittedDate(submittedDate);
			
			//saving to database using our service
			courseApplicantService.saveCourseApplicant(courseApplicant);
			return "redirect:/applications";
		}
			
		
		
	}
	
	@GetMapping("/applications")
	public String applications(Model model) {
		model.addAttribute("appliedCourses", courseApplicantService.findCourseApplicantsByStudentID(1));
		return "applications";
	}
	
	@GetMapping("/applications/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		courseApplicantService.deleteCourseApplicant(id);
		return "redirect:/applications";
	}
}
