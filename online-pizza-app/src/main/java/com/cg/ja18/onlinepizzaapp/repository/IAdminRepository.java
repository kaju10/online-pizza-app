package com.cg.ja18.onlinepizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ja18.onlinepizzaapp.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Long>{

	public Admin findByAdminId(Long adminId);
}
