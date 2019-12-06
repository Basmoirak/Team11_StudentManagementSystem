package com.team11.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team11.entity.Student;
import com.team11.service.StudentService;
import com.team11.service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	// Inject student service
	private StudentService studentService;
	
	//Setter Injection
	@Autowired
	public void setStudentService(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/list")
	public String listAll(Model model) {
//		ArrayList<Student> studentList = new ArrayList<Student>();
//		studentList.addAll(studentService.getStudents());
		model.addAttribute("students", studentService.getStudents());
		return "student-list";
	}
	
	@GetMapping("/showForm")
	public String showForm(Student student, Model model) {
		
		model.addAttribute("levels", studentService.getLevels());
		model.addAttribute("semesters", studentService.getSemesters());
		model.addAttribute("statuses", studentService.getStatuses());
		
		return "student-form";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int theId, Model theModel) {
		// get the student from our service
		Student theStudent = studentService.getStudent(theId);
		
		if(theStudent == null) {
			return "redirect:/student/list";
		}
		
		theModel.addAttribute("student", theStudent);
		return "student-form";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int theId) {
		studentService.deleteStudent(theId);
		return "redirect:/student/list";
	}
	
	@PostMapping("/save")
	public String add(@Valid Student student, BindingResult result, Model model) {
		
		//Don't allow user to add student if there are any form validation errors
		if(result.hasErrors()) {
			model.addAttribute("levels", studentService.getLevels());
			model.addAttribute("semesters", studentService.getSemesters());
			model.addAttribute("statuses", studentService.getStatuses());
			return "student-form";
		}
		
		// save the student using our service
		studentService.saveStudent(student);
		
		return "redirect:/student/list";
	}	
}

