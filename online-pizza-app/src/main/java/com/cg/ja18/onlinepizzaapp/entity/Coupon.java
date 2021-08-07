package com.cg.ja18.onlinepizzaapp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Coupon")
@SequenceGenerator(name = "sequence", initialValue = 1, allocationSize = 1)
public class Coupon implements Serializable {

	@Id
	@SequenceGenerator(name = "coupon_sequence", sequenceName = "coupon_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_sequence")
	@Column(name = "COUPON_ID", length = 10)
	private Long couponId;

	@Column(name = "COUPON_NAME", length = 18)
	private String couponName;

	@Column(name = "DISCOUNT_PERCENTAGE", length = 35)
	private Double discountPercentage;

	@Column(name = "COUPON_DESCRIPTION", length = 60)
	private String couponDescription;

}
