package com.bookmybus.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.exceptions.RouteException;
import com.bookmybus.models.Bus;
import com.bookmybus.models.Route;
import com.bookmybus.repository.RouteRepo;

@Service
public class RouteServiceImplementation implements RouteService {
	
	@Autowired
	private RouteRepo  routeRepo;

	@Override
	public Route addRoute(Route route) throws RouteException {
		
		 Route existingRoute =   routeRepo.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
		 
//		 if(existingRoute==null) {
//			 route.setBusList(route.getBusList());
//			return routeRepo.save(route);
//			 
//		 }
//		 else {
//			 throw new RouteException("Please give route details properly");
//		 }
			 
		
		
		
		
		
		
//		 Route existingRoute =   routeRepo.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
//	
		 if(existingRoute!=null) {
			 throw new RouteException("Route is already present");
		 }
		 
		 Route savedRoute = null;
		  if(route!=null) {
			  List<Bus> buses = route.getBusList();
			  for(Bus k : buses) {
				  k.setRoute(route);
					savedRoute = routeRepo.save(route);
					
				  route.setBusList(null);
			  }
			
             
			route.setBusList(buses);
			savedRoute = routeRepo.save(route);
			return savedRoute;
			 
		  }
		  else {
			  throw new RouteException("Please give route details properly");
		  }
		
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {
		      Optional<Route> existedRoute = routeRepo.findById(route.getRouteId());
		      
		      if(existedRoute.isPresent()) {
		    	  Route presentRoute = existedRoute.get();
		    	  
		    	  List<Bus> busesList = presentRoute.getBusList();
		    	  if(busesList.size()>0) {
		    		  throw new RouteException("Cannot update Route because bus is already scheduled");
		    	  }
		    	  else {
		    		Route savedRoute =   routeRepo.save(route);
		    		return savedRoute;
		    	  }
		    	  
		      }
		      throw new RouteException("Route not exited with this RouteId" + route.getRouteId());
	}

	@Override
	public Route deleteRoute(int routeId) throws RouteException {
		   Optional<Route> route =  routeRepo.findById(routeId);
		   if(route.isPresent()) {
			   Route existingRoute = route.get();
			   routeRepo.delete(existingRoute);
			   return existingRoute;
		   }
		   else {
			   throw new RouteException("No Route found with this routeId"+ routeId);
		   }
	}

	@Override
	public Route viewRoute(int routeId) throws RouteException {
		Route route =  routeRepo.findById(routeId).orElseThrow(()->  new RouteException("mo route"));
		return route;
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
		       List<Route> allRoutes =     routeRepo.findAll();
		       
		       if(allRoutes.size()>0) {
		    	   
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
