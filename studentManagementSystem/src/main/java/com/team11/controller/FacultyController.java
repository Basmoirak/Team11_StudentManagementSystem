package com.team11.controller;

import java.util.ArrayList;

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

import com.team11.entity.Department;
import com.team11.entity.Faculty;
import com.team11.service.DepartmentService;
import com.team11.service.DepartmentServiceImpl;
import com.team11.service.FacultyService;
import com.team11.service.FacultyServiceImpl;

@Controller
@RequestMapping("/faculty")
public class FacultyController {
	private DepartmentService departmentService;
	//setter injection
	@Autowired
	public void setDepartmentService(DepartmentServiceImpl departmentService) {
		this.departmentService = departmentService;
	}
	
	private FacultyService facultyService;
	//setter injection
	@Autowired
	public void SetFacultyService(FacultyServiceImpl facultyService) {
		this.facultyService = facultyService;
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		ArrayList<Faculty> fs = new ArrayList<Faculty>();
		fs.addAll(facultyService.getFaculties());
		model.addAttribute("faculties", fs);
		return "faculty-list";
	}
	
	@GetMapping("showForm")
	public String showForm(Model model, Faculty faculty) {
		//retrieving department list
		ArrayList<Department> ds = new ArrayList<Department>();
		ds.addAll(departmentService.getDepartments());
		model.addAttribute("departments", ds);
		return "faculty-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid Faculty faculty, BindingResult bindingresult) {
		facultyService.saveFaculty(faculty);
		return "redirect:/faculty/list";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		Faculty faculty = facultyService.getFaculty(id);
		
		if(faculty == null)
			return "redirect:/faculty/list";
		
		model.addAttribute(faculty);
			
		//retrieving department list
		ArrayList<Department> ds = new ArrayList<Department>();
		ds.addAll(departmentService.getDepartments());
		model.addAttribute("departments", ds);
		return "faculty-form";
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		facultyService.deleteFaculty(id);
		return "redirect:/faculty/list";
	}
	

}
