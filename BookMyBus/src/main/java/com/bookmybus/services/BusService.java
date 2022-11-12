package com.bookmybus.services;

import java.util.List;

import com.bookmybus.DTO.BusDTO;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.models.Bus;


public interface BusService {
	public Bus addBus(Bus bus)throws BusException;
	public Bus updateBus(Bus bus) throws BusException;
	public Bus deleteBus(int busId) throws BusException;
    public List<Bus> viewBusByType(String busType) throws BusException;
    public List<Bus> viewAllBus() throws BusException;
}
