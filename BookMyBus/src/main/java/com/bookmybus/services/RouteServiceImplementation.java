package com.bookmybus.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.access.dao.CurrentUserSessionRepo;
import com.bookmybus.access.exceptions.AdminException;
import com.bookmybus.access.exceptions.LoginException;
import com.bookmybus.access.models.CurrentUserSession;
import com.bookmybus.exceptions.BusException;
import com.bookmybus.exceptions.RouteException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;
import com.bookmybus.repository.RouteRepo;

@Service
public class RouteServiceImplementation implements RouteService {

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Autowired
	private RouteRepo routeRepo;

	@Override
	public Route addRoute(Route route, String key) throws RouteException, AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Route existingRoute = routeRepo.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());

		if (existingRoute == null) {
			return routeRepo.save(route);
		} else {
			throw new RouteException("Route is Already present");
		}

	}

	@Override
	public Route updateRoute(Route route, String key) throws RouteException, AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Optional<Route> existedRoute = routeRepo.findById(route.getRouteId());

		if (existedRoute.isPresent()) {
			Route presentRoute = existedRoute.get();

			List<Bus> busesList = presentRoute.getBusList();
			if (busesList.size() > 0) {
				throw new RouteException("Cannot update Route because bus is already scheduled");
			} else {
				Route savedRoute = routeRepo.save(route);
				return savedRoute;
			}

		}
		throw new RouteException("Route not exited with this RouteId" + route.getRouteId());
	}

	@Override
	public Route deleteRoute(int routeId, String key) throws RouteException, AdminException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		Optional<Route> route = routeRepo.findById(routeId);
		if (route.isPresent()) {
			Route existingRoute = route.get();
			routeRepo.delete(existingRoute);
			return existingRoute;
		} else {
			throw new RouteException("No Route found with this routeId" + routeId);
		}
	}

	@Override
	public Route viewRoute(int routeId) throws RouteException {
		Route route = routeRepo.findById(routeId).orElseThrow(() -> new RouteException("no route found with this routeId "+ routeId));
		return route;
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		List<Route> allRoutes = routeRepo.findAll();

		if (allRoutes.size() > 0) {

//		    	  RouteDto route = new RouteDto();

//		    	  List<RouteDto> routedao = new ArrayList<>();

//		    	 
//		    	 for(Route k : allRoutes) {
//		    		 route.setRouteId(k.getRouteId());
//		    		 route.setRouteFrom(k.getRouteFrom());
//		    		 route.setRouteTo(k.getRouteTo());
//		    		 route.setDistance(k.getDistance());
//		    		 routedao.add(route);
//		    	 }

//		    	  for(int i =0;i<allRoutes.size();i++) {
//		    		  route.setRouteId(allRoutes.get(i).getRouteId());
//		    		  route.setRouteFrom(allRoutes.get(i).getRouteFrom());
//		    		  route.setRouteTo(allRoutes.get(i).getRouteTo());
//		    		  route.setDistance(allRoutes.get(i).getDistance());
//		    		  
//		    		  routedao.add(route);
//		    	  }

			return allRoutes;
		} else {
			throw new RouteException("No Routes Avilable");
		}
	}

	@Override
	public List<Bus> viewAllBuses(Integer routeId) throws RouteException {

		List<Bus> busList = routeRepo.getBusListFromRouteByRouteId(routeId);

		if (busList.isEmpty())
			throw new RouteException("No Bus on this route found");

		return busList;
	}

	@Override
	public List<Bus> viewAllBusesByRoute(String routeFrom, String routeTo) throws RouteException, BusException {
		                    
		Route existingRoute = routeRepo.findByRouteFromAndRouteTo(routeFrom,routeTo);
		
		if(existingRoute!=null) {
			   List<Bus> busList =       routeRepo.getBusListByRoute(routeFrom, routeTo);
		          
		      	if (busList.isEmpty())
					throw new BusException("No Bus Scheduled in this route now");

				return busList;  
		}
		else {
			throw new RouteException("No Route Found with "+ " routeFrom "+ routeFrom +" To"+ " routeTo "+ routeTo);
		}
		
		         
	}

	@Override
	public List<Route> viewAllRouteSortByAscending() throws RouteException {
		List<Route> allRoutes = routeRepo.findAll();

		if (allRoutes.size() > 0) {
			
			Collections.sort(allRoutes, new Comparator<Route>() {

				@Override
				public int compare(Route o1, Route o2) {
					return o1.getDistance()-o2.getDistance();
				}
			});

			return allRoutes;
		} else {
			throw new RouteException("No Routes Avilable");
		}
	}

	@Override
	public List<Route> viewAllRouteSortByDescinding() throws RouteException {
		List<Route> allRoutes = routeRepo.findAll();

		if (allRoutes.size() > 0) {
			
			Collections.sort(allRoutes, new Comparator<Route>() {

				@Override
				public int compare(Route o1, Route o2) {
					return o2.getDistance()-o1.getDistance();
				}
			});

			return allRoutes;
		} else {
			throw new RouteException("No Routes Avilable");
		}
	}

	@Override
	public List<Route> filterRouteByGreaterThanKm(Integer distance) throws RouteException {
		List<Route> allRoutes = routeRepo.findAll();
		
		 if(allRoutes.size()>0) {
			List<Route> filteredRoute = allRoutes.stream().filter(s-> s.getDistance()>distance).collect(Collectors.toList());
			
			 if(filteredRoute.size()>0) {
				 return filteredRoute;
			 }
			 else {
				 throw new RouteException("No Route found greater than "+ distance+" km ");
			 }
		 }
		 else {
				throw new RouteException("No Routes Avilable");
			}
	
	}

	@Override
	public List<Route> filterRouteByKm(Integer minkm, Integer maxkm) throws RouteException {
		List<Route> allRoutes = routeRepo.findAll();
		
		 if(allRoutes.size()>0) {
			List<Route> filteredRoute = allRoutes.stream().filter(s-> s.getDistance()>minkm && s.getDistance()<maxkm).collect(Collectors.toList());
			
			 if(filteredRoute.size()>0) {
				 return filteredRoute;
			 }
			 else {
				 throw new RouteException("No Route found bewteen "+ minkm+" Km "+ " To " +  maxkm+" Km ");
			 }
		 }
		 else {
				throw new RouteException("No Routes Avilable");
			}
	}

