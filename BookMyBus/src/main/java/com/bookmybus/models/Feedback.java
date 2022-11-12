package com.bookmybus.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer feedbackId;

	@Min(1)
	@Max(5)
	private Integer driverRating;
	@Min(1)
	@Max(5)
	private Integer serviceRating;
	@Min(1)
	@Max(5)
	private Integer overallRating;
	@NotNull(message = "Comment should not be null")
	@NotBlank(message = "Comment should not be blank...!")
	private String comments;

	private LocalDate feedbackdate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Reservation reservation;

}
