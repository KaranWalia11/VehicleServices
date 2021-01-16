package com.techshard.graphql.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column private String name;
    @Column private String msidn;
    @Column private String engineStatus;
    @Column private String fleet;
    @Column private String brand;
    @Column private String countryOfOperation;
    @Column private String chassisNumber;
    @Column private String cassisSeries;
    @Column private String communicationStatus;
  
    public Vehicle() {}
    
    public Vehicle(String name, String msidn, String engineStatus, String fleet, String brand,
			String countryOfOperation, String chassisNumber, String cassisSeries, String communicationStatus) {
		this.name = name;
		this.msidn = msidn;
		this.engineStatus = engineStatus;
		this.fleet = fleet;
		this.brand = brand;
		this.countryOfOperation = countryOfOperation;
		this.chassisNumber = chassisNumber;
		this.cassisSeries = cassisSeries;
		this.communicationStatus = communicationStatus;
	}

    public int getID() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }	
    
}
