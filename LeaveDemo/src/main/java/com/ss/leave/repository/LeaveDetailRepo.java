package com.ss.leave.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.entity.LeaveDetail;

public interface LeaveDetailRepo extends JpaRepository<LeaveDetail, Integer>{
	public List<LeaveDetail> getAllByApprovedSiteContact(Boolean approved);

	public Page<LeaveDetail> findAll(Pageable pageable);

	public Page<LeaveDetail> findByEmployee(EmployeeDetail emp, Pageable pageable);

	public Page<LeaveDetail> findByEmployeeAndLeaveDate(EmployeeDetail emp, Date leaveDate, Pageable pageble);
}
