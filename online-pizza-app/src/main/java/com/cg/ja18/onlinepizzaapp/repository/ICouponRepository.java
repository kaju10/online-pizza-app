package com.cg.ja18.onlinepizzaapp.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;

@Repository
public interface ICouponRepository extends JpaRepository<Coupon, Long> {

}