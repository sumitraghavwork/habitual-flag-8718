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
import org.springframework.web.bind.annotation.RestController;

import com.bookmybus.exceptions.RouteException;
import com.bookmybus.models.Route;
import com.bookmybus.services.RouteService;



@RestController
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@PostMapping("/route")
	public ResponseEntity<Route> addRoute(@Valid @RequestBody Route route) throws RouteException{
		
		Route newRoute =    routeService.addRoute(route);
		
		return new ResponseEntity<Route>(newRoute,HttpStatus.OK);
	}
	
	@PutMapping("/route")
	public ResponseEntity<Route> updateRoute(@Valid @RequestBody Route route) throws RouteException{
		  Route updatedRoute =   routeService.updateRoute(route);
		  
		  return new ResponseEntity<Route>(updatedRoute, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/route/{id}")
	public ResponseEntity<Route> deleteRoute(@PathVariable Integer id) throws RouteException{
		
		  Route deletedRoute =   routeService.deleteRoute(id);
		  
		  return new ResponseEntity<Route>(deletedRoute, HttpStatus.OK);
	}
	
	@GetMapping("/route/{id}")
	public ResponseEntity<Route> viewRoute(@PathVariable Integer id) throws RouteException {
		
		Route viewRoute =   routeService.viewRoute(id);
		
		return new ResponseEntity<Route>(viewRoute,HttpStatus.OK);
	}
	
	@GetMapping("/route")
	public ResponseEntity<List<Route>> viewAllRoute() throws RouteException{
		List<Route> allRoutes =  routeService.viewAllRoute();
		
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
