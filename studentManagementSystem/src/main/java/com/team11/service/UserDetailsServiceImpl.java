package com.team11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team11.dao.UserRepository;
import com.team11.entity.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String s)throws UsernameNotFoundException{
		com.team11.entity.User user=userRepository.findByUsername(s);
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));		
		}
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
