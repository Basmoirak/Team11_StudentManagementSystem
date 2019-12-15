package com.team11.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team11.entity.User;
import com.team11.service.CourseApplicantService;
import com.team11.service.CourseApplicantServiceImpl;

@Controller
@RequestMapping("/faculty")
public class EmailController {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private CourseApplicantService courseApplicantService;
	
	
	@Autowired
	public void setCourseApplicantService(CourseApplicantServiceImpl courseApplicantServiceImpl) {
		this.courseApplicantService = courseApplicantServiceImpl;
	}

	
	@GetMapping("/faculty/show-emailform")
	public String showEmailform(HttpServletRequest request,Model model) {
		//get student email addresses
		String stringid=request.getParameter("id");
		int id=Integer.parseInt(stringid);
		List<String> emailsList=courseApplicantService.findStudentEmailByCourseId(id);		
		String[] receivers=new String[emailsList.size()];
		int i=0;
		for (String email : emailsList) {
			receivers[i]=email;
			i++;
		}
		//display mail information
		String stringemailsList=emailsList.toString().replaceAll("\\[","").replaceAll("\\]", "");
		String subject= null;
		String body= null;
		String footer="<------This is a system generated email form Team11-SMS,please do not reply------> ";
		String department=request.getParameter("dp");
		String coursename=request.getParameter("cn");
		model.addAttribute("subject", subject);
		model.addAttribute("emaillist", stringemailsList);
		model.addAttribute("body", body);
		model.addAttribute("coursename",coursename);
		model.addAttribute("departmentname",department);
		model.addAttribute("footer", footer);
		return "faculty/email-form";
	}
	
	//get mail subject,body,etc
	@PostMapping("/faculty/send")
	public String sendEmail(HttpServletRequest request,
			@RequestParam("subject")String subject ,			
			@RequestParam("body")String body,
			@RequestParam("emailto")String emailto,
			@RequestParam("footer")String footer) throws Exception {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message, true);
		helper.setSubject(subject);
		User u=(User) request.getSession().getAttribute("user");
		helper.setText("Course Name:<a href=\"http://localhost:8081/courseApp/student/courses/my\">"+request.getParameter("cn")+"</a></br>"
				+ "Department Name:"+request.getParameter("dp")+"</br>"
				+ "Teaching Staff:"+u.getFirstName()+" "+u.getLastName()+"("+"<a href=\"http://www.google.com\">"+u.getEmail()+"</a>"+")"+"</br>"
				+ "</br>"
				+ body+"</br></br>"
				+footer,true);
		String[] emails=emailto.split(",");
        //for test use
		//String[] fortestuseonly=new String[] {"e0390012@u.nus.edu","e0338221@u.nus.edu",
		//"mingxi@u.nus.edu","e0457779@u.nus.edu","e0457819@u.nus.edu"};
		
		//send email
		helper.setTo(emails);
		javaMailSender.send(message);
		return "faculty/sendemailsuccessfully";
		
	}




}
