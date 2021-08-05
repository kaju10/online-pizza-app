package com.cg.ja18.onlinepizzaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.PizzaOrder;
import com.cg.ja18.onlinepizzaapp.exceptions.OrderIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IOrderRepository;

@SpringBootTest
class OrderServiceTest {
	
	@Autowired
	private IOrderService orderservice;
	
	@MockBean
	private IOrderRepository orderrepo;
	
	Order order;

	@BeforeEach
	void setUp() throws Exception {
		
		Customer customer = Customer.builder().customerName("ABC").customerAddress("Kolkata").customerEmail("abc@60")
				.userName("abc@gmail.com").password("abc@60").build();
		Pizza pizza=Pizza.builder().pizzaName("Chicken Sausage Pizza").pizzaType("Non Veg").pizzaCost(200.0).pizzaDescription("Contains Chicken Sausage").build();
		Coupon coupon = Coupon.builder().couponName("Winter50").couponDescription("50% Off on All Orders").discountPercentage(50.0).build();
		PizzaOrder pizzaorder= PizzaOrder.builder().quantity(5).transactionMode("Online").pizza(pizza).build();
		order=Order.builder().orderType("Online").orderDescription("Add oregano").customer(customer).orderList(List.of(pizzaorder)).coupon(coupon).build();
		
		
		
		Mockito.when(orderrepo.findById(1L)).thenReturn(Optional.of(order));
		Mockito.when(orderrepo.findAll()).thenReturn(List.of(order));
		Mockito.when(orderrepo.save(order)).thenReturn(order);
		
		
		
	}

	@Test
	@DisplayName("testing the fetching of order by id")
	void viewOrderByOrderIdTest() throws OrderIdNotFoundException {
		
		Long orderId=1L;
		
		Order found_order  = orderservice.viewOrder(orderId);
		
		assertEquals(order, found_order);
		
	}
	
	@Test
	@DisplayName("testing the fetching of orderlist")
	void viewOrderListTest() {
		
		List<Order> orderlist= new ArrayList<Order>() {{add(order);}};
		
		assertEquals(orderlist, orderservice.viewOrderList());		
	}

	
	@Test
	@DisplayName("testing the saving of order")
	void bookOrderTest() {
		
		assertEquals(order, orderservice.bookOrder(order));	
	}
	
	@Test
	@DisplayName("Test the updation of order")	
	void updateOrderTest() {
		
		assertEquals(order, orderservice.updateOrder(order));
		
	}
	
	
	@Test
	@DisplayName("testing the deletion of order")
	void cancelOrderTest() throws OrderIdNotFoundException{
		
		orderservice.cancelOrder(1L);
		verify(orderrepo,times(1)).deleteById(1L);
		
		
	}
	
	
	
	
	
	
}