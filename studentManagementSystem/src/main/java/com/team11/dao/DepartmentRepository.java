package com.team11.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	@Query("select d FROM Department d WHERE "
			+ "d.name like %?1% order by d.Id")
	public Page<Department> searchAndPaginate(String search, Pageable pageable);
}
