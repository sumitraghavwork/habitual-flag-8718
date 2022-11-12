package com.bookmybus.models;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	
	@NotNull(message = "Bus name cannot be null")
	@NotBlank(message = "Bus name cannot be blank...!")
	private String busName;
	
	@NotNull(message = "Bus driver name cannot be null")
	@NotBlank(message = "Bus driver name cannot be blank...!")
	private String driverName;
	
	@NotNull(message = "Bus type cannot be null")
	@NotBlank(message = "Bus type cannot be blank...!")
	private String busType;

	
	@NotNull(message = "Bus routeFrom cannot be null")
	@NotBlank(message = "Bus routeFrom cannot be blank...!")
	private String routeFrom;
	
	@NotNull(message = "Bus routeTo cannot be null")
	@NotBlank(message = "Bus routeTo cannot be blank...!")
	private String routeTo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")

	private LocalTime arrivalTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime departureTime;


	@Min(20)
	@Max(45)
	private Integer seats;
	

	@Min(0)
	private Integer availableSeats;



	@ManyToOne
	private Route route;



}
