package com.bookmybus.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmybus.models.Route;

public interface RouteRepo extends JpaRepository<Route, Integer> {

	public Route findByRouteFromAndRouteTo(String from,String to);
}
