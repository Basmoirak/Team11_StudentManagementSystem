package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team11.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>{

}
