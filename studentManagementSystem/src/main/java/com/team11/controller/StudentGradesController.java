package com.team11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team11.entity.StudentGrades;
import com.team11.entity.tblGPA;
import com.team11.service.StudentGradesService;

@RequestMapping("/studentGrade")
@Controller
public class StudentGradesController {
	
//injecting studentGradesService
	private StudentGradesService studentGradesService;
	
	@Autowired
	public void setStudentGradeService(StudentGradesService studentGradesService) {
		this.studentGradesService = studentGradesService;
	}
	
//	*****  for student role  *****
	@GetMapping("/student/grades")
	public String getGrades(Model model, HttpServletRequest request) {
		model.addAttribute("grades", studentGradesService.findStudentGradesByStudentID((String) request.getSession().getAttribute("userID")));
		return "student/grades";
	}
	
	@GetMapping("/student/GPA")
	public String getGpa(Model model, HttpServletRequest request, tblGPA tblgpa) {		
		
		
		double totalUnits = studentGradesService.getTotalUnits((String) request.getSession().getAttribute("userID"));
		
		double totalGrades = 0;
		
		double gpa = 0;
		
		for (StudentGrades g : studentGradesService.findStudentGradesByStudentID((String) request.getSession().getAttribute("userID"))) {
		
			totalGrades += studentGradesService.convertGrade(g.getGrade()) * g.getCourse().getCourseUnit();
		
		}
		
		 gpa = totalGrades / totalUnits;
		
		 model.addAttribute("gpa", gpa);

		return "student/gpa";
	}
	
	
	
}
