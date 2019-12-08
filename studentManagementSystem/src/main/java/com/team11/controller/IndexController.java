package com.team11.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.team11.dao.RoleRepository;
import com.team11.dao.UserRepository;
import com.team11.entity.Role;
import com.team11.entity.User;

@Controller
public class IndexController {
	@Autowired
	private UserRepository userrepository;

	@Autowired
	private RoleRepository rolerepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @RequestMapping("/default")
    public RedirectView login(HttpServletRequest request) {
    	SecurityContext context = SecurityContextHolder.getContext();		
    	Authentication authentication = context.getAuthentication();
    	Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    	if (roles.contains("ROLE_ADMIN")) {
    		return new RedirectView("/course/list");
			
		}else if (roles.contains("ROLE_FACULTY")) {
			return new RedirectView("/department/list");
		}else {
	    	System.out.println("object:"+authentication.getPrincipal());
	        return new RedirectView("/student/list");
		}

    }
    
    @PostMapping("/registry")
    public String registry(String username,String password,String rolename) {
        //Checking username in database make sure unique one      
    	User user=userrepository.findByUsername(username);
    	
        if (user==null) {             
            Role role=new Role(rolename);
        	rolerepository.save(role);
            List<Role> roles=new ArrayList<Role>();        
            roles.add(role);
            userrepository.save(new User(username, passwordEncoder.encode(password),roles));
            System.out.println("register complete:"+username+","+password+","+rolename);
            return "welcome";
		}
        else {
        	String SameUserErr="The user with name ‘"+username+"’ has already existed!";
			System.out.println(SameUserErr);
			return "welcome";
		}
    }
}



