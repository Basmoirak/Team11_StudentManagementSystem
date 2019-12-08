package com.team11.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.team11.dao.RoleRepository;
import com.team11.dao.UserRepository;
import com.team11.entity.Role;
import com.team11.entity.User;
import com.team11.validation.CrmUser;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepositroy;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user= new User();
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		user.setRoles(Arrays.asList(roleRepositroy.findByName("ROLE_EMPLOYEE")));
		userRepository.save(user);
	}
	

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{
		User user=userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
//	List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//	for (Role role : user.getRoles()) {
//		authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));		
//	}
//	return new User(user.getUsername(), user.getPassword(), authorities);
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
