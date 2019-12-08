package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team11.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
