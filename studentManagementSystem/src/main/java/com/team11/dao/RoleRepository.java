package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team11.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String s);

}
