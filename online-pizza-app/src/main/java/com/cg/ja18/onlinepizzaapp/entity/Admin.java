package com.cg.ja18.onlinepizzaapp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin_table")
public class Admin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "admin_address")
	private String adminAddress;
	
	

//	@SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
//	@Id
//	@Column(name = "admin_Id")
//	private Long adminId;
//
//	@Column(name = "user_name")
//	private String adminName;
//
//	@Column(name = "password")
//	private String adminpassword;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	private User user;

}