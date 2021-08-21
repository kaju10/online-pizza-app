package com.cg.ja18.onlinepizzaapp.service;

import java.util.List;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.exceptions.CouponIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.InvalidCouponOperationException;


public interface ICouponService {

	Coupon addCoupans(Coupon coupan);

	Coupon editCoupans(Coupon coupan) throws InvalidCouponOperationException;

	void deleteCoupans(Long coupanId)throws CouponIdNotFoundException;

	List<Coupon> viewCoupans() throws CouponIdNotFoundException;

	Coupon viewCoupan(Long coupanId)throws CouponIdNotFoundException;
	
	Coupon viewCouponByName(String couponName) throws CouponIdNotFoundException;
	
}
