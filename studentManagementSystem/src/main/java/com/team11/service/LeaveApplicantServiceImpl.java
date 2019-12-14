package com.team11.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.team11.dao.LeaveApplicantRepository;
import com.team11.entity.LeaveApplicant;

@Service
public class LeaveApplicantServiceImpl implements LeaveApplicantService {
	
	//Inject LeaveApplicantRepository
	private LeaveApplicantRepository leaveApplicantRepository;
	
	@Autowired
	public void setLeaveApplicantRepository(LeaveApplicantRepository leaveApplicantRepository) {
		this.leaveApplicantRepository = leaveApplicantRepository;
	}
	
	@Override
	@Transactional
	public List<LeaveApplicant> getLeaveApplicants() {
		return leaveApplicantRepository.findAll();
	}

	@Override
	@Transactional
	public LeaveApplicant getLeaveApplicantById(int id) {
		return leaveApplicantRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteLeaveApplicant(int id) {
		leaveApplicantRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void applyleave(LeaveApplicant leaveApplicant) {
		//Set status to pending for admin approval
		leaveApplicant.setStatus(0);
		leaveApplicantRepository.save(leaveApplicant);
	}

	@Override
	@Transactional
	public List<LeaveApplicant> findLeaveApplicantsByFacultyID(String facultyId) {
		return leaveApplicantRepository.findLeaveApplicantsByFacultyID(facultyId);
	}

	@Override
	@Transactional
	public List<LeaveApplicant> findLeaveApplicantsByFacultyIDAndStatus(String facultyId, int status) {
		return leaveApplicantRepository.findLeaveApplicantsByFacultyIDAndStatus(facultyId, status);
	}
	
	@Override
	@Transactional
	public List<LeaveApplicant> findLeaveApplicantsByStatus(int status) {
		return leaveApplicantRepository.findLeaveApplicantsByStatus(status);
	}

	@Override
	@Modifying
	@Transactional
	public void approvePendingApplicant(int id) {
		leaveApplicantRepository.approvePendingApplicant(id);
	}
}
