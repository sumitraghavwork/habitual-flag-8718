package com.bookmybus.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	private String reservationStatus;
	private String reservationType;
	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private String source;
	
	@Embedded
	private Bus bus;

}
