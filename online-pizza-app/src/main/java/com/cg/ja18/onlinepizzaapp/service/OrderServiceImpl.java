package com.cg.ja18.onlinepizzaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.PizzaOrder;
import com.cg.ja18.onlinepizzaapp.exceptions.OrderIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IOrderRepository;




@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	public IOrderRepository orderrepo;

	@Override
	public Order bookOrder(Order order) {
		// TODO Auto-generated method stub
		Order order1 = getCostAfterCoupon(order);
		
		orderrepo.save(order1);
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		Optional<Order> o = orderrepo.findById(order.getOrderId());
		Order order1=getCostAfterCoupon(order);
		if (o.isPresent()) {
			
			orderrepo.save(order1);
			System.out.println(o.get());
		}else {
			bookOrder(order1);
		}
		return order1;
	}

	@Override
	public void cancelOrder(Long orderId) throws OrderIdNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Order> o = orderrepo.findById(orderId);
		if (o.isPresent()) {

			orderrepo.deleteById(orderId);
		} 
		else 
		{
			throw new OrderIdNotFoundException("Order id is not found");
		}
		
	}

	@Override
	public Order viewOrder(Long orderId) throws OrderIdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Order> o = orderrepo.findById(orderId);
		if (o.isPresent()) {
			return o.get();

		}
		else 
		{
			throw new OrderIdNotFoundException("Order id is not found");
		}	
	}
	
	
	@Override
	public List<Order> viewOrderList() {
		// TODO Auto-generated method stub
		List<Order> list = new ArrayList<>();
		orderrepo.findAll().forEach(list::add);
		return list;
	}
	
	
	public  Order getTotalCost(Order order) {

		List<PizzaOrder> plist = order.getOrderList();

		double sum = 0.0;

		for (PizzaOrder porder : plist) {
			sum += (porder.getPizza().getPizzaCost()) * (porder.getQuantity());
		}

		order.setTotalCost(sum);
		
		return order;

	}
	
	public Order getCostAfterCoupon(Order order) {
	  
		Order order1 = getTotalCost(order);	
		double fetchTotalCost = order1.getTotalCost();
		double costAfterCoupon = fetchTotalCost-((order1.getCoupon()
				.getDiscountPercentage()*fetchTotalCost)/100);
		order1.setCostAfterCoupon(costAfterCoupon);
		return order1;
	}

}