package com.cg.ja18.onlinepizzaapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.PizzaOrder;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.repository.ILoginRepository;
import com.cg.ja18.onlinepizzaapp.service.ICustomerService;
import com.cg.ja18.onlinepizzaapp.service.IOrderService;
import com.cg.ja18.onlinepizzaapp.service.IPizzaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private ICustomerService cservice;

	@MockBean
	private IPizzaService pservice;

	@MockBean
	private ILoginRepository userrepo;

	@Test
	public void SavingCustomerControllerTest() throws Exception {

		Pizza pizza = Pizza.builder().pizzaName("Chicken Sausage Pizza").pizzaType("Non Veg").pizzaCost(200.0)
				.pizzaDescription("Contains Chicken Sausage").build();
		Coupon coupon = Coupon.builder().couponName("Winter50").couponDescription("50% Off on All Orders")
				.discountPercentage(50.0).build();
		PizzaOrder pizzaorder = PizzaOrder.builder().quantity(5).transactionMode("Online").pizza(pizza).build();
		Order order = Order.builder().orderType("Online").orderDescription("Add oregano").orderList(List.of(pizzaorder))
				.coupon(coupon).build();
		Customer customer = Customer.builder().customerName("ABC").customerAddress("Kolkata").customerEmail("abc@60")
				.userName("abc@gmail.com").password("abc@60").build();

		Mockito.when(cservice.addCustomer(Mockito.any(Customer.class))).thenReturn(customer);

		String inputInJson = this.mapToJson(customer);

		String URI = "/saveCustomer";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = result.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

		// assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
