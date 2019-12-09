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

import com.team11.entity.Faculty;
import com.team11.service.DepartmentService;
import com.team11.service.DepartmentServiceImpl;
import com.team11.service.FacultyService;
import com.team11.service.FacultyServiceImpl;
import com.team11.service.UserService;
import com.team11.service.UserServiceImpl;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	
	private DepartmentService departmentService;
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}
	
	private FacultyService facultyService;
	@Autowired
	public void SetFacultyService(FacultyServiceImpl facultyService) {
		this.facultyService = facultyService;
	}
	
	private UserService userService;
	@Autowired
	public void SetUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	// *** ADMIN ROLE ***
	@GetMapping("/admin/list")
	public String list(Model model) {
		// Bind list of faculties
		model.addAttribute("faculties", facultyService.getFaculties());
		return "admin/faculty-list";
	}
	
	@GetMapping("/admin/update/{id}")
	public String update(@PathVariable("id") String id, Model model) {
		Faculty faculty = facultyService.getFaculty(id);
		if(faculty == null)
			return "redirect:/faculty/admin/list";
		
		model.addAttribute("faculty", faculty);
			
		//retrieving department list
		model.addAttribute("departments", departmentService.getDepartments());
		return "admin/faculty-form";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		facultyService.deleteFaculty(id);
		userService.deactivateUser(id);
		
		return "redirect:/faculty/admin/list";
	}
	
	@PostMapping("/admin/save")
	public String save(@Valid Faculty faculty, BindingResult result, Model model) {
		if(result.hasErrors()) { 
			model.addAttribute("departments", departmentService.getDepartments());
			return "admin/faculty-form";
		}
		
		// Save or Update faculty
		facultyService.saveFaculty(faculty);
		return "redirect:/faculty/admin/list";
	}
}
