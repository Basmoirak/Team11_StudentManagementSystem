package com.team11.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.team11.entity.Role;
import com.team11.entity.User;
import com.team11.validation.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
	
	public void save(User user);
	
	public List<Role> getRoles();

	User createNewUser(CrmUser crmUser, String roleName);
}
