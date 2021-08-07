package com.cg.ja18.onlinepizzaapp.exceptions;

public class OrderIdNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderIdNotFoundException(String msg) {
		super(msg);
	}

}
