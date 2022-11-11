package com.bookmybus.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.DTO.ReservationDTO;
import com.bookmybus.exceptions.ReservationException;
import com.bookmybus.models.Reservation;
import com.bookmybus.models.User;
import com.bookmybus.repository.ReservationDao;

@Service
public class ReservationServiceImpl implements ReservationServices {
	
	@Autowired
	private ReservationDao reservationDao;
	
//	@Autowired
//	private BusDao busDao;
//	
//	@Autowired
//	private UserSessionDao userSessionDao;
//	
//	@Autowired
//	private UserDao userDao;
//	
//	@Autowired
//	private AdminSessionDao adminSessionDao;
//	
	

	@Override
	public Reservation addReservation(ReservationDTO reservationDTO, String key) throws ReservationException {
		
	}

	@Override
	public Reservation updateReservation(Integer reservationId, String key) throws ReservationException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Reservation deleteReservation(Integer reservationId, String key) throws ReservationException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession loggedInAdmin= adminSessionDao.findByUuid(key);		
		if(loggedInAdmin == null) {
			throw new ReservationException("Please provide a valid key to view reservation!");
		}		
		Optional<Reservation> Opt = reservationDao.findById(reservationId);
		Reservation foundReservation = Opt.orElseThrow(()-> new ReservationException("No reservation found!"));
		return foundReservation;
	}

	@Override
	public Reservation viewReservation(Integer reservationId, String key) throws ReservationException {
		CurrentAdminSession loggedInAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedInAdmin == null) {
			throw new ReservationException("Please provide a valid key to view all reservations!");
		}
		
		List<Reservation> reservationList = reservationDao.findAll();
		if(reservationList.isEmpty()) throw new ReservationException("No reservations found!");
		return reservationList;
	}

	@Override
	public List<Reservation> viewAllReservation(String key) throws ReservationException {
		
		CurrentUserSession loggedInUser= userSessionDao.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new UserException("Please provide a valid key to view reservation!");
		}
		
		User user = userDao.findById(loggedInUser.getUserId()).orElseThrow(()-> new UserException("User not found!"));
		
		return user.getReservations();
		
	}

	@Override
	public List<Reservation> getAllReservation(String key) throws ReservationException {
		// TODO Auto-generated method stub
		return null;
	}

	


}
