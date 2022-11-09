package com.bookmybus.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Admin {
	
	private Integer adminId;
	private String adminUsername;
	private String adminPassword;
}
