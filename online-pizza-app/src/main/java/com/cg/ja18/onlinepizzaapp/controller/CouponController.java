package com.cg.ja18.onlinepizzaapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.exceptions.CouponIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.InvalidCouponOperationException;
import com.cg.ja18.onlinepizzaapp.service.ICouponService;


@RestController
public class CouponController
{
	@Autowired
	private ICouponService service;
	
	@PostMapping("/savecoupon")
	ResponseEntity<Coupon> add_Coupon(@RequestBody Coupon coupon)
	{
		Coupon result = service.addCoupans(coupon);
		if(result==null)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		return ResponseEntity.of(Optional.of(result));
	}
	
	
	@GetMapping("/showcoupon/{couponId}")
	ResponseEntity<Coupon> view_Coupon(@PathVariable String couponId) throws CouponIdNotFoundException
	{
		Coupon result =service.viewCoupan(Long.parseLong(couponId));
		if(result==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(result));
	}
	
	@GetMapping("/showcouponlist")
	ResponseEntity<List<Coupon>> view_Coupons() throws CouponIdNotFoundException
	{
		List<Coupon> rlist = service.viewCoupans();
		if(rlist.size()==0)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.of(Optional.of(rlist));
	}
	
	
	@PutMapping("/updatecoupon")
	ResponseEntity<Coupon> update_Coupon(@RequestBody Coupon coupon) throws InvalidCouponOperationException
	{
		Coupon result = service.editCoupans(coupon);
		if(result==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(result));
	}
	

	@DeleteMapping("/deletecoupon/{couponId}")
	ResponseEntity<Void> delete_Coupon(@PathVariable String couponId) throws CouponIdNotFoundException
	{
		Coupon result = service.deleteCoupans(Long.parseLong(couponId));
		if(result==null)
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}