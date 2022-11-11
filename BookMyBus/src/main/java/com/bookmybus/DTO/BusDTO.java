package com.bookmybus.DTO;

import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusDTO {
	
	private Integer busId;
	private String busName;
	private String driverName;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private Integer seats;
	private Integer availableSeats;
}
