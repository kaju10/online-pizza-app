package com.cg.ja18.onlinepizzaapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ja18.onlinepizzaapp.entity.Pizza;



@Repository
public interface IPizzaRepository extends JpaRepository<Pizza, Integer> {
	
	

}
