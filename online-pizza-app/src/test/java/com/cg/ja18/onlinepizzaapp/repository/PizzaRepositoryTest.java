package com.cg.ja18.onlinepizzaapp.repository;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.ja18.onlinepizzaapp.entity.Pizza;



@DataJpaTest
//@AutoConfigureTestDatabase(replace=Replace.NONE)
public class PizzaRepositoryTest {
	
	@Autowired
	private IPizzaRepository pizzaRepository;
	
//	@Autowired
//	private TestEntityManager manager;
	
	Pizza p1;
	
	@BeforeEach
	void setUp() throws Exception {
		p1 = Pizza.builder()
				.pizzaType("Veg")
				.pizzaName("Paneer Pizza")
				
				.pizzaDescription("its a peppy paneer pizza")
				.pizzaCost(150.00).build();
		pizzaRepository.save(p1);
	}
	
	@Test
	void addMethodtest() {
		assertEquals(pizzaRepository.save(p1),p1);	
	}
	
	@Test
	void findMethodtest() {
		
		Integer id = p1.getPizzaId();
		assertEquals(id,pizzaRepository.findById(id).get().getPizzaId());	
	}
	
	@Test
	void updateMethodTest()
	{
		Integer id = p1.getPizzaId();
		pizzaRepository.findById(id).get().setPizzaCost(175.00);
		assertEquals(175.00,pizzaRepository.findById(id).get().getPizzaCost());	
	}
	
	@Test
	void findAllMethodTest()
	{
		pizzaRepository.save(p1);
		assertNotNull(pizzaRepository.findAll());
	}
	
	@Test
	void deleteMethodTest()
	{
		Pizza p2 = pizzaRepository.save(p1);
		pizzaRepository.deleteById(p2.getPizzaId());
	}
}
