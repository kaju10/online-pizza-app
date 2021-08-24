package com.cg.ja18.onlinepizzaapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "*")
@RestController
public class CouponController {
	@Autowired
	private ICouponService service;

	@PostMapping("/savecoupon")
	Coupon addCoupon(@RequestBody Coupon coupon) {
		return service.addCoupans(coupon);
	}

	@GetMapping("/showcoupon/{couponId}")
	Coupon viewCoupon(@PathVariable String couponId) throws CouponIdNotFoundException {
		return service.viewCoupan(Long.parseLong(couponId));
	}

	@GetMapping("/showcouponlist")
	List<Coupon> viewCoupons() throws CouponIdNotFoundException {
		return service.viewCoupans();
	}

	@PutMapping("/updatecoupon")
	Coupon updateCoupon(@RequestBody Coupon coupon) throws InvalidCouponOperationException {
		return service.editCoupans(coupon);
	}

	@DeleteMapping("/deletecoupon/{couponId}")
	String deleteCoupon(@PathVariable String couponId) throws CouponIdNotFoundException {
		service.deleteCoupans(Long.parseLong(couponId));
		return "Coupon with coupon id " + couponId + " got deleted";
	}
}