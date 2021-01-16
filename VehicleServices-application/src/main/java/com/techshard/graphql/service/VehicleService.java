package com.techshard.graphql.service;

import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.dao.repository.ServiceRepository;
import com.techshard.graphql.dao.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository ;
    private final ServiceRepository serviceRepository;

    public VehicleService(final VehicleRepository vehicleRepository, final ServiceRepository serviceRepository) {
        this.vehicleRepository = vehicleRepository ;
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public Vehicle createVehicle(String name, String msidn, String engineStatus, String fleet, String brand,
			String countryOfOperation, String chassisNumber, String cassisSeries, String communicationStatus) {
        final Vehicle vehicle = new Vehicle(name, msidn, engineStatus, fleet, brand, countryOfOperation, chassisNumber, cassisSeries, communicationStatus);
        return this.vehicleRepository.save(vehicle);
    }

    @Transactional
    public com.techshard.graphql.dao.entity.Service createService(String serviceName, String status, String lastUpdate,int vehicleID) {
    	com.techshard.graphql.dao.entity.Service service = new com.techshard.graphql.dao.entity.Service(serviceName,status, lastUpdate, vehicleID);
    	return this.serviceRepository.save(service);
    }
    
    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicle(final int id) {
        return this.vehicleRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Vehicle> getVehicles(final String name) {
    	return this.vehicleRepository.searchByNameLike(name);
    }
    
    @Transactional(readOnly = true)
    public List<com.techshard.graphql.dao.entity.Service> getVehiclesByStatus(final String status) {
    	return this.serviceRepository.searchByStatus(status);
    }
}
