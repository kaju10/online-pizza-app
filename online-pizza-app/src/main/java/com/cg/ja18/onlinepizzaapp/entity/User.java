package com.cg.ja18.onlinepizzaapp.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
@MappedSuperclass
public class User {

	@Id
	private Long mobile;

	private String userName;

	private String password;

	private String userType;

}
