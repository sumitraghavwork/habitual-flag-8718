package com.bookmybus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmybus.models.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {
	
}
