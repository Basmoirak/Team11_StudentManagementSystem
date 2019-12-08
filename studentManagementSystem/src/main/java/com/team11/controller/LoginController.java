package com.team11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/student")
	public String showStudent() {
		return "Student";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "Admin";
	}
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		// return "plain-login";

		return "fancy-login";
		
	}
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	
}









