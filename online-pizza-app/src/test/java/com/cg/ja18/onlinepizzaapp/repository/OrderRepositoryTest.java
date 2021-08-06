package com.cg.ja18.onlinepizzaapp.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.PizzaOrder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class OrderRepositoryTest {

	@Autowired
	IOrderRepository orderrepo;

	Order order;
	Order savedOrder;

	@BeforeEach
	void setUp() throws Exception {

		Customer customer = Customer.builder().customerName("ABC").customerAddress("Kolkata").customerEmail("abc@60")
				.userName("abc@gmail.com").password("abc@60").build();
		Pizza pizza = Pizza.builder().pizzaName("Chicken Sausage Pizza").pizzaType("Non Veg").pizzaCost(200.0)
				.pizzaDescription("Contains Chicken Sausage").build();
		Coupon coupon = Coupon.builder().couponName("Winter50").couponDescription("50% Off on All Orders")
				.discountPercentage(50.0).build();
		PizzaOrder pizzaorder = PizzaOrder.builder().quantity(5).transactionMode("Online").pizza(pizza).build();
		order = Order.builder().orderType("Online").orderDescription("Add oregano").customer(customer)
				.orderList(List.of(pizzaorder)).coupon(coupon).build();

		savedOrder = orderrepo.save(order);

	}

	@Test
	void whenFindById_ReturnOrder() {

		assertEquals(order.getOrderType(), orderrepo.findById(1L).get().getOrderType());
	}

	@Test
	void saveOrderTest() {

		assertNotNull(savedOrder);

	}

	@Test
	void deleteByIdTest() {

		orderrepo.deleteById(savedOrder.getOrderId());
		boolean orderGotDeletedAfterDeleting = orderrepo.findById(savedOrder.getOrderId()).isEmpty();
		assertTrue(orderGotDeletedAfterDeleting);
	}

}