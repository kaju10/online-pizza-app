package com.cg.ja18.onlinepizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ja18.onlinepizzaapp.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

}