package com.bookmybus.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Repository
public class User {
	
	private Integer userLoginId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Long contact;
	private String email;
	
	@Embedded
	private Reservation reservation;
}
