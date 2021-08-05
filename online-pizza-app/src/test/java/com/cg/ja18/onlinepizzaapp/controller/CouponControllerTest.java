package com.cg.ja18.onlinepizzaapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrlTemplate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvc.*;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cg.ja18.onlinepizzaapp.entity.Coupon;
import com.cg.ja18.onlinepizzaapp.service.ICouponService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(CouponController.class)
public class CouponControllerTest
{
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private ICouponService service;
	
	//private static ObjectMapper mapper = new ObjectMapper();
    
	@Test
    public void testPostExample() throws Exception {
        Coupon coupon = new Coupon();
        coupon.setCouponName("abc");
        coupon.setDiscountPercentage(27.00);
        coupon.setCouponDescription("xyz");
        
        String inputInJson = this.mapToJson(coupon);
        
        String URI = "/savecoupon";
        
        Mockito.when(service.addCoupans(Mockito.any(Coupon.class))).thenReturn(coupon);
        
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post(URI)
        		.accept(MediaType.APPLICATION_JSON).content(inputInJson)
        		.contentType(MediaType.APPLICATION_JSON);
        
        MvcResult result = mockmvc.perform(requestBuilder).andReturn();
        
        MockHttpServletResponse response = result.getResponse();
        
        String outputInJson = result.getResponse().getContentAsString();
        
        assertThat(outputInJson).isEqualTo(inputInJson);
        
        //assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
        
    }

	
	@Test
	public void testGetTicketById() throws Exception {
		Coupon coupon = new Coupon();
		//coupon.setCouponId(8L);
        coupon.setCouponName("abc");
        coupon.setDiscountPercentage(27.00);
        coupon.setCouponDescription("xyz");       
        
        Mockito.when(service.viewCoupan(Mockito.anyLong())).thenReturn(coupon);
		
		String URI = "/showcoupon/1";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		String expectedJson = this.mapToJson(coupon);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void testGetAllBookedTickets() throws Exception {

		Coupon coupon1 = new Coupon();
        coupon1.setCouponName("abc");
        coupon1.setDiscountPercentage(27.00);
        coupon1.setCouponDescription("xyz");
    
		
        Coupon coupon2 = new Coupon();
        coupon2.setCouponName("abcd");
        coupon2.setDiscountPercentage(27.00);
        coupon2.setCouponDescription("xyz");
		
		List<Coupon> list = new ArrayList<>();
		list.add(coupon1);
		list.add(coupon2);
		
		Mockito.when(service.viewCoupans()).thenReturn(list);
		
		String URI = "/showcouponlist";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(list);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}