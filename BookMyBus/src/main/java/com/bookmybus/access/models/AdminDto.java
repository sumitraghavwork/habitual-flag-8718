package com.bookmybus.access.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
	
	private String adminUsername;
	private String adminPassword;
}
