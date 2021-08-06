package com.cg.ja18.onlinepizzaapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "order_table")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
	@Column(name = "ORDER_ID")
	private Long orderId;

	@Column(name = "ORDER_TYPE")
	private String orderType;

	@Column(name = "ORDER_DESCRIPTION")
	private String orderDescription;

	@Column(name = "ORDER_DATE")
	private LocalDate orderDate;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<PizzaOrder> orderList;

	@ManyToOne
	@JoinColumn(name = "COUPON_ID")
	private Coupon coupon;

	@Column(name = "TOTAL_COST")
	private Double totalCost;

	@Column(name = "BILL_PRICE")
	private Double costAfterCoupon;

}
