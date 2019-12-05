package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team11.dao.DepartmentRepository;
import com.team11.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	@Override
	@Transactional
	public List<Department> getDepartments(){
		return departmentRepository.findAll();
	}
	@Override
	@Transactional
	public void deleteDepartment(int theId) {
		departmentRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public void saveDepartment(Department department) {
		departmentRepository.save(department);
		
	}

	@Override
	@Transactional
	public Department getDepartment(int theId) {
		return departmentRepository.getOne(theId);
	}
	
	
}
