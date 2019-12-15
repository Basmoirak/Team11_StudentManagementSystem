package com.team11.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team11.entity.Course;
import com.team11.entity.StudentGrades;
import com.team11.service.CourseService;
import com.team11.service.CourseServiceImpl;
import com.team11.service.StudentGradesService;
import com.team11.service.StudentGradesServiceImpl;

@RequestMapping("/report")
@Controller
public class ReportController {
	
	private CourseService courseService;
	
	@Autowired
	public void setCourseService(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}
	
	private StudentGradesService studentGradesService;
	
	@Autowired
	public void setStudentGradesService(StudentGradesServiceImpl studentGradesService) {
		this.studentGradesService = studentGradesService;
	}
	
	@GetMapping("/faculty/GpaByStudent")
	public String gpaByStudent(@RequestParam(value = "courseId", required = false) Integer courseId , Model model, HttpServletRequest request, Course course) {
		
		//getting courses by facultyID
		if(courseId != null ) {
			
			
			ArrayList<StudentGrades> sgs = new ArrayList<StudentGrades>();
			
			sgs.addAll(studentGradesService.getStudentGradesByCourseID(courseId));
			
			model.addAttribute("sgs", sgs);
		}
		
		model.addAttribute("courses", courseService.getCourseByFacultyID((String) request.getSession().getAttribute("userID")));
		
		return "faculty/gpa-by-student";
	}
}