//	@Override
//	public List<BusDto> viewListOfBusOnRoute(int routeId) throws RouteException {
//		
//	       Optional<Route> allRoute =    routeRepo.findById(routeId);
//	       
//	       if(allRoute.isPresent()) {
//	    	   Route route = allRoute.get();
//	    	   List<Bus> buses = route.getBusList();
//	    	   
//	    	   List<BusDto> busdao = new ArrayList<>();
//	    	   BusDto bus = new BusDto();
//	    	   for(Bus k : buses) {
//	    	     bus.setBusId(k.getBusId());
//	    	     bus.setBusName(k.getBusName());
//	    	     bus.setBusType(k.getBusType());
//	    	     bus.setArrivalTime(k.getArrivalTime());
//	    	     bus.setAvailableSeats(k.getAvailableSeats());
//	    	     bus.setDepartureTime(k.getDepartureTime());
//	    	     bus.setDriverName(k.getDriverName());
//	    	     bus.setSeats(k.getSeats());
//	    	     
//	    	     busdao.add(bus);
//	    	     
//	    	   }
//	    	 return busdao;
//	    	   
//	    	    
//	    	   
//	       }
//	       else {
//	    	   throw new RouteException("Please enter valid route id");
//	       }
//	}

//	@Override
//	public List<RouteDto> getAllRouteDistanceInAsccending() throws RouteException {
//		 List<Route> allRoutes =   routeRepo.findAll();
//	       
//	       if(allRoutes.size()>0) {
//	    	   
//	    	  RouteDto route = new RouteDto();
//	    	  
//	    	  List<RouteDto> routedao = new ArrayList<>();
//	  
//	    	 
//	    	 for(Route k : allRoutes) {
//	    		 route.setRouteId(k.getRouteId());
//	    		 route.setRouteFrom(k.getRouteFrom());
//	    		 route.setRouteTo(k.getRouteTo());
//	    		 route.setDistance(k.getDistance());
//	    		 
//	    		
//	    		 
//	    		 routedao.add(route);
//	    	 }
//	    	Collections.sort(routedao, new Comparator<RouteDto>() {
//
//				@Override
//				public int compare(RouteDto o1, RouteDto o2) {
//					// TODO Auto-generated method stub
//					return o1.getDistance()- o2.getDistance();
//				}
//			});
//
//			
//	    	 return routedao;
//	       }
//	       else {
//	    	   throw new RouteException("No Routes Avilable");
//	       }
//	}
//

}
