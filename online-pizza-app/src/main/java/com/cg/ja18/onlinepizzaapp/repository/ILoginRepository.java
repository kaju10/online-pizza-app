package com.cg.ja18.onlinepizzaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ja18.onlinepizzaapp.entity.User;

@Repository
public interface ILoginRepository extends JpaRepository<User, Long> {

	User getByMobileAndPassword(Long mobile, String password);

}
