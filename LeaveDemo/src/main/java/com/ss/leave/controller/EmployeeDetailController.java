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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.entity.EmployeeDetail.Groups;
import com.ss.leave.entity.EmployeeDetail.Position;
import com.ss.leave.service.EmployeeDetailService;

@Controller
@RequestMapping("/employee")
public class EmployeeDetailController {

	@Autowired
	private EmployeeDetailService empService;
	
	@GetMapping("/list")
	public ModelAndView viewHomePage(@RequestParam(defaultValue="") String name) {
	 return paginatedEmployee(name,1);  
	}

	@GetMapping("/page/{page}")
	public ModelAndView paginatedEmployee(@RequestParam(defaultValue="") String name, @PathVariable("page") int page) {
		ModelAndView modelAndView = new ModelAndView("/leader/employee-list");
		Pageable sortedByNameDesc = PageRequest.of(page - 1, 10, Sort.by("name").descending());
		Page<EmployeeDetail> empPage = empService.findByName(name,sortedByNameDesc);
		
		int totalPages = empPage.getTotalPages();

		if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

		modelAndView.addObject("empList", empPage.getContent());

		return modelAndView;
	}

	@PostMapping("/register")
	public String registerForm(@RequestParam String mail, @RequestParam String name, @RequestParam String password, @RequestParam String phoneNo,
			@RequestParam Date hireDate, @RequestParam String address, @RequestParam Groups groupNo) {
		EmployeeDetail employee = new EmployeeDetail();

		employee.setMail(mail);
		employee.setName(name);
		employee.setPassword(password);
		employee.setPhoneNo(phoneNo);
		employee.setHireDate(hireDate);
		employee.setAddress(address);
		employee.setGroupNo(groupNo);
		employee.setPosition(Position.Member);

		empService.saveEmployee(employee);

		return "redirect:/employee/list";
	}

	@PostMapping("/update/{mail}")
	public String updateEmployee(HttpServletRequest req, @RequestParam EmployeeDetail emp) {
		// TODO

		return "redirect:/leader/employee-list";
	}

}
