package com.bookmybus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.ReservationException;
import com.bookmybus.models.Reservation;
import com.bookmybus.services.ReservationServices;

@RestController
@RequestMapping("/reservationController")
public class ReservationController {

	@Autowired
	private ReservationServices reservationService;

	@PostMapping("/reservations")
	public ResponseEntity<Reservation> addReservation(@Valid @RequestBody Reservation reservation,
			@RequestParam String key) throws ReservationException, BusException, UserException {

		Reservation savedReservation = reservationService.addReservation(reservation, key);

		return new ResponseEntity<Reservation>(savedReservation, HttpStatus.ACCEPTED);

	}

	@PutMapping("/reservations/{reservationId}")
	public ResponseEntity<Reservation> addReservation(@Valid @RequestBody Reservation reservation,
			@PathVariable("reservationId") Integer reservationId, @RequestParam String key)
			throws ReservationException, BusException, UserException {

		Reservation savedReservation = reservationService.updateReservation(reservation, reservationId, key);

		return new ResponseEntity<Reservation>(savedReservation, HttpStatus.ACCEPTED);

	}

	@GetMapping("/reservations/{reservationId}")
	public ResponseEntity<Reservation> addReservation(@PathVariable("reservationId") Integer reservationId,
			@RequestParam String key) throws ReservationException, BusException, UserException {

		Reservation savedReservation = reservationService.viewReservation(reservationId, key);

		return new ResponseEntity<Reservation>(savedReservation, HttpStatus.ACCEPTED);

	}

	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> addReservation(@RequestParam String key)
			throws ReservationException, BusException, UserException {

		List<Reservation> savedReservations = reservationService.viewAllReservation(key);

		return new ResponseEntity<List<Reservation>>(savedReservations, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/reservation/{id}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Integer reservationId,
			@RequestParam(required = false) String key) throws ReservationException, BusException, UserException {
		Reservation deletedReservation = reservationService.deleteReservation(reservationId, key);
		return new ResponseEntity<Reservation>(deletedReservation, HttpStatus.OK);
	}

}
