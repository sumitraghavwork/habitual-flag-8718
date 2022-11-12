package com.bookmybus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;
import com.bookmybus.repository.BusRepo;
import com.bookmybus.repository.RouteRepo;

@Service
public class BusServiceImplementation implements BusService {
	@Autowired
	private BusRepo busRepo;

	@Autowired
	private RouteRepo routeRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Bus addBus(Bus bus, String key) throws BusException, AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Route route = routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());

		if (route != null) {

			if (route.getBusList().contains(bus))
				throw new BusException("Bus already exists");

			bus.setRoute(route);
//			route.getBusList().add(bus);
//			routeRepo.save(route);

			return busRepo.save(bus);
		} else {
			throw new BusException("Bus detail is not correct");
		}
	}

	@Override
	public Bus updateBus(Bus bus, String key) throws BusException, AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Optional<Bus> existingBusOpt = busRepo.findById(bus.getBusId());

		if (existingBusOpt.isPresent()) {

			Bus existingBus = existingBusOpt.get();

			Route route = routeRepo.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
			if (route == null)
				throw new BusException("This route is invalid!");
			bus.setRoute(route);
			return busRepo.save(bus);
		} else {
			throw new BusException("Bus does not exist with busId : " + bus.getBusId());
		}

	}

	@Override
	public Bus deleteBus(int busId, String key) throws BusException, AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Optional<Bus> bus = busRepo.findById(busId);

		if (bus.isPresent()) {
			Bus existingBus = bus.get();

			busRepo.delete(existingBus);

			return existingBus;
		} else {
			throw new BusException("Bus does not exist with busId : " + busId);
		}
	}

	@Override
	public List<Bus> viewBusByType(String busType) throws BusException {
		List<Bus> listOfBusType = busRepo.findByBusType(busType);

		if (listOfBusType.size() > 0)
			return listOfBusType;
		else {
			throw new BusException("Bus does not exist with such type : " + busType);
		}

	}

	@Override
	public List<Bus> viewAllBus() throws BusException {
		List<Bus> buses = busRepo.findAll();
		if (buses.size() > 0)
			return buses;
		else {
			throw new BusException("There is no bus availabe now");
		}
	}

}
