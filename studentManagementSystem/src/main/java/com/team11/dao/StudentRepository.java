package com.team11.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

	@Query("select s FROM Student s WHERE "
			+ "concat(s.firstName, ' ', s.lastName) like %?1% or s.gender like %?1% order by s.Id")
	public Page<Student> searchAndPaginate(String search, Pageable pageable);
}
