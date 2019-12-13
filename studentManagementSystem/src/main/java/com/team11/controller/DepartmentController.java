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

import com.team11.entity.Department;
import com.team11.service.DepartmentService;
import com.team11.service.DepartmentServiceImpl;


@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	//inject department service
	private DepartmentService departmentService;
	
	//setter injection
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}
	
	// *** ADMIN ROLE ***
	
	//search and show paginated list of departments
	@GetMapping("/admin/list")
	public String search(Model model) {
		
		model.addAttribute("departments", departmentService.getDepartments());
		
		return "admin/department-list";
	}
	
	//Show Department Form
	@GetMapping("/admin/showForm")
	public String showForm(Department department) {
		return "admin/department-form";
	}
	
	@GetMapping("/admin/update/{id}")
	public String update(@PathVariable("id") int theId, Model theModel) {
		//get student by id
		Department department = departmentService.getDepartment(theId);
		if(department == null) {
			return "redirect:/department/admin/list";
		}
		
		theModel.addAttribute(department);
		return "admin/department-form";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") int theId) {
		departmentService.deleteDepartment(theId);
		return "redirect:/department/admin/list";
	}
	
	@PostMapping("/admin/save")
	public String add(@Valid Department department, BindingResult result) {
		
		//Don't allow user to add student if there are any form validation errors
		if(result.hasErrors()) {
			return "admin/department-form";
		}
		
		// save the student using our service
		departmentService.saveDepartment(department);
		
		return "redirect:/department/admin/list";
	}	
}
