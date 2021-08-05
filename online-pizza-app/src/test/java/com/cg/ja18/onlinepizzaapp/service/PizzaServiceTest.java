package com.cg.ja18.onlinepizzaapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.exceptions.PizzaIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IPizzaRepository;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PizzaServiceTest {
	
	@Autowired
	private IPizzaService service;
	
	@MockBean
	private IPizzaRepository pizzaRepository;
	

	Pizza pizza;
	
	@BeforeEach
	void setUp() throws Exception {
		pizza = Pizza.builder()
				.pizzaType("Veg")
				.pizzaName("Paneer Pizza")
				.size("Large")
				.pizzaDescription("its a peppy paneer pizza")
				.pizzaCost(150.00).build();
		//pizzaRepository.save(pizza);
		Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);
	}
	
	@Test
	@Order(1)
	void addPizzaTest() 
	{
		
	    assertEquals(pizza, service.addPizza(pizza));
	}
	
	@Test
	@Order(2)
	void viewPizzaTest() throws PizzaIdNotFoundException 
	{
		int pizzaId=100;
		Optional<Pizza> pizza1 = Optional.ofNullable(pizza);
		Mockito.when(pizzaRepository.findById(100)).thenReturn(pizza1);
		Assertions.assertThat(service.viewPizza(100)).isEqualTo(pizza1.get());
	}

	@Test
	@Order(3)
	void viewPizzaListTest() throws PizzaIdNotFoundException 
	{
		Mockito.when(pizzaRepository.findAll())
		.thenReturn(Stream.of(pizza).collect(Collectors.toList()));
		
		assertEquals(1,service.viewPizzaList().size());
	}
	
	@Test
	@Order(4)
	@Rollback(false)
	void updatePizzaTest() 
	{
		
		Optional<Pizza> pizza1 = Optional.ofNullable(pizza);

		Pizza p = pizza1.get();
		p.setPizzaCost(125.00);
		pizzaRepository.save(p);
		
		Mockito.when(pizzaRepository.findById(100)).thenReturn(pizza1);
		Assertions.assertThat(p.getPizzaCost()).isEqualTo(125.00);
	}

	@Test
	@Order(5)
	void deletePizzaTest() throws PizzaIdNotFoundException 
	{
		pizzaRepository.deleteById(102);
		Assertions.assertThat(pizzaRepository.existsById(102)).isFalse();
	}



}
