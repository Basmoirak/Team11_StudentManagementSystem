package com.team11.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.entity.CourseApplicant;
import com.team11.entity.StudentGrades;
import com.team11.service.CourseApplicantService;
import com.team11.service.CourseService;
import com.team11.service.StudentGradesService;
import com.team11.service.StudentService;

@Controller
@RequestMapping("/courseApp")
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
	
	//injecting studentGradesService
	private StudentGradesService studentGradesService;
	
	@Autowired
	public void setStudentGradeService(StudentGradesService studentGradesService) {
		this.studentGradesService = studentGradesService;
	}
	
	// *** STUDENT ROLE ***
	
//	available courses
	
	@GetMapping("/student/courses")
	public String availableCourses(Model model, HttpServletRequest request) {
		
		//returning all courses
		model.addAttribute("courses", courseService.getAvailableCourses());
		
		//returning applied courses
		model.addAttribute("appliedCourses", courseApplicantService.findCourseApplicantsByStudentID(
				(String) request.getSession().getAttribute("userID")));
		
		return "student/available-courses";
	}
	
	@GetMapping("/student/courses/my")
	public String myCourses(Model model, HttpServletRequest request) {
		//get active courses
		model.addAttribute("courses", courseApplicantService.getActiveCourses((String) request.getSession().getAttribute("userID")));
		return "student/my-courses";
	}
	
	@GetMapping("/student/courses/my/all")
	public String myAllCourses(Model model, HttpServletRequest request) {
		model.addAttribute("courses", courseApplicantService.findCourseApplicantsByStudentIDAndStatus((String) request.getSession().getAttribute("userID"), 1));
		return "student/my-courses";
	}
	
	@GetMapping("/student/courses/apply/{id}")
	public String applyCourse(@PathVariable("id") int id, 
			HttpServletRequest request,
			CourseApplicant courseApplicant, 
			RedirectAttributes redirectAttributes) {
		
		String error = "You have already booked the course.";
		
		//checking student has already booked a course or not
		//assume that default student id is 1 for testing purposes
		boolean flag = courseApplicantService.
				findCourseApplicantsByStudentID((String) request.getSession().getAttribute("userID"))
					.stream().anyMatch(ca -> ca.getCourseID() == id);
		
		//if false send error to front end
		if(flag) {
			redirectAttributes.addFlashAttribute("error", error);
			return "redirect:/courseApp/student/courses";
		} else {
			//setting student_id
			courseApplicant.setStudentID((String) request.getSession().getAttribute("userID"));
			courseApplicant.setCourseID(id);
			courseApplicant.setStatus(0);
			
			Date submittedDate = new Date();
			courseApplicant.setSubmittedDate(submittedDate);
			
			//saving to database using our service
			courseApplicantService.saveCourseApplicant(courseApplicant);
			return "redirect:/courseApp/student/applications";
		}
	}
	
	@GetMapping("/student/applications")
	public String applications(Model model, HttpServletRequest request) {
		model.addAttribute("appliedCourses", courseApplicantService
				.findCourseApplicantsByStudentID((String) request.getSession().getAttribute("userID")));
		return "student/applications";
	}
	
	@GetMapping("/student/applications/approved")
	public String approvedApplications(Model model, HttpServletRequest request) {
		model.addAttribute("appliedCourses", courseApplicantService
				.findCourseApplicantsByStudentIDAndStatus((String) request.getSession().getAttribute("userID"), 1));
		return "/student/applications";
	}
	
	@GetMapping("/student/applications/pending")
	public String pendingApplications(Model model, HttpServletRequest request) {
		model.addAttribute("appliedCourses", courseApplicantService
				.findCourseApplicantsByStudentIDAndStatus((String) request.getSession().getAttribute("userID"), 0));
		return "student/applications";
	}
	
	@GetMapping("/student/applications/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		courseApplicantService.deleteCourseApplicant(id);
		return "redirect:/courseApp/student/applications";
	}
	
	// *** ADMIN ROLE ***
	@GetMapping("/admin/applications/pending")
	public String adminPendingApplications(Model model) {
		model.addAttribute("pendingCourses", courseApplicantService.findCourseApplicantsByStatus(0));
		return "admin/admin-pending";
	}
	
	@GetMapping("/admin/applications/pending/approve/{id}")
	public String approve(@PathVariable("id") int id) {
		courseApplicantService.approvePendingApplicant(id);
		
		// Create new student grade
		studentGradesService.createNewStudentGrades(courseApplicantService.findByIdAndStatus(id));
		return "redirect:/courseApp/admin/applications/pending";
	}
	
}
