package com.techshard.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.techshard.graphql.dao.entity.Service;
import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private VehicleService vehicleService;
    

    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleService.getVehicle(id);
    }
    
    public List<Vehicle> getVehicles(final String name) {
        return this.vehicleService.getVehicles(name);
    }
    
    public List<Vehicle> getVehiclesByStatus(final String status) {
    	List<Service> services = this.vehicleService.getVehiclesByStatus(status);
    	List<Vehicle> vehicles = new ArrayList<>();
    	HashMap<Integer, Boolean> hashMap = new HashMap<>();
    	
    	for (Service service : services) {
    		if (!hashMap.containsKey(service.getVehicleID())) {
    			Vehicle vehicle = getVehicle(service.getVehicleID()).get();
    			vehicles.add(vehicle);
    			hashMap.put(service.getVehicleID(), true);
    		}
		}
    	return vehicles;
    }
}
