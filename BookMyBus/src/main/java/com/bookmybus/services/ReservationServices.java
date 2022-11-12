package com.bookmybus.services;

import java.util.List;

import com.bookmybus.DTO.ReservationDTO;
import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.exceptions.UserException;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.ReservationException;
import com.bookmybus.models.Reservation;

public interface ReservationServices {
	
	public Reservation addReservation(Reservation reservation,Integer busId, String key) throws ReservationException,UserException,BusException, LoginException, AdminException ;
	
	public Reservation updateReservation(Reservation reservation, Integer reservationId, String key) throws ReservationException,UserException,BusException, LoginException, AdminException ;
	
	public Reservation deleteReservation(Integer reservationId, String key) throws ReservationException,UserException, LoginException, AdminException;
	
	public Reservation viewReservation(Integer reservationId,String key) throws ReservationException,UserException;
	
	public List<Reservation> viewAllReservation(String key)throws ReservationException,UserException;
	

}
