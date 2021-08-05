package com.cg.ja18.onlinepizzaapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name= "Pizza_Order")
public class PizzaOrder implements Serializable {
	
	
	
	
	private static final long serialVersionUID = 1L;

	
	
	
	@Id
	@Column(name="BOOKING_ORDER_ID")
	@SequenceGenerator(
			name="pizzaOrder_sequence",
			sequenceName="pizzaOrder_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator="pizzaOrder_sequence"
			)
	private Long bookingOrderId;
	

	@Column(name="QUANTITY")
	private Integer quantity;


	@Column(name="TRANSACTION_MODE")
	private String transactionMode;
	

	@ManyToOne 
	@JoinColumn(name="PIZZA_ID")
	private Pizza pizza;
	
	
}