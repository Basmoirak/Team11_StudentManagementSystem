package com.team11.service;

import java.util.List;

import com.team11.entity.LeaveApplicant;

public interface LeaveApplicantService {
	
	public List<LeaveApplicant> getLeaveApplicants();
	
	public LeaveApplicant getLeaveApplicantById(int id);
	
	public void deleteLeaveApplicant(int id);
	
	public void applyleave(LeaveApplicant leaveApplicant);
	
	public List<LeaveApplicant> findLeaveApplicantsByFacultyID(String facultyId);
	
	public List<LeaveApplicant> findLeaveApplicantsByFacultyIDAndStatus(String facultyId, int status);

	public List<LeaveApplicant> findLeaveApplicantsByStatus(int status);

	public void approvePendingApplicant(int id);
}
