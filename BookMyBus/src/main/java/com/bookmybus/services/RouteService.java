package com.bookmybus.services;

import java.util.List;

import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.RouteException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;

public interface RouteService {

	public Route addRoute(Route route, String key) throws RouteException, AdminException, LoginException;

	public Route updateRoute(Route route, String key) throws RouteException, AdminException, LoginException;

	public Route deleteRoute(int routeId, String key) throws RouteException, AdminException, LoginException;

	public Route viewRoute(int routeId) throws RouteException;

	public List<Route> viewAllRoute() throws RouteException;

	public List<Bus> viewAllBuses(Integer routeId) throws RouteException;
	
	public List<Bus> viewAllBusesByRoute(String routeFrom, String routeTo) throws RouteException, BusException;
	public List<Route> viewAllRouteSortByAscending() throws RouteException;
	public List<Route> viewAllRouteSortByDescinding() throws RouteException;
	public List<Route> filterRouteByGreaterThanKm(Integer distance) throws RouteException;
	public List<Route> filterRouteByKm(Integer minkm, Integer maxkm) throws RouteException;

}