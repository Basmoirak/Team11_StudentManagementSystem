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

import com.team11.entity.Course;
import com.team11.service.CourseService;
import com.team11.service.DepartmentService;

@RequestMapping("/course")
@Controller
public class CourseController {
	
	private CourseService courseService;
	private DepartmentService departmentService;
	
	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	@Autowired
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	//showing list of courses
	@GetMapping("/list")
	public String list(Model model){
		model.addAttribute("courses", courseService.getCourses());
		return "course-list";
	}
	
	//add form
	@GetMapping("/showForm")
	public String showForm(Course course, Model model) {
		model.addAttribute("departments", departmentService.getDepartments());
		return "course-form";
	}
	
	//saving
	@PostMapping("/save")
	public String save(@Valid Course course, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "course-form";
		
		courseService.saveCourse(course);
		return "redirect:/course/list";
	}	
	
	//show edit form
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		//get course by id
		Course course = courseService.getCourse(id);
		if(course == null) {
			return "redirect:/course/list";
		}
		
		//retrieving department list
		model.addAttribute("departments", departmentService.getDepartments());
		model.addAttribute("course", course);
		return "course-form";
	}
	
	//deleting
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int theId) {
		courseService.deleteCourse(theId);
		return "redirect:/course/list";
	}

}
