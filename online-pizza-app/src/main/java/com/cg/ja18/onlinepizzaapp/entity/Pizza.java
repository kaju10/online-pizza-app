package com.cg.ja18.onlinepizzaapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pizza")

public class Pizza implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@Id
	@SequenceGenerator(
			name="pizza_sequence",
			sequenceName="pizza_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator="pizza_sequence"
			)
	@Column(name="PIZZA_ID")
	private Integer pizzaId;
	@Column(name="PIZZA_TYPE")
	private String pizzaType;
	@Column(name="PIZZA_NAME")
	private String pizzaName;
	@Column(name="PIZZA_SIZE")
	private String size;
	@Column(name="PIZZA_DESCRIPTION")
	private String pizzaDescription;
	@Column(name="PIZZA_COST")
	private Double pizzaCost;
	
	
}