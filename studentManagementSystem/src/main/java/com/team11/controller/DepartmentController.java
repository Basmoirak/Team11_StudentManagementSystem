package com.team11.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	
	//show paginated list of departments
	@GetMapping("/list")
	public String listAll(Model model, HttpServletRequest request) {

		int page = 0;
		int size = 5; 
		
		if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1; }
		
		if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
			size=Integer.parseInt(request.getParameter("size")); }
		
		model.addAttribute("departments", departmentService.getPaginated(PageRequest.of(page, size)));
		
		return "department-list";
	}
	
	//Show Department Form
	@GetMapping("/showForm")
	public String showForm(Department department) {
		return "department-form";
	}
	
//	@GetMapping("/add")
//	public String showAddForm(Model model) {
//		Department department = new Department();
//		model.addAttribute("department", department);
//		return "department-form";
//		
//	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int theId, Model theModel) {
		//get student by id
		Department department = departmentService.getDepartment(theId);
		if(department == null) {
			return "redirect:/department/list";
		}
		
		theModel.addAttribute(department);
		return "department-form";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int theId) {
		departmentService.deleteDepartment(theId);
		return "redirect:/department/list";
	}
	
	@PostMapping("/save")
	public String add(@Valid Department department, BindingResult result) {
		
		//Don't allow user to add student if there are any form validation errors
		if(result.hasErrors()) {
			return "department-form";
		}
		
		// save the student using our service
		departmentService.saveDepartment(department);
		
		return "redirect:/department/list";
	}	
}
