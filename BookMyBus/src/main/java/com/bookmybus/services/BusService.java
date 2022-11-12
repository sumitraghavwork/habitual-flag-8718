package com.bookmybus.services;

import java.util.List;

import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.models.Bus;

public interface BusService {
	
	public Bus addBus(Bus bus, String key) throws BusException, AdminException, LoginException;

	public Bus updateBus(Bus bus, String key) throws BusException, AdminException, LoginException;

	public Bus deleteBus(int busId, String key) throws BusException, AdminException, LoginException;

	public List<Bus> viewBusByType(String busType) throws BusException;

	public List<Bus> viewAllBus() throws BusException;
}
