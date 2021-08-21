package com.cg.ja18.onlinepizzaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.exceptions.InvalidMinCostException;
import com.cg.ja18.onlinepizzaapp.exceptions.PizzaIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IPizzaRepository;


@Service
public class PizzaServiceImpl implements IPizzaService{

	@Autowired IPizzaRepository pizzaRepository;
	
	@Override
	public Pizza addPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		return pizzaRepository.save(pizza);
	}

	@Override
	public Pizza updatePizza(Pizza pizza) {
		// TODO Auto-generated method stub
		Optional<Pizza> op = pizzaRepository.findById(pizza.getPizzaId());
		if(op.isPresent())
		{
			pizzaRepository.save(pizza);
		}
		return op.get();
	}

	@Override
	public Pizza deletePizza(int pizzaId) throws PizzaIdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Pizza> op = pizzaRepository.findById(pizzaId);
		if (op.isPresent()) {

			pizzaRepository.deleteById(pizzaId);
			System.out.println(op.get());
			return op.get();
		} 
		else 
		{
			throw new PizzaIdNotFoundException("Pizza id is not found");
		}
	}

	@Override
	public Pizza viewPizza(int pizzaId) throws PizzaIdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Pizza> op = pizzaRepository.findById(pizzaId);
	    if(op.isPresent())
	    {
	    	return op.get();
	    }
	    else
	    {
	    	throw new PizzaIdNotFoundException("Pizza id is not found");
	    }
	}

	@Override
	public List<Pizza> viewPizzaList() {
		// TODO Auto-generated method stub
		List<Pizza> list = new ArrayList<>();
		pizzaRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public List<Pizza> viewPizzaList(double minCost, double maxCost) throws InvalidMinCostException {
		// TODO Auto-generated method stub
		List<Pizza> list = new ArrayList<>();
		pizzaRepository.findAll().forEach(list::add);
		return list.stream().filter(i->i.getPizzaCost()>minCost && i.getPizzaCost()<maxCost)
				.collect(Collectors.toList());
	}

	@Override
	public List<Pizza> viewPizzaList(String pizzaType) {
		// TODO Auto-generated method stub
		List<Pizza> list = new ArrayList<>();
		list.stream().filter(i->i.getPizzaType().equals(pizzaType)).collect(Collectors.toList());
		return list;
	}
	
	

}