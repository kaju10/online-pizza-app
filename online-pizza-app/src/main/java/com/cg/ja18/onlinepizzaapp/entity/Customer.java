package com.cg.ja18.onlinepizzaapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_table")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
	@Id
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "customer_address")
	private String customerAddress;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	@OneToMany(mappedBy = "customer")
	private List<Order> order;

}
