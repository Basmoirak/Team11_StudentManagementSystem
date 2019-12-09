package com.team11.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.entity.Role;
import com.team11.entity.Student;
import com.team11.entity.User;
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
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel,
				RedirectAttributes redirectAttributes) {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 theModel.addAttribute("roles", roles);
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("roles", roles);
        	theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        
        // ** CREATE USER ACCOUNT **    						
        String formRole = theCrmUser.getFormRole();
        User newUser = userService.createNewUser(theCrmUser, formRole);
        userService.save(newUser);
        redirectAttributes.addFlashAttribute("newUser", newUser);
        
        if(formRole.equals("ROLE_STUDENT")) {
        	return "redirect:/register/registerStudentForm";
        }
        
        if(formRole.equals("ROLE_FACULTY")) {
        	
        }
        
        if(formRole.equals("ROLE_ADMIN")) {
        	
        }
        
        return "registration-form";
	}
	
	@GetMapping("/registerStudentForm")
	public String showForm(@ModelAttribute("newUser") User newUser ,Student student, Model model) {
		
		student.setId(newUser.getId());
		model.addAttribute("student", student);
		model.addAttribute("levels", studentService.getLevels());
		model.addAttribute("semesters", studentService.getSemesters());
		model.addAttribute("statuses", studentService.getStatuses());
		
		return "student-form";
	}
	
	@PostMapping("/saveStudentForm")
	public String add(@Valid Student student, BindingResult result, Model model) {
		
		//Don't allow user to add student if there are any form validation errors
		if(result.hasErrors()) {
			model.addAttribute("student", student);
			model.addAttribute("levels", studentService.getLevels());
			model.addAttribute("semesters", studentService.getSemesters());
			model.addAttribute("statuses", studentService.getStatuses());
			return "student-form";
		}
		
		// save the student 
		studentService.saveStudent(student);
		return "registration-confirmation";
	}	
}
