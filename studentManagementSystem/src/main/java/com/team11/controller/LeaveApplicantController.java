package com.team11.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team11.entity.Course;
import com.team11.entity.LeaveApplicant;
import com.team11.service.LeaveApplicantService;
import com.team11.service.LeaveApplicantServiceImpl;

@RequestMapping("/leave")
@Controller
public class LeaveApplicantController {
	
	//Inject leave applicant service
	private LeaveApplicantService leaveApplicantService;
	@Autowired
	public void setLeaveApplicantService(LeaveApplicantServiceImpl leaveApplicantService) {
		this.leaveApplicantService = leaveApplicantService;
	}
	
	// *** FACULTY ROLE ***
	
	@GetMapping("/faculty/applyLeaveForm")
	public String showForm(LeaveApplicant leaveApplicant, Model model) {
		
		model.addAttribute("leaveApplicant", leaveApplicant);
		return "faculty/leave-form";
	}
	
	@PostMapping("/faculty/applyLeave")
	public String save(@Valid LeaveApplicant leaveApplicant, BindingResult bindingResult, 
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) { 
			redirectAttributes.addFlashAttribute("error", "Please input valid dates");
			return "redirect:/leave/faculty/applyLeaveForm";
		}
		
		// Get faculty id from session
		String facultyID = (String) request.getSession().getAttribute("userID");
		
		// Check if leave end date is before start date
		if(leaveApplicant.getEndDate().before(leaveApplicant.getStartDate())) {
			redirectAttributes.addFlashAttribute("error", "Please input a valid date range");
			return "redirect:/leave/faculty/applyLeaveForm";
		}
		
		// Check if leave date overlaps
		for (LeaveApplicant d: leaveApplicantService.findLeaveApplicantsByFacultyID(facultyID)) {
			if(!(leaveApplicant.getStartDate().after(d.getEndDate()) || leaveApplicant.getEndDate().before(d.getStartDate()))) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
				
				redirectAttributes.addFlashAttribute("error", "You already applied for a leave from " + 
				format.format(d.getStartDate()) + " to " + format.format(d.getEndDate()));
				return "redirect:/leave/faculty/applyLeaveForm";
			} 
		}
		
		leaveApplicant.setFacultyID((String) request.getSession().getAttribute("userID"));
		leaveApplicant.setStatus(0);
		leaveApplicantService.applyleave(leaveApplicant);
		redirectAttributes.addFlashAttribute("success", "Success!");
		return "redirect:/leave/faculty/applyLeaveForm";
	}

	@GetMapping("/faculty/applications")
	public String applications(Model model, HttpServletRequest request) {
		model.addAttribute("appliedLeaves", leaveApplicantService
				.findLeaveApplicantsByFacultyID(((String) request.getSession().getAttribute("userID"))));
		return "faculty/leave-applications";
	}
	
	@GetMapping("/faculty/applications/approved")
	public String approvedApplications(Model model, HttpServletRequest request) {
		model.addAttribute("appliedLeaves", leaveApplicantService
				.findLeaveApplicantsByFacultyIDAndStatus((String) request.getSession().getAttribute("userID"), 1));
		return "faculty/leave-applications";
	}
	
	@GetMapping("/faculty/applications/pending")
	public String pendingApplications(Model model, HttpServletRequest request) {
		model.addAttribute("appliedLeaves", leaveApplicantService
				.findLeaveApplicantsByFacultyIDAndStatus((String) request.getSession().getAttribute("userID"), 0));
		return "faculty/leave-applications";
	}
	
	@GetMapping("/faculty/applications/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		leaveApplicantService.deleteLeaveApplicant(id);
		return "redirect:/leave/faculty/applications";
	}
	
	// *** ADMIN ROLE ***
	@GetMapping("/admin/applications/pending")
	public String search(Model model) {
		
		model.addAttribute("pendingLeaves", leaveApplicantService.findLeaveApplicantsByStatus(0));
		
		return "admin/leave-admin-pending";
	}
	
	@GetMapping("/admin/applications/pending/approve/{id}")
	public String approve(@PathVariable("id") int id) {
		leaveApplicantService.approvePendingApplicant(id);
		
		return "redirect:/leave/admin/applications/pending";
	}
}
