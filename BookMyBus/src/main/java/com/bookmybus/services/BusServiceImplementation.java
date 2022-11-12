package com.bookmybus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bookmybus.exceptions.BusException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;
import com.bookmybus.repository.BusRepo;
import com.bookmybus.repository.RouteRepo;

@Service
public class BusServiceImplementation implements BusService {
	@Autowired
	private BusRepo  busRepo;
	
	@Autowired
	private RouteRepo routeRepo;

	@Override
	public Bus addBus(Bus bus) throws BusException {
		Route route=routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
		
		if(route!=null) {
			
			if(route.getBusList().contains(bus))
				throw new BusException("Bus already exists");
			
			bus.setRoute(route);
			route.getBusList().add(bus);
			routeRepo.save(route);
			
			return busRepo.save(bus);
		}
		else {
			throw new BusException("Bus detail is not correct");
		}
	}

	@Override
	public Bus updateBus(Bus bus) throws BusException {
		Optional<Bus> existingBusOpt=busRepo.findById(bus.getBusId());
		
			if(existingBusOpt.isPresent()) {
			
			Bus existingBus = existingBusOpt.get();
			
			Route route=routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
			if(route == null) throw new BusException("This route is invalid!");
			bus.setRoute(route);
			return busRepo.save(bus);
		}
		else {
			throw new BusException("Bus does not exist with busId : "+ bus.getBusId());
		}
		
	}

	@Override
	public Bus deleteBus(int busId) throws BusException {
		Optional<Bus> bus=busRepo.findById(busId);
		
		if(bus.isPresent()) {
			Bus existingBus = bus.get();			
			
			busRepo.delete(existingBus);
			
			return existingBus;
		}
		else {
			throw new BusException("Bus does not exist with busId : "+busId);
		}
	}

	@Override
	public List<Bus> viewBusByType(String busType) throws BusException {
         List<Bus> listOfBusType = busRepo.findByBusType(busType);
		
		if(listOfBusType.size() >0)
			return listOfBusType;
		else {
			throw new BusException("Bus does not exist with such type : "+ busType);
		}
		
	}

	@Override
	public List<Bus> viewAllBus() throws BusException {
		List<Bus> buses= busRepo.findAll();
		if(buses.size()>0)
			return buses;
		else {
			throw new BusException("There is no bus availabe now");
		}
	}

	

}
