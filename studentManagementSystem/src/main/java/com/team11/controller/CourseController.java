package com.team11.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team11.entity.Course;
import com.team11.entity.Department;
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
		ArrayList<Course> cs = new ArrayList<Course>();
		cs.addAll(courseService.getCourses());
		model.addAttribute("courses", cs);
		return "course-list";
	}
	
	//add form
	@GetMapping("/showForm")
	public String showForm(Course course, Model model) {
		ArrayList<Department> ds = new ArrayList<Department>();
		ds.addAll(departmentService.getDepartments());
		model.addAttribute("departments", ds);
		return "course-form";
	}
	
	//saving
	@PostMapping("/save")
	public String save(@Valid Course course, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "course-form";
		
		System.out.println(course);
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
		ArrayList<Department> ds = new ArrayList<Department>();
		ds.addAll(departmentService.getDepartments());
		model.addAttribute("departments", ds);
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
