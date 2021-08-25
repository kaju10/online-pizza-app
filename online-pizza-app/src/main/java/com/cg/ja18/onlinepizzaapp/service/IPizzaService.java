package com.cg.ja18.onlinepizzaapp.service;

import java.util.List;

import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.exceptions.InvalidMinCostException;
import com.cg.ja18.onlinepizzaapp.exceptions.PizzaIdNotFoundException;



public interface IPizzaService {

	public Pizza addPizza(Pizza pizza);

	public Pizza updatePizza(Pizza pizza);

	public Pizza deletePizza(int pizzaId) throws PizzaIdNotFoundException;

	public List<Pizza> viewPizza(int pizzaId) throws PizzaIdNotFoundException;

	public List<Pizza> viewPizzaList();

	public List<Pizza> viewPizzaList(double minCost, double maxCost)throws InvalidMinCostException;
	
	public List<Pizza> viewPizzaList(String pizzaType); 
	
}
