package com.bookmybus.models;

import java.time.LocalDate;

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
public class Feedback {
	
	private Integer feedbackId;
	private Integer driverRating;
	private Integer serviceRating;
	private Integer overallRating;
	private String comments;
	private LocalDate feedbackdate;
	private User user;
	private Bus bus;

}	
