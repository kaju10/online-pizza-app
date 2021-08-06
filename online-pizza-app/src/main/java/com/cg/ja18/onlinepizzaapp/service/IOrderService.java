package com.cg.ja18.onlinepizzaapp.service;

import java.util.List;

import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.exceptions.OrderIdNotFoundException;

public interface IOrderService {

	Order bookOrder(Order order);

	Order updateOrder(Order order);

	void cancelOrder(Long orderId) throws OrderIdNotFoundException;

	Order viewOrder(Long orderId) throws OrderIdNotFoundException;

	List<Order> viewOrderList();

}
