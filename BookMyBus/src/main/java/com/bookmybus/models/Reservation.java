package com.bookmybus.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	private String reservationStatus;
	private String reservationType;
	private LocalDate reservationDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime reservationTime;
	private Integer seatsBooked;
	private String source;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Bus bus;

	@Override
	public int hashCode() {
		return Objects.hash(reservationDate, reservationId, reservationTime, reservationType, source);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return Objects.equals(reservationDate, other.reservationDate)
				&& Objects.equals(reservationId, other.reservationId)
				&& Objects.equals(reservationTime, other.reservationTime)
				&& Objects.equals(reservationType, other.reservationType) && Objects.equals(source, other.source);
	}
	
	

}
