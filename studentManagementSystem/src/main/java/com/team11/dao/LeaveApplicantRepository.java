package com.team11.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.team11.entity.LeaveApplicant;

@Repository
public interface LeaveApplicantRepository extends JpaRepository<LeaveApplicant, Integer>{
	
	@Query("select la from LeaveApplicant la WHERE la.facultyID =:facultyId")
	public List<LeaveApplicant> findLeaveApplicantsByFacultyID(String facultyId);
	
	@Query("select la from LeaveApplicant la WHERE la.facultyID =:facultyId and la.status = :status order by la.startDate asc")
	public List<LeaveApplicant> findLeaveApplicantsByFacultyIDAndStatus(String facultyId, int status);
	
	@Query("select la from LeaveApplicant la WHERE la.status = :status order by la.startDate asc")
	public List<LeaveApplicant> findLeaveApplicantsByStatus(int status);
	
	@Modifying
	@Query("update LeaveApplicant la set la.status =1 where la.Id =:id")
	public void approvePendingApplicant(int id);
}
