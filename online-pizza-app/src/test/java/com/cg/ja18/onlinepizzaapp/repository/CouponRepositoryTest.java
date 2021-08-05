package com.cg.ja18.onlinepizzaapp.repository;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.repository.ICouponRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)

public class CouponRepositoryTest {

	@Autowired
	private ICouponRepository repo;
	
	Coupon coupon;
	
	@BeforeEach
	void setUp() throws Exception {
		coupon = Coupon.builder()
				.couponName("New Year Offer")
				.discountPercentage(40.00)
				.couponDescription("Pizza Coupon")
				.build();
		
		repo.save(coupon);
	}

	@Test
	void findMethodtest() 
	{
		Long id = coupon.getCouponId();
		assertEquals(40.00,repo.findById(id).get().getDiscountPercentage());	
	}

	@Test
	void addMethodtest()
	{
		assertEquals(repo.save(coupon),coupon);	
	}
	
	@Test
	void finaAllMethodTest()
	{
	   assertNotNull(repo.findAll());
	}
	
	@Test
	void deleteMethodTest()
	{
		repo.deleteById(coupon.getCouponId());
	}
}
