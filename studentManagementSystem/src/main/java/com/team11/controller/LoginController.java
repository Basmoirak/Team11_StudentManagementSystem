package com.team11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team11.entity.User;
import com.team11.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String showHome(HttpServletRequest httpRequest) {
		
		//Store userID in session
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		User user =  userService.findByUserName(userDetail.getUsername());
		httpRequest.getSession().setAttribute("userID", user.getId());
		
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









