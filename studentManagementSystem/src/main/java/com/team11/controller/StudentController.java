package com.team11.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// show list of students
	@GetMapping("/list")
	public String listAll(Model model) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.addAll(studentService.getStudents());
		model.addAttribute("students", studentList);
		return "list-students";
	}
	
	@GetMapping("/showFormForAdd")
	public String showAddForm(Model model) {
		
		// Create model attribute to bind form data
		Student student = new Student();
		
		model.addAttribute("student", student);
		
		return "student-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the student from our service
		Student theStudent = studentService.getStudent(theId);
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", theStudent);
		
		//send over our form
		return "student-form";
	}
	
	@PostMapping("/save")
	public String insertStudent(@ModelAttribute Student student) {
	//	System.out.print(student);
		// save the student using our service
		studentService.saveStudent(student);
		
		return "redirect:/student/list";
	}	
	
}

