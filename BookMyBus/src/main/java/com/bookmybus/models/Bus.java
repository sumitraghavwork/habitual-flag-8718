package com.bookmybus.models;

import java.time.LocalTime;

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
@Repository
@Entity
public class Bus {
	
	private Integer busId;
	private String busName;
	private String driverName;
	private String busType;
	private String routeFrom;
	private String routeTo;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private Integer seats;
	private Integer availableSeats;

}
