package com.team11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team11.entity.Status;
import com.team11.entity.StudentGrades;

@Repository
public interface StudentGradesRepository extends JpaRepository<StudentGrades, Integer>{

}
