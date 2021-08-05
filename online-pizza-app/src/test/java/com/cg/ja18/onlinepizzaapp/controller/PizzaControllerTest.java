package com.cg.ja18.onlinepizzaapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

//import javax.ws.rs.core.MediaType;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.service.IPizzaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
@WebMvcTest(PizzaController.class)
public class PizzaControllerTest {
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean 
	private IPizzaService service;

	
	@Test
	public void testAddPizza() throws Exception {
		Pizza pizza = new Pizza();
		//pizza.setPizzaId(100);
		pizza.setPizzaType("Veg");
		pizza.setPizzaName("Paneer Pizza");
		pizza.setSize("Large");
		pizza.setPizzaDescription("its a peppy paneer pizza");
		pizza.setPizzaCost(150.00);
		
		String inputInJson = this.mapToJson(pizza);
		String URI = "/addpizza";
		
		Mockito.when(service.addPizza(Mockito.any(Pizza.class))).thenReturn(pizza);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = result.getResponse().getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testViewPizza() throws Exception {
		
		Pizza pizza = new Pizza();
		pizza.setPizzaId(100);
		pizza.setPizzaType("Veg");
		pizza.setPizzaName("Paneer Pizza");
		pizza.setPizzaDescription("its a peppy paneer pizza");
		pizza.setPizzaCost(150.00);
		
		Mockito.when(service.viewPizza(Mockito.anyInt())).thenReturn(pizza);
		
		String URI = "/viewpizza/100";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		String expectedJson = this.mapToJson(pizza);
		String outputInJson = result.getResponse().getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testViewPizzaList() throws Exception {
		
		Pizza pizza1 = new Pizza();
		pizza1.setPizzaId(100);
		pizza1.setPizzaType("Veg");
		pizza1.setPizzaName("Paneer Pizza");
		pizza1.setPizzaDescription("its a peppy paneer pizza");
		pizza1.setPizzaCost(150.00);
		
		Pizza pizza2 = new Pizza();
		pizza2.setPizzaId(102);
		pizza2.setPizzaType("Non-Veg");
		pizza2.setPizzaName("Sausage Pizza");
		pizza2.setPizzaDescription("its a chicken sausage pizza");
		pizza2.setPizzaCost(200.00);
		
		List<Pizza> pizzaList = new ArrayList<>();
		pizzaList.add(pizza1);
		pizzaList.add(pizza2);
		
		Mockito.when(service.viewPizzaList()).thenReturn(pizzaList);
		
		String URI = "/viewpizza";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		String expectedJson = this.mapToJson(pizzaList);
		String outputInJson = result.getResponse().getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);
		
	}
	
	
	
	private String mapToJson(Object object) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
}
