package com.cg.ja18.onlinepizzaapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
public class PizzaServiceTest {

	@Autowired
	private IPizzaService service;

	@MockBean
	private IPizzaRepository pizzaRepository;

	Pizza pizza;

	@BeforeEach
	void setUp() throws Exception {
		pizza = Pizza.builder().pizzaId(1).pizzaType("Veg").pizzaName("Paneer Pizza").pizzaSize("Large")
				.pizzaDescription("its a peppy paneer pizza").pizzaCost(150.00).build();

		Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);
		Mockito.when(pizzaRepository.findById(pizza.getPizzaId())).thenReturn(Optional.of(pizza));
		Mockito.when(pizzaRepository.findAll()).thenReturn(Stream.of(pizza).collect(Collectors.toList()));

	}

	@Test
	void addPizzaTest() {

		assertEquals(pizza, service.addPizza(pizza));
	}

	@Test
	void viewPizzaTest() throws PizzaIdNotFoundException {
		Assertions.assertThat(service.viewPizza(pizza.getPizzaId()).getPizzaId()).isEqualTo(pizza.getPizzaId());

	}

	@Test
	void viewPizzaListTest() throws PizzaIdNotFoundException {
		assertEquals(1, service.viewPizzaList().size());
	}

	@Test
	void updatePizzaTest() {

		assertEquals(pizza.getPizzaId(), service.updatePizza(pizza).getPizzaId());
	}

	@Test
	void deletePizzaTest() throws PizzaIdNotFoundException {
		service.deletePizza(pizza.getPizzaId());
		verify(pizzaRepository, times(1)).deleteById(pizza.getPizzaId());
	}

}