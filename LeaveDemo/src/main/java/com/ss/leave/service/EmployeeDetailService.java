package com.ss.leave.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ss.leave.entity.EmployeeDetail;
import com.ss.leave.repository.EmployeeDetailRepo;

@Service
public class EmployeeDetailService implements UserDetailsService {
	@Autowired
	private EmployeeDetailRepo empDetailRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		return empDetailRepo.findOneByMail(mail)
				.map(e -> new User(e.getMail(), e.getPassword(),
						Arrays.asList(new SimpleGrantedAuthority("ROLE_".concat(e.getPosition().toString())))))
				.orElseThrow(() -> new UsernameNotFoundException("存在しないメールでログインしています。"));
	}

	public void saveEmployee(EmployeeDetail employee) {
		employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
		empDetailRepo.save(employee);
	}

	public EmployeeDetail findOneByName(String name) {
		return empDetailRepo.findOneByName(name);
	}
}
