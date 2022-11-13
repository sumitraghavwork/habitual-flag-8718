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
import com.bookmybus.models.Bus;
import com.bookmybus.services.BusService;

@RestController
public class BusController {
	@Autowired
	private BusService busService;

	// ***************************************************************************************************************
	@PostMapping("/bus")
	public ResponseEntity<Bus> addBusHandler(@RequestBody Bus bus, @RequestParam String key)
			throws BusException, AdminException, LoginException {

		Bus newBus = busService.addBus(bus, key);

		return new ResponseEntity<Bus>(newBus, HttpStatus.OK);
	}

	// ***************************************************************************************************************
	@PutMapping("/updateBus")
	public ResponseEntity<Bus> updateBus(@RequestBody Bus bus, @RequestParam String key)
			throws BusException, AdminException, LoginException {

		Bus newBus = busService.updateBus(bus, key);

		return new ResponseEntity<Bus>(newBus, HttpStatus.FOUND);
	}

	// ***************************************************************************************************************
	@DeleteMapping("/deleteBus/{busId}")
	public ResponseEntity<Bus> DeleteBus(@PathVariable("busId") Integer busId, @RequestParam String key)
			throws BusException, AdminException, LoginException {

		Bus bus = busService.deleteBus(busId, key);

		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
	}

	// ***************************************************************************************************************
	@GetMapping("/viewBusByType/{busType}")
	public ResponseEntity<List<Bus>> viewBusByType(@PathVariable("busType") String busType) throws BusException {

		List<Bus> listOfBuses = busService.viewBusByType(busType);

		return new ResponseEntity<List<Bus>>(listOfBuses, HttpStatus.OK);
	}

	// ***************************************************************************************************************
	@GetMapping("/viewAllBus")
	public ResponseEntity<List<Bus>> viewAllBus() throws BusException {

		List<Bus> listOfBuses = busService.viewAllBus();

		return new ResponseEntity<List<Bus>>(listOfBuses, HttpStatus.OK);
	}
}
