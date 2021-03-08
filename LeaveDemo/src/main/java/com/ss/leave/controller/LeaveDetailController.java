package com.ss.leave.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.entity.LeaveDetail;
import com.ss.leave.entity.LeaveDetail.LeaveType;
import com.ss.leave.entity.LeaveDetail.Type;
import com.ss.leave.repository.EmployeeDetailRepo;
import com.ss.leave.repository.LeaveDetailRepo;

@Controller
@RequestMapping("/leave")
public class LeaveDetailController {
	
	@Autowired
	private LeaveDetailRepo leaveRepo;
	
	@Autowired
	private EmployeeDetailRepo empRepo;

	@GetMapping
	public String index() {
		return "/leader/leave-manage";
	}

	@PostMapping("/apply")
	public String applyLeave(HttpServletRequest req, @RequestParam String date, @RequestParam Type type, LeaveType leaveType) {
		
		LeaveDetail leave = new LeaveDetail();
		leave.setLeaveDate(date);
		leave.setType(type);
		leave.setLeaveType(leaveType);
		EmployeeDetail emp = empRepo.findOneByMail(req.getRemoteUser()).map(e -> {
			return e;
		}).orElseThrow(() -> new UsernameNotFoundException("ユーザー情報が間違っています。"));
		leave.setEmployee(emp);
		
		leaveRepo.save(leave);

		return "/leader/leave-manage";
	}}
