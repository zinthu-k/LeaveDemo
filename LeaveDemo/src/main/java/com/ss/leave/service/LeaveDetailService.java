package com.ss.leave.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.entity.LeaveDetail;
import com.ss.leave.repository.LeaveDetailRepo;

@Service
public class LeaveDetailService {
	
	@Autowired
	private LeaveDetailRepo leaveRepo;
	
	public void applyLeave(LeaveDetail leaveInfo, EmployeeDetail empInfo) {
		leaveInfo.setEmployee(empInfo);
		leaveRepo.save(leaveInfo);
	}

	public Page<LeaveDetail> findByEmployeeAndLeaveDate(EmployeeDetail emp, Date leaveDate, Pageable pageble) {
		
		return leaveRepo.findByEmployeeAndLeaveDate(emp, leaveDate, pageble);
	}

	public Page<LeaveDetail> findByEmployee(EmployeeDetail emp, Pageable pageble) {
		return leaveRepo.findByEmployee(emp, pageble);
	}

	public Page<LeaveDetail> findByAll(Pageable pageable) {
		return leaveRepo.findAll(pageable);
	}
}
