package com.bookmybus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.RouteException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;
import com.bookmybus.services.RouteService;

@RestController
public class RouteController {

	@Autowired
	private RouteService routeService;

	@PostMapping("/route")
	public ResponseEntity<Route> addRoute(@Valid @RequestBody Route route, @RequestParam String key)
			throws RouteException, AdminException, LoginException {

		Route newRoute = routeService.addRoute(route, key);

		return new ResponseEntity<Route>(newRoute, HttpStatus.OK);
	}

	@PutMapping("/route")
	public ResponseEntity<Route> updateRoute(@Valid @RequestBody Route route, @RequestParam String key)
			throws RouteException, AdminException, LoginException {
		Route updatedRoute = routeService.updateRoute(route, key);

		return new ResponseEntity<Route>(updatedRoute, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/route/{id}")
	public ResponseEntity<Route> deleteRoute(@PathVariable Integer id, @RequestParam String key)
			throws RouteException, AdminException, LoginException {

		Route deletedRoute = routeService.deleteRoute(id, key);

		return new ResponseEntity<Route>(deletedRoute, HttpStatus.OK);
	}

	@GetMapping("/route/{id}")
	public ResponseEntity<Route> viewRoute(@PathVariable Integer id) throws RouteException {

		Route viewRoute = routeService.viewRoute(id);

		return new ResponseEntity<Route>(viewRoute, HttpStatus.OK);
	}

	@GetMapping("/routes")
	public ResponseEntity<List<Route>> viewAllRoute() throws RouteException {
		List<Route> allRoutes = routeService.viewAllRoute();

		return new ResponseEntity<List<Route>>(allRoutes, HttpStatus.OK);
	}

	@GetMapping("/route")
	public ResponseEntity<List<Bus>> viewAllBusesHandler(@RequestParam Integer routeId) throws RouteException {

		List<Bus> allRoutes = routeService.viewAllBuses(routeId);

		return new ResponseEntity<List<Bus>>(allRoutes, HttpStatus.OK);
	}
	
	@GetMapping("/route/{from}/{to}")
	public ResponseEntity<List<Bus>> viewAllBusesHandler(@PathVariable("from") String routeFrom, @PathVariable("to") String routeTo) throws RouteException,BusException {

		List<Bus> allRoutes = routeService.viewAllBusesByRoute(routeFrom, routeTo);

		return new ResponseEntity<List<Bus>>(allRoutes, HttpStatus.OK);
	}

	@GetMapping("/routesByAsc")
	public ResponseEntity<List<Route>> viewAllRouteByAscending() throws RouteException {
		List<Route> allRoutes = routeService.viewAllRouteSortByAscending();

		return new ResponseEntity<List<Route>>(allRoutes, HttpStatus.OK);
	}

	@GetMapping("/routesByDes")
	public ResponseEntity<List<Route>> viewAllRouteByDescinding() throws RouteException {
		List<Route> allRoutes = routeService.viewAllRouteSortByDescinding();

		return new ResponseEntity<List<Route>>(allRoutes, HttpStatus.OK);
	}
	
	@GetMapping("/routesByKm")
	public ResponseEntity<List<Route>> filterRouteByGreaterThanKm(@RequestParam Integer km) throws RouteException {
		List<Route> allRoutes = routeService.filterRouteByGreaterThanKm(km);

		return new ResponseEntity<List<Route>>(allRoutes, HttpStatus.OK);
	}
	
	@GetMapping("/routesByMinMaxKm")
	public ResponseEntity<List<Route>> filterRouteByKm(@RequestParam Integer min, @RequestParam Integer max) throws RouteException {
		List<Route> allRoutes = routeService.filterRouteByKm(min, max);

		return new ResponseEntity<List<Route>>(allRoutes, HttpStatus.OK);
	}

//	@GetMapping("/busroute/{id}")
//	public ResponseEntity<List<BusDto>> viewListOfBusOnRoute(@PathVariable("id") Integer id) throws RouteException{
//		  List<BusDto> busdao =   routeService.viewListOfBusOnRoute(id);
//		  
//		  return new ResponseEntity<List<BusDto>>(busdao,HttpStatus.OK);
//	}

//	@GetMapping("/rootdistance")
//	public ResponseEntity<List<RouteDto>> getAllRouteDistanceInAsccending() throws RouteException{
//		  List<RouteDto> allRouteList =   routeService.getAllRouteDistanceInAsccending();
//		  
//		  return new ResponseEntity<List<RouteDto>>(allRouteList,HttpStatus.OK);
//	}
}
