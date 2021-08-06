package com.cg.ja18.onlinepizzaapp.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
//-----
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
//-----
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.PizzaOrder;
import com.cg.ja18.onlinepizzaapp.service.IOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private IOrderService orderservice;

	Order order;

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

		Mockito.when(orderservice.bookOrder(Mockito.any(Order.class))).thenReturn(order);
		Mockito.when(orderservice.viewOrder(Mockito.anyLong())).thenReturn(order);
		Mockito.when(orderservice.viewOrderList()).thenReturn(List.of(order));
	}

	@Test
	public void PostMappingTest() throws Exception {

		String inputInJson = this.mapToJson(order);

		String URI = "/saveorder";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = result.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		// assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testGetTicketById() throws Exception {

		String URI = "/showorder/1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(order);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetAllBookedTickets() throws Exception {

		String URI = "/showorder";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(List.of(order));
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}