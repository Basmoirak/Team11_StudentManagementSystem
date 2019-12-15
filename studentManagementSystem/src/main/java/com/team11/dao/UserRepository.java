package com.team11.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.team11.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	User findByUserName(String username);
	
	

	
	
}
