package com.bookmybus.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;

public interface RouteRepo extends JpaRepository<Route, Integer> {

	public Route findByRouteFromAndRouteTo(String from,String to);
	
	@Query("Select r.busList from Route r where r.routeId =:rid")
	public List<Bus> getBusListFromRouteByRouteId(@Param("rid") Integer rId);
	
	@Query("select r.busList from Route r where r.routeFrom=:rf and r.routeTo=:rto")
	public List<Bus> getBusListByRoute(@Param("rf") String routeFrom, @Param("rto") String routeTo);
}
