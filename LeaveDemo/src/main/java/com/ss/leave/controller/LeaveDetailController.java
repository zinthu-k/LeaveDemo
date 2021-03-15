package com.ss.leave.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.entity.LeaveDetail;
import com.ss.leave.entity.LeaveDetail.LeaveType;
import com.ss.leave.entity.LeaveDetail.Type;
import com.ss.leave.repository.EmployeeDetailRepo;
import com.ss.leave.repository.LeaveDetailRepo;
import com.ss.leave.service.LeaveDetailService;

@Controller
@RequestMapping("/leave")
public class LeaveDetailController {

	@Autowired
	private LeaveDetailRepo leaveRepo;

	@Autowired
	private EmployeeDetailRepo empRepo;

	@Autowired
	private LeaveDetailService leaveServicfe;

	@GetMapping("/leader")
	public ModelAndView leaderHome() {
		return allPaginatedLeaves(1);
	}

	@GetMapping("/member")
	public ModelAndView memberHome(HttpServletRequest req) {
		return myPaginatedLeave(1, req);
	}

	@GetMapping("/myLeavePage/{page}")
	public ModelAndView myPaginatedLeave(@PathVariable("page") int page, HttpServletRequest req) {
		ModelAndView modelAndView = new ModelAndView("/member/leave-apply");
		EmployeeDetail emp = empRepo.findOneByMail(req.getRemoteUser()).map(e -> {
			return e;
		}).orElseThrow(() -> new UsernameNotFoundException("ユーザー情報が間違っています。"));
		Pageable sortedByDateAsc = PageRequest.of(page - 1, 10, Sort.by("leaveDate").ascending());
		Page<LeaveDetail> leavePage = leaveServicfe.findByEmployee(emp, sortedByDateAsc);

		int totalPages = leavePage.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}

		modelAndView.addObject("leaveList", leavePage.getContent());

		return modelAndView;
	}
	
	@GetMapping("/allLeaves/{page}")
	public ModelAndView allPaginatedLeaves(@PathVariable("page") int page) {
		ModelAndView modelAndView = new ModelAndView("/leader/leave-manage");
		Pageable sortedByDateAsc = PageRequest.of(page - 1, 10, Sort.by("leaveDate").ascending());
		Page<LeaveDetail> leavePage = leaveServicfe.findByAll(sortedByDateAsc);

		int totalPages = leavePage.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}

		modelAndView.addObject("leaveList", leavePage.getContent());

		return modelAndView;
	}

	@PostMapping("/apply")
	public String applyLeave(HttpServletRequest req, @RequestParam("leaveDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date leaveDate, @RequestParam Type type,
			LeaveType leaveType) {

		LeaveDetail leave = new LeaveDetail();
		leave.setLeaveDate(leaveDate);
		leave.setType(type);
		leave.setLeaveType(leaveType);
		EmployeeDetail emp = empRepo.findOneByMail(req.getRemoteUser()).map(e -> {
			return e;
		}).orElseThrow(() -> new UsernameNotFoundException("ユーザー情報が間違っています。"));
		leave.setEmployee(emp);

		leaveRepo.save(leave);

		return "redirect:/leave/member";
	}

	@PostMapping("/update/{leaveId}")
	public String updateMyLeave(HttpServletRequest req, @RequestParam LeaveDetail leave) {
		// TODO

		return "redirect:/leave/member";
	}
}
