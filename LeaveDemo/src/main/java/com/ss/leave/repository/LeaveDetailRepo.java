package com.ss.leave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.entity.LeaveDetail;

public interface LeaveDetailRepo extends JpaRepository<LeaveDetail, Integer>{
//    @Query(nativeQuery = true, value = "select array_to_json(array_agg(row_to_json(l))) from (select employee_id||' on leave' as title,to_char(from_date,'YYYY-MM-DD') as start,to_char(to_date,'YYYY-MM-DD') as end from leave_contact) as l;")
//    public Object getAllLeavesAsJsonArray();
	public List<LeaveDetail> getAllByApprovedSiteContact(Boolean approved);
	public List<LeaveDetail> findAllByEmployee(EmployeeDetail emp);

	public List<LeaveDetail> findAll();
}
