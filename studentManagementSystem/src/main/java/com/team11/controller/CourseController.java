package com.team11.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	// *** ADMIN ROLE ***
	
	//Search and paginate
	@GetMapping("/admin/list")
	public String search(
			@RequestParam Optional<String> search, 
			Model model, HttpServletRequest request){
		
		int page = 0;
		int size = 5; 
		
		if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1; }
		
		if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size=Integer.parseInt(request.getParameter("size")); }
		
		model.addAttribute("courses", courseService.searchAndPaginate(search.orElse("_"), PageRequest.of(page, size)));
		return "admin/course-list";
	}

	
	//add form
	@GetMapping("/admin/showForm")
	public String showForm(Course course, Model model) {
		model.addAttribute("departments", departmentService.getDepartments());
		return "admin/course-form";
	}
	
	//saving
	@PostMapping("/admin/save")
	public String save(@Valid Course course, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) { 
			model.addAttribute("departments", departmentService.getDepartments());
			return "admin/course-form";
		}
		courseService.saveCourse(course);
		return "redirect:/course/admin/list";
	}	
	
	//show edit form
	@GetMapping("/admin/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		//get course by id
		Course course = courseService.getCourse(id);
		if(course == null) {
			return "redirect:/course/admin/list";
		}
		
		//retrieving department list
		model.addAttribute("departments", departmentService.getDepartments());
		model.addAttribute("course", course);
		return "admin/course-form";
	}
	
	//deleting
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") int theId) {
		courseService.deleteCourse(theId);
		return "redirect:/course/admin/list";
	}
	
}
