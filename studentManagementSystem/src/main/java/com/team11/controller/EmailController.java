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
	
	//for getting emails
	private String[] emailList;
	
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
		this.emailList=receivers;
		//display mail information
		String subject="Notification";
		String coursename=request.getParameter("cm");		
		User u=(User) request.getSession().getAttribute("user");
		String teachingstaff=u.getFirstName()+" "+u.getLastName();
		String departmentname=request.getParameter("dp");
		String body="This is a notification email from faculty";
		String footer="<------This is a system generated email form Team11-SMS,please do not reply------> ";
		model.addAttribute("subject", subject);
		model.addAttribute("emaillist", "AllStudentsUnderCourse@gmail.com");
		model.addAttribute("coursename", coursename);
		model.addAttribute("teachingstaff", teachingstaff);
		model.addAttribute("departmentname", departmentname);
		model.addAttribute("body", body);
		model.addAttribute("footer", footer);
		return "faculty/email-form";
	}
	
	//get mail subject,body,etc
	@PostMapping("/faculty/send")
	public String sendEmail(@RequestParam("subject")String subject,HttpServletRequest request,
			@RequestParam("body")String body,
			@RequestParam("coursename")String coursename,
			@RequestParam("departmentname")String departmentname,
			@RequestParam("footer")String footer,
			@RequestParam("teachingstaff")String teachingstaff) throws Exception {
		
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message, true);
		User u=(User) request.getSession().getAttribute("user");
		helper.setSubject(subject);
		helper.setText("Course Name:<a href=\"http://localhost:8081/courseApp/student/courses/my\">"+coursename+"</a></br>"
				+ "Department Name:"+departmentname+"</br>"
				+ "Teaching Staff:"+teachingstaff+"("+"<a href=\"http://www.google.com\">"+u.getEmail()+"</a>"+")"+"</br>"
				+ "</br>"
				+ body+"</br></br>"
				+footer,true);
        //for test use
		String[] fortestuseonly=new String[] {"e0390012@u.nus.edu","e0486421@u.nus.edu","mingxi@u.nus.edu","e0457819@u.nus.edu","e0338221@u.nus.edu"};

		//send email
		helper.setTo(fortestuseonly);
		javaMailSender.send(message);
		return "faculty/sendemailsuccessfully";
		
	}




}
