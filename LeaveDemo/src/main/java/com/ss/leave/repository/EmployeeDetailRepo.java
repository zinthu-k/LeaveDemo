package com.ss.leave.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.leave.entity.EmployeeDetail;

public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetail, Integer>{
	
	Optional<EmployeeDetail> findOneByMail(String mail);
	
	EmployeeDetail findOneByName(String name);

	public List<EmployeeDetail> findAll();

	public Page<EmployeeDetail> findByNameLike(String name, Pageable pageable);
}
