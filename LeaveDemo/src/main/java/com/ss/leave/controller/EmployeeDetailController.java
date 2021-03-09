package com.ss.leave.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.repository.EmployeeDetailRepo;
import com.ss.leave.service.EmployeeDetailService;

@Controller
@RequestMapping("/employee")
public class EmployeeDetailController {
	
	@Autowired
	private EmployeeDetailService empService;
	
	@Autowired
	private EmployeeDetailRepo empRepo;
	
	@Autowired
	private BCryptPasswordEncoder bPswEncoder;


	@GetMapping("/list")
	public String index(Model model){
		List<EmployeeDetail> empList = empRepo.findAll();
		model.addAttribute("empList", empList);

		return "/leader/employee-list";
	}
	
	@PostMapping("/register")
	public String createEmployee(@RequestParam EmployeeDetail emp) {
		EmployeeDetail employee = new EmployeeDetail();
		
		employee.setName(emp.getName());
		employee.setMail(emp.getMail());
		employee.setPassword(bPswEncoder.encode(emp.getPassword()));
		employee.setPhoneNo(emp.getPhoneNo());
		employee.setAddress(emp.getAddress());
		employee.setPosition(emp.getPosition());
		employee.setGroupNo(emp.getGroupNo());
		employee.setHireDate(emp.getHireDate());
		
		empService.saveEmployee(employee);

		return "redirect:/leader/employee-list";
	}

	@PostMapping("/update/{mail}")
	public String updateEmployee(HttpServletRequest req, @RequestParam EmployeeDetail emp) {
		//TODO

		return "redirect:/leader/employee-list";
	}

}
