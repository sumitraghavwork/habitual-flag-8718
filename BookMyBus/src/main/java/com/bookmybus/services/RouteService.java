package com.bookmybus.services;


import java.util.List;

import com.bookmybus.exceptions.RouteException;
import com.bookmybus.models.Route;

public interface RouteService {
	
	public Route addRoute(Route route) throws RouteException;
	public Route updateRoute(Route route) throws RouteException;
	public  Route deleteRoute(int routeId) throws RouteException;
    public Route viewRoute(int routeId) throws RouteException;
    public List<Route> viewAllRoute() throws RouteException;

}