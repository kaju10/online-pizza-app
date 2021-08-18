package com.cg.ja18.onlinepizzaapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.exceptions.OrderIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.service.IOrderService;

@CrossOrigin(origins="*")
@RestController
public class OrderController {

	@Autowired
	public IOrderService order_service;

	@PostMapping("/saveorder")
	public Order saveOrder(@RequestBody Order order) {

		return order_service.bookOrder(order);
	}

	@DeleteMapping("/deleteorder/{order_id}")
	public String deleteOrder(@PathVariable Long order_id) throws OrderIdNotFoundException {

		order_service.cancelOrder(order_id);
		return "order with order id " + order_id + " got deleted";
	}

	@GetMapping("/showorder/{order_id}")
	public Order showOrder(@PathVariable Long order_id) throws OrderIdNotFoundException {
		return order_service.viewOrder(order_id);
	}

	@GetMapping("/showorder")
	public List<Order> showOrderList() throws OrderIdNotFoundException {
		return order_service.viewOrderList();
	}

	@PutMapping("/updateorder")
	public String updateOrder(@RequestBody Order order) throws OrderIdNotFoundException {

		order_service.updateOrder(order);
		return "order with order id " + order.getOrderId() + " got updated";
	}

}
