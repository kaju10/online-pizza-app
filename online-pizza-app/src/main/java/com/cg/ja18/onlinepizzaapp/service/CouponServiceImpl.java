package com.cg.ja18.onlinepizzaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.exceptions.CouponIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.InvalidCouponOperationException;
import com.cg.ja18.onlinepizzaapp.repository.ICouponRepository;

@Service
public class CouponServiceImpl implements ICouponService {
	@Autowired
	public ICouponRepository repo;

	@Override
	public Coupon addCoupans(Coupon coupon) {
		Coupon rcoupon = repo.save(coupon);
		return rcoupon;
	}

	@Override
	public Coupon editCoupans(Coupon coupon) throws InvalidCouponOperationException {
		Optional<Coupon> rcoupon = repo.findById(coupon.getCouponId());
		if (rcoupon.isPresent()) {
			// repo.deleteById(coupon.getCouponId());
			repo.save(coupon);
			return rcoupon.get();
		} else {
			throw new InvalidCouponOperationException("Coupon is not available");
		}
	}

	@Override
	public void deleteCoupans(Long couponId) throws CouponIdNotFoundException {
		Optional<Coupon> rcoupon = repo.findById(couponId);
		if (rcoupon.isPresent()) {
			repo.deleteById(couponId);

		} else {
			throw new CouponIdNotFoundException("There is no such Coupon");
		}

	}

	@Override
	public List<Coupon> viewCoupans() throws CouponIdNotFoundException {
		List<Coupon> list = new ArrayList<>();
		repo.findAll().forEach(list::add);
		if (list.isEmpty()) {
			throw new CouponIdNotFoundException("Empty");
		} else {
			return list;
		}
	}

	@Override
	public Coupon viewCoupan(Long couponId) throws CouponIdNotFoundException {
		Optional<Coupon> rcoupon = repo.findById(couponId);
		if (rcoupon.isPresent()) {
			return rcoupon.get();
		} else {
			throw new CouponIdNotFoundException("Coupon is not available");
		}
	}

	@Override
	public Coupon viewCouponByName(String couponName) throws CouponIdNotFoundException {
		// TODO Auto-generated method stub
		List<Coupon> list= new ArrayList<Coupon>();
		list= repo.findAll();
		Long d = 0L;
		for(Coupon c : list) {
			c.getCouponDescription().equalsIgnoreCase(couponName);
			d=c.getCouponId();
			break;
		}
		Optional<Coupon> rcoupon = repo.findById(d);
		if (rcoupon.isPresent()) {
			return rcoupon.get();
		} else {
			throw new CouponIdNotFoundException("Coupon is not available");
		}
		
	}
}