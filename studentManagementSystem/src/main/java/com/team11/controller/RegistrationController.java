package com.team11.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team11.entity.Faculty;
import com.team11.entity.Role;
import com.team11.entity.Student;
import com.team11.entity.User;
import com.team11.service.DepartmentService;
import com.team11.service.FacultyService;
import com.team11.service.StudentService;
import com.team11.service.UserService;
import com.team11.validation.CrmUser;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
    @Autowired
	private StudentService studentService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private DepartmentService departmentService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    private List<Role> roles;
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@PostConstruct
	protected void loadRoles() {
		
		roles = userService.getRoles();
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		// add roles to the model for form display
		theModel.addAttribute("roles", roles);
		
		return "registration/registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel,
				HttpSession session) {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 theModel.addAttribute("roles", roles);
			 return "registration/registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("roles", roles);
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration/registration-form";
        }
        
        // ** CREATE USER ACCOUNT **    						
        String formRole = theCrmUser.getFormRole();
        User newUser = userService.createNewUser(theCrmUser, formRole);
        session.setAttribute("newUser", newUser);
        
        if(formRole.equals("ROLE_STUDENT")) {
        	return "redirect:/register/registerStudentForm";
        }
        
        if(formRole.equals("ROLE_FACULTY")) {
        	return "redirect:/register/registerFacultyForm";
        }
        
        return "registration/welcome";
	}
	
	// Register Student
	@GetMapping("/registerStudentForm")
	public String showStudentForm(Student student, Model model) {
		
		model.addAttribute("student", student);
		model.addAttribute("levels", studentService.getLevels());
		model.addAttribute("semesters", studentService.getSemesters());
		
		return "registration/student-form";
	}
	
	@PostMapping("/saveStudentForm")
	public String saveStudentForm(@Valid Student student, BindingResult result, Model model,HttpSession session) {
		
		//Don't allow user to add student if there are any form validation errors
		if(result.hasErrors()) {
			model.addAttribute("student", student);
			model.addAttribute("levels", studentService.getLevels());
			model.addAttribute("semesters", studentService.getSemesters());
			return "registration/student-form";
		}
		User newUser=(User)session.getAttribute("newUser");
		student.setId(newUser.getId());
		userService.save(newUser);
		studentService.saveStudent(student);
		return "registration/welcome";
	}	
	
	// Register Faculty
	@GetMapping("/registerFacultyForm")
	public String showFacultyForm(Faculty faculty, Model model) {
		
		model.addAttribute("faculty", faculty);
		model.addAttribute("departments", departmentService.getDepartments());
		return "registration/faculty-form";
	}
	
	@PostMapping("/saveFacultyForm")
	public String saveFacultyForm(@Valid Faculty faculty, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) { 
			model.addAttribute("departments", departmentService.getDepartments());
			return "registration/faculty-form";
		}
		
		// Save or Update faculty
		User newUser=(User)session.getAttribute("newUser");
		faculty.setFaculty(newUser.getId());
		userService.save(newUser);
		facultyService.saveFaculty(faculty);
		return "registration/welcome";
	}
	
}
