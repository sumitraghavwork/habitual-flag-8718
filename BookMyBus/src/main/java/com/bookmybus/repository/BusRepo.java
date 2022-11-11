package com.bookmybus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmybus.models.Bus;

@Repository
public interface BusRepo extends JpaRepository<Bus, Integer> {
//	@Query("select b.busId,b.busName,b.driverName,b.arrivalTime,b.departureTime,b.seats,b.availableSeats from Bus b where b.busType=?1")
//	public List<BusDTO> getBusByType(String busType);
	public List<Bus> findByBusType(String busType);
	

}
