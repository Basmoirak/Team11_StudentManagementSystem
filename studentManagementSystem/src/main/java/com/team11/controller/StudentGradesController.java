package com.team11.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team11.entity.StudentGrades;
import com.team11.entity.tblGPA;
import com.team11.service.CourseService;
import com.team11.service.CourseServiceImpl;
import com.team11.service.StudentGradesService;
import com.team11.service.StudentGradesServiceImpl;

@RequestMapping("/studentGrade")
@Controller
public class StudentGradesController {
	
//injecting studentGradesService
	private StudentGradesService studentGradesService;
	
	@Autowired
	public void setStudentGradeService(StudentGradesServiceImpl studentGradesService) {
		this.studentGradesService = studentGradesService;
	}
	
	private CourseService courseService;
	@Autowired
	public void setCourseService(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}
	
//	*****  for student role  *****
	@GetMapping("/student/grades")
	public String getGrades(Model model, HttpServletRequest request) {
		model.addAttribute("grades", studentGradesService.findStudentGradesByStudentID((String) request.getSession().getAttribute("userID")));
		return "student/grades";
	}
	
	@GetMapping("/student/GPA")
	public String getGpa(Model model, HttpServletRequest request, tblGPA tblgpa) {		
		
		try {
			double totalUnits = studentGradesService.getTotalUnits((String) request.getSession().getAttribute("userID"));
		
			double totalGrades = 0;
			
			double gpa = 0;
			

//			System.out.println(totalUnits);
			for (StudentGrades g : studentGradesService.findStudentGradesByStudentID((String) request.getSession().getAttribute("userID"))) {
											
				totalGrades += studentGradesService.convertGrade(g.getGrade()) * g.getCourse().getCourseUnit();
				
				gpa = totalGrades / totalUnits;
			}
			 model.addAttribute("gpa", gpa);
		}
		catch (Exception e) {
			System.err.println(e);
		}


		return "student/gpa";
	}
	
	// *** FACULTY ROLE ***
	
	// Can view can update student grades
	@GetMapping("/faculty/courses/viewUpdate/{id}")
	public String myAllCoursesViewUpdate(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("studentgrades", studentGradesService
				.getStudentGradesByCourseID(id));
		
		model.addAttribute("course", courseService.getCourse(id));
		return "faculty/my-student-list-update";
	}
	
	// Can only view student grades
	@GetMapping("/faculty/courses/view/{id}")
	public String myAllCoursesView(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("studentgrades", studentGradesService
				.getStudentGradesByCourseID(id));
		model.addAttribute("course", courseService.getCourse(id));
		return "faculty/my-student-list-view";
	}
	
	@GetMapping("/faculty/courses/viewUpdate/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		StudentGrades grade = studentGradesService
				.getStudentGradesById(id);
		
		if(grade == null) {
			return "redirect:/faculty/faculty/courses/my";
		}
		
		model.addAttribute("grade", grade);
			
		return "faculty/grades-form";
	}
	
	@PostMapping("/faculty/updateGrade")
	public String save(@Valid StudentGrades grade, BindingResult result, Model model) {
		if(result.hasErrors()) { 
			model.addAttribute("grade", studentGradesService.getStudentGradesById(grade.getId()));
			return "faculty/grades-form";
		}
		
		// Save or Update faculty
		studentGradesService.updateGrade(grade.getGrade(), grade.getId());
		return "redirect:/faculty/faculty/courses/my";
	}
}
