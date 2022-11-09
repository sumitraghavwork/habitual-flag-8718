package com.bookmybus.models;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Repository
@Entity
public class Reservation {
	
	private Integer reservationId;
	private String reservationStatus;
	private String reservationType;
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private String source;
	
	@Embedded
	private Bus bus;

}
