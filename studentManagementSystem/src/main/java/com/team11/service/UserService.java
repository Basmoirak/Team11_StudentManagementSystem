package com.team11.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.team11.entity.User;
import com.team11.validation.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
	public void save(CrmUser crmUser);
	
}
