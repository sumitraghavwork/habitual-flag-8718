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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.ToString;

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

	@Min(value = 20,message = "Min seats required is 20")
	@Max(value = 50,message = "Min seats required is 50")
	private Integer seats;

	@Min(value = 0,message = "Negative seats not allowed")
	private Integer availableSeats;
	
	
	@ManyToOne
	@JsonIgnore
	private Route route;

}
