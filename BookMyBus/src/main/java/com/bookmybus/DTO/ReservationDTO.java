package com.bookmybus.DTO;


import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO {
	
	@NotEmpty(message="Source field can not be left blank")
	private String source;
	
	@NotEmpty(message="Destination field can not be left blank")
	private String destination;
	
	@Min(value=1,message="Atleast One seat must be added")
	private Integer noOfSeatsToBook;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate journeyDate;
}
