package com.ss.leave.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ss.leave.entity.EmployeeDetail.Position;
import com.ss.leave.repository.EmployeeDetailRepo;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private EmployeeDetailRepo empRepo;

	@GetMapping
	public String index(HttpServletRequest req) {
		return empRepo.findOneByMail(req.getRemoteUser()).map(e -> {
			req.getSession(true).setAttribute("LoginUser", e);
			return e.getPosition() == Position.Leader ? "redirect:/leave/leader" : "redirect:/leave/member";
		}).orElseThrow(() -> new UsernameNotFoundException("ユーザー情報が間違っています。"));
	}
}