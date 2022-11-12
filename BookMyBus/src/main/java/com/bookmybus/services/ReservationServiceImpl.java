package com.bookmybus.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.access.service.UserService;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.ReservationException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Reservation;
import com.bookmybus.models.User;
import com.bookmybus.repository.BusRepo;
import com.bookmybus.repository.ReservationDao;

@Service
public class ReservationServiceImpl implements ReservationServices {

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private BusRepo busRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Autowired
	private UserService userService;

	@Override
	public Reservation addReservation(Reservation reservation, Integer busId, String key)
			throws ReservationException, UserException, BusException, LoginException, AdminException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		Optional<Bus> existingBus = busRepo.findById(busId);

		if (existingBus.isPresent() == false) {
			throw new BusException("No Bus found for this Route and Id: " + busId);
		}

		Bus bus = existingBus.get();

		if (bus.getAvailableSeats() < reservation.getSeatsBooked())
			throw new BusException("Required seats not available.\n Available Seats: " + bus.getAvailableSeats());

		bus.setAvailableSeats(bus.getAvailableSeats() - reservation.getSeatsBooked());

		User user = userService.findByUserLoginId(loggedInUser.getUserId());

		Set<Reservation> res = user.getReservations();

		if (res.contains(reservation))
			throw new ReservationException("Reservation already exists");

		reservation.setBus(bus);
		user.getReservations().add(reservation);

		return reservationDao.save(reservation);

	}

	@Override
	public Reservation updateReservation(Reservation reservation, Integer reservationId, String key)
			throws ReservationException, BusException, UserException, LoginException, AdminException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		Optional<Reservation> existingReservation = reservationDao.findById(reservationId);

		if (existingReservation.isPresent() == false)
			throw new ReservationException("No reservations found!");

		Reservation res = existingReservation.get();

		Bus bus = res.getBus();
		bus.setAvailableSeats(res.getSeatsBooked() + bus.getAvailableSeats());

		if (bus.getAvailableSeats() < reservation.getSeatsBooked())
			throw new BusException("Required seats not available.\n Available Seats: " + bus.getAvailableSeats());

		bus.setAvailableSeats(bus.getAvailableSeats() - reservation.getSeatsBooked());

		reservation.setBus(bus);

		return reservationDao.save(reservation);
	}

	@Override
	public Reservation deleteReservation(Integer reservationId, String key)
			throws ReservationException, UserException, LoginException, AdminException {
		
		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		Optional<Reservation> existingReservation = reservationDao.findById(reservationId);

		if (existingReservation.isPresent() == false)
			throw new ReservationException("No reservations found!");
		
		Reservation res = existingReservation.get();
		
		User user = userService.findByUserLoginId(loggedInUser.getUserId());
		
		Set<Reservation> reservations = user.getReservations();
		
		res.setBus(null);
		
		reservations.remove(res);

		reservationDao.deleteById(reservationId);

		return res;
	}

	@Override
	public Reservation viewReservation(Integer reservationId, String key) throws ReservationException, UserException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new UserException("user not logged in");
		}

		Optional<Reservation> existingReservation = reservationDao.findById(reservationId);

		if (existingReservation.isPresent() == false)
			throw new ReservationException("No reservations found!");

		return existingReservation.get();
	}

	@Override
	public List<Reservation> viewAllReservation(String key) throws ReservationException, UserException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new UserException("user not logged in");
		}

		List<Reservation> reservations = reservationDao.findAll();

		if (reservations.isEmpty())
			throw new ReservationException("No reservations found!");

		return reservations;

	}

}
