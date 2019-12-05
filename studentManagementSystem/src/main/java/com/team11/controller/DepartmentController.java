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
	//show list of departments
	@GetMapping("/list")
	public String listAll(Model model) {
		ArrayList<Department> departments = new ArrayList<Department>();
		departments.addAll(departmentService.getDepartments());
		model.addAttribute("departments", departments);
		return "list-departments";
	}
	//adding
	@GetMapping("/showFormForAdd")
	public String showAddForm(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "department-form";
	}
	//updating
	@GetMapping("/showFormForUpdate")
	public String showUpdateForm(@RequestParam int theId, Model theModel) {
		//get student by id
		Department department = departmentService.getDepartment(theId);
		theModel.addAttribute(department);
		return "department-form";
	}
	//saving
	@PostMapping("/save")
	public String save(@ModelAttribute Department department) {
	
		departmentService.saveDepartment(department);
		System.out.println(department);
		return "redirect:/department/list";
	}
}
