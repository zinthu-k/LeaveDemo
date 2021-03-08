package com.ss.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<LeaveDetail> findEmployeeOf(EmployeeDetail empInfo){	
		return leaveRepo.findAllByEmployee(empInfo);		
	}
}
