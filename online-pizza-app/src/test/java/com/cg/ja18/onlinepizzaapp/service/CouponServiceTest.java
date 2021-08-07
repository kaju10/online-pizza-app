package com.cg.ja18.onlinepizzaapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.exceptions.CouponIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.InvalidCouponOperationException;
import com.cg.ja18.onlinepizzaapp.repository.ICouponRepository;

@SpringBootTest
public class CouponServiceTest {
	@Autowired
	private ICouponService service;

	@MockBean
	private ICouponRepository repo;

	Coupon coupon;

	@BeforeEach
	void setUp() throws Exception {
		coupon = Coupon.builder().couponId(1L).couponName("New Year Offer").discountPercentage(40.00)
				.couponDescription("Pizza Coupon").build();

		Mockito.when(repo.save(coupon)).thenReturn(coupon);
		Mockito.when(repo.findById(coupon.getCouponId())).thenReturn(Optional.of(coupon));
		Mockito.when(repo.findAll()).thenReturn(Stream.of(coupon).collect(Collectors.toList()));
	}

	@Test
	void addCouponTest() {
		assertEquals(coupon.getCouponId(), service.addCoupans(coupon).getCouponId());
	}

	@Test
	void showCouponTest() throws CouponIdNotFoundException {
		assertThat(service.viewCoupan(coupon.getCouponId()).getCouponId()).isEqualTo(coupon.getCouponId());
	}

	@Test
	void showCouponListTest() throws CouponIdNotFoundException {
		assertEquals(1, service.viewCoupans().size());
	}

	@Test
	void updateCouponTest() throws InvalidCouponOperationException {
		assertEquals(coupon, service.editCoupans(coupon));
	}

	@Test

	void deleteCouponTest() throws CouponIdNotFoundException {
		System.out.println(coupon.getCouponId());
		service.deleteCoupans(coupon.getCouponId());

		verify(repo, times(1)).deleteById(coupon.getCouponId());
	}
}