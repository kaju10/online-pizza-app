package com.cg.ja18.onlinepizzaapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
public class CouponServiceTest
{
	@Autowired
	private ICouponService service;
	
	@MockBean
	private ICouponRepository repo;
	
	Coupon coupon;
	
	@BeforeEach
	void setUp() throws Exception {
		coupon = Coupon.builder()
				.couponName("New Year Offer")
				.discountPercentage(40.00)
				.couponDescription("Pizza Coupon")
				.build();
		
	}
	
	@Test
	void addCouponTest() 
	{
		Mockito.when(repo.save(coupon)).thenReturn(coupon);
	    assertEquals(coupon, service.addCoupans(coupon));
	}
	
	@Test
	void showCouponTest() throws CouponIdNotFoundException 
	{
		Optional<Coupon> coupon3 = Optional.ofNullable(coupon);
		Long id = coupon.getCouponId();
		Mockito.when(repo.findById(id)).thenReturn(coupon3);
		assertThat(service.viewCoupan(id)).isEqualTo(coupon3.get());
	}
	
	
	@Test
	void showCouponListTest() throws CouponIdNotFoundException 
	{
		Mockito.when(repo.findAll())
		.thenReturn(Stream.of(coupon).collect(Collectors.toList()));
		
		assertEquals(1,service.viewCoupans().size());
	}
	
	
	@Test
	void updateCouponTest() throws InvalidCouponOperationException 
	{
		
		Optional<Coupon> coupon3 = Optional.ofNullable(coupon);
		Coupon c = coupon3.get();
		c.setDiscountPercentage(10.00);
		repo.save(c);
		
		Mockito.when(repo.findById(coupon.getCouponId())).thenReturn(coupon3);
		assertThat(c.getDiscountPercentage()).isEqualTo(10.00);
	}
	
	
	@Test
	
	void deleteCouponTest() throws CouponIdNotFoundException 
	{
	   repo.deleteById(coupon.getCouponId());
	   assertThat(repo.existsById(coupon.getCouponId())).isFalse();
	    
		
	}
}