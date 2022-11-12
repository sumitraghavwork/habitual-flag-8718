package com.bookmybus.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;

public interface RouteRepo extends JpaRepository<Route, Integer> {

	public Route findByRouteFromAndRouteTo(String from,String to);
	
//	@Query("Select r.busList from Route r where r.routeId =?rId")
	public List<Bus> getBusListFromRouteByRouteId(Integer rId);
}
